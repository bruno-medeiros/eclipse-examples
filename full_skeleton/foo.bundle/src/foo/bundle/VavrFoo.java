package foo.bundle;

import io.vavr.control.Try;

public class VavrFoo {
	
	public static Try<Integer> foo() {
		return Try.ofSupplier(() -> 123);
	}

}
