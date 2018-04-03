package foo.bundle.tests;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import foo.bundle.VavrFoo;
import io.vavr.control.Try;

public class VavrFooTest {

	@Test
	public void test() {
		assertThat(VavrFoo.foo(), CoreMatchers.equalTo(Try.ofSupplier(() -> 123)));
	}

}
