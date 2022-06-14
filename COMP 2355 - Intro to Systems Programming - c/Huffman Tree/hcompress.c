///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 2355: Intro to Systems Programming
//Project info: Huffman Tree
///////////////////////////////////////////////////

#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "linkedList.h"
#include "hcompress.h"
#include <ctype.h>

int main(int argc, char* argv[]) {
    if (argc != 4) {
        printf("%d", argc);
        printf("Error: The correct format is \"hcompress -e filename\" or \"hcompress -d filename.huf\"\n");
        fflush(stdout);
        exit(1);
    }
    LinkedList* freqTable = generateFreqTable("decind.txt");
    llFreqDisplay(freqTable);
    tnode* treeRoot = createHuffmanTree(freqTable);

    if (strcmp(argv[2], "-e") == 0) {
        encodeFile(argv[3], treeRoot, freqTable);

    }
    else {
        decodeFile(argv[3], freqTable->nodeV);
    }
       freeTree(treeRoot);      
       freeLinkedList(freqTable);
    return 0;
}

void freeTree(tnode* root) {
    if (root->c < 0) {
        freeTree(root->right);
        freeTree(root->left);
        free(root);
    }
}

void freeLinkedList(LinkedList* top) {
    LinkedList* temp = top;
    int size = llSize(top);
    for (int i = 0; i < size ; i++) {
        LinkedList* t = temp->next;
        llRemoveTop(&temp);
        temp = t;
    }
}

LinkedList* generateFreqTable(char* file) {

    FILE* stream;
    stream = fopen(file, "r");

    int size = 129;
    int charArray[129];
    for (int i = 0; i < size; i++) {
        charArray[i] = 0;
    }
    char c;
    LinkedList* xxx = llCreate();

    while ((c = fgetc(stream)) != EOF) {
        charArray[(int)c]++;
    }
    LinkedList* leafNodes = llCreate();
    rewind(stream);
    int count = 0;
   
    while ((c = fgetc(stream)) != EOF) {
        if (charArray[(int)c] > 0) {
            tnode* tn = (tnode*)malloc(sizeof(tnode));
            if (tn) {
                tn->c = (int)c;
                tn->weight = charArray[(int)c];
            }
          
            lladdInOrder(&leafNodes, tn);
            count++;
            charArray[(int)c] = 0;
        }
    }
    fclose(stream);
    return leafNodes;
}

tnode* createHuffmanTree(LinkedList* leafNodes) {
    tnode* root = NULL;
    LinkedList* newLL = NULL;
    LinkedList* head = leafNodes;
    while (leafNodes != NULL) {
        lladdInOrder(&newLL, leafNodes->nodeV);
        leafNodes = leafNodes->next;
    }


    while (llSize(newLL) > 1) {
        LinkedList* currentL = newLL;

            root = (tnode*)malloc(sizeof(tnode));
            root->parent = NULL;
            root->c = -1;
            currentL->nodeV->parent = root;
            root->left = currentL->nodeV;
            currentL = currentL->next;
            currentL->nodeV->parent = root;
            root->right = currentL->nodeV;
            root->weight = (root->left)->weight + (root->right)->weight;
            llRemoveTop(&newLL);
            llRemoveTop(&newLL);
            lladdInOrder(&newLL, root);
        
       
    }
    return root;
}

void encodeFile(char* file, tnode* treeRoot, LinkedList* leafNodes) {

    FILE* fileOpen;
    fileOpen = fopen("decind.txt", "r");
    if (fileOpen == NULL) {
        printf("\t\t EMPTY OR NOT EXISTING FILE \n");
        return;
    }
    char buffer;
    FILE* fileWrite;
    fileWrite = fopen("decind.huf", "w");
    int switchC = 0;
    int cursor = 0;
    unsigned char myByte = 0;
    
    int cPathLength = 0;
    
    while (fscanf(fileOpen, "%c", &buffer) != EOF) {
        LinkedList* foundLeaf = list_search(leafNodes,(int) buffer);
        tnode* found = foundLeaf->nodeV;
        char pathToLeaf[127];
        cPathLength = 0;
        while (found->parent != NULL) {
            if (found->parent->left == found) {
                
                pathToLeaf[cPathLength] = '0';
            }
            else {
                pathToLeaf[cPathLength] = '1';
            }
            found = found->parent;
            cPathLength++;
        }
        for (int i = cPathLength-1; i >= 0; i--)
      {
            if (pathToLeaf[i]=='0') {
                myByte = (myByte * 2);
        }
            else {
                myByte = (myByte * 2) + 1;
            }
            cursor++;
            if (cursor==8) {
    fputc(myByte, fileWrite);
    cursor = 0;
    myByte = 0;
            }
      }
       
    }
    fputc(myByte, fileWrite);
    fclose(fileOpen);
    fclose(fileWrite);
}

