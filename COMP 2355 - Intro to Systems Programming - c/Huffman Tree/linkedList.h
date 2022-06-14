#ifndef _LINKEDLIST_H_
#define _LINKEDLIST_H_
typedef struct treeNode {
	double weight;
	int c;
	struct treeNode* left;
	struct treeNode* right;
	struct treeNode* parent;
} tnode;

typedef struct node {
	tnode* nodeV;
	struct node* next;
} LinkedList;


LinkedList* list_search(LinkedList* n, int c);
LinkedList* llCreate();
int size;
void llRemoveTop(LinkedList** ll);
void llFreqDisplay(LinkedList* ll);
void llAdd(LinkedList** ll, tnode* newValue);
void lladdInOrder(LinkedList** ll, tnode* newVal);
int llSize(LinkedList* ll);

#endif
