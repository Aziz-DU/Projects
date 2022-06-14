#include <iostream>
#include <string>
#include "Duck.h"
using namespace std;
Duck::Duck() : Animal(){
}

Duck::Duck(string color) : Animal(color){
   this->color = color;
   sound = "Quack quack\n";
}

Duck::Duck(const Duck& d) : Animal(d){
   this->color = d.color;
   this->sound = d.sound;
}

Duck::~Duck(){
cout << "Destructor for Duck\n";
}

string Duck::getColor(){
   return color;
}

void Duck::makeSound(){
cout << "*Duck lango*\n";
}

