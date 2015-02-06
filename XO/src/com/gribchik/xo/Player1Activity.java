package com.gribchik.xo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;



public class Player1Activity extends FragmentActivity {
	
	private Button mButtonClear;
	static TextView mPointsPlayer11;
	static TextView mPointsPlayer12;

	FragmentManager fm = getSupportFragmentManager();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player1);
		
		for(int i=0; i<3;i++){
            for(int j=0; j<3; j++){
            	 ClassAlgoritm.massiv[i][j]=0;
        	}
         }
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			
		mButtonClear = (Button)findViewById(R.id.button1);
		mButtonClear.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	ClassVictory.flagVictory=0;
	            for(int i=0; i<3;i++){
	                for(int j=0; j<3; j++){
	                	 ClassAlgoritm.massiv[i][j]=0;
	            	}
	             }
	               
	       		Fragment fragment = fm.findFragmentById(R.id.Container1);
	       		
	       		if (fragment!=null){
	       		    fm.beginTransaction()
	       		    .remove(fragment)
	       		    .commit();
	       		}
	       		
	       		fragment = fm.findFragmentById(R.id.Container2);
	       		
	       		if (fragment!=null){
	       			fm.beginTransaction()
	       			.remove(fragment)
	       			.commit();
	       		}
	       		
	       		fragment = fm.findFragmentById(R.id.Container3);
	       		
	       		if (fragment!=null){
	       			fm.beginTransaction()
	       			.remove(fragment)
	       			.commit();
	       		}
	       		
	       		fragment = fm.findFragmentById(R.id.Container4);
	       			
	       		if (fragment!=null){
	       			fm.beginTransaction()
	       			.remove(fragment)
	       			.commit();
	       		}
	       		
	       		fragment = fm.findFragmentById(R.id.Container5);
	       			
	       		if (fragment!=null){
	       			fm.beginTransaction()
	       			.remove(fragment)
	       			.commit();
	       		}
	       		
	       		fragment = fm.findFragmentById(R.id.Container6);
	       			
	       		if (fragment!=null){
	       			fm.beginTransaction()
	       			.remove(fragment)
	       			.commit();
	       		}
	       			
	       		fragment = fm.findFragmentById(R.id.Container7);
	       			
	       		if (fragment!=null){
	       			fm.beginTransaction()
	       			.remove(fragment)
	       			.commit();
	       		}
	       		
	       		fragment = fm.findFragmentById(R.id.Container8);
	       			
	       		if (fragment!=null){
	       			fm.beginTransaction()
	       			.remove(fragment)
	       			.commit();
	       		}
	       		
	       		
	       		fragment = fm.findFragmentById(R.id.Container9);
	       			
	       		if (fragment!=null){
	       			fm.beginTransaction()
	       			.remove(fragment)
	       			.commit();
	       			}
	        }
	        
	        });


	    mPointsPlayer11=(TextView)findViewById(R.id.PointsPlayer11);
		mPointsPlayer11.setText("0");
				   
	    mPointsPlayer12=(TextView)findViewById(R.id.PointsPlayer12);
		mPointsPlayer12.setText("0");
		    
		LinearLayout mContainer1 = (LinearLayout) findViewById(R.id.Container1);
		mContainer1.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if( ClassAlgoritm.massiv[0][0]==0){
	        		ClassAlgoritm.algoritm(R.id.Container1,0,0,fm,getApplicationContext());
	            	}
	            }
	        });
		
		LinearLayout mContainer2 = (LinearLayout) findViewById(R.id.Container2);
		mContainer2.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(ClassAlgoritm.massiv[0][1]==0){
	            		ClassAlgoritm.algoritm(R.id.Container2,0,1,fm,getApplicationContext());
	            	}
	            }
	        });
		
		LinearLayout mContainer3 = (LinearLayout) findViewById(R.id.Container3);
		mContainer3.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(ClassAlgoritm.massiv[0][2]==0){
	            		ClassAlgoritm.algoritm(R.id.Container3,0,2,fm,getApplicationContext());
	            	}
	            }
	        });
		
		LinearLayout mContainer4 = (LinearLayout) findViewById(R.id.Container4);
		mContainer4.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(ClassAlgoritm.massiv[1][0]==0){
	            		ClassAlgoritm.algoritm(R.id.Container4,1,0,fm,getApplicationContext());
	            	}
	            }
	        });
		
		LinearLayout mContainer5 = (LinearLayout) findViewById(R.id.Container5);
		mContainer5.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(ClassAlgoritm.massiv[1][1]==0){
	            		ClassAlgoritm.algoritm(R.id.Container5,1,1,fm,getApplicationContext());
	            	}
	            }
	        });
		
		LinearLayout mContainer6 = (LinearLayout) findViewById(R.id.Container6);
		mContainer6.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(ClassAlgoritm.massiv[1][2]==0){
	            		ClassAlgoritm.algoritm(R.id.Container6,1,2,fm,getApplicationContext());
	            	}
	            }
	        });
		
		LinearLayout mContainer7 = (LinearLayout) findViewById(R.id.Container7);
		mContainer7.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(ClassAlgoritm.massiv[2][0]==0){
	            		ClassAlgoritm.algoritm(R.id.Container7,2,0,fm,getApplicationContext());
	            	}
	            }
	        });
		
		LinearLayout mContainer8 = (LinearLayout) findViewById(R.id.Container8);
		mContainer8.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(ClassAlgoritm.massiv[2][1]==0){
	            		ClassAlgoritm.algoritm(R.id.Container8,2,1,fm,getApplicationContext());
	            	}
	            }
	        });
		
		LinearLayout mContainer9 = (LinearLayout) findViewById(R.id.Container9);
		mContainer9.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(ClassAlgoritm.massiv[2][2]==0){
	            		ClassAlgoritm.algoritm(R.id.Container9,2,2,fm,getApplicationContext());
	            	}
	            }
	        });
		
	}



}
