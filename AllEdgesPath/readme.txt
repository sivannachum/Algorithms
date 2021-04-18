README
Sivan Nachum

4/10/21

What is in the Portfolio:
Data // contains graph data one which to run the Java code and output from the Java code for visualization in Mathematica
Java
Mathematica // contains Mathematica visualizations for the Java code


**** Organization of "Java" folder:
Classes // contains Java classes after compilation via command line
Src // contains source code for various Java applications, see below for details
UnitTests // contains instructions for running code and viewing demos

Note: 
- run the java demos from the UnitTests, using the instructions from demos.txt
- run the java program from the UnitTests, using the instructions from run.txt.

*** Visualize using one of the two Mathematica notebooks provided:
- 1 is for visualizing the graphs
- 2 is for visualizing both the graphs and the components

The Mathematica notebooks take the input from:
Data/GrfCmp


**** Src folder:
SCC folder:
DGraphEdgeList and DGraphAdjList directed graph representations. 
The DGraphEdgeList can be initialized by reading a graph from a file and can be converted to a DGraphAdjList. 
The DGraphAdjList has functionality to figure out the strongly connected components of the graph.
There is also a class called SCCMain which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph and a folder path to which to write the strongly connected components.

AllEdgesPath folder (extra credit):
GraphEdgeList and GraphAdjList undirected graph representations. 
The GraphEdgeList can be initialized by reading a graph from a file and can be converted to a GraphAdjList. 
The GraphAdjList has functionality to figure out a path through the graph that traverses each edge exactly once in each direction.
This is to solve the problem of efficient garbage collection. Note that this problem assumes the graph is connected.
There is also a class called AllEdgesPathMain which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph and 
a folder path to which to write the path that traverses each edge exactly once in each direction.