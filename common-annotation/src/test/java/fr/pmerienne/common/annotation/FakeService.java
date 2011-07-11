package fr.pmerienne.common.annotation;

import org.springframework.stereotype.Component;

@Component
public class FakeService {

	public void doNothingWithString(@NotNull("some string") String someString) {

	}

	public void doNothingWithString(@NotNull("some string") String someString, int integer, @NotNull String otherThing) {

	}

	public void doNothing3(@GreaterThan(2) int arg1) {

	}

	public void doNothing4(@GreaterThan(value = 2, orEquals = true) int arg1) {

	}

	public void doNothing5(@LowerThan(2) int arg1) {

	}

	public void doNothing6(@LowerThan(value = 2, orEquals = true) int arg1) {

	}
}
