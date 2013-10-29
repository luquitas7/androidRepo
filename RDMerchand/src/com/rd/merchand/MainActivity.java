package com.rd.merchand;

import com.rd.biz.company.CompanyResponse;
import com.rd.biz.company.CompanyService;
import com.rd.common.Configuracion;
import com.rd.common.ConfiguracionImpl;
import com.rd.communication.ClienteRestFactory;
import com.rd.communication.SpringRestCompanyFactory;
import com.rd.services.CompanyServiceImpl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

	private TextView userTxt;
	private TextView companyTxt;
	protected CompanyResponse companies;
	private int appUserId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Show the Up button in the action bar.
		setupActionBar();
		userTxt = (TextView)findViewById(R.id.userNameTxt);
		companyTxt = (TextView)findViewById(R.id.companyTxt);
		
		Bundle receiveBundle = this.getIntent().getExtras();
		userTxt.setText(receiveBundle.getString("user"));
		appUserId = receiveBundle.getInt("userId");
		ImageView companyListImg = (ImageView)findViewById(R.id.companyListImage);
		
		companyListImg.setOnClickListener(getCompanyListEvent);
		
		 getCompanies();
	}

	
	private OnClickListener getCompanyListEvent = new OnClickListener() {

		@Override
		public void onClick(View v) {

            Bundle sendBundle = new Bundle();
            sendBundle.putInt("userId", appUserId);

			Intent i = new Intent(MainActivity.this, CompanyListActivity.class);
			i.putExtras(sendBundle);
			startActivityForResult(i, 1);
		}

	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		companyTxt.setText(companies.getLista()[data.getIntExtra("companyPos", 0)].getCompanyName());
	};	
	
	private void dialog(String title, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void getCompanies()
	{
		ClienteRestFactory clienteRestFactory = new SpringRestCompanyFactory();
		Configuracion configuracion = new ConfiguracionImpl(this);
		CompanyService service = new CompanyServiceImpl(clienteRestFactory, configuracion);
		
		new AsynchCompanyRequest().execute(service, appUserId);
	}
	
	class AsynchCompanyRequest extends AsyncTask<Object, Integer, CompanyResponse> {

		@Override
		protected CompanyResponse doInBackground(Object... params) {

			CompanyService service = (CompanyService)params[0];
			
			return service.getCompaniesByUserId((Integer)params[1]);
		}

		
		protected void onPostExecute(CompanyResponse result)
		{
			companies = result;
			companyTxt.setText(companies.getLista()[0].getCompanyName());
		}
	}
}
