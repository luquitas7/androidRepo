package com.rd.merchand;

import com.rd.biz.auth.AutenticacionResponse;
import com.rd.common.Configuracion;
import com.rd.common.ConfiguracionImpl;
import com.rd.communication.ClienteRestFactory;
import com.rd.communication.SpringRestTemplateFactory;
import com.rd.services.AutenticacionException;
import com.rd.services.AutenticacionServiceImpl;
import com.rd.services.BaseServiceImpl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Context ctx;
	private EditText usernameTxt;
	private EditText passTxt;
	private LinearLayout loginLayout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        ctx = this;
        Button loginBtn = (Button)findViewById(R.id.logInButton);
        usernameTxt = (EditText)findViewById(R.id.editText1);
        passTxt = (EditText)findViewById(R.id.editText2);
        
        loginBtn.setOnClickListener(login_event);
        
        usernameTxt.requestFocus();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	private void dialog(String title, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(title)
				.setMessage(msg)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
    
    private OnClickListener login_event = new OnClickListener() {

		@Override
		public void onClick(View view) {
			
			ClienteRestFactory clienteRestFactory = new SpringRestTemplateFactory();
			Configuracion configuracion = new ConfiguracionImpl(ctx);
			BaseServiceImpl auth = new AutenticacionServiceImpl(clienteRestFactory, configuracion);
			try {
				
				new AsynchAuth().execute(auth, usernameTxt.getText().toString(), 
						passTxt.getText().toString());
				
				/*Toast.makeText(ctx, String.format("User %s logeado", ticket.getUsername())
						, TRIM_MEMORY_MODERATE).show();*/
				
			} catch (Exception e) {
				e.printStackTrace();
				//Toast.makeText(ctx, e.getMessage(), TRIM_MEMORY_MODERATE).show();
				dialog("Login Exception", e.getMessage());
			}
		}
	};
	
	class AsynchAuth extends AsyncTask<Object, Object, AutenticacionResponse> {
		
		@Override
	    protected void onProgressUpdate(Object... progress) {
	        //setProgressPercent(progress[0]);
	    }
	    
		private void dialog(String title, String msg) {
			AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
			builder.setTitle(title)
					.setMessage(msg)
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
			
			AlertDialog alert = builder.create();
			alert.show();
		}

		@Override
		protected AutenticacionResponse doInBackground(Object... params) {
			AutenticacionServiceImpl auth = (AutenticacionServiceImpl) params[0];
			try {
				AutenticacionResponse ticket = auth.autenticar((String)params[1]
						, (String)params[2]);
				return  ticket;
			} catch (AutenticacionException e) {
				e.printStackTrace();
				dialog("Login AutenticacionException", e.getMessage());
			}
			return null;
		}
		
		LinearLayout linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
		
		@Override
		protected void onPreExecute()
		{
			linlaHeaderProgress.setVisibility(View.VISIBLE);
		}
		
	    protected void onPostExecute(AutenticacionResponse result) {
	    	//dialog("Login", String.format("User %s logeado", result.getUsername()));
	    	linlaHeaderProgress.setVisibility(View.GONE);
	    	
	    	if (result.getPasswordhash() == null)
	    		dialog("Login", "Incorrect user or pass!");
	    	else
	    	{
	            Bundle sendBundle = new Bundle();
	            sendBundle.putInt("userId", result.getAppuserid());
	            sendBundle.putString("user", String.format("%s %s", result.getFirstname(), result.getLastname()));
	            Intent i = new Intent(LoginActivity.this, MainActivity.class);
	            i.putExtras(sendBundle);
	    		startActivity(i);
	    		finish();
	    	}
	    }

	}
	
}
