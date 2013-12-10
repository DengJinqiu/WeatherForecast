package edu.jhu.weatherforecast.activity;

import edu.jhu.weatherforecast.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class TemperatureActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temperature);
		// Show the Up button in the action bar.
		setupActionBar();
		TextView cityName = (TextView) findViewById(R.id.city_name);
		cityName.setText(getIntent().getStringExtra(MainActivity.CITY_NAME));
		TextView temperature = (TextView) findViewById(R.id.temperature);
		temperature.setText(getIntent().getStringExtra(MainActivity.TEMPERATURE));
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
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

}
