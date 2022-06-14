#include <iostream>
#include <string>
#include "Dog.h"
using namespace std;
Dog::Dog() : Animal(){
}

Dog::Dog(string name, string color) : Animal(color){
   this->name = name;
   this->color = color;
   sound = "*Dog lango*";
}

Dog::Dog(const Dog& d) : Animal(d){
   this->name = d.name;
   this->color = d.color;
   this->sound = d.sound;
}

Dog::~Dog(){
   cout << "Destructor for Dog\n";
}
string Dog::getName() {
	return name;
}
string Dog::getColor(){
   return color;
}
void Dog::makeSound(){
   cout << sound << "\n";
}


