package fr.inria.gag.ui.component;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class ComponentIHM {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComponentIHM window = new ComponentIHM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ComponentIHM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 703, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem exitMenuItem = new JMenuItem("exit");
		fileMenu.add(exitMenuItem);
		
		JMenu toolMenu = new JMenu("Tool");
		menuBar.add(toolMenu);
		
		JMenu aboutMenu = new JMenu("About");
		menuBar.add(aboutMenu);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelBtn = new JPanel();
		frame.getContentPane().add(panelBtn, BorderLayout.NORTH);
		
		JButton btnSave = new JButton("Save");
		panelBtn.add(btnSave);
		
		JButton btnPause = new JButton("Pause");
		panelBtn.add(btnPause);
		
		JButton btnResume = new JButton("Resume");
		panelBtn.add(btnResume);
		
		JButton btnStop = new JButton("Stop");
		panelBtn.add(btnStop);
		
		JPanel panelMetaData = new JPanel();
		frame.getContentPane().add(panelMetaData, BorderLayout.SOUTH);
		panelMetaData.setPreferredSize(new Dimension(100,159));
		panelMetaData.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panelConfValue = new JPanel();
		panelMetaData.add(panelConfValue);
		panelConfValue.setLayout(new BorderLayout(0, 0));
		
		JPanel panelConValueTitle = new JPanel();
		panelConfValue.add(panelConValueTitle, BorderLayout.NORTH);
		
		JLabel lblConfigurationValues = new JLabel("Configuration Values");
		panelConValueTitle.add(lblConfigurationValues);
		
		JPanel panelInputs = new JPanel();
		panelMetaData.add(panelInputs);
		panelInputs.setLayout(new BorderLayout(0, 0));
		
		JPanel panelInputValueTitle = new JPanel();
		panelInputs.add(panelInputValueTitle, BorderLayout.NORTH);
		
		JLabel lblInputVariables = new JLabel("Inputs");
		panelInputValueTitle.add(lblInputVariables);
		
		JPanel panelOutputs = new JPanel();
		panelMetaData.add(panelOutputs);
		panelOutputs.setLayout(new BorderLayout(0, 0));
		
		JPanel panelOutputsTitle = new JPanel();
		panelOutputs.add(panelOutputsTitle, BorderLayout.NORTH);
		
		JLabel lblOutputTitle = new JLabel("Outputs / Subscriptions");
		panelOutputsTitle.add(lblOutputTitle);
		
		JPanel panelConfiguration = new JPanel();
		frame.getContentPane().add(panelConfiguration, BorderLayout.CENTER);
		panelConfiguration.setLayout(new BorderLayout(0, 0));
		
		JPanel panelConfigurationText = new JPanel();
		panelConfiguration.add(panelConfigurationText, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Configuration");
		panelConfigurationText.add(lblNewLabel);
	}

}
