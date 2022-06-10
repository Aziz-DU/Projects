//main.cpp Aziz alkhelaiwi project2
#include <iostream>
#include "HexHistogram.h"
#include <iomanip>
using namespace std;
int main(int argc, char *argv[])
{
  if (argc == 2)
  {
    Hhistogram h1(argv[1]);
    cout << "Number of values read: " << h1.get_value_count() << "\n";
    cout << "Number of unique values read: " << h1.get_unique_value_count() << "\n";
    cout << "Number of lines: " << h1.get_line_count() << "\n";
    cout << "\nSmallest number on each line  "
         << "\n";
    for (int i = h1.get_line_count(); i >= 1; i--)
    {
      stringstream tempS;
      tempS << hex << h1.get_smallest_number(i);
      cout << right << setw(4) << i << ":" << setw(8) << tempS.str() << "\n";
    }
    h1.print();
  }
  else
  {
    cerr << " Usage:  ./Project2 <filename> \n";
  }
  return 0;
}