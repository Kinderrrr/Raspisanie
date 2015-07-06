package com.example.raspviewproj;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Adapters.ListViewRaspAdapter;
import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raspviewproj.DateTimePicker.DateWatcher;

import db.MyPrefs;
import db.MySQLiteClass;

/**
 * Created by Егор on 07.05.2015.
 */
public class RaspOneStationActivity extends FragmentActivity {
    private ListView listView;
    private boolean mFlag_empty;
    String mCity;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_today);
        
        ActionBar actionbar=getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        
        
        listView = (ListView) findViewById(R.id.MylistView);

        Intent intent =getIntent();
        String station = intent.getStringExtra(MyPrefs.STATION_SINGLE);
        mCity=station;
        actionbar.setTitle(station);
        MySQLiteClass mySQLiteClass = new MySQLiteClass(RaspOneStationActivity.this);
        mySQLiteClass.open(false);//Так сделанно потому, что я не знаю, почему на телефоне вылетает, а на эмуляторе нет
        ArrayList<String[]> list = mySQLiteClass.getScheduleForOneStation(station);
        mySQLiteClass.close();


        if (list.get(0).length == 1)///***
        {
            LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View empty_view = inflater.inflate(R.layout.empty_list, null, false);

            TextView textViewMessage = (TextView) empty_view.findViewById(R.id.show_empty_set_tv);
            textViewMessage.setText(getResources().getString(R.string.nothing_to_show));

            setContentView(empty_view);////svsdvsdvsdv
            
            actionbar.setTitle("Нет маршрута");
            mFlag_empty=true;
        } else {
            ListViewRaspAdapter adapter = new ListViewRaspAdapter(RaspOneStationActivity.this, RaspisanieContent.getListRasp(list));
            listView.setAdapter(adapter);
        }
        
        listView.setLongClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                // TODO Auto-generated method stub

                final View currentView = v;

                Builder alertDialogBuilder = new AlertDialog.Builder(RaspOneStationActivity.this);
                
                final RelativeLayout mDateTimeDialogView = (RelativeLayout) getLayoutInflater().inflate(R.layout.dialog, null);
				// Grab widget instance
				final DateTimePicker mDateTimePicker = (DateTimePicker) mDateTimeDialogView.findViewById(R.id.DateTimePicker);
				DateWatcher s = null;
				mDateTimePicker.setDateChangedListener(s); 
				alertDialogBuilder.setView(mDateTimeDialogView);
                
                alertDialogBuilder.setTitle("Добавить напоминание");
       
                
                alertDialogBuilder.setPositiveButton("Да",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                            	
                            int mYear = mDateTimePicker.getYear();
                            int mMonth =	mDateTimePicker.getMonth();
                            int mDay =	mDateTimePicker.getDay();
							int mHour =	mDateTimePicker.getHour();
							int mMinute = mDateTimePicker.getMinute();

                              TextView Text = (TextView) currentView.findViewById(R.id.textView_Out);
                                String timeLeave=Text.getText().toString();
              
                                
                                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ms").format(new Date());
                                Uri uri= Uri.parse(date);

                                String temp = new String(mCity);
                                
                                MakeAlarm(mYear,mMonth,mDay,mHour, mMinute, uri,timeLeave,temp);

                                Toast.makeText(RaspOneStationActivity.this, "long click : "  + mMonth+ "/"+ mDay + "/" + mYear +"/" +  mHour+ "/" + mMinute, Toast.LENGTH_LONG).show();
                            	
                            	
                            }
                        }
                );

                alertDialogBuilder.setNegativeButton("Нет",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {

                            }

                        }
                );

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                return true;
            }
        });

    }
 private void MakeAlarm(int year, int month,int day,int hour,int minute, Uri uri,String timeLeave,String City) {
    	
    	Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Intent intent = new Intent(MainActivity.getAppContext(),NotificationReciever.class);
        intent.setData(uri);
        
        PendingIntent sender = PendingIntent.getBroadcast(
                    MainActivity.getAppContext(), 0, intent, 0);

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
       
        String timeAlarm=year+"/"+month+"/"+day+" в "+ hour+ ":" + minute;      
      
      AlarmSave.get(this).put(uri.toString(),City,timeLeave,timeAlarm);
     
        
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toggle, menu);
        return super.onCreateOptionsMenu(menu);

    }
  
    boolean birthSort=false;
   
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        
        case android.R.id.home:
            Intent upIntent = new Intent(this, MainActivity.class);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                NavUtils.navigateUpTo(this, upIntent);
         
                finish();
            } else {
         
                finish();
            }
            return true;
            case R.id.star:

                if(birthSort){
                    //change your view and sort it by Alphabet
                    item.setIcon(R.drawable.ic_star_not_selected);
                    item.setTitle("1");
                    birthSort=false;
                }else{
                    //change your view and sort it by Date of Birth
                    item.setIcon(R.drawable.ic_star_selected);
                    item.setTitle("2");
                    birthSort=true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
        


    }
    
    @Override
    protected void onPause(){
    	super.onPause();
    	if(birthSort & !mFlag_empty){
    		
        	Intent intent = this.getIntent();
            String station1 = intent.getStringExtra(MyPrefs.STATION_SINGLE);
            Log.d("ПОЛОЖЕНО",station1);
            ManagerPref.get(this).put(station1);
        }
    }
}
