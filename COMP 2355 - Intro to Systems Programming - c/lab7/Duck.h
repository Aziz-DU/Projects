#ifndef DUCK_H
#define DUCK_H
#include <iostream>
#include <string>
#include "Animal.h"

class Duck : public Animal{

 public:
  Duck();
  Duck(std::string color);
  Duck(const Duck& d);
  virtual ~Duck();
  std::string getColor();
  void makeSound();
};
#endif

