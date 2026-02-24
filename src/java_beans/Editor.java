package java_beans;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Editor extends Frame {

	public Editor(Object o) {
		super("Property Editor");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		setLayout(new GridLayout(0, 2));
		addComponents(o);

		pack();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	public Editor() {
		this(null);
	}

	private void addComponents(Object o) {
		if (o == null) {
			return;
		}

		try {
			BeanInfo i = Introspector.getBeanInfo(o.getClass());

			for (PropertyDescriptor pd : i.getPropertyDescriptors()) {
				if ("class".equals(pd.getName())) {
					continue;
				}

				add(new Label(pd.getName()));

				TextField tfValue = new TextField();
				try {
					String s = String.valueOf(pd.getReadMethod().invoke(o));
					tfValue.setText(s);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}

				tfValue.addTextListener(new TextListener() {
					
					@Override
					public void textValueChanged(TextEvent e) {
						try {
							//Class t = pd.getPropertyType();
							Method setter = pd.getWriteMethod();
							setter.invoke(o, tfValue.getText());
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
							e1.printStackTrace();
						}
					}
				});
				
				 if (o instanceof PropertyChangeEventProducer) {
					 ((PropertyChangeEventProducer)o).addPropertyChangeListener(new PropertyChangeListener() {
						
						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							if (evt.getPropertyName().equals(pd.getName())) {
								tfValue.setText(String.valueOf(evt.getNewValue()));
							}
						}
					});
				 }
				
				add(tfValue);
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}
}
