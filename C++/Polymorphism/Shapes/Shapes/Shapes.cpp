// Shapes.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

using namespace std;
class Shape {
public:
	virtual void draw() = 0;
	Shape()
	{
		cout << "constructor of base class shape\n";
		//draw();
	}
	/*virtual void draw()
	{
		cout << "draw of base class shape";
	}*/
};

class circle : public Shape {
public:
	void draw() 
	{
		cout << "draw of derived class Circle \n";
	}
};

class Square : public Shape {
public:
	void draw()
	{
		cout << "draw of derived class Square \n";
	}
};

class Triangle : public Shape {
public:
	void draw()
	{
		cout << "draw of derived class triangle \n";
	}
};

void tellShape(Shape& obj)
{
	obj.draw();
}
int main()
{
	Shape* ptr[] = { new Triangle,  new circle,  new Square };
	//ptr[0]->draw();
	ptr[1]->draw();
	ptr[2]->draw();
	//Shape obj = new Shape;

	Triangle t;
	tellShape(t);
	getchar();
    return 0;
}

