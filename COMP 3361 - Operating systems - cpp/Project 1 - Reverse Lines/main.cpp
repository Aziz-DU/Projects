///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 3361: Operating Systems
//Project info:  Project 1 - Reverse Lines
///////////////////////////////////////////////////
#include <iostream>
#include <fstream>
#include <string>
#include <bits/stdc++.h>
using namespace std;

void readToPrint(string fileName);
void readToFile(string fileName, string writeFile);

int main(int argc, char *argv[])
{
  if (argc == 2)readToPrint(argv[1]);
  else if (argc == 3)
  {
    if (strcmp(argv[1], argv[2]) == 0)
    {
      cerr << "Output and input files have the same name \n";
      return 1;
    }
    else
    {
      readToFile(argv[1], argv[2]);
    }
  }
  else 
  {
  cerr << "Number of argument is incorrect \n";
  return 1;
  }
  return 0;
}
void readToPrint(string fileName)
{
  char c;
  ifstream inFile(fileName, ios::ate);
  streampos size = inFile.tellg();
  string currentL;
 if (!inFile.is_open()) {
		throw std::runtime_error("Could not open " + fileName);
	}

    for (int i = 1; i <= size; i++)
    {
      inFile.seekg(-i, ios::end);
      inFile.get(c);
      currentL += c;
      if (c == '\n' || i >= size)
      {
        if (c == '\n')
        {
          currentL.pop_back(); //removed the new line from string and printed the new line below because...
        }
        reverse(currentL.begin(), currentL.end());
        cout << currentL;
        cout << "\n";  //...without this new line print, the first line doesn't get printed which doesn't make sense why.(would love to know the reason)...
        currentL = ""; //... it still goes inside the if statement when it reaches the first line, weird behavior
      }
    }
    inFile.close();
}
void readToFile(string fileName, string writeFile)
{
  char c;
  ifstream inFile(fileName, ios::ate);
  streampos size = inFile.tellg();
  string currentL;
  ofstream outFile(writeFile);
   if (!inFile.is_open()) {
		throw std::runtime_error("Could not open " + fileName);
	}
    for (int i = 1; i <= size; i++)
    {
      inFile.seekg(-i, ios::end);
      inFile.get(c);
      currentL += c;
      if (c == '\n' || i >= size)
      {
        if (c == '\n')
        {
          currentL.pop_back();
        }
        reverse(currentL.begin(), currentL.end());
        outFile << currentL;
        outFile << "\n";
        currentL = "";
      }
    }
    outFile.close();
    inFile.close();
}