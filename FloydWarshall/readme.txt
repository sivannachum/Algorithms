README
Sivan Nachum

4/25/21

What is in the Homework8 folder:
Data // contains graph data on which to run the Java code and output from the Java code for visualization in Mathematica
Java
Mathematica // contains Mathematica visualizations for the Java code


**** Organization of "Java" folder:
Classes // contains Java classes after compilation via command line
Src // contains source code for various Java applications, see below for details
Demos // contains main classes, see below for details
UnitTests // contains instructions for running code and viewing demos

Note: 
- run the java demos from the UnitTests, using the instructions from the various available demos.txt
- run the java program from the UnitTests, using the instructions from run.txt.

*** Visualize using one of the three Mathematica notebooks provided:
- 5 is for visualizing weighted digraphs from files
- 6 is for visualizing the minimum spanning tree produced by Kruskal's algorithm
- 7 is for visualizing the minimum spanning tree produced by Prim's algorithm

The Mathematica notebooks take the input from:
Data/GrfWTxt, Data/GrfKTre and Data/GrfPTre, respectively


**** Src folder:
FloydWarshall folder:
DGraphEdgeList and DGraphAdjMatrix directed weighted graph representations. 
The DGraphEdgeList can be initialized by reading a graph from a file and can be converted to a DGraphAdjMatrix. 
The DGraphAdjMatrix has functionality to figure out the all pairs shortest paths lengths using the Floyd-Warshall algorithm.
There is also a class called FloydWarshallMain in the Demos folder which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph 
and a folder path to which to write the shortest paths lengths information.

Kruskal folder:
GraphEdgeList undirected weighted graph representation. 
The GraphEdgeList can be initialized by reading a graph from a file and
has functionality to figure out the minimum spanning tree using Kruskal's algorithm.
There is also a class called KruskalMain in the Demos folder which can be run to interact with the graph class.
It requires the name of a file from which to read a graph and a folder path to which to write the minimum spanning tree edges.

Prim folder:
GraphEdgeList and GraphAdjList undirected weighted graph representations. 
The GraphEdgeList can be initialized by reading a graph from a file and can be converted to a GraphAdjList. 
The GraphAdjList has functionality to figure out the minimum spanning tree using Prim's algorithm.
There is also a class called PrimMain in the Demos folder which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph and a folder path to which to write the minimum spanning tree edges.