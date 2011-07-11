package fr.pmerienne.common.annotation;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class GreaterThanAspectTest extends TestCase {

	@Autowired
	private FakeService fakeService;

	@Test
	public void checkArgGreater() {
		try {
			fakeService.doNothing3(5);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		try {
			fakeService.doNothing3(2);
			fail();
		} catch (Exception e) {
		}
		try {
			fakeService.doNothing3(0);
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void checkArgGreaterEquals() {
		try {
			fakeService.doNothing4(5);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		try {
			fakeService.doNothing4(2);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		try {
			fakeService.doNothing4(0);
			fail();
		} catch (Exception e) {
		}
	}

}
