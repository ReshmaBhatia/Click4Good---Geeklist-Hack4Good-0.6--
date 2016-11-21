package com.Click4Good;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ShowLocationActivity extends Activity implements LocationListener {
	//private TextView latituteField;
	//private TextView longitudeField;
	private LocationManager locationManager;
	private String provider,address;
	
	

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//setContentView(R.layout.activity_show_location);
		//latituteField = (TextView) findViewById(R.id.TextView02);
		//longitudeField = (TextView) findViewById(R.id.TextView04);

		// Get the location manager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		
		Location location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			onLocationChanged(location);
		} else {
			//latituteField.setText("Location not available");
			//longitudeField.setText("Location not available");
		}
	}

	/* Request updates at startup */
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		Toast.makeText(getApplicationContext(), " hihihhihi",Toast.LENGTH_LONG).show();
		double lat = (double) (location.getLatitude());
		double lng = (double) (location.getLongitude());
		Toast.makeText(getApplicationContext(),"hhihihih", Toast.LENGTH_LONG).show();
		//latituteField.setText(address);
		//longitudeField.setText(String.valueOf(lng));
		if (!isNetworkAvailable()) {

			 address = "Latitude : " + String.valueOf(lat) + ""
					+ System.getProperty("line.separator") + "Longitude : "
					+ String.valueOf(lng);
			System.out.println(address);
			Toast.makeText(getApplicationContext(), address, Toast.LENGTH_LONG)
					.show();

		}
	

			Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

			try {

				// Place your latitude and longitude
				List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);

				if (addresses != null) {

					Address fetchedAddress = addresses.get(0);
					StringBuilder strAddress = new StringBuilder();

					for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
						strAddress.append(fetchedAddress.getAddressLine(i))
								.append("\n");
					}
					address = strAddress.toString();
					//Toast.makeText(getApplicationContext(), " "+i+" ",Toast.LENGTH_LONG).show();
					//Log.d("location",address);
					Intent i = new Intent();
					i.setAction("com.Click4Good.sendToServer");
					Bundle bundle = new Bundle();
					bundle.putString("latitude", "10000"/*/*String.valueOf(lat)*/);
					bundle.putString("longitude","10000" /*String.valueOf(lng)*/);
					bundle.putString("address", address);
					
					i.putExtras(bundle);
					startActivity(i);
				}

			} catch (IOException e) {

				e.printStackTrace();

			}
			Intent i = new Intent();
			i.setAction("com.Click4Good.sendToServer");
			Bundle bundle = new Bundle();
			bundle.putString("latitude", "10000"/*/*String.valueOf(lat)*/);
			bundle.putString("longitude","10000" /*String.valueOf(lng)*/);
			bundle.putString("address", address);
			
			i.putExtras(bundle);
			startActivity(i);

			

		}

	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}
	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
}
