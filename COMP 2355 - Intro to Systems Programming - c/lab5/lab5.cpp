#include <iostream>
#include <omp.h>
#include <mutex>

std::mutex screenLock;

int main() {
    int numThreads = 4;
    omp_set_num_threads(numThreads);
    int rangeStart = 0;    //1000->1000000
    int rangeEnd = 400000;
    double  timeT;
    std::cout << "Blocking\n";
    timeT = 0.0;
    int threadN;
    int dividingTask = (rangeEnd - rangeStart) / numThreads;
#pragma omp parallel reduction(+:timeT,totalCount)  
    {
        int threadN = omp_get_thread_num();
        int numT = omp_get_num_threads();
        totalCount = 0;
        double  timeS = omp_get_wtime();
      double timeE = 0.0;
      
#pragma omp for schedule(static, dividingTask)

     for (int i = rangeStart; i < rangeEnd; i++) {
            int j = 2;
            while ((i % j) != 0 && j <= i / 2) {
                j++;
            }
            if ((i % j) != 0) {
                totalCount++;
            }
        }
        timeE = omp_get_wtime();
        timeT = timeE - timeS;
        screenLock.lock();
        std::cout << "time for " << threadN << ": " << timeT << " with " << totalCount << " found\n";
        screenLock.unlock();
    }
       
       
        
    
    std::cout << "overall time: " << timeT << " with " << totalCount << " found\n";

timeT=0.0;
#pragma omp parallel reduction(+:timeT)  
    {
        int threadN = omp_get_thread_num();
        int numT = omp_get_num_threads();
        totalCount = 0;
        int currentCount = totalCount;
        double  timeS = omp_get_wtime();
       double timeE,timeCur = 0.0;
     
      
#pragma omp for schedule(static, 1)

     for (int i = rangeStart; i < rangeEnd; i++) {
            int j = 2;
            while ((i % j) != 0 && j <= i / 2) {
                j++;
            }
            if ((i % j) != 0) {
                currentCount++;
            }
        }
        timeE = omp_get_wtime();
        timeCur = timeE - timeS;
        timeT += timeCur;
        totalCount += currentCount;
        screenLock.lock();
        std::cout << "time for " << threadN << ": " << timeCur << " with " << currentCount << " found\n";
        screenLock.unlock();
    }
           std::cout << "overall time: " << timeT << " with " << totalCount << " found\n";
}
