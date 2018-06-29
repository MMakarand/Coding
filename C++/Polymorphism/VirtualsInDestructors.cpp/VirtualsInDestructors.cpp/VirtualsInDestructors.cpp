// VirtualsInDestructors.cpp.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
//: C15:VirtualsInDestructors.cpp
// From Thinking in C++, 2nd Edition
// Available at http://www.BruceEckel.com
// (c) Bruce Eckel 2000
// Copyright notice in Copyright.txt
// Virtual calls inside destructors

using namespace std;

class Base {
public:
	int a;
	 ~Base() {
		cout << "Base1()\n";
		
	}
	
};

class Derived : public Base {
public:
	int b;
	~Derived() { cout << "~Derived()\n";  }
	
};

void func(Base b) {
	cout << sizeof(b) << endl;

}

int main() {
	//Base* bp = new Derived; // Upcast

	Derived d;
	cout << sizeof(d) << endl;
	func(d);
	getchar();
} ///:~


