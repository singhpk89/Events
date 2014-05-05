package com.example.countdowntimer;


import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	public ArrayList<EventData> arrayList = new ArrayList<EventData>();
	private ImageView plus;
	private ListView eventListView;
	private EventAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		plus = (ImageView)findViewById(R.id.add);
		plus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showTimePickerDialog();

			}
		});
	}


	public void showTimePickerDialog() {

		final Dialog dialog = new Dialog(this);

		dialog.setContentView(R.layout.date_time_layout);

		dialog.setTitle("Please enter a title :-");
		final TimePicker timePicker1 = (TimePicker)dialog.findViewById(R.id.timePicker1);
		final DatePicker  datePicker1 = (DatePicker)dialog.findViewById(R.id.datePicker1);
		final EditText  title = (EditText)dialog.findViewById(R.id.event_title);

		Button add = (Button)dialog.findViewById(R.id.add);
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EventData data = new EventData();
				data.eventDate = getDateFromTimePicket(datePicker1, timePicker1);
				data.eventTitle = title.getText().toString();
				if(data.eventTitle.length()==0)
					data.eventTitle = data.eventDate.toLocaleString();
				arrayList.add(data);
				dialog.dismiss();

			}
		});
		Button cancel = (Button)dialog.findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});



		dialog.show();
	}


	public static Calendar getDateFromDatePicket(DatePicker datePicker){
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth();
		int year =  datePicker.getYear();

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);

		return calendar;
	}

	public static java.util.Date getDateFromTimePicket(DatePicker datePicker,TimePicker timePicker){
		int Hrs = timePicker.getCurrentHour();
		int Mins = timePicker.getCurrentMinute();
		Calendar calendar = getDateFromDatePicket(datePicker);
		calendar.set(Calendar.HOUR_OF_DAY,Hrs);
		calendar.set(Calendar.MINUTE,Mins);
		return calendar.getTime();
	}


	private JSONArray createJsonArray() {
		JSONArray array = new JSONArray();
		for (EventData iterable_element : arrayList) {
			try {
				JSONObject object = new JSONObject("Event");
				object.put("Title", iterable_element.eventTitle);
				object.put("Date", iterable_element.eventDate);
				array.put(object);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return array;

	}
	
	
	private void updateSharedPrefs() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		sharedPreferences.edit().putString("EventDetails", createJsonArray().toString()).commit();

	}
	
	private String  getSharedPrefsString() {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		String str =sharedPreferences.getString("EventDetails", null);
		return str;
		

	}

}


/*I want someone to improve an event countdown app for android.  I have the current source code for it, and this is what it can currently do:

-Create countdowns for unlimited events, it counts down the days, hours and minutes until the event.
-Set event countdown as widgit.

What I want to Add:
-Ability to Edit a Holiday already created
-Sort Holidays by Date, instead of order entered
-Ability to Delete a Holiday Entered
-Ability to make an event recurring every year if the choose to, ie Christmas.  After Christmas passes for the current year, the app starts counting down for the next Christmas.
-Sends a notification 24 hours before the event begins (user would choose if they want this while creating the event).

Images of the App Right Now:*/