//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 9, 2021
// Description:	Java code to create a directional Graph representation via an adjacency matrix
//-----------------------------------------------------
package DGraphs;
import Graphs.*;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DGraphAdjMatrix extends Graph {
    private int numVertices;
    private int[][] matrix;

    // Constructors
    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjMatrix
    // Input: 	none
    // Output:	none
    //          creates an object of type DGraphAdjMatrix with no vertices or edges
    //-------------------------------------
    public DGraphAdjMatrix(){
        this.numVertices = 0;
        this.matrix = null;
    }

    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjMatrix
    // Input: 	the number of vertices for the graph
    // Output:	none
    //          creates an object of type DGraphAdjMatrix with the specified number of vertices
    //-------------------------------------
    public DGraphAdjMatrix(int numVertices){
        this.numVertices = numVertices;
        this.matrix = new int[numVertices][numVertices];
        for (int row = 0; row < numVertices; row++){
            for (int column = 0; column < numVertices; column++){
                matrix[row][column] = 0;
            }
        }
    }

    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjMatrix
    // Input: 	the number of vertices and edges for the graph
    // Output:	none
    //          creates an object of type DGraphAdjMatrix with the specified number of vertices and edges, if possible;
    //          the graph is random
    //-------------------------------------
    public DGraphAdjMatrix(int numVertices, int edges){
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices];
        for (int row = 0; row < numVertices; row++){
            for (int column = 0; column < numVertices; column++){
                matrix[row][column] = 0;
            }
        }
        if (numVertices != 0){
            int maxNumberEdges = numVertices*numVertices;
            if (edges >= maxNumberEdges){
                for (int row = 0; row < numVertices; row++){
                    for (int column = 0; column < numVertices; column++){
                        matrix[row][column] = 1;
                    }
                }
            } else {
                int i = 0;
                int u = 0;
                int w = 0;
                Random rand = new Random();
                while (i < edges){
                    u = rand.nextInt(numVertices);
                    w = rand.nextInt(numVertices);
                    while (matrix[u][w] == 1){
                        u = rand.nextInt(numVertices);
                        w = rand.nextInt(numVertices);
                    }
                    matrix[u][w] = 1;
                    i++;
                }
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
    // Name:    getAdjacencyMatrix
    // Input: 	none
    // Output:	the graph's adjacency matrix
    //-------------------------------------
    public int[][] getAdjacencyMatrix(){
        return matrix;
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setNumVertices
    // Input: 	the new number of vertices for the graph
    // Output:	none
    //          sets the number of vertices to the given input, creates a new cleared adjaceny matrix
    //-------------------------------------
    public void setNumVertices(int numVertices){
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices];
        for (int row = 0; row < numVertices; row++){
            for (int column = 0; column < numVertices; column++){
                matrix[row][column] = 0;
            }
        }
    }

    //-------------------------------------
    // Function
    // Name:    setAdjacencyMatrix
    // Input: 	a n x n adjacency matrix
    // Output:	none
    //          sets the graph's adjacency matrix to the given input, updates the number of vertices accordingly
    //-------------------------------------
    public void setAdjacencyMatrix(int[][] newMatrix){
        numVertices = newMatrix.length;
        matrix = newMatrix;
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
        int[][] newMatrix = new int[numVertices+num][numVertices+num];
        for (int row = 0; row < numVertices; row++){
            for (int column = 0; column < numVertices; column++){
                newMatrix[row][column] = matrix[row][column];
            }
        }
        for (int row = numVertices; row < numVertices+num; row++){
            for (int column = numVertices; column < numVertices+num; column++){
                newMatrix[row][column] = 0;
            }
        }
        matrix = newMatrix;
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
        int[][] newMatrix = new int[numVertices-1][numVertices-1];
        for (int row = 0; row < numVertices; row++){
            for (int column = 0; column < numVertices; column++){
                if (row > num && column > num){
                    newMatrix[row-1][column-1] = matrix[row][column];
                }
                else if (row > num){
                    newMatrix[row-1][column] = matrix[row][column];
                }
                else if (column > num){
                    newMatrix[row][column-1] = matrix[row][column];
                }
                else if (row == num){
                    continue;
                }
                else if (column == num){
                    continue;
                }
                else{
                    newMatrix[row][column] = matrix[row][column];
                }
            }
        }
        matrix = newMatrix;
        numVertices -= 1;
    }

    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          adds a directed edge from vertex u to vertex v in the adjacency matrix
    //-------------------------------------
    public void addEdge(int u, int v){
        matrix[u][v] = 1;
    }

    //-------------------------------------
    // Function
    // Name:    removeEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          removes a directed edge from vertex u to vertex v in the adjacency matrix
    //-------------------------------------
    public void removeEdge(int u, int v){
        matrix[u][v] = 0;
    }

    // Testers
    //-------------------------------------
    // Function
    // Name:    hasEdge
    // Input: 	two vertices u and v
    // Output:	true if there is a directed edge from vertex u to vertex v, false otherwise
    //-------------------------------------
    public boolean hasEdge(int u, int v){
        return matrix[u][v] == 1;
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
        for (int row = 0; row < numVertices; row++){
            for (int column = 0; column < numVertices; column++){
                if (matrix[row][column] == 1){
                    empty = false;
                    break;
                }
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
    //          prints out the adjacency matrix representation of the graph
    //-------------------------------------
	public void printGraph(){
		for (int row = 0; row < numVertices; row++) { 
            System.out.print("Edges exist from vertex " + (row+1) + " to: ");
            for (int column = 0; column < numVertices; column++){
                if (matrix[row][column] == 1){
                    System.out.print((column+1) + " ");
                }
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
        for (int row = 0; row < numVertices; row++){
            for (int column = 0; column < numVertices; column++){
                if (matrix[row][column] == 1){
                    if (!firstElem) {
                        result += ",";
                    } else {
                        firstElem = false;
                    }
                    result += "{" + (row+1) + "," + (column+1) + "}";
                }
            }
        }
        result += "\n}\n}";
        return result;
    }

    // File I/O
    //-------------------------------------
    // Function - referenced w3schools code at https://www.w3schools.com/java/java_files_create.asp
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
    // Output:	a GraphAdjMatrix representation of the same graph
    //-------------------------------------
    public GraphAdjMatrix convertToUndirectedGraph(){
        GraphAdjMatrix undirected = new GraphAdjMatrix(numVertices);
        for (int row = 0; row < numVertices; row++) {
            for (int column = 0; column < numVertices; column++){
                if (matrix[row][column] == 1){
                    undirected.addEdge(row, column);
                }
            }
        }
        return undirected;
    }

    //-------------------------------------
    // Function
    // Name:    convertToDAdjList
    // Input: 	none
    // Output:	a DGraphAdjList representation of the same graph
    //-------------------------------------
    public DGraphAdjList convertToDAdjList(){
        DGraphAdjList dAdjList = new DGraphAdjList(numVertices);
        for (int row = 0; row < numVertices; row++) {
            for (int column = 0; column < numVertices; column++){
                if (matrix[row][column] == 1){
                    dAdjList.addEdge(row, column);
                }
            }
        }
        return dAdjList;
    }

    //-------------------------------------
    // Function
    // Name:    convertToDEdgeList
    // Input: 	none
    // Output:	a DGraphEdgeList representation of the same graph
    //-------------------------------------
    public DGraphEdgeList convertToDEdgeList(){
        DGraphEdgeList dEdgeList = new DGraphEdgeList(numVertices);
        for (int row = 0; row < numVertices; row++) {
            for (int column = 0; column < numVertices; column++){
                if (matrix[row][column] == 1){
                    dEdgeList.addEdge(row, column);
                }
            }
        }
        return dEdgeList;
    }
}