void decodeFile(char* file, tnode* tree) {
    FILE* f = fopen("decind.huf", "rb");
    FILE* fwrite = fopen("decind.huf.dec", "w");
    if (f == NULL) {
        printf("\t\t EMPTY OR NOT EXISTING FILE \n");
        return;
    }
    while (tree->parent != NULL) {
        tree = tree->parent;
    }

    tnode* rootToLeaf = tree;
    char cToBin[9];
    cToBin[8] = '\0';
    fseek(f, 0, SEEK_END);
    long fsize = ftell(f);
    fseek(f, 0, SEEK_SET);
    unsigned char* fileContent = malloc(fsize + 1);
    if (fsize>0) {
        fread(fileContent, 1, fsize, f);

    }
    fclose(f);
    int fileEnd = 0;
    for (int i = 0; i < fsize; i++) {
        strncpy(cToBin, getBinSet(2, (int)(*(fileContent + i))), 8);
        for (int j = 0; j < 8; j++) {
            if (i>=fsize-1 && fileEnd == 0) {
                while (cToBin[j]=='0') {
                    j++;
                }
                fileEnd = 1;
            }
            if (rootToLeaf->c < 0) {
                if (cToBin[j] == '0') {
                    rootToLeaf = rootToLeaf->left;
                }
                else if (cToBin[j] == '1') {
                    rootToLeaf = rootToLeaf->right;
                }
            }
            if (rootToLeaf->c > 0) {
                unsigned char x = (unsigned char)rootToLeaf->c;
                putc(x, fwrite);
                rootToLeaf = tree;
            }
        }
    }
    fclose(fwrite);
    free(fileContent);
}



void findNodeAddress(tnode* root, int c) {
    if (root != NULL) {
        if (root->c < 0) {
            findNodeAddress(root->left, c);
            findNodeAddress(root->right, c);
        }
        if (root->c == c) {
            found = root;
            return;
        }
    }
}
int leafToRoot(tnode* leaf, char* binA, int count) {
    if (leaf == NULL) {
        return -1;
    }
    char temp = leaf->c;

    printf("Node %c Path:", temp);
    while (leaf->parent != NULL) {
        if (leaf->parent->left == leaf) {
            *(binA + count) = '0';
        }
        else {
            *(binA + count) = '1';
        }
        printf("%c ", *(binA + count));
        leaf = leaf->parent;
        count++;
    }
    printf("\n");
    return count;
}
char* getBinSet(int base, int dec) {
    int i = 0;
    int remainder = 0;
    char* temp = malloc(8 * sizeof(char));
    while (dec != 0) {
        remainder = dec % base;
        if (remainder > 9) {
            temp[i] = getChar(remainder);
        }
        else {
            temp[i] = (char)(remainder + '0');
        }
        dec = dec / base;
        i++;
    }
    int l = i;

    while (l < 8) {

            temp[l] = '0';         
        l++;
    }
    for (int low = 0, high = 8 - 1; low < high; low++, high--)
    {
        int tc = temp[low];
        temp[low] = temp[high];
        temp[high] = tc;
    }
    return temp;
}
char getChar(int n)
{
    char c = '0';
    while ((n = getchar()) != '\n' && n != EOF) {
        c = n - '0';
    }
    return c;
}
void printBits(size_t const size, void const* const ptr)
{
    unsigned char* b = (unsigned char*)ptr;
    unsigned char byte;
    int i, j;

    for (i = size - 1; i >= 0; i--) {
        for (j = 7; j >= 0; j--) {
            byte = (b[i] >> j) & 1;
            printf("%u", byte);
        }
    }
    puts("");
}