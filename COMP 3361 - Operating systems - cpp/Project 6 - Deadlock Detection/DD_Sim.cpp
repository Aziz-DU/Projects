///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 3361: Operating Systems
//Project info: Project 6 - Deadlock Detection
///////////////////////////////////////////////////
#include "DD_Sim.h"
#include <fstream>
#include <string>
#include <vector>
using namespace std;

DeadlockDetector::DeadlockDetector(string fileN)
{
	ifstream inputFile(fileN);
	if (!inputFile.is_open())
	{
		string errorMsg = "Could not open " + fileN;
		throw std::runtime_error(errorMsg);
	}
	string currentLine;
	int lineN = 0;
	string tempS;

	getline(inputFile, currentLine);
	tempS = currentLine[0];
	numberOfP = stoi(tempS);
	tempS = currentLine[2]; // index 1 is the space
	numberOfR = stoi(tempS);

	getline(inputFile, currentLine);
	for (int i = 0; i < currentLine.length(); i++)
	{
		if (currentLine[i] != ' ' || i == currentLine.length() - 1)
		{
			tempS = currentLine[i];
			totalResources.push_back(stoi(tempS));
		}
	}
	currentResources = totalResources;

	while (getline(inputFile, currentLine))
	{
		vector<int> tempV;
		process tempP;
		for (int i = 0; i < currentLine.length(); i++)
		{
			if (currentLine[i] != ' ' || i == currentLine.length() - 1)
			{
				tempS = currentLine[i];
				tempV.push_back(stoi(tempS));
			}
		}
		if (lineN <= numberOfP - 1)
		{
			processes.push_back(tempP);
			processes.at(lineN).currentAllocation = tempV;
			processes.at(lineN).processNumber = lineN;
		}
		else processes.at(lineN - numberOfP).requestMatrix = tempV;
		lineN++;
	}
	inputFile.close();
}
DeadlockDetector::~DeadlockDetector() {}
void DeadlockDetector::run()
{
	cout << "Initial Values: " << endl;
	cout << "Number of processes: " << numberOfP << endl;
	cout << "Number of resource types: " << numberOfR << endl;

	cout << "Existing Resource Vector " << endl;
	for (int i = 0; i < numberOfR; i++) cout << totalResources.at(i) << " ";

	cout << "\nCurrent Allocation Matrix" << endl;
	for (int i = 0; i < numberOfP; i++)
	{
		for (int j = 0; j < numberOfR; j++)
		{
			cout <<processes.at(i).currentAllocation.at(j)<<" ";
			currentResources.at(j) -= processes.at(i).currentAllocation.at(j);
		}
		cout << endl;
	}

	cout << "Request Matrix" << endl;
	for (int i = 0; i < numberOfP; i++)
	{
		for (int j = 0; j < numberOfR; j++) cout << processes.at(i).requestMatrix.at(j)<< " ";
		cout << endl;
	}

	cout << "Available Resource Vector" << endl;
	for (int j = 0; j < numberOfR; j++) cout << currentResources.at(j) << " ";
	cout << endl;

	for (int i = 0; i < processes.size(); i++)
	{
		int nextP = findNext();
		if (nextP >= 0)
		{
			cout << "\nProcess " << nextP << " marked" << endl;
			markProcess(nextP);
			cout << "Available Resource Vector" << endl;
			for (int j = 0; j < numberOfR; j++) cout << currentResources.at(j) << " ";
			i = -1; 	//reset i to 0 to check from beginning with the added resources
		}
		else if (nextP < 0 && i == processes.size() - 1)
		{
			cout << "System is deadlocked" << endl;
			cout << "Deadlocked processes:";
			for (int j = 0; j < processes.size(); j++) cout << processes.at(j).processNumber << " ";
		}
		cout << endl;
	}
	if (processes.empty()) cout << "\nSystem is not deadlocked" << endl;
}
int DeadlockDetector::findNext()
{
	vector<int> tempV = currentResources;
	int currP = 0;
	int currR = 0;
	int nextP = -7; //-7 acts like a boolean for not finding a process, if it gets changed then it's returned as the process number
	while (nextP == -7)
	{
		tempV.at(currR) -= processes.at(currP).requestMatrix.at(currR);
		if (tempV.at(currR) < 0)  //if it goes below 0 reset the resource index and go to the next process
		{
			currR = 0;
			tempV = currentResources;
			currP++;
			if (currP >= processes.size()) break;
		}
		if (currR == numberOfR - 1) nextP = processes.at(currP).processNumber;
		currR++;
	}
	return nextP;
}
void DeadlockDetector::markProcess(int currPN)
{
	int size = processes.size();
	for (int i = 0; i < size; i++)
	{
		if (processes.at(i).processNumber == currPN)
		{
			for (int j = 0; j < numberOfR; j++) currentResources.at(j) += processes.at(i).currentAllocation.at(j);
			processes.erase(processes.begin() + i);
			break;
		}
	}
}
int main(int argc, char *argv[])
{
	if (argc == 2)
	{
		DeadlockDetector dd(argv[1]);
		dd.run();
	}
	else
	{
		cerr << "You only need 1 arguement \n";
	}
	return 0;
}