#ifndef SHAPE_H
#define SHAPE_H
#include <iostream>
#include <string>

class Animal{
 protected:
  std::string  color;
  std::string  sound;
 public:
  Animal();
  Animal(std::string c);
  Animal(const Animal& a);
  virtual ~Animal();
  virtual std::string getColor();
  virtual void makeSound();
};
#endif

