///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 3361: Operating Systems
//Project info:  Project 3 - Perfect Numbers - cppThreads
///////////////////////////////////////////////////
#include <iostream>
#include <thread>
#include <mutex>
using namespace std;

//shared variables
uint64_t factors[100];
int factorsIndex = 0;

mutex mtx;

void startingRoutine(uint64_t start, uint64_t stop, uint64_t chosenNumber)
{
  for (uint64_t i = start; i <= stop; i++)
  {
    if (chosenNumber % i == 0 && chosenNumber != i)
    {
      mtx.lock();
      factors[factorsIndex++] = i;
      mtx.unlock();
    }
  }
}

int main()
{
  int num_threads;
  uint64_t chosenNumber;
  cout << "Enter number: ";
  cin >> chosenNumber;
  cout << "Number of threads: ";
  cin >> num_threads;
  thread *threads[num_threads];
  
  for (int i = 0; i < num_threads; i++)
  {
    uint64_t start = (chosenNumber / num_threads) * i + 1;
    uint64_t stop = (chosenNumber / num_threads) * (i + 1);
    threads[i] = new thread(startingRoutine, start, stop, chosenNumber);
  }
  for (int i = 0; i < num_threads; i++)
  {
    threads[i]->join();
  }
  uint64_t sum = 0;
  cout << "Number of factors is: " << factorsIndex << endl;
  for (int i = 0; i < factorsIndex; i++)
  {
    sum += factors[i];
    cout << factors[i] << endl;
  }
  if (sum == chosenNumber)
  {
    cout << chosenNumber << " is a perfect number" << endl;
  }
  else
  {
    cout << chosenNumber << " is not a perfect number" << endl;
  }
  return 0;
}