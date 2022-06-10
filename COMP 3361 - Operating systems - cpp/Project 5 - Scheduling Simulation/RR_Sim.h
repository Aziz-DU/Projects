// RR_Sim.h Aziz alkhelaiwi project5
#ifndef RRSIMULATION_H
#define RRSIMULATION_H
#include <iostream>
#include <deque>

class Process
{
private:
	std::string processName;
	int processArrivalTime;
	int processTotalRunTime;
	int processBurst;
	int processNextTurn;
	int timeRemaining;
	int originalBurst;
	int turnAroundTime;
public:
	Process(std::string pName,int pArrivalT,int pRunT,int burstT );
	~Process();
	std::string getName();
	int getArrival();
	int getRunT();
	int getBurst();
	int getNextTurn();
	int getTAT();
	int getTimeRemaining();
	void updateTimeRemaining(int t);
	void setTAT(int tat);
	void setNextTurn(int nextT);
	void setBurst(int burst);
	void resetBurst();
};
class RR_Scheduler
{
private:
	std::deque <Process> listOfProcesses;
	int blockDuration;
	int quantum;
	static bool sortProcesses(Process i, Process j);
	int nextAvailableProcess(std::deque  <Process> *listOfProcesses);
	void insertReadyProcesses(std::deque  <Process>* blocked,std::deque  <Process>* ready, int time);
	bool processesWaiting(std::deque  <Process>* blocked,std::deque  <Process>* ready,int tq,Process cur);
public:
	RR_Scheduler( std::deque  <Process> listOfProcesses,int blockDuration,int quantum);
	~RR_Scheduler();
	void run();
};
#endif
