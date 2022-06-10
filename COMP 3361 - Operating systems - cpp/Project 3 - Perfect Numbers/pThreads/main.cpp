///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 3361: Operating Systems
//Project info:  Project 3 - Perfect Numbers - pThreads
///////////////////////////////////////////////////
#include <iostream>
#include <pthread.h>

using namespace std;

//shared variables
uint64_t factors[100];
int factorsIndex = 0;
uint64_t chosenNumber;

pthread_mutex_t lock;

struct thread_args
{
  uint64_t start;
  uint64_t stop;
};

void *startingRoutine(void *tArgs)
{
  thread_args *args = static_cast<thread_args *>(tArgs);
  for (uint64_t i = args->start; i <= args->stop; i++)
  {
    if (chosenNumber % i == 0 && chosenNumber != i)
    {
      pthread_mutex_lock(&lock);
      factors[factorsIndex++] = i;
      pthread_mutex_unlock(&lock);
    }
  }
  pthread_exit(NULL);
}

int main()
{
  int num_threads;
  cout << "Enter number: ";
  cin >> chosenNumber;
  cout << "Number of threads: ";
  cin >> num_threads;

  pthread_t threads[num_threads];
  thread_args args[num_threads];
  for (int i = 0; i < num_threads; i++)
  {
    args[i].start = (chosenNumber / num_threads) * i + 1;
    args[i].stop = (chosenNumber / num_threads) * (i + 1);
    int status = pthread_create(&threads[i], NULL, startingRoutine, &args[i]);

  if (status != 0) cout << "Unable to create thread " << i << '\n';
  }
  for (int i = 0; i < num_threads; i++)
  {
    pthread_join(threads[i], NULL);
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
  pthread_mutex_destroy(&lock);
  return 0;
}