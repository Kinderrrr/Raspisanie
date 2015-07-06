package com.example.raspviewproj;

import Adapters.MyPagerAdapterRasp;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import db.MyPrefs;

public class RaspActivity extends FragmentActivity {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_rasp);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Расписание");

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapterRasp(getSupportFragmentManager()));
        PagerTabStrip strip = (PagerTabStrip) findViewById(R.id.pagerTabStrip);
        strip.setTabIndicatorColor(0x0060b7);
        strip.setMinimumHeight(7);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toggle, menu);
        return super.onCreateOptionsMenu(menu);

    }

    
    @Override
    protected void onPause(){
    	super.onPause();
    	if(birthSort){
        	Intent intent = this.getIntent();
            String station1 = intent.getStringExtra(MyPrefs.STATION_FROM);
            String station2 = intent.getStringExtra(MyPrefs.STATION_TO);
            ManagerPref.get(this).put(station1, station2);
        }
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
    

	
}