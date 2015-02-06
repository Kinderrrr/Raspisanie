package com.gribchik.xo;

import java.io.IOException;
import java.util.Random;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

public class ClassAlgoritm extends FragmentActivity {
	
    static int massiv[][] = new int[3][3];
	static int StatX=0;
	static int StatO=0;
	
	//////Получение контейнера
	public static int checkContainer(int i, int j)  {
		massiv[i][j]=1;
		int container=0;
		if (i==0){
			if(j==0){
				container=R.id.Container1;
			}else if(j==1){
				container=R.id.Container2;
			}else if(j==2){
				container=R.id.Container3;
			}		
		}else if (i==1){
			if(j==0){
				container=R.id.Container4;
			}else if(j==1){
				container=R.id.Container5;
			}else if(j==2){
				container=R.id.Container6;
			}
		}else if (i==2){
			if(j==0){
				container=R.id.Container7;
			}else if(j==1){
				container=R.id.Container8;
			}else if(j==2){
				container=R.id.Container9;
			}	
		}
		return container;
	}
	
	////Ход компьютера
	public static void algoritm(int cont,int massi, int massj,FragmentManager fm,Context context){	
		 
		massiv[massi][massj]=100;
		
		int FlagHoda=0;
		
		Fragment fragment = fm.findFragmentById(cont);
		
		fragment = new fragmentX();
		fm.beginTransaction()
		.add(cont, fragment)
		.commit();
			
	    if(FlagHoda==0){
	        ////нули горизонталь
			for(int i=0; i<3;i++){
			    int summ=0;
				for (int j=0; j<3; j++){
			        summ=summ+massiv[i][j];
			    }
				if(summ==2){
			        for(int k=0;k<3;k++){
						if(massiv[i][k]!=1){
							if(massiv[i][k]!=100){
								FlagHoda=1;
							    cont=checkContainer(i,k);
							    fragment = new fragmentO();
							    fm.beginTransaction()
							    .add(cont, fragment)
							    .commit();
							}
					    }
					}
				}
			}
        }
			
	    if(FlagHoda==0){
	    	
		    //Нули вертикаль
		    for(int i=0; i<3;i++){
		        int summ=0;
			    for (int j=0; j<3; j++){
					summ=summ+massiv[j][i];
				}
				if(summ==2){
					for(int k=0;k<3;k++){
						if(massiv[k][i]!=1){	
							if(massiv[k][i]!=100){
								FlagHoda=1;
							    cont=checkContainer(k,i);
							    fragment = new fragmentO();
							    fm.beginTransaction()
							    .add(cont, fragment)
							    .commit();
							}	
						}
					}
				}
			}
        }
	    
	    //нули диогональ слева направо
		if (FlagHoda==0){
			int d=0;
			int summd=0;
			for(int i=0; i<3;i++){
				summd=summd+massiv[i][d];
				d++;
				if(summd==2){	
					int dc=0;
					for(int k=0;k<3;k++){
						if(massiv[k][dc]!=1){
							if(massiv[k][dc]!=100){
								FlagHoda=1;
								cont=checkContainer(k,dc);
								fragment = new fragmentO();
								fm.beginTransaction()
								.add(cont, fragment)
								.commit();
							}
						}
						dc++;
					}
				}
			}
        }	
		
		////Нули диоганаль справа налево
		if (FlagHoda==0){
		    int d=2;
			int summd=0;
			for(int i=0; i<3;i++){
			    summd=summd+massiv[i][d];
			    d--;
				if(summd==2){		
				    int dc=2;
				    for(int k=0;k<3;k++){
					    if(massiv[k][dc]!=1){
						    if(massiv[k][dc]!=100){
							    FlagHoda=1;
								cont=checkContainer(k,dc);
								fragment = new fragmentO();
								fm.beginTransaction()
								.add(cont, fragment)
								.commit();
							}
						}
							dc--;
					}
				}	
			}
		}
		
//////////////////////////////////////////////////////////////////////////
		
		///Строка иксы
		if (FlagHoda==0){
			for(int i=0; i<3;i++){
				int summ=0;
				for (int j=0; j<3; j++){
					summ=summ+massiv[i][j];
				}
				if(summ==200){	
					for(int k=0;k<3;k++){
						if(massiv[i][k]!=1){
							if(massiv[i][k]!=100){
								FlagHoda=1;
							    cont=checkContainer(i,k);
							    fragment = new fragmentO();
							    fm.beginTransaction()
							    .add(cont, fragment)
							    .commit();
							}
						}
					}
				}
			}
		}
		
		//////Иксы столбцы
		if(FlagHoda==0){
			for(int i=0; i<3;i++){
				int summ=0;
				for (int j=0; j<3; j++){
					summ=summ+massiv[j][i];
				}
				if(summ==200){	
					for(int k=0;k<3;k++){
						if(massiv[k][i]!=1){
							if(massiv[k][i]!=100){
								FlagHoda=1;
							    cont=checkContainer(k,i);
							    fragment = new fragmentO();
							    fm.beginTransaction()
							    .add(cont, fragment)
							    .commit();
							}
						}
					}
				}
			}
		}
		
		/////диогональ иксы слева направо
		if(FlagHoda==0){
			int d=0;
			int summd=0;
			for(int i=0; i<3;i++){
				summd=summd+massiv[i][d];
				d++;
				if(summd==200){	
					int dc=0;
					for(int k=0;k<3;k++){
						if(massiv[k][dc]!=1){
							if(massiv[k][dc]!=100){
								FlagHoda=1;
								cont=checkContainer(k,dc);
								fragment = new fragmentO();
								fm.beginTransaction()
								.add(cont, fragment)
								.commit();
							}
						}
						dc++;
					}
				}
				
			}
		}
		
	    //////диоганаль иксы справа налево
		if (FlagHoda==0){
			int d=2;
			int summd=0;
			for(int i=0; i<3;i++){
				summd=summd+massiv[i][d];
				d--;
				if(summd==200){	
					int dc=2;
					for(int k=0;k<3;k++){
						if(massiv[k][dc]!=1){
							if(massiv[k][dc]!=100){
								FlagHoda=1;
								cont=checkContainer(k,dc);
								fragment = new fragmentO();
								fm.beginTransaction()
								.add(cont, fragment)
								.commit();
							}
						}
						dc--;
					}
				}
			}
		}

		/////Проверка победы
		ClassVictory.Victory(massiv);
	
		int RandI = massi;
		int RandJ = massj;
		Random random = new Random();
		int FlagRandom =0;
		
		for(int i=0;i<3;i++){
		    for(int j=0;j<3;j++){
			    if(massiv[i][j]!=0){
				    FlagRandom ++;
			    }
		    }
	
	    }
	
	    if(FlagRandom!=9){
	        if(FlagHoda==0){
		    while(massiv[RandI][RandJ]!=0){
		        RandI=random.nextInt(3);
		        RandJ=random.nextInt(3);
		    }
		    cont=checkContainer(RandI,RandJ);
		    fragment = new fragmentO();
		    fm.beginTransaction()
		    .add(cont, fragment)
		    .commit();	
	        }
	    }
	    
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
			
			Player1Activity.mPointsPlayer12.setText(" " + StatO);
			Toast toast = Toast.makeText(context, "Нули выиграли", Toast.LENGTH_SHORT); 
			toast.show(); 
			
		    }else if(ClassVictory.flagVictory==2){
		    	
			    StatX++;
			    
			    Player1Activity.mPointsPlayer11.setText(" " + StatX);
			    Toast toast = Toast.makeText(context, "Иксы выиграли", Toast.LENGTH_SHORT); 
				toast.show(); 
				
		    }else if(ClassVictory.flagVictory==3){
			
			Toast toast = Toast.makeText(context, "Ничья", Toast.LENGTH_SHORT); 
			toast.show(); 
		    }
			
		fragment = new fragmentX();		
	}
	
}
