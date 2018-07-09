#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

/*In here i have implemented the simplex method to the problems which have 3 constraints and 3 varibles.
You can change it by change the defined values of K and L 
In Here the inputs are given in the main method.They should be  in standard form.

I Have shown an example with
objective function : P = 6x + 5y + 4z

4x + 2y + 2z <= 360 ,
2x + 6y + 4z <= 600,
4x + 2y + 4z <= 480
 x, y, z >= 0	
 

	

*/
#define K 3 //  3 constraints
#define L 3 //  3 variables


int col,row;

float table[K+1][K+L+2];


float* simplex(float A[K][L],float B[],float C[],float d[],int m,int n);

void printTable( int m, int n);

int main() {
	float A[K][L];
	float b[K];
	float c[L];
	
	c[0] = 6; c[1] = 5; c[2] = 4;// P = 6x + 5y + 4z
	A[0][0] = 4; A[0][1] = 2; A[0][2] = 2; b[0] = 360;// 4x + 2y + 2z <= 360 
	A[1][0] = 2; A[1][1] = 6; A[1][2] = 4; b[1] = 300;// 2x + 6y + 4z <= 600
	A[2][0] = 4; A[2][1] = 2; A[2][2] = 4; b[2] = 480;// 4x + 2y + 4z <= 480

	float d[K];//solution array
	
	simplex(A, b, c, d, K, L);// solutions are stored in the d[L] array
	int i;
	// solutions for x,y,z
	printf("x = %f\n",d[0]);
	printf("y = %f\n",d[1]);
	printf("z = %f\n",d[2]);

	return 0;
}
//create the table from A,B,C
void initializeSimplex(float A[K][L], float b[], float c[], int m, int n ){
    int q = m + 1;
	int r = n + m + 2;

	int i,j;

    for( i = 0;i < m; i++){// put data to table
		table[i][r -1] = b[i];// put B data to table
		for( j = 0;j < n; j++){// put  A data to table
			table[i][j] = A[i][j];
		}
		
		// fill s1,s2 & s3
		for( j = n; j < r - 1; j++){
			if(i == (j - n)){
				table[i][j] = 1;
			}
			else{
				table[i][j] = 0;
			}
			//fill the last row of the slack variables
			if(i == m-1){
				table[i+1][j] = 0;
				if(j == r - 2){
					table[i+1][j] = 1;
				}
			}
			if(j == r - 2){
				table[i][j] = 0;
			}
		}
	}
	// add objective coefficent data to table
	for( i = 0;i < n;i++){
		table[m][i] = -c[i];
	}


	table[q - 1][r - 1] = 0;
}
bool selectPivot(int m,int n){
    int q = m + 1;
	int r = n + m + 2;
    int i,j;
    bool isNegative=false;
	int minimum=0;
	
		// search for  negative column
		for( j = 0; j < r - 1; j++){
			if(table[q-1][j] < minimum){
				col = j;
				minimum = table[q-1][j];
				isNegative = true;
			}
		}
		minimum = table[0][r-1] / table[0][col];
		row = 0;
		if(isNegative){
			
			//search for the minimum row
			for( i = 1; i < q - 1; i++){
				if((table[i][r-1] / table[i][col]) < minimum){
					minimum = (table[i][r-1] / table[i][col]);
					row = i;
				}
			}
}
return isNegative;
}






float* simplex(float A[K][L], float b[], float c[], float d[],int m, int n){

	int q = m + 1;
	int r = n + m + 2;

	int i,j;
	bool isNegative;
	
	float  divisor, multiplier;
	bool isNull;
    
    initializeSimplex(A, b, c, K, L);
    
	//Simplex method
	do{
		
		printTable(q,r);
		printf("\n---------------------------------------------------\n");
		isNegative=selectPivot(m,n);
		if(isNegative){
		    divisor = table[row][col];
			for(j = 0; j < r; j++){
				table[row][j] = table[row][j] / divisor; 
			}
			for(i = 0; i < q; i++){
				if(i != row){
					multiplier = -(table[i][col]);
					for(j = 0; j < r; j++){
						table[i][j] = (table[i][j] + (table[row][j] * multiplier));
					}
				}
			}
		}
        
			
			
			
    }while(isNegative);
    
   

	

    
    
	for(j = 0; j < n; j++){//traversing columns
		isNull = false;
		for(i = 0; i < q; i++){//traversing rows
			if(j != i){
				if(table[i][j] != 0) isNull = true;
			}
		}
		if(isNull){
		    d[j] = 0;
		}else {
		    d[j] = table[j][r-1]; // i = j at the relavent variable
		}
		
	}
    
	return d;

}

void printTable( int m, int n){
    int i,j;
	for( i = 0; i < m; i++){
		for(j = 0; j < n; j++){
		printf("%f | ",table[i][j] ) ;
		}
		printf("\n");
	}
}
