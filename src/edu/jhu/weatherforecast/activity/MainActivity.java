package edu.jhu.weatherforecast.activity;

import java.io.IOException;

import edu.jhu.weatherforecast.R;
import edu.jhu.weatherforecast.model.City;
import edu.jhu.weatherforecast.model.NetworkLoader;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String CITY_NAME = "edu.jhu.weatherforecast.cityname";

	public final static String TEMPERATURE = "edu.jhu.weatherforecast.temperature";

	private DownloadTask downloadTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void showTemperature(View view) {
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null) {
			Button button = (Button) view;
			City city = new City();
			if (button.getId() == R.id.beijing) {
				city.setName("Beijing");
				city.setWOEID("2151330");
			} else {
				city.setName("New York");
				city.setWOEID("2459115");
			}

			Intent intent = new Intent(this, TemperatureActivity.class);
			intent.putExtra(CITY_NAME, button.getText().toString());
			if (downloadTask != null) {
				downloadTask.cancel(true);
			}
			downloadTask = new DownloadTask(intent);
			downloadTask.execute(city);
		} else {
			Toast.makeText(this, "No network connection", Toast.LENGTH_SHORT)
					.show();
		}
	}

	public class DownloadTask extends AsyncTask<City, Void, String> {

		private Intent intent;

		public DownloadTask(Intent intent) {
			this.intent = intent;
		}

		@Override
		protected String doInBackground(City... city) {
			String result = null;
			try {
				Thread.sleep(1000);
				result = NetworkLoader.downloadUrl(city[0]);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (!isCancelled()) {
				String temperature = result;
				intent.putExtra(TEMPERATURE, temperature);
				MainActivity.this.startActivity(this.intent);
			}
		}

	}

}
