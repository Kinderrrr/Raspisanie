package com.example.raspviewproj;

import Adapters.ListViewAlarmAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmActivity extends Activity {
    
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_activity);  
        final Bundle sav=savedInstanceState;
        final ListView lv = (ListView) findViewById(R.id.MylistAlarm);
      
        if(AlarmSave.get(this).get_all().size()==0){
        	LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View empty_view = inflater.inflate(R.layout.empty_list, null, false);

            TextView textViewMessage = (TextView) empty_view.findViewById(R.id.show_empty_set_tv);
            textViewMessage.setText(getResources().getString(R.string.empty_alarm));

            setContentView(empty_view);
        }
        final ListViewAlarmAdapter adapter = new ListViewAlarmAdapter(this, AlarmSave.get(this).get_all());
        lv.setAdapter(adapter);
        
        ActionBar actionbar=getActionBar();
    	actionbar.setTitle("Напоминания");
    	actionbar.setDisplayHomeAsUpEnabled(true);
        
        lv.setLongClickable(true);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                // TODO Auto-generated method stub

                final int i=index;

                Builder alertDialogBuilder = new AlertDialog.Builder(AlarmActivity.this);
                alertDialogBuilder.setTitle("Удаление");
                alertDialogBuilder.setMessage("Удалить напоминание?");
                
                alertDialogBuilder.setPositiveButton("Да",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {

                            	
                            	 AlarmManager am = (AlarmManager) AlarmActivity.this.getSystemService(Context.ALARM_SERVICE);
                            	 am = (AlarmManager) getSystemService(ALARM_SERVICE);

                                 Uri myUri = Uri.parse(AlarmSave.get(AlarmActivity.this).get_key().get(i));
                             
                                 Toast.makeText(getApplicationContext(), "Размер  "+ myUri, Toast.LENGTH_LONG).show();
                                 AlarmSave.get(AlarmActivity.this).delete(i);
                              
                                 Intent intent = new Intent(MainActivity.getAppContext(),NotificationReciever.class);
                                 intent.setData(myUri);
                                
                                 PendingIntent sender = PendingIntent.getBroadcast(MainActivity.getAppContext(), 0, intent, 0);
                                 am.cancel(sender);
                                 onCreate(sav);
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


    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_alarm, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch (item.getItemId()) {
        case R.id.menu:
        	 Log.d("onOptionsItemSelected", "onOptionsItemSelected");
             Intent intent = new Intent(this, MenuActivity.class);
             startActivity(intent);
            return true;
            
        case android.R.id.home:
            Intent upIntent = new Intent(this, MainActivity.class);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                NavUtils.navigateUpTo(this, upIntent);
                finish();
            } else {
                finish();
            }
        default:
            return super.onOptionsItemSelected(item);
    }
   
    }

    

}
