#include <stdio.h>
#include <stdlib.h>
#include "linkedList.h"
LinkedList* llCreate() {
    return NULL;
}

void llRemoveTop(LinkedList** ll) {
    if (ll == NULL || *ll==NULL) {
        return;
    }
    LinkedList* temp = *ll;
        *ll = temp->next; 
        free(temp); 
}

void llFreqDisplay(LinkedList* ll) {
    LinkedList* p = ll;
    int i = 0;
    while (p != NULL) {
        printf("int c = %d :\tweight = %f :\tchar = %c \n", p->nodeV->c, p->nodeV->weight, p->nodeV->c);
        i++;
        p = p->next;
    }
}

void llAdd(LinkedList** ll, tnode* newValue) {
    LinkedList* newNode = (LinkedList*)malloc(1 * sizeof(LinkedList));
    newNode->nodeV = newValue;
    newNode->next = NULL;
    LinkedList* p = *ll;
    if (p == NULL) {
        *ll = newNode;
        //printf(" THIS LIST IS EMPTY \n");
    }
    else {
        while (p->next != NULL) {
            p = p->next;
        }
        p->next = newNode;
    }
}

void lladdInOrder(LinkedList** ll, tnode* newVal) {
    LinkedList* newNode = (LinkedList*)malloc(1 * sizeof(LinkedList));
    newNode->nodeV = newVal; 
    newNode->next = NULL; 
    LinkedList* p = *ll; 

    if (p == NULL) { 
        *ll = newNode;
    }
    else if (p->nodeV->weight > newVal->weight) {
        newNode->next = *ll; 
        *ll = newNode;
    }
    else if (p != NULL) {
        while (p->next != NULL) {
            if ((p->next->nodeV->weight) > (newVal->weight)) {
                LinkedList* temp = p->next;

                p->next = newNode; 

                newNode->next = temp;
                return;
            }
            else { 
                p = p->next; 
            }
        }
        llAdd(ll, newVal);
    }
}
int llSize(LinkedList* ll) {
    LinkedList* p = ll;
    int size = 0;

    if (p == NULL) {
        return size;
    }
    size++;
    while (p->next != NULL) {
        p = p->next;
        size++;
    }
    
    return size;
}

LinkedList* list_search(LinkedList* n, int c)
{
    if (n == NULL)
        return NULL;

    while (n!= NULL)
    {
        if (n->nodeV->c == c)
        {
            return n;
        }
        n = n->next;
    }
    return NULL;
}


