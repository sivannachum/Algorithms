GraphEdgeList and GraphAdjList undirected graph representations. 
The GraphEdgeList can be initialized by reading a graph from a file and can be converted to a GraphAdjList. 
The GraphAdjList has functionality to figure out a path through the graph that traverses each edge exactly once in each direction.
This is to solve the problem of efficient garbage collection. Note that this problem assumes the graph is connected.
There is also a class called AllEdgesPathMain which can be run to interact with the graph classes.
It requires the name of a file from which to read a graph and a file to which to write the path that traverses each edge exactly once in each direction.
