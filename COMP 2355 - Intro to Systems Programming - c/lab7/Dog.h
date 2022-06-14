#ifndef DOG_H
#define DOG_H
#include <iostream>
#include <string>
#include "Animal.h"

class Dog : public Animal{
 protected:
  std::string  name;
 public:
  Dog();
  Dog(std::string name, std::string color);
  Dog(const Dog& d);
  virtual ~Dog();
  std::string getColor();
  void makeSound();
  std::string getName();
};
#endif

