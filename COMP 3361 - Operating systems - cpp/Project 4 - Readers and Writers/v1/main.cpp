///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 3361: Operating Systems
//Project info: Project 4 - Readers and Writers -V1
///////////////////////////////////////////////////
#include <iostream>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

using namespace std;

//shared variables
int sharedVariable = 7;
int readersCount = 0;
sem_t writeS, mutex;

void *readers(void *sv)
{
  const int NUM_READS = 3;
  int *var = static_cast<int *>(sv);
  for (int i = 0; i < NUM_READS; i++)
  {
    int rt = (rand() % 10 + 1) * 10;
    usleep(rt);

    sem_wait(&mutex);
    if (readersCount == 0)sem_wait(&writeS);
    readersCount += 1;
    sem_post(&mutex);

    printf("Reader %d read %d,  %d reader(s)\n", *var, sharedVariable, readersCount); //printing with printf due to it not being inside a mutex, cout mixes lines with threads 

    sem_wait(&mutex);  
    if (--readersCount == 0)sem_post(&writeS);
    sem_post(&mutex);
  }

  pthread_exit(NULL);
}
void *writers(void *sv)
{
  const int NUM_WRITES = 2;
  int *var = static_cast<int *>(sv);
  for (int i = 0; i < NUM_WRITES; i++)
  {
    int rt = (rand() % 10 + 1) * 10;
    usleep(rt);
    sem_wait(&writeS);
    sharedVariable = rand() % 100;
    cout << "Writer " << *var << " wrote " << sharedVariable << ", " << readersCount << " reader(s)" << endl;
    sem_post(&writeS);
  }
  pthread_exit(NULL);
}

int main()
{
  const int NUM_READERS = 3;
  const int NUM_WRITERS = 2;
  pthread_t readersT[NUM_READERS];
  pthread_t writersT[NUM_WRITERS];
  int threadIDW[NUM_WRITERS], threadIDR[NUM_READERS];
  sem_init(&writeS, 0, 1);
  sem_init(&mutex, 0, 1);

  for (int i = 0; i < NUM_READERS; i++)
  {
    threadIDR[i] = i + 1;
    int status = pthread_create(&readersT[i], NULL, readers, (void *)(threadIDR + i));
    if (status != 0)cout << "Unable to create readers thread " << i << '\n';
  }
  for (int i = 0; i < NUM_WRITERS; i++)
  {
    threadIDW[i] = i + 1;
    int status = pthread_create(&writersT[i], NULL, writers, (void *)(threadIDW + i));
    if (status != 0)cout << "Unable to create writers thread " << i << '\n';
  }

  for (int i = 0; i < NUM_READERS; i++)
  {
    pthread_join(readersT[i], NULL);
  }
  for (int i = 0; i < NUM_WRITERS; i++)
  {
    pthread_join(writersT[i], NULL);
  }

  sem_destroy(&writeS);
  sem_destroy(&mutex);
  return 0;
}