#include "stdafx.h"

using namespace std;

class Base {

public:
	virtual void f(int a) const
	{
		cout << " Base f int \n";
	}
	virtual void f(int a, int b) const
	{
		cout << " Base f int int \n";
	}
	virtual void f(float a) const
	{
		cout << " Base f float \n";
	}

};

class  Derived : public Base
{
public:
	Derived() : Base() {}

	~ Derived();

	void f(int a) const
	{
		cout << "derived f int \n";
	}

};

int main()
{
	Derived d;// = new Derived;
	d.f(1);
	getchar();
	return 0;
	
}

