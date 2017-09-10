1 cvs2arff.java
This file is used to convert dataset to arff file.
you should use the “letter mod 1.csv” as input file.

Input:
args[0]: letter mod 1.csv
args[1]: define a location for new arff file, e.g. ”test2.arff”

2 project.java
This file is used to perform the weka analysis.
Before using the file, you should build path to the weka.jar
** It takes quite long to run the MLP analysis if you have a large training times or number of layers.

Input
args[0]: the file you produced using cvs2arff.java (test2.arff)