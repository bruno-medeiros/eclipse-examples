package foo.bundle.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class FooWidget {

	protected Label label;

	public void create(Composite parent) {
		label = new Label(parent, SWT.NONE);
	}
}
