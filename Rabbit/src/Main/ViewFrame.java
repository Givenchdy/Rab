package Main;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ViewFrame extends JFrame implements iViewFrame{

	
	JPanel mainPanel = new JPanel();
	JButton importButton = new JButton("Import File");
	JButton searchButton = new JButton("Search");

	JTextField textSearch = new JTextField(15);
	JTextArea textView = new JTextArea(12, 15);
	
	
	FileProcessor fileSelector = null;
	JFrame _this;
	
	
	public ViewFrame()
	{
		_this = this;
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(textSearch);
		mainPanel.add(textView);
		mainPanel.add(importButton);
		mainPanel.add(searchButton);
		
		this.add(mainPanel);
		this.setSize(200, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		fileSelector = new FileProcessor(this);
		onImportButtonClick();
		onSearchButtonClick();
				
	}
	
	public void onImportButtonClick()
	{
		
		importButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				fileSelector.showFileChooser();
				
				//fileSelector.readFileContents();
				
			}
	
		});
		
	}
	
	
	public void onSearchButtonClick()
	{
		
		searchButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String text = textSearch.getText();
				
				if(!text.isEmpty())
				{
					ArrayList<String> textCollection = fileSelector.readFileContents(text);
					displayReturnedText(textCollection);
				}
				else
				{
					showMessage("Search word not provided");
				}
							
			}
	
		});
		
	}
	
	public void showMessage(String message)
	{
		JOptionPane.showMessageDialog(this, message);
	}
	
	private void displayReturnedText(ArrayList<String> textCollection)
	{
		textView.setText("");
		if(!textCollection.isEmpty())
		{
			System.out.println("Search results: " + textCollection.size());
			for(String word : textCollection)
			{
				textView.append(word + "\n\r");
			}
		}
		else
		{  
			System.out.println("Search results: Empty");
			showMessage("No matching text found");
		}
		
	}
	
	
}
