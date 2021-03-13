//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 9, 2021
// Description:	Java code to create a directional Graph representation via an adjacency list
//-----------------------------------------------------
package DGraphs;
import Graphs.*;
import java.util.Random;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DGraphAdjList extends Graph {
    private int numVertices;
    private LinkedList<Integer>[] adjLists;

    // Constructors
    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjList
    // Input: 	none
    // Output:	none
    //          creates an object of type DGraphAdjList with no vertices or edges
    //-------------------------------------
    public DGraphAdjList(){
        this.numVertices = 0;
        this.adjLists = null;
    }

    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjList
    // Input: 	the number of vertices for the graph
    // Output:	none
    //          creates an object of type DGraphAdjList with the specified number of vertices
    //-------------------------------------
    public DGraphAdjList(int numVertices){
        this.numVertices = numVertices;
        this.adjLists = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++){
            adjLists[i] = new LinkedList<Integer>();
        }
    }

    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjList
    // Input: 	the adjacency lists for the graph
    // Output:	none
    //          creates an object of type DGraphAdjList with the given adjacency lists and the corresponding number of vertices
    //-------------------------------------
    public DGraphAdjList(LinkedList<Integer>[] adjLists){
        this.numVertices = adjLists.length;
        this.adjLists = adjLists;
    }

    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjList
    // Input: 	the number of vertices and edges for the graph
    // Output:	none
    //          creates an object of type DGraphAdjList with the specified number of vertices and edges; the graph is random
    //-------------------------------------
    public DGraphAdjList(int numVertices, int numEdges){
        this.numVertices = numVertices;
        this.adjLists = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++){
            adjLists[i] = new LinkedList<Integer>();
        }
        if (numVertices != 0){
            int i = 0;
            int u = 0;
            int w = 0;
            Random rand = new Random();
            while (i < numEdges){
                u = rand.nextInt(numVertices);
                w = rand.nextInt(numVertices);
                adjLists[u].add(w);
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
    // Name:    getAdjacencyLists
    // Input: 	none
    // Output:	the graph's adjacency list
    //-------------------------------------
    public LinkedList<Integer>[] getAdjacencyLists(){
        return adjLists;
    }

    //-------------------------------------
    // Function
    // Name:    getNeighbors
    // Input: 	the number of the vertex you want the neighbors of
    // Output:	the adjacency list of that vertex (which will show who that vertex is connected to)
    //-------------------------------------
    public LinkedList<Integer> getNeighbors(int vertex){
        return adjLists[vertex];
    }

    //-------------------------------------
    // Function
    // Name:    getNumNeighbors
    // Input: 	the number of the vertex you want the number of neighbors of
    // Output:	the number of neighbors that vertex has
    //-------------------------------------
    public int getNumNeighbors(int vertex){
        return adjLists[vertex].size();
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setNumVertices
    // Input: 	the new number of vertices for the graph
    // Output:	none
    //          sets the number of vertices to the given input, creates a new cleared adjacency list
    //-------------------------------------
    public void setNumVertices(int numVertices){
        this.numVertices = numVertices;
        adjLists = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++){
            adjLists[i] = new LinkedList<Integer>();
        }
    }

    //-------------------------------------
    // Function
    // Name:    setAdjacencyList
    // Input: 	an adjacency list
    // Output:	none
    //          sets the graph's adjacency list to the given input, updates the number of vertices accordingly
    //-------------------------------------
    public void setAdjacencyList(LinkedList<Integer>[] newList){
        numVertices = newList.length;
        adjLists = newList;
    }

    // Depth First Search
    //-------------------------------------
    // Function
    // Name:    dfs
    // Input: 	the root vertex from which to conduct depth first search
    // Output:	none
    //          conducts depth-first search by creating a DGraphDFSAdjList object and having it do the dfs
    //          prints updates along the way
    //-------------------------------------
    public void dfs(int root){
        DGraphDFSAdjList graph = new DGraphDFSAdjList(adjLists);
        graph.dfsR(root);
        System.out.println();
        graph.printVisitedVertices();
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
        LinkedList<Integer>[] newAdjLists = new LinkedList[numVertices+num];
        int i = 0;
        for (i = 0; i < numVertices; i++){
            newAdjLists[i] = adjLists[i];
        }
        while (i < numVertices+num){
            newAdjLists[i] = new LinkedList<Integer>();
            i++;
        }
        adjLists = newAdjLists;
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
        LinkedList<Integer>[] newAdjLists = new LinkedList[numVertices];
        int i = 0;
        for (i = 0; i < numVertices; i++){
            if (i < num){
                newAdjLists[i] = adjLists[i];
            } else {
                break;
            }
        }
        while (i < numVertices){
            newAdjLists[i] = adjLists[i+1];
            i++;
        }
        for (i = 0; i < numVertices; i++){
            int index = newAdjLists[i].indexOf(num);
            while (index != -1){
                newAdjLists[i].remove(index);
                index = newAdjLists[i].indexOf(num);
            }
            for (int j = num+1; j <= numVertices; j++){
                index = newAdjLists[i].indexOf(j);
                while (index != -1){
                    newAdjLists[i].remove(index);
                    newAdjLists[i].add(j-1);
                    index = newAdjLists[i].indexOf(j);
                }
            }
        }
        adjLists = newAdjLists;
    }

    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          adds a directed edge from vertex u to vertex v in the adjacency list
    //-------------------------------------
    public void addEdge(int u, int v){
        adjLists[u].add(v);
    }

    //-------------------------------------
    // Function
    // Name:    removeEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          removes one directed edge from vertex u to vertex v in the adjacency list
    //-------------------------------------
    public void removeEdge(int u, int v){
        int index = adjLists[u].indexOf(v);
        if (index != -1){
            adjLists[u].remove(index);
        }
    }

    // Testers
    //-------------------------------------
    // Function
    // Name:    hasEdge
    // Input: 	two vertices u and v
    // Output:	true if there is a directed edge from vertex u to vertex v, false otherwise
    //-------------------------------------
    public boolean hasEdge(int u, int v){
        return adjLists[u].contains(v);
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
        boolean empty = true;
        for (int i = 0; i < numVertices; i++){
            if (!adjLists[i].isEmpty()){
                empty = false;
                break;
            }
        }
        return empty;
        */
    }

    // Functions for printing
    //-------------------------------------
    // Function
    // Name:    printGraph
    // Input: 	none
    // Output:	none
    //          prints out the adjacency list representation of the graph
    //-------------------------------------
	public void printGraph(){
		for (int vertex = 0; vertex < numVertices; vertex++) { 
            System.out.print("Edges exist from vertex " + (vertex+1) + " to: ");
            for (int connection = 0; connection < adjLists[vertex].size(); connection++){
                System.out.print((adjLists[vertex].get(connection)+1) + " ");
            }
			System.out.println(); 
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
        for (int vertex = 0; vertex < numVertices; vertex++){
            for (int connection = 0; connection < adjLists[vertex].size(); connection++){
                if (!firstElem) {
                    result += ",";
                } else {
                    firstElem = false;
                }
                result += "{" + (vertex+1) + "," + (adjLists[vertex].get(connection)+1) + "}";
            }
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
    // Name:    convertToUndirectedGraph
    // Input: 	none
    // Output:	a GraphAdjList representation of the same graph
    //-------------------------------------
    public GraphAdjList convertToUndirectedGraph(){
        GraphAdjList undirected = new GraphAdjList(numVertices);
        for (int vertex = 0; vertex < numVertices; vertex++){
            for (int connection = 0; connection < adjLists[vertex].size(); connection++){
                undirected.addEdge(vertex, adjLists[vertex].get(connection));
            }
        }
        return undirected;
    }

    //-------------------------------------
    // Function
    // Name:    convertToDAdjMatrix
    // Input: 	none
    // Output:	a DGraphAdjMatrix representation of the same graph
    //          Note: multi-edges will be lost
    //-------------------------------------
    public DGraphAdjMatrix convertToDAdjMatrix(){
        DGraphAdjMatrix dAdjMatrix = new DGraphAdjMatrix(numVertices);
        for (int vertex = 0; vertex < numVertices; vertex++){
            for (int connection = 0; connection < adjLists[vertex].size(); connection++){
                dAdjMatrix.addEdge(vertex, adjLists[vertex].get(connection));
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
        DGraphEdgeList dEdgeList = new DGraphEdgeList(numVertices);
        for (int vertex = 0; vertex < numVertices; vertex++){
            for (int connection = 0; connection < adjLists[vertex].size(); connection++){
                dEdgeList.addEdge(vertex, adjLists[vertex].get(connection));
            }
        }
        return dEdgeList;
    }
}
