import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import java.io.File;
/*----------------------------------------------------------------------
 *  Author: Qianying Lin
 *  Written: 5/1/2016
 *  Last updated: 5/1/2016
 * 
 *  Complication: 
 *  Execution: 
 *
 *  This file convert the cvs file to arff file
 *---------------------------------------------------------------------*/

public class cvs2arff {
	public static void main(String[] args) throws Exception{
		//Load CSV
		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(args[0]));
//		loader.setSource(new File("src/letter mod 1.csv"));
		Instances data = loader.getDataSet(); // get instances object		
		// save ARFF
		ArffSaver saver = new ArffSaver();
		saver.setInstances(data);
		// Save as ARFF
		saver.setFile(new File(args[1]));
//		saver.setFile(new File("/Users/qianying/Desktop/test/test2.arff"));
		saver.writeBatch();
	}
}
