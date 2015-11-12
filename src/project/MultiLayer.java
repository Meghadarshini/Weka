package project;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class MultiLayer {
	MultiLayer() {
		try {
			DataSource trainsource = new DataSource("/home/megha/Desktop/trainingset.arff");
			Instances traindata = trainsource.getDataSet();
			DataSource testsource = new DataSource("/home/megha/Desktop/testsetweka.arff");
			Instances testdata = testsource.getDataSet();
			//Instance testdata = (Instance) testsource.getLoader();
			
		//	if (traindata.classIndex() == -9) {
				traindata.setClassIndex(traindata.numAttributes() -9);
			//}
			
			//if (testdata.classIndex() == -9) {
				testdata.setClassIndex(testdata.numAttributes() -9);
			//}
			
			
			MultilayerPerceptron mlp = new MultilayerPerceptron();
			mlp.setOptions(Utils.splitOptions("-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H 4"));
			
			mlp.buildClassifier(traindata);
			
			Evaluation eval = new Evaluation(traindata);
			eval.evaluateModel(mlp, testdata);
			
		//	eval.evaluateModelOnce(mlp, testdata);
			//eval.evaluateModelOnce(mlp, testsource);
			//System.out.println(eval.toSummaryString("\nResults\n====\n", false));
			System.out.println(eval.toSummaryString());
			/*
			double label = mlp.classifyInstance(testdata.instance(0));
			testdata.instance(0).setClassValue(label);
			System.out.println(testdata.instance(0).stringValue(4));
			*/
			eval.c
			
			//Class<StringBuffer> predictions = java.lang.StringBuffer.class;
			
			System.out.println(eval.correct());
			Object predictions = new StringBuffer();
			eval.crossValidateModel(mlp, testdata, 10, new Random(1), predictions);
		//	eval.crossValidateModel(mlp, testdata, 2, new Random(1),predictions );
			
			//eval.crossValidateModel(mlp, testdata, 10, options, new Random(1));
			
			//StringBuffer predsBuffer = new StringBuffer();
			//PlainText plainText = new PlainText();
			
			
			//String[] options = new String[10];
			//options[0] = "-R";
			//options[1] = "";
		//	String[] options = weka.core.Utils.splitOptions("-R 1");
			
		//	Remove remove = new Remove();
			//remove.setOptions(options);
			//remove.setInputFormat(data);
			//Instances newData = Filter.useFilter(data, remove);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	public static void main(String[] args) throws Exception {
		new MultiLayer();

		
		
		
		
		
		
		

	}

}
