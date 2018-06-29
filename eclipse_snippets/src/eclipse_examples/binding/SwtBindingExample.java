package eclipse_examples.binding;


import java.util.concurrent.ExecutionException;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;

import melnorme.util.swt.SWTTestUtils;

public class SwtBindingExample {

	@Test
	public void test() throws Exception {
		SWTTestUtils.runWithShell(shell -> {
			withShell(shell);
		});
	}
	

	@SuppressWarnings("unchecked")
	public static IObservableValue<String> textObservable(Text text) {
		return WidgetProperties.text(SWT.Modify).observe(text);
	}
	
	protected void withShell(Shell shell) {
		
		ExecutorRealm executorRealm = new ExecutorRealm();
		
		WritableValue<String> propertyInThisRealm = new WritableValue<>();
		WritableValue<String> propertyInOtherRealm = new WritableValue<>(executorRealm);
		
		try {
			executorRealm.getExecutor().submit(() -> propertyInOtherRealm.setValue("123")).get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}

		DataBindingContext ctx = new DataBindingContext();
		{
			Text text = new Text(shell, SWT.BORDER);
			IObservableValue<String> textObservable = textObservable(text);
			ctx.bindValue(textObservable, propertyInThisRealm);
		}
		
		{
			Text text = new Text(shell, SWT.BORDER);
			IObservableValue<String> textObservable = textObservable(text);
			ctx.bindValue(textObservable, propertyInOtherRealm);
		}

	}

}