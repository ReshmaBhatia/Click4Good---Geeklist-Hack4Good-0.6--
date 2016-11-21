package com.Click4Good;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sendToServer extends Activity implements OnClickListener{

	//EditText name,phone_no;
	//Button register;
	String latitude,longitude,address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bundle receivedData = getIntent().getExtras();
		 latitude = receivedData.get("latitude").toString();
	    longitude = receivedData.get("longitude").toString();
	    address = receivedData.get("address").toString();
		//name=(EditText)findViewById(R.id.name);
		//phone_no=(EditText)findViewById(R.id.phone_no);
		//register=(Button)findViewById(R.id.register);
		//register.setOnClickListener(this);

	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		new SigninActivity().execute();
	}
	public class SigninActivity  extends AsyncTask<String,Void,String>{
		
		StringBuffer responseBody=new StringBuffer();
		boolean registrationSuccess = false;

		@Override
		protected String doInBackground(String... arg0) {
			try{
			// TODO Auto-generated method stub
			 HttpClient client = new DefaultHttpClient();
			 String link="http://192.168.43.18/hack4good/testog.php?latitude="
            +latitude+"&longitude="+longitude+"&address="+address;
	            HttpPost httppost = new HttpPost(link);
	            
	            	httppost.setURI(new URI(link));
	            	List<NameValuePair>nameValuePairs=new ArrayList<NameValuePair>(2);
	            	nameValuePairs.add(new BasicNameValuePair("latitude",latitude));
	            	nameValuePairs.add(new BasicNameValuePair("longitude",longitude));
	             	nameValuePairs.add(new BasicNameValuePair("address",address));
	            	httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            	//System.out.println(name.getText().toString()+"  "+phone_no.getText().toString());
	            	
	            	HttpResponse response = client.execute(httppost);
	            	//System.out.println(httppost+"hihih");
	            	//Toast.makeText(MainActivity.this, httppost+" ", Toast.LENGTH_LONG).show();
		            responseBody=new StringBuffer();
		            String s=new String();
		            BufferedReader br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		            while((s=br.readLine())!=null)
		            {
		            	responseBody.append(s).append(System.getProperty("line.separator"));
		            	//Toast.makeText(MainActivity.this, responseBody+" ", Toast.LENGTH_LONG).show();
		            	
		            }
		            br.close();
		            
		            registrationSuccess = responseBody.toString().trim().equals("Data Received");
		            //System.out.println(registrationSuccess+"hihihi");
	            }
	            catch(Exception e){
	            	e.printStackTrace();
	            	//Toast.makeText(MainActivity.this, responseBody+" hhihi", Toast.LENGTH_LONG).show();
	            	
	            }
			return null;
		}
		@Override
		public void onPostExecute(String s)
		{
			/*if(registrationSuccess)
				Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(MainActivity.this,"error occured ", Toast.LENGTH_LONG).show();*/
		}
	}
}