import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

/*----------------------------------------------------------------------
 *  Author: Qianying Lin
 *  Written: 5/1/2016
 *  Last updated: 5/1/2016
 * 
 *  Complication: 
 *  Execution: 
 *
 *  This file performs the Naive Bayes and multilayer perceptron 
 *  classification on target dataset.
 *---------------------------------------------------------------------*/

public class project {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));		
//		BufferedReader br = new BufferedReader(new FileReader("src/test2.arff"));		
		Instances train = new Instances(br);
		train.setClassIndex(0);		
		br.close();
		
		// Naive Bayes 
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(train);
		Evaluation eval = new Evaluation(train);
		eval.crossValidateModel(nb, train, 10, new Random(1));
		System.out.println("Naive Bayes results:");
		System.out.println(eval.toSummaryString("\nResults\n======\n", false));
		
		// Multilayer perceptron
		MultilayerPerceptron mp = new MultilayerPerceptron();
		mp.setHiddenLayers("10, 10, 10");
		mp.setLearningRate(0.3);
		mp.setMomentum(0.2);
		mp.setTrainingTime(10);
		Evaluation eval2 = new Evaluation(train);
		eval2.crossValidateModel(mp, train, 10, new Random(1));
		System.out.println("Multilayer perceptron results:");
		System.out.println(eval2.toSummaryString("\nResults\n======\n", false));
		
	}
}
