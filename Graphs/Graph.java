//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 7, 2021
// Description:	Java code for an abstract class representing an undirectional Graph
//-----------------------------------------------------
package Graphs;
public abstract class Graph {
    // Getters
    //-------------------------------------
    // Function
    // Name:    getNumVertices
    // Input: 	none
    // Output:	the number of vertices in the graph
    //-------------------------------------
    public abstract int getNumVertices();

    // Setters
    //-------------------------------------
    // Function
    // Name:    setNumVertices
    // Input: 	the new number of vertices for the graph
    // Output:	none
    //          sets the number of vertices to the given input
    //-------------------------------------
    public abstract void setNumVertices(int numVertices);

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addVertices
    // Input: 	the number of vertices to add
    // Output:	none
    //          adds num vertices to the graph
    //-------------------------------------
    public abstract void addVertices(int num);

    //-------------------------------------
    // Function
    // Name:    deleteVertex
    // Input: 	the number of the vertex to delete
    // Output:	none
    //          deletes the vertex from the graph
    //-------------------------------------
    public abstract void deleteVertex(int num);

    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          adds an undirected edge between the vertices u and v
    //-------------------------------------
    public abstract void addEdge(int u, int v);

    //-------------------------------------
    // Function
    // Name:    removeEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          removes one undirected edge between the vertices u and v
    //-------------------------------------
    public abstract void removeEdge(int u, int v);

    // Testers
    //-------------------------------------
    // Function
    // Name:    hasEdge
    // Input: 	two vertices u and v
    // Output:	true if the vertices have an edge between them, false otherwise
    //-------------------------------------
    public abstract boolean hasEdge(int u, int v);

    //-------------------------------------
    // Function
    // Name:    isEmpty
    // Input: 	none
    // Output:	true if the graph is empty (has no vertices or edges), false otherwise
    //-------------------------------------
    public abstract boolean isEmpty();

    // Functions for printing
    //-------------------------------------
    // Function
    // Name:    printGraph
    // Input: 	none
    // Output:	none
    //          prints out the graph
    //-------------------------------------
	public abstract void printGraph();

    //-------------------------------------
    // Function
    // Name:    toString
    // Input: 	none
    // Output:	a String representing the graph
    //-------------------------------------
    public abstract String toString();

    // File I/O
    //-------------------------------------
    // Function
    // Name:    writeToFile
    // Input: 	the name of the file to write the graph to
    // Output:	none
    //          writes the serialized version of the graph to a file
    //-------------------------------------
    public abstract void writeToFile(String filename);

    //-------------------------------------
    // Function
    // Name:    readFromFile
    // Input: 	the name of the file to read from
    // Output:	none
    //          creates a graph from the information in the file
    //-------------------------------------
    public abstract void readFromFile(String filename);
}
