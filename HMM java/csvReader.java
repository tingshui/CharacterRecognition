/*----------------------------------------------------------------------
 *  Author: Qianying Lin
 *  Written: 1/31/2016
 *  Last updated: 1/31/2016
 * 
 *  Complication: 
 *  Execution: 
 *
 *  This file performs the reading of cvs file. 
 *  deal with the missing values by calculate the weighting value.
 *  reading in words.  
 *---------------------------------------------------------------------*/

import java.io.*;
import java.util.*;

public class csvReader {	
	ArrayList<ArrayList<letter>> words = new ArrayList<ArrayList<letter>>();
	ArrayList<String> wordlist = new ArrayList<String>();
	ArrayList<String> dist_word = new ArrayList<String>();
	int num_word = 0;
	int num_dis_word = 0;
	public csvReader(String fileName) throws Exception{
		// manually add the first word to initiate.
		dist_word.add("ommanding");
		num_dis_word++;
		
		BufferedReader CSVFile = null;
		try {				
			CSVFile = new BufferedReader(new FileReader(fileName));			
			String dataRow = CSVFile.readLine();
			
			ArrayList<letter> newword = new ArrayList<letter>();
			String newstring = new String();
			while (dataRow != null){				
				String[] dataArray = dataRow.split(",");
				// store the characters of a letter
				letter newletter = new letter();
				newletter.id = Integer.parseInt(dataArray[0]);
				newletter.character = dataArray[1];
				newletter.next_id = Integer.parseInt(dataArray[2]);
				newletter.word_id = Integer.parseInt(dataArray[3]);
				newletter.position = Integer.parseInt(dataArray[4]);
				newletter.fold = Integer.parseInt(dataArray[5]);
				int column = 0;
				for (int i = 6; i < dataArray.length; i++) {
					newletter.pixels[column] = Integer.parseInt(dataArray[i]);
					column++;
				}				
				if(newletter.next_id != -1){					
					newword.add(newletter);
					newstring += newletter.character;
				}
				else{
					newword.add(newletter);
					newstring += newletter.character;
					if(!dist_word.get(num_dis_word-1).equals(newstring)){
						dist_word.add(newstring);
						num_dis_word++;
					}
					num_word++;					
					words.add(newword);
					wordlist.add(newstring);
					newword = new ArrayList<letter>();
					newstring = new String();
				}
				dataRow = CSVFile.readLine(); 				
			}
			CSVFile.close();
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        	} 
		catch (IOException e) {
            e.printStackTrace();
        	}
	}
}



