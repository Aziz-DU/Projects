///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 3361: Operating Systems
//Project info: Project 7 - Memory Management with Lists
///////////////////////////////////////////////////

#include "MM_Sim.h"
#include <fstream>
#include <string>
#include <list>
#include <iterator>
#include <algorithm> 
using namespace std;

MemoryChunk::MemoryChunk(string status, int startingPos, int size)
{
	this->status = status;
	this->startingPos = startingPos;
	this->size = size;
}
MemoryChunk::~MemoryChunk() {}
void MemoryChunk::setStartingPos(int i){startingPos=i;}
void MemoryChunk::setSize(int i){size=i;}
void MemoryChunk::setStatus(string s){status=s;}
int MemoryChunk::getStartingPos(){return startingPos;}
int MemoryChunk::getSize(){return size;}
string MemoryChunk::getStatus(){return status;}
MemoryManager::MemoryManager(string fileName){fileN=fileName;}
MemoryManager::~MemoryManager() {}
void MemoryManager::run() {
	string currentLine;
	string algorithim;
	string initialSize;
	string status;
	string task;
	int reachedSpace = 0;
	ifstream inputFile(fileN);
	if (!inputFile.is_open())
	{
		string errorMsg = "Could not open " + fileN;
		throw std::runtime_error(errorMsg);
	}

	getline(inputFile, currentLine);
	for (int i = 0; i < currentLine.length(); i++)
	{
		if (reachedSpace == 1) initialSize += currentLine[i];
		if (currentLine[i] != ' ' && reachedSpace != 1) algorithim += currentLine[i];
		if (currentLine[i] == ' ') reachedSpace = 1;
	}
	setAlgorithim(algorithim);
	MemoryChunk startingHole("hole", 0, stoi(initialSize));
	mManager.push_back(startingHole);
	cout << "hole: start 0, size "<< stoi(initialSize) << endl;

	while (getline(inputFile, currentLine))
	{
		string tempS = "";
		reachedSpace = 0;
		for (int i = 0; i < currentLine.length(); i++)
		{
			if (currentLine[i] != ' ' || i==currentLine.length()-1) tempS += currentLine[i];
			else if (currentLine[i] == ' ')
			{
				if (reachedSpace == 0) task = tempS;
				else status = tempS;
				reachedSpace += 1;
				tempS = "";
			}
		}
		if (task.compare("load") == 0)
		{
			cout << "\n"<< task <<" "<< status << " " << stoi(tempS) << endl;
			MemoryChunk tempChunk(status, 0, stoi(tempS));			
			addToMemory(tempChunk);
		}
		else
		{
			cout <<"\n"<< task <<" "<< tempS << endl;
			removeFromMemory(tempS);
		}
		printSorted();
	}
	inputFile.close();
}
void MemoryManager::addToMemory(MemoryChunk c) {
if(algorithimType.compare("firstFit")==0) firstFitAdd(c);
else if(algorithimType.compare("bestFit")==0) bestFitAdd(c);
else if(algorithimType.compare("worstFit")==0) worstFitAdd(c);
}
void MemoryManager::firstFitAdd(MemoryChunk c){
list <MemoryChunk> :: iterator it;
for(it = mManager.begin(); it != mManager.end(); ++it){
		if(it->getSize()>=c.getSize()&&it->getStatus().compare("hole")==0){
			c.setStartingPos(it->getStartingPos());
			it->setSize(it->getSize() - c.getSize());
			it->setStartingPos(it->getStartingPos()+c.getSize());
			mManager.push_back(c);
			if(it->getSize()<=0) it=mManager.erase(it);
			break;
		}
		else if((it->getStatus().compare("hole")==0&&it->getSize()<=c.getSize())||next(it)==mManager.end()) cout<<"\nUnable to load process "<<c.getStatus()<<endl;
	}
}
void MemoryManager::bestFitAdd(MemoryChunk c){
list <MemoryChunk> :: iterator it;
list <MemoryChunk> :: iterator currentBest;
bool found=false;
for(it = mManager.begin(); it != mManager.end(); ++it){
		if(it->getSize()>=c.getSize()&&it->getStatus().compare("hole")==0){
			if(!found) currentBest=it; found=true;
			if(it->getSize()<currentBest->getSize()) currentBest=it;
		}
}
		if(found){
			c.setStartingPos(currentBest->getStartingPos());
			currentBest->setSize(currentBest->getSize() - c.getSize());
			currentBest->setStartingPos(currentBest->getStartingPos()+c.getSize());
			mManager.push_back(c);
			if(currentBest->getSize()<=0)currentBest=mManager.erase(currentBest);
		}
		else cout<<"\nUnable to load process "<<c.getStatus()<<endl;
}
void MemoryManager::worstFitAdd(MemoryChunk c){
list <MemoryChunk> :: iterator it;
list <MemoryChunk> :: iterator currentWorst;
bool found=false;
for(it = mManager.begin(); it != mManager.end(); ++it){
		if(it->getSize()>=c.getSize()&&it->getStatus().compare("hole")==0){
			if(!found) currentWorst=it; found=true;
			if(it->getSize()>currentWorst->getSize()) currentWorst=it;
		}
}
		if(found){
			c.setStartingPos(currentWorst->getStartingPos());
			currentWorst->setSize(currentWorst->getSize() - c.getSize());
			currentWorst->setStartingPos(currentWorst->getStartingPos()+c.getSize());
			mManager.push_back(c);
			if(currentWorst->getSize()<=0) currentWorst=mManager.erase(currentWorst);
		}
		else cout<<"\nUnable to load process "<<c.getStatus()<<endl;
}
void MemoryManager::removeFromMemory(string status)
{
	list<MemoryChunk>::iterator it;
	bool makeNew;
	for (it = mManager.begin(); it != mManager.end(); ++it)
	{
		if (it->getStatus().at(0) == status.at(0))
		{
			if (it == mManager.begin())
			{ //it's the first element, don't check previous
				if (next(it)->getStatus().compare("hole") == 0)
				{
					it->setSize(next(it)->getSize() + it->getSize());
					next(it) = mManager.erase(next(it));
					it->setStatus("hole");
				}
				else
				{
					makeNew = true;
				}
			}
			else if (it == prev(mManager.end()))
			{ //it's the last element, don't check next
				if (prev(it)->getStatus().compare("hole") == 0)
				{
					it->setSize(prev(it)->getSize() + it->getSize());
					it->setStartingPos(prev(it)->getStartingPos());
					prev(it) = mManager.erase(prev(it));
					it->setStatus("hole");
				}
				else
				{
					makeNew = true;
				}
			}
			else { //it's neither first nor last, check both directions
				if (next(it)->getStatus().compare("hole") == 0 || (prev(it)->getStatus().compare("hole") == 0))
				{
					if (next(it)->getStatus().compare("hole") == 0)
					{
						it->setSize(it->getSize() + next(it)->getSize());
						next(it) = mManager.erase(next(it));
					}
					if (prev(it)->getStatus().compare("hole") == 0)
					{
						it->setSize(prev(it)->getSize() + it->getSize());
						it->setStartingPos(prev(it)->getStartingPos());
						prev(it) = mManager.erase(prev(it));
					}
					it->setStatus("hole");
				}
				else
				{
					makeNew = true;
				}
			}
			if (makeNew)
			{
				MemoryChunk hole("hole", it->getStartingPos(), it->getSize());
				mManager.push_back(hole);
				it = mManager.erase(it);
			}
			break;
		}
	}
}
void MemoryManager::setAlgorithim(string s){algorithimType=s;}
void MemoryManager::printSorted(){
	mManager.sort([](const MemoryChunk &ai, const MemoryChunk &aj) { 
		MemoryChunk i=ai;
		MemoryChunk j=aj;
		return i.getStartingPos() < j.getStartingPos(); });

	list <MemoryChunk> :: iterator it;
	for(it = mManager.begin(); it != mManager.end(); ++it){
		cout <<it->getStatus()<< ": start " <<it->getStartingPos()<<", size "<< it->getSize() << endl;
	}
}

int main(int argc, char *argv[])
{
	if (argc == 2)
	{
		MemoryManager mm(argv[1]);
		mm.run();
	}
	else
	{
		cerr << "You only need 1 arguement \n";
	}
	return 0;
}