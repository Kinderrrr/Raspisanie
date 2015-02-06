package com.gribchik.xo;

public class ClassVictory {
	
	static int flagVictory=0;
	
	
    public static void Victory (int massiv[][]){
    	
        ////строки нули
        for(int i=0; i<3;i++){
	         int summ=0;
	         for (int j=0; j<3; j++){
		         summ=summ+massiv[i][j];
	         }
	         if(summ==3){
		         flagVictory=1;
		         for(int i1=0;i1<3;i1++){
			         for(int j1=0;j1<3;j1++){
				     massiv[i1][j1]=17;
			         }
	             }
             }
	
         }
        
        //////столбцы нули
        if(flagVictory==0){
            for(int i=0; i<3;i++){
	            int summ=0;
	            for (int j=0; j<3; j++){
		            summ=summ+massiv[j][i];
	            }
	            if(summ==3){
		            flagVictory=1;
		            for(int i1=0;i1<3;i1++){
			            for(int j1=0;j1<3;j1++){
				            massiv[i1][j1]=3;
			            }
	                }
                }
            }
        }
    
        /////диогональ слева на право нули
        if(flagVictory==0){
            int d=0;
            int summd=0;
            for(int i=0; i<3;i++){
	            summd=summd+massiv[i][d];
	            d++;
	            if(summd==3){
		            flagVictory=1;
		            for(int i1=0;i1<3;i1++){
			            for(int j1=0;j1<3;j1++){
				            massiv[i1][j1]=3;
			            }
	                }
                }
            }
        }

        ///диоганаль справо налево нули
        if (flagVictory==0){
	        int d=2;
	        int summd=0;
    	    for(int i=0; i<3;i++){
	    	    summd=summd+massiv[i][d];
		        d--;
    		    if(summd==3){
	    		    flagVictory=1;
		    	    for(int i1=0;i1<3;i1++){
			    	    for(int j1=0;j1<3;j1++){
				    	    massiv[i1][j1]=3;
				        }
		            }
	            }
	        }
        }

////////////////////////////////////////////////////////////////////////////////////

        ///строка иксы
        for(int i=0; i<3;i++){
	        int summ=0;
	        for (int j=0; j<3; j++){
		        summ=summ+massiv[i][j];
	        }
	        if(summ==300){
		        flagVictory=2;
		        for(int i1=0;i1<3;i1++){
			        for(int j1=0;j1<3;j1++){
				        massiv[i1][j1]=17;
			        }
	            }
            }	
        }
    
        //////столбцы иксы
        if(flagVictory==0){
            for(int i=0; i<3;i++){
	            int summ=0;
	            for (int j=0; j<3; j++){
		            summ=summ+massiv[j][i];
	            }
	            if(summ==300){
		            flagVictory=2;
		            for(int i1=0;i1<3;i1++){
			            for(int j1=0;j1<3;j1++){
				        massiv[i1][j1]=3;
			            }
	                }
                }
            }
        }
    
        /////диогональ слева на право иксы
        if(flagVictory==0){
            int d=0;
            int summd=0;
            for(int i=0; i<3;i++){
	            summd=summd+massiv[i][d];
	            d++;
	            if(summd==300){
		            flagVictory=2;
		            for(int i1=0;i1<3;i1++){
			            for(int j1=0;j1<3;j1++){
				            massiv[i1][j1]=3;
			            }
	                }
                }	
            }
        }

        ///диоганаль справо налево иксы
        if (flagVictory==0){
	        int d=2;
	        int summd=0;
	        for(int i=0; i<3;i++){
		        summd=summd+massiv[i][d];
		        d--;
		        if(summd==300){
			        flagVictory=2;
			        for(int i1=0;i1<3;i1++){
				        for(int j1=0;j1<3;j1++){
					        massiv[i1][j1]=3;
				        }
		            }
	            }
	        }
	    }
    }
    
}
