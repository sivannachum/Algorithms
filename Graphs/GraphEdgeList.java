//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 9, 2021
// Description:	Java code to create an undirectional Graph representation via an edge list
//-----------------------------------------------------
package Graphs;
import DGraphs.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Collections;

public class GraphEdgeList extends Graph {
    private int numVertices;
    private ArrayList<Edge> edges;

    // Constructors
    //-------------------------------------
    // Constructor
    // Name:    GraphEdgeList
    // Input: 	none
    // Output:	none
    //          creates an object of type GraphEdgeList with no vertices or edges
    //-------------------------------------
    public GraphEdgeList(){
        this.numVertices = 0;
        this.edges = new ArrayList<Edge>();
    }

    //-------------------------------------
    // Constructor
    // Name:    GraphEdgeList
    // Input: 	the number of vertices for the graph
    // Output:	none
    //          creates an object of type GraphEdgeList with the specified number of vertices
    //-------------------------------------
    public GraphEdgeList(int numVertices){
        this.numVertices = numVertices;
        this.edges = new ArrayList<Edge>();
    }

    //-------------------------------------
    // Constructor
    // Name:    GraphEdgeList
    // Input: 	the number of vertices and edges for the graph
    // Output:	none
    //          creates an object of type GraphEdgeList with the specified number of vertices and edges; the graph is random
    //-------------------------------------
    public GraphEdgeList(int numVertices, int numEdges){
        this.numVertices = numVertices;
        this.edges = new ArrayList<Edge>();
        if (numVertices != 0){
            int i = 0;
            int u = 0;
            int w = 0;
            Random rand = new Random();
            while (i < numEdges){
                u = rand.nextInt(numVertices);
                w = rand.nextInt(numVertices);
                Edge connection = new Edge(u, w);
                edges.add(connection);
                i++;
            }
        }
    }

    // Getters
    //-------------------------------------
    // Function
    // Name:    getNumVertices
    // Input: 	none
    // Output:	the number of vertices in the graph
    //-------------------------------------
    public int getNumVertices(){
        return numVertices;
    }

