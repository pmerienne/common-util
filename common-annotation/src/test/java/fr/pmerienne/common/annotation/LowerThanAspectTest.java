package fr.pmerienne.common.annotation;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class LowerThanAspectTest extends TestCase {

	@Autowired
	private FakeService fakeService;

	@Test
	public void checkArgLower() {
		try {
			fakeService.doNothing5(1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		try {
			fakeService.doNothing5(2);
			fail();
		} catch (Exception e) {
		}
		try {
			fakeService.doNothing5(3);
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void checkArgLowerEquals() {
		try {
			fakeService.doNothing6(1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		try {
			fakeService.doNothing6(2);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		try {
			fakeService.doNothing6(3);
			fail();
		} catch (Exception e) {
		}
	}

}
