In this
assignment you will use Weka’s implementation of an SVM classifier (called
SMO). First you will divide the data to training and test set. Then you will
select the best kernel according to the TPR and FPR (with 
 
 
  
  
  
  
  
  
  
  
  
  
  
  
 
 
 

 
). In addition you will plot
the result of each kernel in a ROC graph in excel. After selecting the best kernel
you will try different values for the parameter C (the slack regularization)
and once again you will plot the result for each value in a ROC graph.



Finally, you
will answer some theoretical questions regarding kernel functions and Lagrange
multipliers (at the end of the document).



 



Finding the
best kernel:



1.       Load
the cancer data (cancer.txt).



2.       Divide
the data to training and test set – 80% training
and 20% test.



3.       For
each kernel value, build the SVM classifier on the training set using the SMO
WEKA class.



4.       Calculate
& print to the console the TPR and FPR on the test set.



5.    Select
the best kernel according to the best TPR-
 
FPR (with 
 
).



6.       The
possible values for the kernel are:



a.    Polynomial
Kernel – with one of the following degrees 
 




b.   RBF
Kernel – with one of the following gamma values 
 




7.       In
the attached excel file, fill the TPR and FPR for each kernel in the
appropriate yellow cells. Add a parallel line (to the line from the origin to
(1,1), which is already there) that shows the best kernel on the graph.



 



Finding the
best C value (the slack regularization):



1.       For
the selected kernel, try different C values.



2.       For
each C value, build the SVM classifier with the selected kernel on the training
set using the SMO WEKA class.



3.       Calculate
& print to the console the TPR and FPR on the test set.



4.    The
possible C values are all the combinations of 
 
,                                                                             
where 
 
 and 
 




5.       In
the attached excel file, fill the selected kernel in the gray cell and fill the
TPR and FPR for each C value in the appropriate green cells. The ROC graph will
update automatically. Does your graph make sense?



 



In
order to do the above you need to first install WEKA:



1.       See
instructions in HW1.



Prepare
your Eclipse project:



1.       Create
a project in Eclipse called HomeWork5.



2.       Create
a package called HomeWork5.



3.       Move
the SVM.java and MainHW5.java that you downloaded from the Moodle into this
package.



4.       Add
WEKA to the project (see instructions in HW1).



 



The
following methods are mandatory methods and you can't change
their signatures, but you can add additional methods:



1.       void setKernel: Setting the Weka SMO
classifier kernel.



a.       Input:
Kernel object.



2.       void setC: Setting the C value for the
Weka SMO classifier.



a.       Input:
double.



3.       double getC: Getting the C value for the
Weka SMO classifier.



a.       output:
double.



4.       Int[] calcConfusion: Calculate the TP, FP, TN, FN for a given instances object.



a.       Input: Instances object.



b.      Output: int array of size 4 in this order [TP, FP, TN, FN]. 



 



In order to
calculate the confusion matrix use the following definitions:



·        
recurrence-events
is the 0.0 class and will be the NEGATIVE class



·        
no-recurrence-events
is the 1.0 class and will be the POSITIVE class
