#ifndef CAT_H
#define CAT_H
#include <iostream>
#include <string>
#include "Animal.h"


class Cat : public Animal{
 protected:
  std::string name;
 public:
  Cat();
  Cat(std::string name, std::string color);
  Cat(const Cat& c);
  virtual ~Cat();
  std::string getName();
  std::string getColor();
  void makeSound();
};
#endif

