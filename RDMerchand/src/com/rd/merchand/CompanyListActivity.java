package com.rd.merchand;

import com.rd.biz.company.CompanyDto;
import com.rd.biz.company.CompanyResponse;
import com.rd.biz.company.CompanyService;
import com.rd.common.Configuracion;
import com.rd.common.ConfiguracionImpl;
import com.rd.communication.ClienteRestFactory;
import com.rd.communication.SpringRestCompanyFactory;
import com.rd.merchand.MainActivity.AsynchCompanyRequest;
import com.rd.merchand.util.SystemUiHider;
import com.rd.services.CompanyServiceImpl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class CompanyListActivity extends Activity {
	
	private ListView companyListView;
	private Context ctx;
	private int appUserId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		 ctx = this;
		setContentView(R.layout.activity_company_list);
		Bundle receiveBundle = this.getIntent().getExtras();
		appUserId = receiveBundle.getInt("userId");
		companyListView = (ListView) findViewById(R.id.companyListView);
		getCompanies();
		
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
			ListAdapter adapter =  new ArrayAdapter<CompanyDto>( ctx, R.layout.company_item, result.getLista());
			final CompanyDto[] companyList = result.getLista();
			companyListView.setAdapter(adapter);
			companyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parentAdapter, View view,
						int position, long id) {
					
					//CompanyDto company = companyList[position];
					Intent i = getIntent();
					i.putExtra("companyPos", position);
					setResult(RESULT_OK, i);
					finish(); 
					
					/*//TextView clickedView = (TextView) view;
					
					Toast.makeText(CompanyListActivity.this, "Item with id ["+company.getCompanyID()+"] - " +
							"Position ["+position+"] - Planet ["+company.getCompanyName()+"]", Toast.LENGTH_SHORT).show();
					*/
				}
				
			});
		}
	}
}
