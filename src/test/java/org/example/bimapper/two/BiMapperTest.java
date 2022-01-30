package org.example.bimapper.two;

import static org.junit.jupiter.api.Assertions.*;

import org.example.bimapper.two.BiMapper;
import org.junit.jupiter.api.Test;

class BiMapperTest {
	
	@Test
	void testPushName() {
		A a = new A();
		
		assertNull(a.name);
		assertNull(a.pushName("John"));
		assertEquals("John", a.name);
		assertEquals("John", a.pushName("Alice"));
		assertEquals("Alice", a.name);
	}

	@Test
	void testPushAge() {
		A a = new A();
		
		assertEquals(0, a.age);
		assertEquals(0, a.pushAge(18));
		assertEquals(18, a.age);
		assertEquals(18, a.pushAge(23));
		assertEquals(23, a.age);
	}


	@Test
	void testMap() {
		// SETUP
		A a = new A();
		a.name = "John";
		a.age = 11;
		
		B b = new B();
		
		// TEST
		BiMapper<A, B> mapper = new BiMapper<A, B>();
		mapper.add(a::pushName, b::pushName);
		mapper.add(a::pushAge, b::pushAge);
		mapper.map();
		
		// ASSERT
		assertEquals("John", a.name);
		assertEquals("John", b.name);
		assertEquals(11, a.age);
		assertEquals(11, b.age);
	}

	@Test
	void testRev() {
		// SETUP
		A a = new A();
		B b = new B();
		b.name = "John";
		b.age = 11;
		
		// TEST
		BiMapper<A, B> mapper = new BiMapper<A, B>();
		mapper.add(a::pushName, b::pushName);
		mapper.add(a::pushAge, b::pushAge);
		mapper.adds((x) -> x::pushName, (x) -> x::pushName);
		mapper.rev();
		
		// ASSERT
		assertEquals("John", a.name);
		assertEquals("John", b.name);
		assertEquals(11, a.age);
		assertEquals(11, b.age);
	}

	@Test
	void testMaps() {
		// SETUP
		A a = new A();
		a.name = "John";
		a.age = 11;
		
		B b = new B();
		
		// TEST
		BiMapper<A, B> mapper = new BiMapper<A, B>();
		mapper.adds((x) -> x::pushName, (x) -> x::pushName);
		mapper.adds((x) -> x::pushAge, (x) -> x::pushAge);
		mapper.maps(a, b);
		
		// ASSERT
		assertEquals("John", a.name);
		assertEquals("John", b.name);
		assertEquals(11, a.age);
		assertEquals(11, b.age);
	}

	@Test
	void testRevs() {
		// SETUP
		A a = new A();
		
		B b = new B();
		b.name = "John";
		b.age = 11;
		
		// TEST
		BiMapper<A, B> mapper = new BiMapper<A, B>();
		mapper.adds((x) -> x::pushName, (x) -> x::pushName);
		mapper.adds((x) -> x::pushAge, (x) -> x::pushAge);
		mapper.revs(a, b);
		
		// ASSERT
		assertEquals("John", a.name);
		assertEquals("John", b.name);
		assertEquals(11, a.age);
		assertEquals(11, b.age);
	}
}
