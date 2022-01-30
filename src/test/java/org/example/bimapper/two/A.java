package org.example.bimapper.two;

public class A {
	String name;
	int age;
	
	public String pushName(String name) {
		String previous = this.name;
		this.name = name;
		return previous;
	}

	public Integer pushAge(Integer age) {
		Integer previous = this.age;
		this.age = age == null ? 0 : age;
		return previous;
	}
}