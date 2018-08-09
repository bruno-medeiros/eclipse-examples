package com.example.e4.rcp.todo;

import javax.annotation.PostConstruct;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class PlayGroundToolControl {
	
    @PostConstruct
    public void createControls(Composite parent) {
        final Composite comp = new Composite(parent, SWT.NONE);
        
        comp.setLayout(GridLayoutFactory.fillDefaults().create());
        comp.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN));

        Label label = new Label(comp, SWT.NONE);
        ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(
        		FileLocator.find(Platform.getBundle("com.example.e4.rcp.todo"), new Path("images/disconnect.png"), null));
		label.setImage(imageDescriptor.createImage(false));
		label.setLayoutData(GridDataFactory.fillDefaults().create());
		parent.computeSize(100, 8);
    }
}