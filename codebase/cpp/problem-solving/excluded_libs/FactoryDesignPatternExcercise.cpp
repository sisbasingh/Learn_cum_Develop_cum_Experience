/*
 * FactoryDesignPatternExcercise.cpp
 *
 *  Created on: Mar 4, 2018
 *      Author: sisba01
 */
#include <string>
#include <iostream>

using namespace std;

struct Person
{
  int id;
  string name;

  friend ostream& operator<<(ostream& os, const Person& obj)
    {
      os<<"Person Id: " << obj.id << " Person Name: " << obj.name<<endl;
      return os;
    }
};

class PersonFactory
{
private:
	static int person_id;
public:
  Person create_person(const string& name)
  {
    return {person_id++, name};
  }
};

int PersonFactory::person_id;

int main()
{
	PersonFactory pf ;
	cout<< pf.create_person("ram");
	cout<< pf.create_person("shyam");
	cout<< pf.create_person("hari");

	return 0;
}



