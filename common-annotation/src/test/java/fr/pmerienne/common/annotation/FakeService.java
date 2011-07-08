package fr.pmerienne.common.annotation;

import org.springframework.stereotype.Component;

@Component
public class FakeService {

	public void doNothingWithString(@NotNull("some string") String someString) {
		
	}
}
