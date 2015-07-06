package com.example.raspviewproj;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class AlarmSave {
	
	
	public static final String APP_PREFERENCES = "mysettingss"; 
	public static final String APP_PREFERENCES_NAME_ALL="All";
	public static final String APP_PREFERENCES_NAME_KEY="Key";
	
	
	private static AlarmSave sManagerPref;
	private SharedPreferences mSettings;
	private Context mAppContext;
	
	
	LinkedHashSet<String> temp_all;
    ArrayList<String> all;
    
    LinkedHashSet<String> temp_key;
    ArrayList<String> key;

    
    	 private AlarmSave(Context appContext) {
    		 	
    		 	mAppContext = appContext;
    		 
    		 	mSettings = appContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    		 	temp_all = new LinkedHashSet<String>();
    			all = new ArrayList<String>(temp_all);
    			temp_key = new LinkedHashSet<String>();
    			key = new ArrayList<String>(temp_all);
      
    }
    
    public static AlarmSave get(Context c) {
        if (sManagerPref == null) {
        	sManagerPref = new AlarmSave(c.getApplicationContext());
        }
        
        return sManagerPref;
    }
    
    
    public ArrayList<String> get_all() {
		ArrayList<String> list = new ArrayList<String>(mSettings.getStringSet(APP_PREFERENCES_NAME_ALL, new LinkedHashSet<String>()));
		return list ;
	}
    
    public ArrayList<String> get_key() {
		ArrayList<String> list = new ArrayList<String>(mSettings.getStringSet(APP_PREFERENCES_NAME_KEY, new LinkedHashSet<String>()));
		return list ;
	}
    
	
	public void delete (int index){
		
		all=new ArrayList<String>(mSettings.getStringSet(APP_PREFERENCES_NAME_ALL, new LinkedHashSet<String>()));
		all.remove(index);
		temp_all=new LinkedHashSet<String>(all);
		
		key=new ArrayList<String>(mSettings.getStringSet(APP_PREFERENCES_NAME_KEY, new LinkedHashSet<String>()));
		key.remove(index);
		temp_key=new LinkedHashSet<String>(key);
		
		Editor e = mSettings.edit();
		e.putStringSet(APP_PREFERENCES_NAME_ALL, temp_all);
		e.putStringSet(APP_PREFERENCES_NAME_KEY, temp_key);
		e.apply();
		
	}
	
	
	
	public void put (String argKey, String argCity, String argLeave,String argAlarm){
		
		all=new ArrayList<String>(mSettings.getStringSet(APP_PREFERENCES_NAME_ALL, new LinkedHashSet<String>()));
		all.add(argKey+'+'+argCity+'+'+argLeave+'+'+argAlarm);
		temp_all=new LinkedHashSet<String>(all);
		
		key=new ArrayList<String>(mSettings.getStringSet(APP_PREFERENCES_NAME_KEY, new LinkedHashSet<String>()));
		key.add(argKey);
		temp_key=new LinkedHashSet<String>(key);
		
		
		Editor e = mSettings.edit();
		e.putStringSet(APP_PREFERENCES_NAME_ALL, temp_all);
		e.putStringSet(APP_PREFERENCES_NAME_KEY, temp_key);
		e.apply();
	}
	
	public void remove(){
		
		 mSettings.edit().clear().commit();
	
	}
    
}
