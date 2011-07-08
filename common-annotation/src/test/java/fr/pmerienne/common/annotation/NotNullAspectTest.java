package fr.pmerienne.common.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class NotNullAspectTest {

	@Autowired
	private FakeService fakeService;

	@Test
	public void checkNotNullArgsWithNotNull() {
		String someString = "someString";
		fakeService.doNothingWithString(someString);
	}

	@Test(expected = IllegalArgumentException.class)
	public void checkNotNullArgsWithNull() {
		fakeService.doNothingWithString(null);
	}

}
