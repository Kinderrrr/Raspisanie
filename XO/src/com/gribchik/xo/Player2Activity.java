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
import android.widget.Toast;

public class Player2Activity extends FragmentActivity {
	
	TextView mPointsPlayer1;
	TextView mPointsPlayer2;
	Button mClearButton;
	
	FragmentManager fm = getSupportFragmentManager();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player2);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    
	    mPointsPlayer1=(TextView)findViewById(R.id.PointsPlayer1);
	    mPointsPlayer1.setText("0");
	
	   
	    mPointsPlayer2=(TextView)findViewById(R.id.PointsPlayer2);
	    mPointsPlayer2.setText("0");
	    
	    mClearButton=(Button)findViewById(R.id.button21);
	    mClearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClassVictory.flagVictory=0;
            	sost=0;
                for(int i=0; i<3;i++){
            	   for(int j=0; j<3; j++){
            		   massiv[i][j]=0;
            	   }
               }
               
       		    Fragment fragment = fm.findFragmentById(R.id.Container21);
       		    if (fragment!=null){
       		        fm.beginTransaction()
       		        .remove(fragment)
       		        .commit();
       		    }
       		
       		    fragment = fm.findFragmentById(R.id.Container22);
       		    if (fragment!=null){
       			    fm.beginTransaction()
       			    .remove(fragment)
       			    .commit();
       		    }
       		
       			fragment = fm.findFragmentById(R.id.Container23);
       			if (fragment!=null){
       			    fm.beginTransaction()
       			    .remove(fragment)
       			    .commit();
       			}
       		
       			fragment = fm.findFragmentById(R.id.Container24);
       			if (fragment!=null){
       			    fm.beginTransaction()
       			    .remove(fragment)
       			    .commit();
       			}
       		
       			fragment = fm.findFragmentById(R.id.Container25);
       			if (fragment!=null){
       			    fm.beginTransaction()
       			    .remove(fragment)
       			    .commit();
       			}
       		
       			fragment = fm.findFragmentById(R.id.Container26);
       			if (fragment!=null){
       			    fm.beginTransaction()
       			    .remove(fragment)
       			    .commit();
       			}
       			
       			fragment = fm.findFragmentById(R.id.Container27);
       			if (fragment!=null){
       			    fm.beginTransaction()
       			    .remove(fragment)
       			    .commit();
       			}
       		
       			fragment = fm.findFragmentById(R.id.Container28);
       			if (fragment!=null){
       			    fm.beginTransaction()
       			    .remove(fragment)
       			    .commit();
       			}
       		
       		
       			fragment = fm.findFragmentById(R.id.Container29);
       			if (fragment!=null){
       			    fm.beginTransaction()
       			    .remove(fragment)
       			    .commit();
       			}
			}
		});
	
	    LinearLayout mContainer1 = (LinearLayout) findViewById(R.id.Container21);
	    mContainer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(massiv[0][0]==0){
            	algoritm(R.id.Container21,0,0,fm);
            	}
            }
        });
	
	    LinearLayout mContainer2 = (LinearLayout) findViewById(R.id.Container22);
	    mContainer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(massiv[0][1]==0){
            	algoritm(R.id.Container22,0,1,fm);
            	}
            }
        });
	
	    LinearLayout mContainer3 = (LinearLayout) findViewById(R.id.Container23);
	    mContainer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(massiv[0][2]==0){
            	algoritm(R.id.Container23,0,2,fm);
            	}
            }
        });
	
	    LinearLayout mContainer4 = (LinearLayout) findViewById(R.id.Container24);
	    mContainer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(massiv[1][0]==0){
            	algoritm(R.id.Container24,1,0,fm);
            	}
            }
        });
	
	    LinearLayout mContainer5 = (LinearLayout) findViewById(R.id.Container25);
	    mContainer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(massiv[1][1]==0){
            	algoritm(R.id.Container25,1,1,fm);
            	}
            }
        });
	
	    LinearLayout mContainer6 = (LinearLayout) findViewById(R.id.Container26);
	    mContainer6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(massiv[1][2]==0){
            	algoritm(R.id.Container26,1,2,fm);
            	}
            }
        });
	
	    LinearLayout mContainer7 = (LinearLayout) findViewById(R.id.Container27);
	    mContainer7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(massiv[2][0]==0){
            	algoritm(R.id.Container27,2,0,fm);
            	}
            }
        });
	
	    LinearLayout mContainer8 = (LinearLayout) findViewById(R.id.Container28);
	    mContainer8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(massiv[2][1]==0){
            	algoritm(R.id.Container28,2,1,fm);
            	}
            }
        });
	
	    LinearLayout mContainer9 = (LinearLayout) findViewById(R.id.Container29);
	    mContainer9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(massiv[2][2]==0){
            	algoritm(R.id.Container29,2,2,fm);
            	}
            }
        });
	}
	
	int massiv[][] = new int[3][3];
	int sost=0;
	int StatX=0;
	int StatO=0;
	private void algoritm(int cont,int massi, int massj,FragmentManager fm){
		
		
		
		Fragment fragment = fm.findFragmentById(cont);
		
		if(sost==1){
			fragment = new fragmentO();
			massiv[massi][massj]=1;
			sost=0;
		}else{
			fragment = new fragmentX();
			massiv[massi][massj]=100;
			sost=1;
		}
		
		
		fm.beginTransaction()
		.add(cont, fragment)
		.commit();
		////Проверка победы
		
		ClassVictory.Victory(massiv);
		
/////////////////////////////////////////////////////
		//вынесение вердикта
		
		
		if(ClassVictory.flagVictory==0){
			int numb=0;
			for(int i=0; i<3;i++){
				for(int j=0; j<3;j++){
					if(massiv[i][j]!=0){
						numb++;
					}
				}
			}
			if(numb==9){
				ClassVictory.flagVictory=3;
			}
		}
		
		if(ClassVictory.flagVictory==1){
			
			StatO++;
			mPointsPlayer2.setText(" " + StatO);
			
			Toast toast = Toast.makeText(getApplicationContext(), "Нули выиграли", Toast.LENGTH_SHORT); 
			toast.show(); 
		}else if(ClassVictory.flagVictory==2){
			
			StatX++;
			mPointsPlayer1.setText(" " + StatX);
			
			Toast toast = Toast.makeText(getApplicationContext(), "Иксы выиграли", Toast.LENGTH_SHORT); 
			toast.show(); 
		}else if(ClassVictory.flagVictory==3){
		
			Toast toast = Toast.makeText(getApplicationContext(),  "Ничья", Toast.LENGTH_SHORT); 
			toast.show(); 
		}
	}

}
