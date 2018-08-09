package com.example.e4.rcp.todo;

import javax.annotation.PostConstruct;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class PlayGroundToolControl2 {
	
    @PostConstruct
    public void createControls(Composite parent) {
        final Composite comp = new Composite(parent, SWT.NONE);
        
        comp.setLayout(GridLayoutFactory.fillDefaults().create());
        comp.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN));
        Text text = new Text(comp, SWT.SEARCH | SWT.ICON_SEARCH | SWT.CANCEL | SWT.BORDER);
        text.setMessage("Search");
        text.setLayoutData(GridDataFactory.fillDefaults().hint(130, 50).minSize(150, 130).grab(true, true).create());
    }
}