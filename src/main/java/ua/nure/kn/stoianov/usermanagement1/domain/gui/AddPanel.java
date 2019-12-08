package ua.nure.kn.stoianov.usermanagement1.domain.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AddPanel extends JPanel implements ActionListener {
	
	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	
	public AddPanel(MainFrame parent) {
		this.parent = parent;
		initialize();
	}

	private void initialize() {
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		
	}

	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Отмена");
			cancelButton.setName("cancelButton");
			cancelButton.setActionCommand("cancel");
			cancelButton.addActionListener(this);
		}
		return okButton;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("Ок");
			okButton.setName("okButton");
			okButton.setActionCommand("ok");
			okButton.addActionListener(this);
		}
		return null;
	}

	private JPanel getFieldPanel() {
		// TODO Auto-generated method stub
		return fieldPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
