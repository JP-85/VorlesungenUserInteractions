package pruefung.klausur2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	private Model model;
	private View view;

	public void initialise(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	private static boolean isNumberBetweenZeroAndNine(String str) {
		try {
			int i = Integer.valueOf(str);
			
			return i >= 0 && i <= 9;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.model.getStringBuilder().setLength(0);
		this.model.getStringBuilder().append(this.view.getCurrentInput());

		String ac = e.getActionCommand();
		if(isNumberBetweenZeroAndNine(ac)){
			this.model.getStringBuilder().append(ac);
			this.view.setCurrentInput(this.model.getStringBuilder().toString());
		} else if (".".equals(ac)) {
			if (this.model.getStringBuilder().lastIndexOf(".") == -1) {
				this.model.getStringBuilder().append(ac);
				this.view.setCurrentInput(this.model.getStringBuilder().toString());
			}
		} else if ("C".equals(ac)) {
			this.model.getStringBuilder().setLength(0);
			this.view.setCurrentInput("");
		} else if ("Calculate".equals(ac)) {
			String str = this.model.getStringBuilder().toString().replace(',', '.');
			this.model.calculate(Double.valueOf(str));

			this.model.initialise(0, this.view.getCurrentSelection());
			this.model.getStringBuilder().setLength(0);
			this.view.setCurrentInput("");
		} else if ("choose".equals(ac)) {
			this.model.setUnit(this.view.getCurrentSelection());
		}
		this.view.repaint();

	}
}
