import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*----------------------------------------------------------------------
 *  Author: Qianying Lin
 *  Written: 5/1/2016
 *  Last updated: 5/1/2016
 * 
 *  Complication: 
 *  Execution: 
 *
 *  This file performs write the results to a new cvs file
 *---------------------------------------------------------------------*/

public class wordCsvWriter {
	private ArrayList<String> wordlist;
	private String filename;
    private final String CRLF = "\r\n"; 
    private String delimiter = ","; 

	public wordCsvWriter(ArrayList<String> wordlist, String filename){
		this.wordlist = wordlist;
		this.filename = filename;
	}
	public void write(){
		int num_word = wordlist.size();
        try { 
            FileWriter writer = new FileWriter(filename); 

            for (int i = 0; i < num_word; i++) { 
                writer.append(wordlist.get(i)); 
                //Add delimiter and end of the line 
                if (i < num_word - 1) { 
                    writer.append(delimiter + CRLF); 
                } 
            } 
            writer.flush(); 
            writer.close(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
}
