#include <iostream>
#include <thread>
int primeByRange(int start, int end);
bool primeNumberFinder(int x);
void myRun( int start, int stop,int threadN, int* threadPrimeCount) {
    threadPrimeCount[threadN] = primeByRange(start, stop);
}
 bool primeNumberFinder(int x) {
    bool isPrime = true;
    int i = 2;
    while (isPrime && i < x) {
        if (x % i == 0) {
            isPrime = false;
            i = x + 1;
        }
        i++;
    }
    return isPrime;
}
 int primeByRange(int start, int end) {
     int totalCount = 0;
     const int size = end - start + 1;
    int* arr = new int[size];
    int index = 0;
    for (int i = start; i <= end; i++) {
        arr[index] = i;
        index++;
    }
    for (int i = 1; i < size; i++) {
        if (primeNumberFinder(arr[i])) {
            totalCount++;
        }
    }
    delete[] arr;
    return totalCount;
}
int main() {
    int totalCount = 0;
    int rangeStart = 1000;    //1000->1000000
    int rangeEnd = 1000000;
    int numThreads = 4;
    int* primesCount = (int*)malloc(numThreads * sizeof(int));
    std::thread* ths[numThreads];

    for (int i = 0; i < numThreads; i++) {
        int start = (((rangeEnd - rangeStart) / numThreads) * i) + rangeStart;
        int stop = (((rangeEnd - rangeStart) / numThreads) * (i + 1)) + rangeStart;
        std::thread* th = new std::thread(myRun,start, stop, i, primesCount);
        ths[i] = th;
    }
    for (int i = 0; i < numThreads; i++) {
        ths[i]->join();
    }

    for (int i = 0; i < numThreads; i++) {
        totalCount += primesCount[i];
    }
    std::cout << "Total count: " << totalCount<< "\n";
}
