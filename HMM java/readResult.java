import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
 *  This file claculate the result from weka, to build the hmm model 
 *  for matlab.
 *---------------------------------------------------------------------*/

public class readResult {
	private String fileName;
	ArrayList<String> origin;
	ArrayList<String> predict;
	ArrayList<Double> prob;

	public readResult(String filename){
		this.fileName = filename;
		origin = new ArrayList<String>();
		predict = new ArrayList<String>();
		prob = new ArrayList<Double>();

	}
	public void read() throws Exception{
		BufferedReader CSVFile = null;
		try {				
			CSVFile = new BufferedReader(new FileReader(fileName));			
			String dataRow = CSVFile.readLine();
			while (dataRow != null){				
				String[] dataArray = dataRow.split(",");
				origin.add(dataArray[0]);
				predict.add(dataArray[1]);
				prob.add(Double.parseDouble(dataArray[2]));
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
