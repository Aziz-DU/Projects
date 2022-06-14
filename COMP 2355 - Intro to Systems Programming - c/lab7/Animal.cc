#include <iostream>
#include <string>
#include "Animal.h"
using namespace std;
Animal::Animal(){
   this->color = "White";
   this->sound = "Sound";
}

Animal::Animal(string color){
   this->color = color;
}

Animal::Animal(const Animal& a){
   this->color = a.color;
   this->sound = a.sound;
}

Animal::~Animal(){
   cout << "Destructor for Animal\n";
}

string Animal::getColor(){
   return color;
}

void Animal::makeSound(){
   cout << "Animal sound meowoof\n";
}

   
