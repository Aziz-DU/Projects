///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 3361: Operating Systems
//Project info: Project 4 - Readers and Writers -V2
///////////////////////////////////////////////////
#include <iostream>
#include <pthread.h>
#include <unistd.h>

using namespace std;

//shared variables
int sharedVariable = 7;
int readersCount, writerssCount = 0;
int readersWaiting, writersWaiting = 0;

pthread_cond_t canWrite, canRead;
pthread_mutex_t mutex;

void *readers(void *sv)
{
  const int NUM_READS = 3;
  int *var = static_cast<int *>(sv);
  for (int i = 0; i < NUM_READS; i++)
  {
    int rt = (rand() % 10 + 1) * 10;
    usleep(rt);
    pthread_mutex_lock(&mutex);
    if (writerssCount == 1 || writersWaiting > 0)
    {
      readersWaiting++;
      pthread_cond_wait(&canRead, &mutex);
      readersWaiting--;
    }
    readersCount += 1;
    printf("Reader %d read %d,  %d reader(s), %d reader(s) waiting, %d writer(s) waiting.. \n", *var, sharedVariable, readersCount, readersWaiting, writersWaiting); 
    pthread_mutex_unlock(&mutex);                     

    pthread_cond_broadcast(&canRead);

    pthread_mutex_lock(&mutex);
    if (--readersCount == 0)pthread_cond_signal(&canWrite);
    pthread_mutex_unlock(&mutex);
  }
  pthread_exit(NULL);
}
void *writers(void *sv)
{
  const int NUM_WRITES = 3;
  int *var = static_cast<int *>(sv);
  for (int i = 0; i < NUM_WRITES; i++)
  {
    int rt = (rand() % 10 + 1) * 10;
    usleep(rt);
    pthread_mutex_lock(&mutex);
    if (writerssCount == 1 || readersCount > 0)
    {
      ++writersWaiting;
      pthread_cond_wait(&canWrite, &mutex);
      --writersWaiting;
    }
    writerssCount = 1;
    sharedVariable = rand() % 100;
    cout << "Writer " << *var << " wrote " << sharedVariable << ", " << readersCount << " reader(s)," << readersWaiting << " reader(s) waiting," << writersWaiting << " writer(s) waiting.." << endl;
    writerssCount = 0;

    if (readersWaiting > 0)pthread_cond_signal(&canRead);
    else pthread_cond_signal(&canWrite);
    pthread_mutex_unlock(&mutex);
  }
  pthread_exit(NULL);
}

int main()
{
  const int NUM_READERS = 5;
  const int NUM_WRITERS = 2;
  pthread_t readersT[NUM_READERS];
  pthread_t writersT[NUM_WRITERS];
  int threadIDW[NUM_WRITERS], threadIDR[NUM_READERS];
  pthread_cond_init(&canWrite, NULL);
  pthread_cond_init(&canRead, NULL);

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
  pthread_mutex_destroy(&mutex);
  pthread_cond_destroy(&canWrite);
  pthread_cond_destroy(&canRead);
  return 0;
}