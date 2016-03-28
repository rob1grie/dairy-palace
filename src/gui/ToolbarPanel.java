/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JPanel;

/**
 *
 * @author rob
 */
public class ToolbarPanel extends JPanel {

	private ModeToolbar modeToolbar;
	private ViewToolbar viewToolbar;

	public ToolbarPanel() {
		modeToolbar = new ModeToolbar();
		viewToolbar = new ViewToolbar();

		modeToolbar.setToolbarListener((String name1) -> {
//			setVisiblePanel(name1);
		});

	}
}
