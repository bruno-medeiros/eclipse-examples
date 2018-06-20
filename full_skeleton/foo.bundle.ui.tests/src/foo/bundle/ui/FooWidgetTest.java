package foo.bundle.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class FooWidgetTest {

	@Test
	public void test() {
		Shell shell = new Shell(Display.getCurrent());
		shell.open();
		FooWidget fooWidget = new FooWidget();
		fooWidget.create(shell);
		assertThat(fooWidget.label).isNotNull();
	}

}
