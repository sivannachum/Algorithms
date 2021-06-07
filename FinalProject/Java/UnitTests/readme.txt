README
Zampa Provenzale + Sivan Nachum

4/30/21

What is in the FinalProject folder:
Data // contains graph data on which to run the Java code and output from the Java code for visualization in Mathematica
Java
Mathematica // contains Mathematica visualization for the Java code


**** Organization of "Java" folder:
Classes // contains Java classes after compilation via command line
Src // contains source code for performing the stable matching algorithm, see below for details
Demos // contains the main class, see below for details
UnitTests // contains instructions for running code and viewing demos

Note: 
- run the java demos from the UnitTests, using the instructions from stablematchingdemos.txt
- run the java program from the UnitTests, using the instructions from run.txt.

*** Visualize using the Mathematica notebook provided:
- 1 is for viewing the stable matchings after running the code

The Mathematica notebook takes the input from:
Data/GrfStb


**** Src folder:
EdgeListGraph and MatchingGraph directed weighted graph representations. 
The EdgeListGraph can be initialized by reading a graph from a file and can be converted to a MatchingGraph. 
The MatchingGraph has functionality to figure out stable matchings using the Gale-Shapley algorithm.
There is also a class called StableMatchingMain in the Demos folder which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph and a folder path to which to write the stable matching edges.
Each class indicates author at the top.
For classes created in collaboration, in-line comments indicate where each author was typing.
All code writing unrelated to graphics was done over Zoom, so even if a team member was not typing,
they were present and contributing to the class' design.