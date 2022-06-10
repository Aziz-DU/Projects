///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 3361: Operating Systems
//Project info:  Project 5 - Scheduling Simulation 
///////////////////////////////////////////////////
#include "RR_Sim.h"
#include <fstream>
#include <string>
#include <deque>
#include <algorithm> 
using namespace std;

Process::Process(string pName, int pArrivalT, int pRunT, int burstT)
{
	processName = pName;
	processArrivalTime = pArrivalT;
	processTotalRunTime = pRunT;
	processBurst = burstT;
	processNextTurn = pArrivalT;
	timeRemaining = pRunT;
	originalBurst = burstT;
}
Process::~Process() {}
string Process::getName() { return processName; }
int Process::getArrival() { return processArrivalTime; }
int Process::getRunT() { return processTotalRunTime; }
int Process::getBurst() { return processBurst; }
int Process::getNextTurn() { return processNextTurn; }
void Process::setNextTurn(int nextT) { processNextTurn = nextT; }
int Process::getTAT() { return turnAroundTime; }
void Process::setTAT(int tat) { turnAroundTime = tat; }
int Process::getTimeRemaining() { return timeRemaining; }
void Process::updateTimeRemaining(int t) { timeRemaining -= t; }
void Process::setBurst(int burst) { processBurst = burst; }
void Process::resetBurst() { processBurst = originalBurst; }
RR_Scheduler::RR_Scheduler(deque<Process> p, int b, int q)
{
	listOfProcesses = p;
	blockDuration = b;
	quantum = q;
}
RR_Scheduler::~RR_Scheduler() {}
void RR_Scheduler::run()
{
	int time = 0;
	int excessTime = 0;
	char currentStatus;
	int currentTurnDuration;
	deque<Process> ready;
	deque<Process> blocked;
	deque<Process> terminated;
	for (int i = 0; i < listOfProcesses.size(); i++)
	{
		if (listOfProcesses[i].getArrival() <= time)
		{
			ready.push_back(listOfProcesses[i]);
		}
		else
		{
			blocked.push_back(listOfProcesses[i]);
		}
	}
	if (processesWaiting(&ready, &blocked, time + quantum, ready.front()) && ready.front().getBurst() > quantum)
	{
		currentStatus = 'Q';
		ready.front().setBurst(ready.front().getBurst() - quantum);
		currentTurnDuration = quantum;
		ready.front().setNextTurn(time + quantum);
	}
	else
	{
		currentStatus = 'B';
		currentTurnDuration = ready.front().getBurst();
		ready.front().setNextTurn(time + ready.front().getBurst());
	}
	ready.front().updateTimeRemaining(currentTurnDuration);
	int nextTurn = currentTurnDuration;
	cout << time << "\t" << ready.front().getName() << "\t" << currentTurnDuration << "\t" << currentStatus << endl;

	while ((!ready.empty() || !blocked.empty()))
	{

		time += 5;
		if (time % nextTurn == 0)
		{
			if (currentStatus == 'B')
			{

				ready.front().setNextTurn(time + blockDuration);
				ready.front().resetBurst();
				blocked.push_back(ready.front());
				ready.pop_front();
			}
			else if (currentStatus == 'Q')
			{
				ready.front().setNextTurn(time + quantum);
				insertReadyProcesses(&blocked, &ready, time);
				sort(ready.begin(), ready.end(), sortProcesses);
			}
			insertReadyProcesses(&blocked, &ready, time);

			if (ready.empty())
			{
				currentTurnDuration = nextAvailableProcess(&blocked) - time;
				currentStatus = 'I';
				cout << time << "\t"<< "[IDLE]"<< "\t" << currentTurnDuration << "\t" << currentStatus << endl;
			}
			else
			{
				string n = ready.front().getName();
				if (ready.front().getBurst() > quantum && processesWaiting(&ready, &blocked, time + quantum, ready.front())) 
				{
					currentStatus = 'Q';
					ready.front().setBurst(ready.front().getBurst() - quantum);
					currentTurnDuration = quantum;
					ready.front().setNextTurn(time + quantum);
					
				}
				else
				{
					currentTurnDuration = ready.front().getBurst();
					currentStatus = 'B';
					ready.front().setNextTurn(time + ready.front().getBurst());
				}
				ready.front().updateTimeRemaining(currentTurnDuration);
				if (ready.front().getTimeRemaining() <= 0)
				{
					currentStatus = 'T';
					if (terminated.size() == 2)
					{
						excessTime = ready.front().getBurst();
					}
					ready.front().setTAT((ready.front().getBurst() + time) - ready.front().getArrival());
					terminated.push_back(ready.front());
					ready.pop_front();
				}
				cout << time << "\t" << n << "\t" << currentTurnDuration << "\t" << currentStatus << endl;
			}
			nextTurn = currentTurnDuration + time;
		}
	}
	cout << time + excessTime << "\t[END]" << endl;
	int size = terminated.size();
	double averegeTAT = 0;
	for (int i = 0; i < size; i++)
	{
		averegeTAT += terminated.front().getTAT();
		cout << terminated.front().getName() << "\t" << terminated.front().getTAT() << endl;
		terminated.pop_front();
	}
	cout << averegeTAT / size << endl;
}
int RR_Scheduler::nextAvailableProcess(std::deque<Process> *listOfProcesses)
{
	int closestTurn = listOfProcesses->at(0).getNextTurn();
	for (int i = 0; i < listOfProcesses->size(); i++)
	{
		if (listOfProcesses->at(i).getNextTurn() < closestTurn)
		{
			closestTurn = listOfProcesses->at(i).getNextTurn();
		}
	}
	return closestTurn;
}
void RR_Scheduler::insertReadyProcesses(std::deque<Process> *blocked, std::deque<Process> *ready, int time)
{
	int index = 0;
	int size=blocked->size();
	for (int i = 0; i < size; i++)
	{
		if (blocked->front().getNextTurn() <= time)
		{
			ready->push_back(blocked->front());
			blocked->pop_front();
		}
		else
		{
			blocked->push_back(blocked->front());
			blocked->pop_front();
		}
	}
}
bool RR_Scheduler::sortProcesses(Process i, Process j) { return (i.getNextTurn() < j.getNextTurn()); }
bool RR_Scheduler::processesWaiting(std::deque<Process> *blocked, std::deque<Process> *ready, int tq, Process cur)
{
	bool waiting = false;
	for (int i = 0; i < ready->size(); i++)
	{
		if (ready->at(i).getRunT() != cur.getRunT())
		{
			if (ready->at(i).getNextTurn() <= tq)
			{
				waiting = true;
			}
		}
	}
	for (int i = 0; i < blocked->size(); i++)
	{
		if (blocked->at(i).getRunT() != cur.getRunT())
		{
			if (blocked->at(i).getNextTurn() <= tq)
			{
				waiting = true;
			}
		}
	}
	return waiting;
}

int main(int argc, char *argv[])
{
	if (argc == 4)
	{
		deque<Process> processes;
		string fileN = argv[1];
		ifstream inputFile(fileN);
		if (!inputFile.is_open())
		{
			string errorMsg = "Could not open " + fileN;
			throw std::runtime_error(errorMsg);
		}
		string currentLine;
		int index = 0;
		while (getline(inputFile, currentLine))
		{
			string tempS[4];
			string curS;
			int pos = 0;
			for (int i = 0; i < currentLine.length(); i++)
			{
				if (currentLine[i] != ' ')
				{
					curS += currentLine[i];
				}
				if (currentLine[i] == ' ' || i == currentLine.length() - 1)
				{
					tempS[pos] = curS;
					pos++;
					curS = "";
				}
			}
			Process temp(tempS[0], stoi(tempS[1]), stoi(tempS[2]), stoi(tempS[3]));
			processes.push_back(temp);
			index++;
		}
		inputFile.close();
		RR_Scheduler sched(processes,atoi(argv[2]),atoi(argv[3]));
		sched.run();
	}
	else
	{
		cerr << "You need 3 arguements \n";
	}
	return 0;
}