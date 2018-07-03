package eclipse_examples.binding;


import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.databinding.observable.sideeffect.ISideEffect;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.junit.Test;

import melnorme.util.swt.SWTTestUtils;

public class SideEffectExample {
	
	@Test
	public void test() throws Exception {
		SWTTestUtils.runWithShell(shell -> {
			withShell();
			shell.dispose();
		});
	}

	protected void withShell() {
		
		IObservableValue<String> propertyFoo = new WritableValue<>("xxx", null);
		IObservableValue<Integer> propertyNumber = new WritableValue<>();

		AtomicInteger counter = new AtomicInteger();
		
		ISideEffect.create( () -> {
			propertyFoo.getValue();
			if(propertyFoo.getValue().equals("xxx")) { 
				propertyNumber.getValue();
			}
			
			counter.incrementAndGet();
		});
		assertTrue(counter.get() == 1);
		
		propertyFoo.setValue("blah");
		SWTTestUtils.runPendingUIEvents();
		assertTrue(counter.get() == 2);
		
		propertyNumber.setValue(123);
		SWTTestUtils.runPendingUIEvents();
		assertTrue(counter.get() == 3);
	}

}