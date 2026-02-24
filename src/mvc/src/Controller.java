package mvc.src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class Controller implements ActionListener, PropertyChangeListener, Serializable {

	private static final long serialVersionUID = 3774765293477324348L;

	private Model model;
	private View view;
	
	public Controller() {
		this(null, null);
	}
	
	public Controller(Model model, View view) {
		setView(view);
		setModel(model);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		model.setName(view.getTfName().getText());
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		if (model == null) {
			return;
		}
		
		this.model = model;
		this.model.addPropertyChangeListener(this);
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		if (view == null) {
			return;
		}
		
		this.view = view;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (this.view == null) {
			return;
		}
		
		if ("counter".equals(evt.getPropertyName())) {
			String newTitle = new StringBuilder(View.TITLE)
				.append(' ')
				.append('(')
				.append(evt.getNewValue())
				.append(')')
				.toString();
			
			this.view.setTitle(newTitle);
		} else if ("name".equals(evt.getPropertyName())) {
			this.view.getTfName().setText((String)evt.getNewValue());
		}
	}
}
