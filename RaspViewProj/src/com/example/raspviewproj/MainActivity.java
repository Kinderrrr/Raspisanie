package com.example.raspviewproj;

import Adapters.MyPagerAdapter;
import android.app.ActionBar;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import db.MyPrefs;
import db.MySQLiteClass;

public class MainActivity extends FragmentActivity {
	
	private static Context context;

	public static Context getAppContext(){
	    return MainActivity.context;
	}

	ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	context=getApplicationContext();

    	ManagerPref.get(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.main);
        
        ActionBar actionbar=getActionBar();
    	actionbar.setTitle("Расписание");

        pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(0);
        PagerTabStrip strip = (PagerTabStrip) findViewById(R.id.pagerTabStrip);
        strip.setTabIndicatorColor(0x0060b7);
        strip.setMinimumHeight(7);

        Log.d("MainActivity", "onCreate");
     //   getWindow().
       // getDecorView().
       // setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION); 
    }


    @Override
    protected void onResume() {
        super.onResume();
        pager.getAdapter().notifyDataSetChanged();
        Log.d("MainActiv", "onResume");
        String updateStatus = MyPrefs.getStringPrefs(MainActivity.this, MenuActivity.UPDATE_STATUS);
        Log.d("MainActiv", "updateStatus = " + updateStatus);
        if (updateStatus.equals(MenuActivity.STATUS_WILL_UPDATE)) {
            MyPrefs.setPrefs(MainActivity.this, MenuActivity.UPDATE_STATUS, MenuActivity.STATUS_NOW_UPDATING);//!
            new MyTask().execute();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch (item.getItemId()) {
        case R.id.alarm:
        	 Intent intent1 = new Intent(this, AlarmActivity.class);
             startActivity(intent1);

            return true;
        case R.id.menu:
        	 Log.d("onOptionsItemSelected", "onOptionsItemSelected");
             Intent intent = new Intent(this, MenuActivity.class);
             startActivity(intent);
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
   
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            makeNotification("Загрузка началась", "Обновление базы началось");
            setProgressBarIndeterminateVisibility(true);
            Log.d("MyTask", "Загрузка началась");
        }

        @Override
        protected Void doInBackground(Void... params) {

            MySQLiteClass mySQLiteClass = new MySQLiteClass(MainActivity.this);
            mySQLiteClass.fillingTest(getResources());

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
   
            Log.d("MyTask", "Загрузка закончилась");
            MyPrefs.setPrefs(MainActivity.this, MenuActivity.UPDATE_STATUS, MenuActivity.STATUS_NOT_UPDATING);

            MyPrefs.setPrefs(MainActivity.this, MyPrefs.UPDATE_DATE, MySQLiteClass.getCurrentTime());
            makeNotification("Загрузка закончилась", "Обновление базы завершено");
            setProgressBarIndeterminateVisibility(false);

            
            String[] data = MyPrefs.getStringPrefs(MainActivity.this, MyPrefs.STATION_PREFS).split(",");  // terms is a List<String>
            ArrayAdapter<String> adapter = new ArrayAdapter<String>((MainActivity.this), android.R.layout.simple_list_item_1, data);
            ((AutoCompleteTextView) findViewById(R.id.editText_from)).setAdapter(adapter);
            ((AutoCompleteTextView) findViewById(R.id.editText_to)).setAdapter(adapter);
            ((AutoCompleteTextView) findViewById(R.id.editText_one)).setAdapter(adapter);///***
            Log.d("###", "setAdapters");
           

        }
    }

    public void makeNotification(String title, String messageText) {
        final int NOTIFY_ID = 101;

        Context context = getApplicationContext();

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = context.getResources();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.parovoz)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.parovoz_small))
                .setTicker(title)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(res.getString(R.string.app_name))
                .setContentText(title);

        Notification notification = builder.getNotification();

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
    }


}
