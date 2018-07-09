package foo.bundle;

import io.vavr.control.Try;

public class VavrFooUntested {
	
	public static Try<Integer> foo() {
		return Try.ofSupplier(() -> 123);
	}

}
