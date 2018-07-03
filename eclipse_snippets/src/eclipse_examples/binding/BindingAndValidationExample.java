package eclipse_examples.binding;


import java.nio.file.Paths;

import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;

import melnorme.util.swt.SWTTestUtils;

public class BindingAndValidationExample {

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
		
		// padding
		new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		WritableValue<String> propertyFoo = new WritableValue<>();
		WritableValue<Integer> propertyNumber = new WritableValue<>();

		DataBindingContext dbc = new DataBindingContext();
		{
			Text text = new Text(shell, SWT.BORDER);
			GridDataFactory.fillDefaults().grab(true, false).applyTo(text);
			IObservableValue<String> textObservable = textObservable(text);
			
			propertyFoo.setValue("foo:bar<.?>"); // Note: this is only invalid in Windows
			
			IConverter convertToStringArray = IConverter.create(String.class, String.class, id -> id);

			UpdateValueStrategy updateStrategy = UpdateValueStrategy.create(convertToStringArray);
			updateStrategy.setBeforeSetValidator((Object pathStrObj) -> {
				String pathStr = (String) pathStrObj;
				try {
					Paths.get(pathStr);
					return ValidationStatus.ok();
				} catch (Exception e) {
					return ValidationStatus.error(e.getMessage(), e);
				}
			});

			Binding bindValue = dbc.bindValue(textObservable, propertyFoo, updateStrategy, null);
			
			ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);
		}
		
		{
			Text text = new Text(shell, SWT.BORDER);
			IObservableValue<String> textObservable = textObservable(text);
			dbc.bindValue(textObservable, propertyNumber);
		}
		
		AggregateValidationStatus ags = new AggregateValidationStatus(dbc.getBindings(),
		        AggregateValidationStatus.MAX_SEVERITY);
		{
			Text text = new Text(shell, SWT.BORDER);
			GridDataFactory.fillDefaults().grab(true, false).applyTo(text);
			IObservableValue<String> textObservable = textObservable(text);
			
			dbc.bindValue(textObservable, ags);
		}

	}

}