#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h> 

int binToDec(char* bin);
char* decToBin(int dec);
int baseToDec(int base, char* value);
char* decToBase(int base, int dec); 

int getNumber(char c)
{	
	if (c >= '0' && c <= '9'){return (int)c - '0';}
    else {return (int)c - 'A' + 10;}
	
}
char getChar(int n)
{
    if (n >= 0 && n <= 9){return (char)(n + '0');}
    else{return (char)(n - 10 + 'A');}
}

int baseToDec(int base, char* value){
	int result=0;
	char** val = &value;
	int len=strlen(value);
	int power =1;
	for (int i = len - 1; i >= 0; i--)
    {
		result += (getNumber(toupper(*(*val+i)))) * power;
        power = power * base;
	}
	return result;
}

char* decToBase(int base, int dec){	
	int i = 0; 	
	int remainder = 0;
	char temp[100];
	while(dec != 0){		
		remainder = dec%base;
		if(remainder>9){
			temp[i]=getChar(remainder);
		}
		else{
			temp[i]=(char)(remainder + '0');
		}
		dec = dec / base;		
		i++;
	}
	char* bins = malloc(sizeof(char)*i+1);
	int l=i;
	for (i >= 0; i--;)
    {
		bins[l-i-1]=temp[i];
	}
	return bins;
}

// int main(){
	// int nn= baseToDec(8,"11001");
	// int decz = baseToDec(8, "157");
	// int dec = baseToDec(16, "f8");
	
	// printf("\n%d",dec);
	// char* binzy = decToBase(16,999);
	// printf("\n%s",binzy);
	// return 0;
// }






