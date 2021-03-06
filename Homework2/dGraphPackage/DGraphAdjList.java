//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 4, 2021
// Description:	Java code to create a directional Graph representation via an adjacency list
//-----------------------------------------------------
import java.util.Random;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DGraphAdjList extends GraphAdjList {
    // Constructors
    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjList
    // Input: 	none
    // Output:	none
    //          creates an object of type DGraphAdjList with no vertices or edges
    //-------------------------------------
    public DGraphAdjList(){
        super();
    }

    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjList
    // Input: 	the number of vertices for the graph
    // Output:	none
    //          creates an object of type DGraphAdjList with the specified number of vertices
    //-------------------------------------
    public DGraphAdjList(int numVertices){
        super(numVertices);
    }

    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjList
    // Input: 	the number of vertices and edges for the graph
    // Output:	none
    //          creates an object of type DGraphAdjList with the specified number of vertices and edges; the graph is random
    //-------------------------------------
    public DGraphAdjList(int numVertices, int numEdges){
        super(numVertices);
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numVertices; i++){
            edges.add(new ArrayList<Integer>());
        }
        if (numVertices != 0){
            int i = 0;
            int u = 0;
            int w = 0;
            Random rand = new Random();
            while (i < numEdges){
                u = rand.nextInt(numVertices);
                w = rand.nextInt(numVertices);
                edges.get(u).add(w);
                i++;
            }
        }
        super.setAdjacencyList(edges);
    }

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          adds an directed edge from vertex u to vertex v in the adjacency list
    //-------------------------------------
    @Override
    public void addEdge(int u, int v){
        ArrayList<ArrayList<Integer>> edges = super.getAdjacencyList();
        edges.get(u).add(v);
        super.setAdjacencyList(edges);
    }

    //-------------------------------------
    // Function
    // Name:    removeEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          removes one directed edge from vertex u to vertex v in the adjacency list
    //-------------------------------------
    @Override
    public void removeEdge(int u, int v){
        ArrayList<ArrayList<Integer>> edges = super.getAdjacencyList();
        int index = edges.get(u).indexOf(v);
        if (index != -1){
            edges.get(u).remove(index);
        }
        super.setAdjacencyList(edges);
    }

    // Functions for printing
    //-------------------------------------
    // Function
    // Name:    toString
    // Input: 	none
    // Output:	a String representing the graph
    //-------------------------------------
    @Override
    public String toString(){
        ArrayList<ArrayList<Integer>> edges = super.getAdjacencyList();
        String result = "{\n" + super.getNumVertices() + ",\n{\n";
        boolean firstElem = true;
        for (int vertex = 0; vertex < super.getNumVertices(); vertex++){
            for (int connection = 0; connection < edges.get(vertex).size(); connection++){
                if (!firstElem) {
                    result += ",";
                } else {
                    firstElem = false;
                }
            result += "{" + (vertex+1) + "," + (edges.get(vertex).get(connection)+1) + "}";
            }
        }
        result += "\n}\n}";
        return result;
    }

    /*
    // Converters
    //-------------------------------------
    // Function
    // Name:    convertToDAdjMatrix
    // Input: 	none
    // Output:	a DGraphAdjMatrix representation of the same graph
    //          Note: multi-edges will be lost
    //-------------------------------------
    public DGraphAdjMatrix convertToDAdjMatrix(){
        DGraphAdjMatrix dAdjMatrix = new DGraphAdjMatrix(super.getNumVertices());
        for (int vertex = 0; vertex < super.getNumVertices(); vertex++){
            for (int connection = 0; connection < edges.get(vertex).size(); connection++){
                dAdjMatrix.addEdge(vertex, edges.get(vertex).get(connection));
            }
        }
        return dAdjMatrix;
    }

    //-------------------------------------
    // Function
    // Name:    convertToDEdgeList
    // Input: 	none
    // Output:	a DGraphEdgeList representation of the same graph
    //-------------------------------------
    public DGraphEdgeList convertToDEdgeList(){
        DGraphEdgeList dEdgeList = new DGraphEdgeList(super.getNumVertices());
        for (int vertex = 0; vertex < super.getNumVertices(); vertex++){
            for (int connection = 0; connection < edges.get(vertex).size(); connection++){
                dEdgeList.addEdge(vertex, edges.get(vertex).get(connection));
            }
        }
        return dEdgeList;
    }
    */
}
