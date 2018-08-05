In this assignment you will use Weka’s implementation of an SVM classifier (called SMO). First you will divide the data to training and test set. Then you will select the best kernel according to the TPR and FPR (with α=1). In addition you will plot the result of each kernel in a ROC graph in excel. After selecting the best kernel you will try different values for the parameter C (the slack regularization) and once again you will plot the result for each value in a ROC graph.
Finally, you will answer some theoretical questions regarding kernel functions and Lagrange multipliers (at the end of the document).

Finding the best kernel:
	Load the cancer data (cancer.txt).
	Divide the data to training and test set – 80% training and 20% test.
	For each kernel value, build the SVM classifier on the training set using the SMO WEKA class.
	Calculate & print to the console the TPR and FPR on the test set.
	Select the best kernel according to the best TPR-αFPR (with α=1).
	The possible values for the kernel are:
	Polynomial Kernel – with one of the following degrees {2,3,4}
	RBF Kernel – with one of the following gamma values {1/100,1/10  ,1}
	In the attached excel file, fill the TPR and FPR for each kernel in the appropriate yellow cells. Add a parallel line (to the line from the origin to (1,1), which is already there) that shows the best kernel on the graph.

Finding the best C value (the slack regularization):
	For the selected kernel, try different C values.
	For each C value, build the SVM classifier with the selected kernel on the training set using the SMO WEKA class.
	Calculate & print to the console the TPR and FPR on the test set.
	The possible C values are all the combinations of {〖10〗^i*j/3  },                                                                              where i={1,0,-1,-2,-3,-4} and j={3,2,1}
	In the attached excel file, fill the selected kernel in the gray cell and fill the TPR and FPR for each C value in the appropriate green cells. The ROC graph will update automatically. Does your graph make sense?

In order to do the above you need to first install WEKA:
	See instructions in HW1.
Prepare your Eclipse project:
	Create a project in Eclipse called HomeWork5.
	Create a package called HomeWork5.
	Move the SVM.java and MainHW5.java that you downloaded from the Moodle into this package.
	Add WEKA to the project (see instructions in HW1).

The following methods are mandatory methods and you can't change their signatures, but you can add additional methods:
	void setKernel: Setting the Weka SMO classifier kernel.
	Input: Kernel object.
	void setC: Setting the C value for the Weka SMO classifier.
	Input: double.
	double getC: Getting the C value for the Weka SMO classifier.
	output: double.
	Int[] calcConfusion: Calculate the TP, FP, TN, FN for a given instances object.
	Input: Instances object.
	Output: int array of size 4 in this order [TP, FP, TN, FN]. 