    //-------------------------------------
    // Function
    // Name:    getEdgeList
    // Input: 	none
    // Output:	the graph's edge list
    //-------------------------------------
    public ArrayList<Edge> getEdgeList(){
        return edges;
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setNumVertices
    // Input: 	the new number of vertices for the graph
    // Output:	none
    //          sets the number of vertices to the given input, creates a new cleared edge list
    //-------------------------------------
    public void setNumVertices(int numVertices){
        this.numVertices = numVertices;
        edges = new ArrayList<Edge>();
    }

    //-------------------------------------
    // Function
    // Name:    setEdgeList
    // Input: 	an edge list
    // Output:	none
    //          sets the graph's edge list to the given input; note that the number of vertices is not adjusted
    //-------------------------------------
    public void setEdgeList(ArrayList<Edge> newList){
        edges = newList;
    }

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addVertices
    // Input: 	the number of vertices to add
    // Output:	none
    //          adds num vertices to the graph
    //-------------------------------------
    public void addVertices(int num){
        numVertices += num;
    }

    //-------------------------------------
    // Function
    // Name:    deleteVertex
    // Input: 	the number of the vertex to delete
    // Output:	none
    //          deletes the vertex from the graph
    //-------------------------------------
    public void deleteVertex(int num){
        numVertices -= 1;
        for (int i = edges.size()-1; i >= 0; i--){
            Edge connection = edges.get(i);
            if (connection.getFirstVertex() == num || connection.getSecondVertex() == num){
                edges.remove(i);
            }
            else if (connection.getFirstVertex() > num && connection.getSecondVertex() > num){
                edges.remove(i);
                edges.add(new Edge(connection.getFirstVertex()-1, connection.getSecondVertex()-1));
            }
            else if (connection.getFirstVertex() > num){
                edges.remove(i);
                edges.add(new Edge(connection.getFirstVertex()-1, connection.getSecondVertex()));
            }
            else if (connection.getSecondVertex() > num){
                edges.remove(i);
                edges.add(new Edge(connection.getFirstVertex(), connection.getSecondVertex()-1));
            }
        }
    }

    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          adds an undirected edge between the vertices u and v in the edge list
    //-------------------------------------
    public void addEdge(int u, int v){
        Edge connection = new Edge(u, v);
        edges.add(connection);
    }

    //-------------------------------------
    // Function
    // Name:    removeEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          remove one undirected edge between the vertices u and v in the edge list
    //-------------------------------------
    public void removeEdge(int u, int v){
        if (u > v){
            int temp = u;
            u = v;
            v = temp;
        }
        int i = 0;
        for (i = 0; i < edges.size(); i++){
            Edge connection = edges.get(i);
            if (connection.getFirstVertex() == u && connection.getSecondVertex() == v){
                break;
            }
        }
       if (i != edges.size()){
           edges.remove(i);
       }
    }

    // Testers
    //-------------------------------------
    // Function - this could be sped up with binary search, which is the original reason I wanted
    //            to implement the Comparable interface for Edge but currently I am just using sequential search
    // Name:    hasEdge
    // Input: 	two vertices u and v
    // Output:	true if the vertices have an edge between them, false otherwise
    //-------------------------------------
    public boolean hasEdge(int u, int v){
        if (u > v){
            int temp = u;
            u = v;
            v = temp;
        }
        for (Edge connection : edges){
            if (connection.getFirstVertex() == u && connection.getSecondVertex() == v){
                return true;
            }
        }
        return false;
    }

    //-------------------------------------
    // Function
    // Name:    isEmpty
    // Input: 	none
    // Output:	true if the graph is empty (has no vertices or edges), false otherwise
    //-------------------------------------
    public boolean isEmpty(){
        return numVertices == 0;
        /*
        return edges.isEmpty();
        */
    }

    //-------------------------------------
    // Function
    // Name:    isSimple
    // Input: 	none
    // Output:	true if the graph is simple (has no self-loops or multiple edges between vertices), false otherwise
    //-------------------------------------
    public boolean isSimple(){
        boolean simple = true;
        Collections.sort(edges);

        Edge prev = null;
        for (int i = 0; i < edges.size(); i++){
            // contains self-loop
            if (edges.get(i).getFirstVertex() == edges.get(i).getSecondVertex()){
                simple = false;
                break;
            }
            // contains multi-edge
            if (prev != null && edges.get(i).equals(prev)){
                simple = false;
                break;
            }
            prev = edges.get(i);
        }

        return simple;
    }

    // Functions for printing
    //-------------------------------------
    // Function
    // Name:    printGraph
    // Input: 	none
    // Output:	none
    //          prints out the edge list representation of the graph
    //-------------------------------------
	public void printGraph(){
        String[] toPrint = new String[numVertices];
        for (int vertex = 0; vertex < numVertices; vertex++) { 
            toPrint[vertex] = "Edges exist from vertex " + (vertex+1) + " to: ";
        }
        for (Edge connection : edges) { 
            toPrint[connection.getFirstVertex()] += (connection.getSecondVertex()+1) + " ";
            if (connection.getFirstVertex() != connection.getSecondVertex()){
                toPrint[connection.getSecondVertex()] += (connection.getFirstVertex()+1) + " ";
            }
        }
        for (String s : toPrint){
            System.out.println(s);
        }
	}

    //-------------------------------------
    // Function
    // Name:    toString
    // Input: 	none
    // Output:	a String representing the graph
    //-------------------------------------
    public String toString(){
        String result = "{\n" + numVertices + ",\n{\n";
        boolean firstElem = true;
        for (Edge connection : edges){
            if (!firstElem) {
                result += ",";
            } else {
                firstElem = false;
            }
            result += "{" + (connection.getFirstVertex()+1) + "," + (connection.getSecondVertex()+1) + "}";
        }
        result += "\n}\n}";
        return result;
    }

    // File I/O
    //-------------------------------------
    // Function - referenced w3schools code for basic file I/O at https://www.w3schools.com/java/java_files_create.asp
    // Name:    writeToFile
    // Input: 	the name of the file to write the graph to
    // Output:	none
    //          writes the serialized version of the graph to a file
    //-------------------------------------
    public void writeToFile(String filename){
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(toString());
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    //-------------------------------------
    // Function - referenced w3schools code for basic file I/O at https://www.w3schools.com/java/java_files_read.asp
    // Name:    readFromFile
    // Input: 	the name of the file to read from
    // Output:	none
    //          creates a graph from the information in the file
    //-------------------------------------
    public void readFromFile(String filename){
        try {
            File graphFile = new File(filename);
            Scanner myReader = new Scanner(graphFile);
            // The first line is just a bracket, so we can skip it
            myReader.nextLine();
            // The second line has the number of vertices
            String data = myReader.nextLine();
            String cleanData = "";
            for (int i = 0; i < data.length()-1; i++){
                cleanData += data.charAt(i);
            }
            // Initialize the graph with the proper number of vertices
            setNumVertices(Integer.parseInt(cleanData));

            // The next line is just a bracket, so we can skip it
            myReader.nextLine();
            // The next line is the edges
            data = myReader.nextLine();
            String u = "";
            String v = "";
            boolean buildingU = true;

            for (int i = 0; i < data.length(); i++){
                if (data.charAt(i) == '{'){
                    buildingU = true;
                    u = "";
                    v = "";
                }
                else if (data.charAt(i)=='}'){
                    addEdge(Integer.parseInt(u)-1, Integer.parseInt(v)-1);
                }
                else if (data.charAt(i)==','){
                    buildingU = false;
                }
                else if (buildingU){
                    u += data.charAt(i);
                }
                else {
                    v += data.charAt(i);
                }
            }
            // At this point, we have gotten all the information we need from the file
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    // Converters
    //-------------------------------------
    // Function
    // Name:    convertToDirectedGraph
    // Input: 	none
    // Output:	a DGraphEdgeList representation of the same graph
    //-------------------------------------
    public DGraphEdgeList convertToDirectedGraph(){
        DGraphEdgeList directed = new DGraphEdgeList(numVertices);
        for (Edge connection : edges){
            directed.addEdge(connection.getFirstVertex(), connection.getSecondVertex());
            if (connection.getFirstVertex() != connection.getSecondVertex()){
                directed.addEdge(connection.getSecondVertex(), connection.getFirstVertex());
            }
        }
        return directed;
    }

    //-------------------------------------
    // Function
    // Name:    convertToAdjMatrix
    // Input: 	none
    // Output:	a GraphAdjMatrix representation of the same graph
    //          Note: multi-edges will be lost
    //-------------------------------------
    public GraphAdjMatrix convertToAdjMatrix(){
        GraphAdjMatrix adjMatrix = new GraphAdjMatrix(numVertices);
        for (Edge connection : edges){
            adjMatrix.addEdge(connection.getFirstVertex(), connection.getSecondVertex());
        }
        return adjMatrix;
    }

    //-------------------------------------
    // Function
    // Name:    convertToAdjList
    // Input: 	none
    // Output:	a GraphAdjList representation of the same graph
    //-------------------------------------
    public GraphAdjList convertToAdjList(){
        GraphAdjList adjList = new GraphAdjList(numVertices);
        for (Edge connection : edges){
            adjList.addEdge(connection.getFirstVertex(), connection.getSecondVertex());
        }
        return adjList;
    }
}
