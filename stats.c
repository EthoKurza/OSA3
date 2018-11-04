#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]){
	//check if there are enough arguments
	if (argc == 1){
		printf("not enough arguments entered \n");
	}//end of argument check
	
	//declare variables
	int i, tempNum = 0, minimum = 0, maximum = 0, average = 0;
	int numArray[(argc-1)]; 
	
	//grab input from command and place data into array
	for (i=1; i < argc; i++){
		tempNum = atoi(argv[(i)]); //convert data from commandline to type int
		numArray[i] = tempNum; //place number into array
		//printf("number %d: %d\n",i,numArray[i]); //test to see if numbers are place into array
		
	}//end of for loop
	
	
	//check minimum
	minimum = numArray[1];
	for (i = 1; i < argc; i++){
		if(minimum >= numArray[i]){
			minimum = numArray[i];
		}
	}//end of minimum check
	
	//check maximum
	maximum = numArray[1];
	for (i = 1; i < argc; i++){
		if(maximum <= numArray[i]){
			maximum = numArray[i];
		}
	}	
	//check average
	int tempTotal = 0;
	for (i=1; i < argc; i++){
		tempTotal += numArray[i];
	}
	average = tempTotal/(argc-1);
	
	
	//print results
	printf("The average value: %d \n", average);
	printf("The minimum value: %d \n", minimum);
	printf("The maximum value: %d \n", maximum);
}//end of main
