///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 3361: Operating Systems
//Project info:  Project 2 - HexHistogram
///////////////////////////////////////////////////
#include "HexHistogram.h"
#include <iostream>
#include <fstream>
#include <map>
#include <iomanip>
using namespace std;

Hhistogram::Hhistogram(string fileName)
{
	ifstream inputFile(fileName);
	if (!inputFile.is_open())
	{
		string errorMsg = "Could not open " + fileName;
		throw std::runtime_error(errorMsg);
	}
	string currentLine;
	while (getline(inputFile, currentLine))
	{
		stringstream currentV;
		string tempS;
		uint32_t tempI;
		map<uint32_t, string> tempM;
		for (int i = 0; i < currentLine.length(); i++)
		{
			if (currentLine[i] != ' ')
			{
				tempS += currentLine[i];
				tempI = 0;
			}

			if ((currentLine[i] == ' ' || i == currentLine.length() - 1) && (tempI != -7)) //once we get to none space char, tempI changes from -7 making it possible to..
			{																			   //.. re-enter the if statement when we get to the other space
				currentV << hex << tempS;
				currentV >> tempI;
				if (histoMap.count(tempI) > 0)
				{
					histoMap[tempI]++;
				}
				else
				{
					histoMap.insert(pair<uint32_t, uint32_t>(tempI, 1));
					uniqueCount++;
				}
				tempM.insert(pair<uint32_t, string>(tempI, tempS));
				tempS = "";
				tempI = -7;
				currentV.str("");
				currentV.clear();
				valueCount++;
			}
		}
		lineCount++;
		smallestPerLineMap.insert(pair<int, string>(lineCount, tempM.begin()->second));
	}
	inputFile.close();
}
Hhistogram::~Hhistogram() {}
uint32_t Hhistogram::get_value_count() { return valueCount; }
uint32_t Hhistogram::get_unique_value_count() { return uniqueCount; }
uint32_t Hhistogram::get_line_count() { return lineCount; }
uint32_t Hhistogram::get_smallest_number(uint32_t lineNumber)
{
	if (lineNumber < 1 || lineNumber > lineCount)
	{
		throw std::logic_error("Given line number is invalid");
	}
	stringstream currentV;
	currentV << hex << smallestPerLineMap[lineNumber];
	uint32_t value;
	currentV >> value;
	return value;
}

void Hhistogram::print()
{
	cout << "\nHistogram " << "\n";
	for (const auto &p : histoMap)
	{
		stringstream currentV;
		currentV << hex <<  p.first;
		cout << right << setw(8) << currentV.str() << ":" << setw(8) << p.second << "\n";
	}
}
