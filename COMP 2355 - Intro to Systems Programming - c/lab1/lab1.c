#include <stdio.h>
struct frq {
	int num;
	int freqOfn;
};

void displayScores(int* n,int* c,int l)
{   for(int i=0;i<l;i++){
		printf("score %d:%d\n ",i,n[i]);
}
}
void calculateHistogramFreq(int* n,int* l,struct frq* freq)
{ 
	for(int i=0;i<*l;i++){	
		freq[i].freqOfn=0;
		freq[i].num=-777;       //-777 means it's empty, could use 0 instead but 
	}							//-777 is safer because it's more likey that we have 0 as a number th
	int currentN=0;
	int uniqueE=0;
	for(int i=0;i<*l;i++){
		if(freq[i].num==-777){
			freq[i].num=n[currentN];
			freq[i].freqOfn+=1;
			currentN+=1;
			uniqueE+=1;
			i=-1;
		}
		else{
			if(freq[i].num==n[currentN]){
				freq[i].freqOfn+=1;
				currentN+=1;
				i=-1;
			}
		}
		if(currentN>=12){  		//if we went over the whole array we update distinct entries and exit loop
			*l=uniqueE;
			i=currentN;
		}
	}
}
void displayHistogramFreq(int l,struct frq* freq)
{   for(int i=0;i<l;i++){
		printf("\n value %d :freq %d ",freq[i].num,freq[i].freqOfn);
}
}
void sortHistogramFreq(int l,struct frq* freq)
{   int mostFreq=0;
	int indexOfMQ=0;
	int startFromTop=0;
	for(int i=0;i<l;i++){
		if(freq[i].freqOfn>mostFreq){
			mostFreq=freq[i].freqOfn;
			indexOfMQ=i;
		}
		if(i>= l-1){
			int tf=freq[startFromTop].num;
			int tn=freq[startFromTop].freqOfn;
			freq[startFromTop].freqOfn=freq[indexOfMQ].freqOfn;
			freq[startFromTop].num=freq[indexOfMQ].num;
			
			freq[indexOfMQ].freqOfn=tn;
			freq[indexOfMQ].num=tf;
			mostFreq=0;
			startFromTop+=1;
			if(startFromTop<l-1){
				i=startFromTop-1;			
			}	
		}
	}
}
int main(){
	int arr[100];
	int count=0;	
	while(scanf("%d",&arr[count])!=EOF){
		count++;	
	}
	int countArr[count];
	struct frq freqArr[count];
	
	printf("Displaying the file results:  \n ");
	displayScores(&arr[0],&countArr[0],count);
	calculateHistogramFreq(&arr[0],&count,&freqArr[0]);
	
	printf("Displaying the histogram frequencies: ");
	displayHistogramFreq(count,&freqArr[0]);
	sortHistogramFreq(count,&freqArr[0]);
	
	printf("\n Displaying the sorted histogram frequencies:");
	displayHistogramFreq(count,&freqArr[0]);	
	
	return 0;
}
