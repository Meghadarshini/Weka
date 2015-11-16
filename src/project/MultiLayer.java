package project;

import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class MultiLayer {
	MultiLayer() {	
		try {
			DataSource trainsource = new DataSource("/home/megha/Desktop/trainingsetwekalatest.arff");
			//DataSource trainsource = new DataSource("/media/megha/Dataset/trainingsetwekalatest.arff");
			Instances traindata = trainsource.getDataSet();
			DataSource testsource = new DataSource("/home/megha/Desktop/testsetlatest.arff");
			//DataSource testsource = new DataSource("/media/megha/Dataset/testsetlatest.arff");
			Instances testdata = testsource.getDataSet();
			//Instance testdata = (Instance) testsource.getLoader();
			
		//	if (traindata.classIndex() == -9) {
				//traindata.setClassIndex(traindata.numAttributes() -10); 
				traindata.setClassIndex(traindata.numAttributes() -2);
			
			//}
				/* filter 
				//String[] options = weka.core.Utils.splitOptions("-R 6 7 8 9 10 11 12 13");
				String[] options = weka.core.Utils.splitOptions("-R 2 3 4 6 7 8 9 10 11 12 13");
				Remove remove = new Remove();
				remove.setOptions(options);
				remove.setInputFormat(traindata);
				Instances newTrainData = Filter.useFilter(traindata, remove);
				Instances newTestData = Filter.useFilter(testdata, remove);
				newTrainData.setClassIndex(newTrainData.numAttributes() - 10);
				newTestData.setClassIndex(newTestData.numAttributes() - 10);
				*/
				
			
			//if (testdata.classIndex() == -9) {
				//testdata.setClassIndex(testdata.numAttributes() -10);
			testdata.setClassIndex(testdata.numAttributes() -2);
			//}
			
			
			MultilayerPerceptron mlp = new MultilayerPerceptron();
			mlp.setOptions(Utils.splitOptions("-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H 4"));
			
			mlp.buildClassifier(traindata);
		//	mlp.buildClassifier(newTrainData);
		//mlp.buildClassifier(newData);
			
			//Evaluation eval = new Evaluation(traindata);
			//eval.evaluateModel(mlp, testdata);
			
			//eval.evaluateModelOnce(mlp, testdata);
			//eval.evaluateModelOnce(mlp, testsource);
			//System.out.println(eval.toSummaryString("\nResults\n====\n", false));
			//System.out.println(eval.toSummaryString());
			double stockPriceStartDate = 0;
			double stockPriceEndDate = 0; 
			double stockOpenPriceStartDate = 0;
			double stockOpenPriceEndDate = 0;			
			double difference = 0;
			double amount = 0;
			
			/*
			int[][] a = new int[2][2];
			for(int i = 0; i < testdata.numInstances(); i++) {
				for(int j = 0; j < testdata.numInstances(); j++) {

				
					System.out.println(i+" "+j);
			//	if (i == 0) {
					stockOpenPriceStartDate = mlp.classifyInstance(testdata.instance(a[i][j]));					
					System.out.println("Stock open price on start date is: "+stockOpenPriceStartDate+" $");
				//	stockPriceStartDate = mlp.classifyInstance(testdata.instance(i));					
					//System.out.println("Stock  close price on start date is: "+stockPriceStartDate+" $");
					
				//}
					/*
				if (i == 1) {
					stockOpenPriceEndDate = mlp.classifyInstance(testdata.instance(i));
					System.out.println("Stock open price on end date is: "+stockOpenPriceEndDate+" $");
					stockPriceEndDate = mlp.classifyInstance(testdata.instance(i));
					System.out.println("Stock close price on end date is: "+stockPriceEndDate+" $");
					
				}
				
			}
			*/
			/* filter 
			for(int i = 0; i < newTestData.numInstances(); i++) {
				
				if (i == 0) {
					stockPriceStartDate = mlp.classifyInstance(newTestData.instance(i));
					//labeled.
					System.out.println("Stock price on start date is: "+stockPriceStartDate+" $");					
				}
				if (i == 1) {
					stockPriceEndDate = mlp.classifyInstance(newTestData.instance(i));
					System.out.println("Stock price on end date is: "+stockPriceEndDate+" $");
				}
			}
			*/
			
			for(int i = 0; i < testdata.numInstances(); i++) {
	
				if (i == 0) {
					stockPriceStartDate = mlp.classifyInstance(testdata.instance(i));
					//labeled.
					System.out.println("Stock price on start date is: "+stockPriceStartDate+" $");					
				}
				if (i == 1) {
					stockPriceEndDate = mlp.classifyInstance(testdata.instance(i));
					System.out.println("Stock price on end date is: "+stockPriceEndDate+" $");
				}
			}
		
			difference = stockPriceEndDate - stockPriceStartDate;
			if (difference > 0) {
				System.out.println("Profit per stock is: "+ difference+" $");
				double profitPercentage = ((stockPriceEndDate - stockPriceStartDate)/stockPriceStartDate )*100;
				System.out.println("Profit Percentage is:"+profitPercentage+" %");
				System.out.println("Total Profit amount is: "+(amount*difference)+" $");
			}
			else if (difference < 0){
				System.out.println("Loss per stock is: "+ difference+" $");
				double lossPercentage = ((stockPriceStartDate - stockPriceEndDate)/stockPriceStartDate)*100;
				System.out.println("Loss Percentage is:"+lossPercentage+" %");
				System.out.println("Total Loss amount is: "+(amount*difference)+" $");
			}
			else {
				System.out.println("There is No Profit or Loss. \n The stock price remains same for the start date and end date specified");
				System.out.println("Total return amount is: "+amount+" $");
			}
			
			
			/*
			double label = mlp.classifyInstance(testdata.instance(0));
			testdata.instance(0).setClassValue(label);
			System.out.println(testdata.instance(0).stringValue(1));
			*/
			
			//Class<StringBuffer> predictions = java.lang.StringBuffer.class;
			
			//System.out.println(eval.correct());
			//Object predictions = new StringBuffer();
			//eval.crossValidateModel(mlp, testdata, 10, new Random(1), predictions);
		//	eval.crossValidateModel(mlp, testdata, 2, new Random(1),predictions );
			
			//eval.crossValidateModel(mlp, testdata, 10, options, new Random(1));
			
			//StringBuffer predsBuffer = new StringBuffer();
			//PlainText plainText = new PlainText();
			
			
			//String[] options = new String[10];
			//options[0] = "-R";
			//options[1] = "";
		//	
			
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
