package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

public class FileProcessor {
	
	File importedFile;
	iViewFrame viewView;
	
	public FileProcessor(iViewFrame viewView)
	{
		this.viewView = viewView;

	}
	
	public void showFileChooser()
	{
			
		
		JFileChooser jfileChooser = new JFileChooser(".");
		
		int result = jfileChooser.showSaveDialog(null);
		
		if(result == JFileChooser.APPROVE_OPTION)
		{
			importedFile = jfileChooser.getSelectedFile();
			System.out.println("File seletced: " + importedFile.getPath());
			
			int index = importedFile.getName().lastIndexOf('.');
			String fileType = importedFile.getName().substring(index + 1);
			checkFileType(fileType);
		}
		else
		{
			System.out.println("File Selection cancelled");
		}
		
	}
	
	private void checkFileType(String fileExtension)
	{
		if(!fileExtension.equals("txt"))
		{
			viewView.showMessage("File Extension not supported");
		}
	}
	
	/**
	 * Search for matching strings from file
	 * @param searchString
	 * @return
	 */
	public ArrayList<String> readFileContents(String searchString)
	{
	
		ArrayList<String> matchingStrings = null;
		
		if(importedFile != null)
		{
			
			matchingStrings = new ArrayList<>();
	
			try 
			{
				Scanner scanner = new Scanner(importedFile);
				
				while(scanner.hasNextLine())
				{
					String newLine = scanner.nextLine();
					/**
					 * Two strings are considered to be equivalent if all the characters from the one string is also
					   present in the other string.
					 */
					if(newLine.equalsIgnoreCase(searchString))
					{
						System.out.println("Match String found: " + newLine);
						matchingStrings.add(newLine);
					}
				}
				
			} catch (FileNotFoundException e)
			{
				
				e.printStackTrace();
			}
		
		}
		
		return matchingStrings;
		
	}
	
	

}
