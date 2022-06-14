#ifndef _HCOMPRESS_H_
#define _HCOMPRESS_H_
tnode* found; 
tnode* treeRoot;
typedef unsigned char bitSet;
LinkedList* generateFreqTable(char* file); 
tnode* createHuffmanTree(LinkedList* tree);
void encodeFile(char* file, tnode* treeRoot, LinkedList* leafNodes);
void decodeFile(char* file, tnode* tree);
char* getBinSet(int base, int dec);
void findNodeAddress(tnode* n, int c);
void freeTree(tnode* root);
void freeLinkedList(LinkedList* top);
int leafToRoot(tnode* leaf, char* binA, int count);
char getChar(int n);
void printBits(size_t const size, void const* const ptr);
#endif