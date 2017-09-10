import java.io.FileWriter;
import java.io.IOException;

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

public class cvsWriter {
	private double[][] result;
	private String filename;
    private final String CRLF = "\r\n"; 
    private String delimiter = ","; 

	public cvsWriter(double[][] result, String filename){
		this.result = result;
		this.filename = filename;
	}
	public void write(){
		int num_char = result.length;
        try { 
            FileWriter writer = new FileWriter(filename); 

            for (int i = 0; i < num_char; i++) { 
                for (int j = 0; j < num_char; j++) { 
                    writer.append(String.valueOf(result[i][j])); 
                    if (j < num_char - 1) { 
                        writer.append(delimiter); 
                    } 
                } 
                //Add delimiter and end of the line 
                if (i < num_char - 1) { 
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
