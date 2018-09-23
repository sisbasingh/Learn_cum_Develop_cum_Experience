package com.babu.practice.udemy.design_patterns.creational.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MyClass {
	private String classType, className;
	private List<Field> fields;
	private String lineSeparator = System.lineSeparator();
	private static final String spacer = " ";
	private static final String SEMI_COLON = ";";
	private static final int indentations = 2;
	private static final String indentator = String.join("", Collections.nCopies(indentations, spacer));
	
	public MyClass(String classType, String className, List<Field> fields) {
		super();
		this.classType = classType;
		this.className = className;
		this.fields = fields;
	}
	
	public List<Field> getFields() {
		return fields;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(classType).append(spacer).append("class").append(spacer).append(className).append(lineSeparator);
		sb.append("{").append(lineSeparator);
		
		fields.forEach((field) -> {
			sb.append(indentator).append(field.getFieldType()).append(spacer).append(field.getFieldDataType()).append(spacer)
			.append(field.getFieldName()).append(SEMI_COLON).append(lineSeparator); 
		});
		
		sb.append("}");
		return sb.toString();
	}
	
}

class Field {
	private String fieldName;
	private String fieldDataType;
	private String fieldType; // private/protected/public
	
	public Field(String fieldName, String fieldDataType, String fieldType) {
		super();
		this.fieldName = fieldName;
		this.fieldDataType = fieldDataType;
		this.fieldType = fieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldDataType() {
		return fieldDataType;
	}
	
	public String getFieldType() {
		return fieldType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldDataType == null) ? 0 : fieldDataType.hashCode());
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((fieldType == null) ? 0 : fieldType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (fieldDataType == null) {
			if (other.fieldDataType != null)
				return false;
		} else if (!fieldDataType.equals(other.fieldDataType))
			return false;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		if (fieldType == null) {
			if (other.fieldType != null)
				return false;
		} else if (!fieldType.equals(other.fieldType))
			return false;
		return true;
	}
}

public class CodeBuilder
{
	private static final String DEFAULT_TYPE = "public";
	private MyClass mClass;
	
    public CodeBuilder(String className)
    {
    	mClass = new MyClass(DEFAULT_TYPE, className, new ArrayList<Field>());
    }
    
    public CodeBuilder addField(String name, String type)
    {
    	this.mClass.getFields().add(new Field(name, type, DEFAULT_TYPE));
    	return this;
    }
    
    @Override
    public String toString()
    {
        return this.mClass.toString();
    }
    
    public static void main(String[] args) {
		CodeBuilder cb = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
		System.out.println(cb);
	}
}
