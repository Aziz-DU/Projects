#include <iostream>
int primeByRange(int start, int end);
bool primeNumberFinder(int x);
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
    int rangeStart = 10;    //10->1000
    int rangeEnd = 1000;
    int totalCount=primeByRange(rangeStart,rangeEnd);
    std::cout << "Total count: " << totalCount<< "\n";
}
