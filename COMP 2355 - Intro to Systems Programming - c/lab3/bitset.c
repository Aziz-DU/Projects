#include "baseConv.c" 
#include <math.h>

typedef unsigned short bitSet;

bitSet makeBitSet(); // Create a new bitset
void displayBitSet(bitSet bs); // Displays the 16 bits of the bitset to the screen (i.e. print them)
void setBit(bitSet* bs, int index); // Sets bit at 'index' of the bitset to 1
void clearBit(bitSet* bs, int index); // Sets bit at 'index' of the bitset to 0
int bitValue(bitSet bs, int index); // Returns the value of the bit at 'index'

bitSet makeBitSet()
{
	bitSet newBitSet = 0;
	return newBitSet;
}

void displayBitSet(bitSet bs) {
	int i = 0;
	printf("\n");
	while(i<16){
		printf("%d", ((bs & (int)pow(2, (15 - i))) >> (15 - i)));
		i++;
	}
}
void setBit(bitSet* bs, int index){
	*bs = *bs | (int) pow(2, index);
}
void clearBit(bitSet* bs, int index) {
	*bs = *bs & (255 - (int) pow(2, index));
}
int bitValue(bitSet bs, int index){
	return ((bs & (int) pow(2, index)) >> index);
}
int main(){
	int baseToDec1 = baseToDec(8, "157");
	int baseToDec2 = baseToDec(16, "f8");
	printf(" 158 base 8 = %d",baseToDec1);
	char* decToBase1 = decToBase(16,999);
	printf("\n 999 in decimal to base 16 = %s",decToBase1);
	
	bitSet newBitSet = makeBitSet();
	printf("\n Making new bit set:");
	displayBitSet(newBitSet);
	printf("\n setting index 3 and 4 to 1:");
	setBit(&newBitSet,3);
	setBit(&newBitSet,4);
	displayBitSet(newBitSet);
	printf("\n clearing index 4:");
	clearBit(&newBitSet, 4);
	displayBitSet(newBitSet);
	printf("\n value of bit at index 3 = %d\n", bitValue(newBitSet, 3));
	
	return 0;
}






