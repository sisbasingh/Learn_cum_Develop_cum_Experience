/*
 * CodeBuilder.cpp
 *
 *  Created on: Mar 3, 2018
 *      Author: sisba01
 */
#include <string>
#include <ostream>
#include <iostream>
#include <vector>
using namespace std;

class CodeBuilder
{
private:
    std::string className;
    std::vector<pair<string, string> > attributes;
public:
  CodeBuilder(const string& class_name)
  {
      className = class_name;
  }

  CodeBuilder& add_field(const string& name, const string& type)
  {
    attributes.push_back(std::make_pair(type, name));
    return *this;
  }

  friend ostream& operator<<(ostream& os, const CodeBuilder& obj)
  {
    os<<"class "<<obj.className<<endl;
    os<<"{"<<endl;
    for(unsigned int i=0; i<obj.attributes.size(); i++) {
    	os<<"  " << obj.attributes[i].first << " " << obj.attributes[i].second << ";" <<endl;
    }
    os<<"};";
    return os;
  }
};

int main() {
  	CodeBuilder cb = CodeBuilder{"Person"}.add_field("name", "string").add_field("age", "int");
	cout << cb;
  }



