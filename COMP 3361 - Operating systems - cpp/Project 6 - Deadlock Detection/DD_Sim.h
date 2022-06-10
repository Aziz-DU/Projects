// DD_Sim.h Aziz alkhelaiwi project6
#ifndef DDSIMULATION_H
#define DDSIMULATION_H
#include <iostream>
#include <vector>

struct process{
	int processNumber;
	std::vector<int> currentAllocation,requestMatrix;
};
class DeadlockDetector
{
private:
	std::vector<process> processes;
	std::vector<int> currentResources;
	std::vector<int> totalResources;
	int numberOfR,numberOfP;
	int findNext();
	void markProcess(int processNumber);
public:
	DeadlockDetector(std::string fileName);
	~DeadlockDetector();
	void run();
};
#endif
