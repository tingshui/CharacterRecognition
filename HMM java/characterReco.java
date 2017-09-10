import java.io.File;
/*----------------------------------------------------------------------
 *  Author: Qianying Lin
 *  Written: 4/24/2016
 *  Last updated: 4/24/2016
 * 
 *  Complication: 
 *  Execution: 
 *
 *  This file performs character recognition using hidden markov model
 *---------------------------------------------------------------------*/
import java.util.ArrayList;
/*----------------------------------------------------------------------
 *  Author: Qianying Lin
 *  Written: 5/1/2016
 *  Last updated: 5/1/2016
 * 
 *  Complication: 
 *  Execution: 
 *
 *  This file prepares the data for HMM on Matlab
 *---------------------------------------------------------------------*/

public class characterReco {
	public static void main(String[] args) throws Exception{
		csvReader train = new csvReader(args[0]);		
//		csvReader train = new csvReader("src/letter.csv");
		ArrayList<String> dist_word = train.dist_word;
		ArrayList<String> wordlist = train.wordlist;
		ArrayList<ArrayList<letter>> words = train.words;
		readResult rd = new readResult(args[1]);
//		readResult rd = new readResult("src/pro.csv");
		rd.read();
		ArrayList<String> origin = rd.origin;
		ArrayList<String> predict = rd.predict;
		String transi_result = args[2];
		String emiss_result = args[3];
		String word_result = args[4];
		String seq_ori = args[5];
		String seq_pred = args[6];
//		String transi_result = "/Users/qianying/Desktop/result/transi.csv";
//		String emiss_result = "/Users/qianying/Desktop/result/emiss.csv";
//		String word_result = "/Users/qianying/Desktop/result/words.csv";
//		String seq_ori = "/Users/qianying/Desktop/result/originSeq_mod.csv";
//		String seq_pred = "/Users/qianying/Desktop/result/predSeq_mod.csv";
		
		// Build the transition table and the emission table for matlab
		// dis_caracter stores the sequence for both tables, like header.
		ArrayList<String> dis_character = new ArrayList<String>();
		for(String word: dist_word){
			int num_chara = word.length();
			String[] arr = word.split("");
			for (int i = 0; i < num_chara; i++) {
				if(!dis_character.contains(arr[i])){
					if(!arr[i].equals("")){
						dis_character.add(arr[i]);
					}					
				}
			}
		}
		
		int num_char = dis_character.size();
		// build the transition table, we assume each line represent previous char
		// and each column represent the next char, so first we count the pairs.
		int[][] trainsi_ori = new int[num_char][num_char];
		for(String word: dist_word){
			String[] arr = word.split("");
			int num_chara = arr.length;
			for (int i = 0; i < num_chara-1; i++) {
				String prev = arr[i];
				if(prev.equals("")) continue;
				String next = arr[i+1];				
				int index_prev = dis_character.indexOf(prev);
				int index_next = dis_character.indexOf(next);
				trainsi_ori[index_prev][index_next]++;				
			}
		}		
		// Normalize the transition table
		// to avoid 0, we use laplace smoothing.
		double[][] trainsi_norm = new double[num_char][num_char];
		for (int i = 0; i < num_char; i++) {
			double sum = 0.0; // sum for each row
			for (int j = 0; j < num_char; j++) {
				sum += trainsi_ori[i][j];
			}
			for (int k = 0; k < num_char; k++) {
				trainsi_norm[i][k] = ((double)trainsi_ori[i][k]+1.0)/(sum+(double)num_char);
			} 			
		}

		// Build the emission table
		// We assume the row is the true word, column is the predicted words
		// First count
		int num_record = origin.size();
		int[][] emiss_ori = new int[num_char][num_char];
		for (int i = 0; i < num_record; i++) {
			String origin_c = origin.get(i).replaceAll("\\s+","");
			String predict_c = predict.get(i).replaceAll("\\s+","");
			int index_orig = dis_character.indexOf(origin_c);
			int index_pred = dis_character.indexOf(predict_c);
			emiss_ori[index_orig][index_pred]++;			
		}
		// Normalize + Laplace smoothing
		double[][] emiss_norm = new double[num_char][num_char];
		for (int i = 0; i < num_char; i++) {
			double sum = 0.0; // sum for each row
			for (int j = 0; j < num_char; j++) {
				sum += emiss_ori[i][j];
			}
			for (int k = 0; k < num_char; k++) {
				emiss_norm[i][k] = ((double)emiss_ori[i][k]+1.0)/(sum+(double)num_char);
			} 			
		}
		// Write out the result
		wordCsvWriter wcw = new wordCsvWriter(wordlist, word_result);
		wcw.write();
		SeqCsvWriter scw = new SeqCsvWriter(words, predict, dis_character, seq_ori, seq_pred);
		scw.write();
		cvsWriter cw1 = new cvsWriter(trainsi_norm,transi_result);
		cw1.write();
		cvsWriter cw2 = new cvsWriter(emiss_norm, emiss_result);
		cw2.write();
	}
}
