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

public class SeqCsvWriter {
	private ArrayList<ArrayList<letter>> words;
	private ArrayList<String> predict;
	private ArrayList<String> dis_character;
	private String fileOri;
	private String filePred;
    private final String CRLF = "\r\n"; 
    private String delimiter = ","; 

	public SeqCsvWriter(ArrayList<ArrayList<letter>> words, ArrayList<String> predict, ArrayList<String> dis_character, String filename, String filePred){
		this.words = words;
		this.predict = predict;
		this.fileOri = filename;
		this.filePred = filePred;
		this.dis_character = dis_character;
	}
	public void write(){
		int num_word = words.size();
		// write the original sequence
        try { 
            FileWriter writer = new FileWriter(fileOri); 
            for (int i = 0; i < num_word; i++) {
            	int num_char = words.get(i).size();
                for (int j = 0; j < num_char; j++) { 
                	String chara = words.get(i).get(j).character;
                	//as Matlab starts from 1, so we need to add 1 to index
                    writer.append(String.valueOf(dis_character.indexOf(chara)+1)); 
                    if (j < num_char - 1) { 
                        writer.append(delimiter); 
                    } 
                } 
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
        
        // Write the predict sequence
        try { 
            FileWriter writer = new FileWriter(filePred); 
            int count = 0;
            for (int i = 0; i < num_word; i++) {
            	int num_char = words.get(i).size();
            	int end = count + num_char -1;
                for (int j = count; j <= end; j++) { 
                	int index = words.get(i).get(j-count).id - 1;
                	String chara = predict.get(index).replaceAll("\\s+","");
                	//as Matlab starts from 1, so we need to add 1 to index
                    writer.append(String.valueOf(dis_character.indexOf(chara)+1)); 
                    if ((j-count) < num_char - 1) { 
                        writer.append(delimiter); 
                    } 
                } 
                count = count + num_char - 1;
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
