#include <iostream>
#include <string>
#include "Cat.h"
using namespace std;
Cat::Cat() : Animal(){
}

Cat::Cat(string name, string color) : Animal(color){
   this->name = name;
   this->color;
   sound = "*Cat lango*";
}

Cat::Cat(const Cat& c) : Animal(c){
   this->color = c.color;
   this->name = c.name;
   this->sound = c.sound;
}

Cat::~Cat(){
   cout << "Destructor for Cat \n";
}
string Cat::getName() {
	return name;
}
string Cat::getColor(){
   return color;
}


void Cat::makeSound(){
   cout << sound << "\n";
}

