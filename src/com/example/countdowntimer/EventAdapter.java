package com.example.countdowntimer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter{
	ArrayList<EventData>  eventDatas;
	Context  context;
	LayoutInflater inflater;
	public EventAdapter(Context context,ArrayList<EventData>  datas) {
		eventDatas = datas;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return eventDatas.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return eventDatas.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder ;
		EventData data = eventDatas.get(position);
		if(convertView==null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, parent, false);
			holder.title = (TextView)convertView.findViewById(R.id.event_name);
			holder.days = (TextView)convertView.findViewById(R.id.days);
			holder.hrs = (TextView)convertView.findViewById(R.id.hours);
			holder.min = (TextView)convertView.findViewById(R.id.mins);
			convertView.setTag(holder);
		}
		else
		{

			holder = (ViewHolder) convertView.getTag();
		}

		holder.title.setText(getTitle(data));
		holder.days.setText(getDays(data));
		holder.hrs.setText(getHrs(data));
		holder.min .setText(getMins(data));

		return convertView;
	}


	class ViewHolder
	{
		TextView title,days,hrs,min;

	}


	public String getTitle(EventData eventData)
	{
		String title = eventData.eventTitle;
		String date = new SimpleDateFormat("yyyy-MM-yy").format(eventData.eventDate);
		return title +"  ("+date+")   ";

	}

	public String getDays(EventData date)
	{
		long difference = date.eventDate.getTime()-new Date().getTime();

		long x = difference / 1000;
		long  days = x;
		return "Days " +String.valueOf(days);

	}

	public String getHrs(EventData date)
	{
		long difference = date.eventDate.getTime()-new Date().getTime();

		long x = difference / 1000;
		x = x%24;
		long  days = x;
		return "Hours " +String.valueOf(days);

	}

	public String getMins(EventData date)
	{
		long difference = date.eventDate.getTime()-new Date().getTime();

		long x = difference / 1000;
		x /= 24;
		long  days = x;
		long hrs  = x%24;
		long min = hrs %60;
		return "Minutes " +String.valueOf(min);

	}

	public String  getDHM(EventData date) {
		long difference = date.eventDate.getTime()-new Date().getTime();

		long x = difference / 1000;
		long  seconds = x % 60;
		x /= 60;
		long minutes = x % 60;
		x /= 60;
		long  hours = x % 24;
		x /= 24;
		long  days = x;
		return "Days " +String.valueOf(days);
	}

}
