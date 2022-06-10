// MM_Sim.h Aziz alkhelaiwi project7
#ifndef MMSIMULATION_H
#define MMSIMULATION_H
#include <iostream>
#include <list>

class MemoryChunk
{
private:
	std::string status;
	int startingPos,size;
public:
	MemoryChunk(std::string status,int startingIndex,int size);
	~MemoryChunk();
	void setStatus(std::string s);
	void setStartingPos(int i);
	void setSize(int i);
	std::string getStatus();
	int getStartingPos();
	int getSize();
};
class MemoryManager
{
private:
	std::list<MemoryChunk> mManager;
	std::string algorithimType;
	std::string fileN;
public:
	MemoryManager(std::string fileName);
	~MemoryManager();
	void run();
	void addToMemory(MemoryChunk c);
	void firstFitAdd(MemoryChunk c);
	void bestFitAdd(MemoryChunk c);
	void worstFitAdd(MemoryChunk c);
	void removeFromMemory(std::string s);
	void setAlgorithim(std::string s);
	void printSorted();
};
#endif
