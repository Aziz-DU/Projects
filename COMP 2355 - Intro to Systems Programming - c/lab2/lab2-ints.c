

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Histogram {
	int num;
	int freqOfn;
}Histogram;


char** readScores(int* count)
{   
		char** arr = malloc(sizeof(char*)*100);
		char* buffer = malloc(sizeof(char)*100);
	//	char buffer[100];
		printf("The array is:\n");
		int res = scanf("%s",buffer);
		int c = 0;
	while(res!=EOF){
		*(arr + c) = malloc((strlen(buffer)+1) * sizeof(char));
		 stpcpy(*(arr+c),buffer);
	//	printf(" %s ",*(arr+c));
		res = scanf("%s",buffer);
		c+=1;	
	}

	*count = c;
	free(buffer);
	return arr; 
}
void displayScores(char** n,int l)
{   
	for(int i=0;i<l;i++){
		printf("score %d:%s\n ",i,*(n+i));
}
}
int calculateHistogramFreq(char** arr,Histogram** freq)
{ 
		int arrInt[13];
	
	for(int i=0;i<12;i++){
	char* c=*(arr+i);
	arrInt[i]=atoi(c);
	//printf("hhh %d \n",arrInt[i]);
	}
	
//	printf("\n\n dddd %s",*(arr));
	Histogram* heapFreq=malloc(100 * sizeof(struct Histogram));

	for(int i=0;i<12;i++){	
		(heapFreq+i)->freqOfn=0;
		(heapFreq+i)->num=-777;       //-777 means it's empty, could use 0 instead but 
	}							//-777 is safer because it's more likey that we have 0 as a number th
	
	

	
	int currentN=0;
	int uniqueE=0;
	for(int i=0;i<13;i++){
		if((heapFreq+i)->num==-777){
			(heapFreq+i)->num=arrInt[currentN];
			(heapFreq+i)->freqOfn+=1;
			currentN+=1;
			uniqueE+=1;
			i=-1;
		}
		else{
			if((heapFreq+i)->num==arrInt[currentN]){
				(heapFreq+i)->freqOfn+=1;
				currentN+=1;
				i=-1;
			}
		}
					

		if(currentN>=12){  		//if we went over the whole array we update distinct entries and exit loop
		//	*l=uniqueE;
			i=currentN;
		}
	}

	*freq= heapFreq;
	// for(int i=0;i<uniqueE;i++){
			// printf("\n hhhhhhhhhhhhhhhhhhh %d :freq %d ",(*freq+i)->num,(*freq+i)->freqOfn);

	// }
	
	
	return uniqueE;
}
void displayHistogramFreq(int l,Histogram** freq)

{   
	for(int i=0;i<l;i++){
		printf("\n value %d :freq %d ",(*freq+i)->num,(*freq+i)->freqOfn);
	//printf("\n zzzzzzz %d :freq %d ",(*(freq+i))->num,(*freq+i)->freqOfn);
	//printf("\n value %d :freq %d ",(*(freq+i)->num),(*(freq+i)->freqOfn));
}
}
void sortHistogramFreq(int l,Histogram** freq)
{   int mostFreq=0;
	int indexOfMQ=0;
	int startFromTop=0;
	for(int i=0;i<l;i++){
		if((*freq+i)->freqOfn>mostFreq){
			mostFreq=(*freq+i)->freqOfn;
			indexOfMQ=i;
		}
		if(i>= l-1){
			int tf=(*freq+startFromTop)->num;
			int tn=(*freq+startFromTop)->freqOfn;
			(*freq+startFromTop)->freqOfn=(*freq+indexOfMQ)->freqOfn;
			(*freq+startFromTop)->num=(*freq+indexOfMQ)->num;
			
			(*freq+indexOfMQ)->freqOfn=tn;
			(*freq+indexOfMQ)->num=tf;
			mostFreq=0;
			startFromTop+=1;
			if(startFromTop<l-1){
				i=startFromTop-1;			
			}	
		}
	}
}
int main(){
	int count;
	//int *arr;
	//char** array;
	
	
	char** array=readScores(&count);

	//printf("\n\n %s",*(array+3));
	struct Histogram freqArr;
	struct Histogram* ref;
	printf("Displaying the file results:  \n ");
	//printf("xxx %p:%d\n ",array,count);
		displayScores(array,count);
	int histCount=calculateHistogramFreq(&array[0],&ref);

	//printf("\n xxx %d\n ", (ref)->freqOfn);
	displayHistogramFreq(histCount,&ref);
	sortHistogramFreq(histCount,&ref);
	printf("\n \nDisplaying the sorted histogram frequencies:");
	displayHistogramFreq(histCount,&ref);
	for(int i=0;i<12;i++){
	//printf("vvv %s\n ",*(array+i));
		free(*(array+i));
	}

	free(ref);
	free(array);
	//free(arr);
	return 0;
}



