#include <iostream>
#include <string>
#include "Animal.h"
#include "Dog.h"
#include "Cat.h"
#include "Duck.h"

using namespace std;
int main(){
	cout << "Doggies: \n";
	cout << "-\n";
	Dog* d1 = new Dog((string)"Doggo", (string)"Blue");
	Dog* d2 = new Dog((string)"The other one...", (string)"Some color...");
	cout <<"Name: "<< d1->getName() << "\n";
	cout << "Sound: ";
	d1->makeSound();
	cout << "Color: " << d1->getColor() << "\n\n";
	cout << "Name: " << d2->getName() << "\n";
	cout << "Sound: ";
	d2->makeSound();
	cout << "Color: " << d2->getColor() << "\n";
	cout << "-\n\n";


	cout << "Catties: \n";
	cout << "-\n";
	Cat* cat = new Cat((string)"Evie", (string)"Brown");
	cout << "Name: " << cat->getName() << "\n";
	cout << "Sound: ";
	cat->makeSound();
	cout << "Color: " << cat->getColor() << "\n";
	cout << "-\n\n";


	cout << "Duckies?: \n";
	cout << "-\n";
   Duck* duck = new Duck("black");
   cout << "Sound: ";
   duck->makeSound();
   cout << duck->getColor() << "\n";
   cout << "-\n\n";

   cout << "-\n";
   cout << "Animals cast as the generic\n";
   Animal* a2 = new Cat("Whiskers", "tabby");
   cout << "Sound: ";
   a2->makeSound();
   cout << "-\n\n";

   delete d1;
   delete d2;
   delete cat;
   delete duck;
   delete a2;
}

