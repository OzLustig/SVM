package HomeWork5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import weka.core.Instances;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.Kernel;
import weka.classifiers.functions.supportVector.PolyKernel;
import weka.classifiers.functions.supportVector.RBFKernel;

public class MainHW5 {

	public static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;

		try {
			inputReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		}

		return inputReader;
	}

	public static Instances loadData(String fileName) throws IOException 
	{
		BufferedReader datafile = readDataFile(fileName);
		Instances data = new Instances(datafile);
		data.setClassIndex(data.numAttributes() - 1);
		return data;
	}
	
	public static void main(String[] args) throws Exception 
	{
		Instances data = loadData("cancer.txt");
		data.randomize(new Random());
		int lastTrainingDataIndex = (int) Math.round((float) data.size()*0.8);
		Instances trainingData = new Instances(data, 0, lastTrainingDataIndex);
		Instances testingData = new Instances(data, 0, 0);
		for(int i=lastTrainingDataIndex+1;i<data.size();i++)
			testingData.add(data.get(i));
		double bestExponent=-1, bestGamma=-1;
		double bestKernelScore=0;
		boolean isPolyKernel=true;
		
		for(int i=2;i<5;i++)
		{
			SVM svm = new SVM();
			PolyKernel kernel = new PolyKernel();
			kernel.setExponent(i);
			svm.setKernel(kernel);
			svm.m_smo.buildClassifier(trainingData);
			double tpr = (double)(svm.calcConfusion(testingData)[0]) / svm.calcPositive(testingData);
			double fpr = (double) svm.calcConfusion(testingData)[1]/svm.calcNegative(testingData);
			if(tpr-fpr>bestKernelScore)
			{
				bestExponent=i;
				bestKernelScore=tpr-fpr;
			}
			System.out.println("For PolyKernel with degree "+i+" the rates are:\nTPR = "+tpr+"\nFPR = "+fpr);
		}
		double[] gammaValues = new double[3];
		gammaValues[0] = (double)1/100;
		gammaValues[1] = (double)1/10;
		gammaValues[2] = 1;
		for(int i=0;i<3;i++)
		{
			SVM svm = new SVM();
			RBFKernel kernel = new RBFKernel();
			kernel.setGamma(gammaValues[i]);
			svm.setKernel(kernel);
			svm.m_smo.buildClassifier(trainingData);
			double tpr = (double)(svm.calcConfusion(testingData)[0]) / svm.calcPositive(testingData);
			double fpr = (double) svm.calcConfusion(testingData)[1]/svm.calcNegative(testingData);
			if(tpr-fpr>bestKernelScore)
			{
				bestGamma=gammaValues[i];
				bestKernelScore=tpr-fpr;
				isPolyKernel=false;
			}
			System.out.println("For RBFKernel with degree "+gammaValues[i]+" the rates are:\nTPR = "+tpr+"\nFPR = "+fpr);
		}
		String bestKernel = isPolyKernel? "Poly" : "RBF";
		double bestParameter = isPolyKernel? bestExponent : bestGamma;
		System.out.println("The best kernel is: "+bestKernel+" "+bestParameter+" "+bestKernelScore);
		
		// choosing c value
		for(int i=1;i>=-4;i--)
		{
			for(int j=3;j>=1;j--)
			{
				SVM svm = new SVM();
				if(isPolyKernel)
				{
					PolyKernel kernel = new PolyKernel();
					kernel.setExponent(bestExponent);
					svm.setKernel(kernel);
				}
				else
				{
					RBFKernel kernel = new RBFKernel();
					kernel.setGamma(bestGamma);
					svm.setKernel(kernel);
				}
				double currentC = (double) Math.pow(10, i) * ((double)j / 3);
				svm.m_smo.setC(currentC);
				svm.m_smo.buildClassifier(trainingData);
				double tpr = (double)(svm.calcConfusion(testingData)[0]) / svm.calcPositive(testingData);
				double fpr = (double) svm.calcConfusion(testingData)[1]/svm.calcNegative(testingData);	
				System.out.println("For C "+currentC+" the rates are:\nTPR = "+tpr+"\nFPR = "+fpr);
			}
		}
	}
}
