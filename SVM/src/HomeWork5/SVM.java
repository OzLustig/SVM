package HomeWork5;

import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.Kernel;
import weka.core.Instance;
import weka.core.Instances;

public class SVM {
	public SMO m_smo;
	public Kernel m_kernel;
	private double m_c;

	public SVM() {
		this.m_smo = new SMO();
	}
	
	public void setKernel(Kernel kernel)
	{
		this.m_kernel=kernel;
		m_smo.setKernel(kernel);
	}
	
	public void setC(double c)
	{
		this.m_c=c;
	}
	
	public double getC()
	{
		return m_c;
	}
	
	public void buildClassifier(Instances instances) throws Exception{
		m_smo.buildClassifier(instances);
	}
	
	public int calcPositive(Instances data)
	{
		int positive = 0;
		
		for (Instance instance : data) 
		{
			if(instance.classValue()==1.0)
				positive++;
		}
		return positive;
	}
	
	public int calcNegative(Instances data)
	{
		int negative = 0;
		
		for (Instance instance : data) 
		{
			if(instance.classValue()==0.0)
				negative++;
		}
		return negative;
	}
	
	public int[] calcConfusion(Instances instances) throws Exception
	{
		Instances truePositive = new Instances(instances, 0, 0);
		Instances trueNegative = new Instances(instances, 0, 0);
		Instances falsePositive = new Instances(instances, 0, 0);
		Instances falseNegative = new Instances(instances, 0, 0);
		
		for (Instance instance : instances) 
		{
			if(m_smo.classifyInstance(instance) == 1.0 && instance.classValue()==1.0)
				truePositive.add(instance);
			if(m_smo.classifyInstance(instance) == 0.0 && instance.classValue()==1.0)
				trueNegative.add(instance);
			if(m_smo.classifyInstance(instance) == 1.0 && instance.classValue()==0.0)
				falsePositive.add(instance);
			if(m_smo.classifyInstance(instance) == 0.0 && instance.classValue()==0.0)
				falseNegative.add(instance);
		}
		int[] confusion = new int[4];
		confusion[0] = truePositive.size();
		confusion[1] = falsePositive.size();
		confusion[2] = trueNegative.size();
		confusion[3] = falseNegative.size();
		return confusion;
	}
}
