//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 21, 2021
// Description:	Java code to create a directional weighted Graph representation via an adjacency matrix
//              Has functionality to run the Floyd-Warshall all-pairs-shortest-paths algorithm
//-----------------------------------------------------
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DGraphAdjMatrix {
    private int numVertices;
    private int[][] matrix;
    private int[][] shortestPathsLengths;

    // Constructors
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
        this.shortestPathsLengths = new int[numVertices][numVertices];
        for (int row = 0; row < numVertices; row++){
            for (int column = 0; column < numVertices; column++){
                if (row == column){
                    matrix[row][column] = 0;
                }
                else{
                    matrix[row][column] = Integer.MAX_VALUE;
                }
            }
        }
    }

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v and the weight on the edge between them
    // Output:	none
    //          adds a directed edge from vertex u to vertex v with the given weight in the adjacency matrix
    //-------------------------------------
    public void addEdge(int u, int v, int weight){
        matrix[u][v] = weight;
    }

    // Floyd-Warshall Algorithm and Related Funtions
    //-------------------------------------
    // Function
    // Name:    initializeShortestPathsLengths
    // Input: 	none
    // Output:	none
    //          sets the shortestPathsLengths up for the Floyd-Warshall algorithm by initializing the shortest path lengths
    //          with the lengths of any existing edges
    //-------------------------------------
    public void initializeShortestPathsLengths(){
        for (int row = 0; row < numVertices; row++){
            for (int column = 0; column < numVertices; column++){
                shortestPathsLengths[row][column] = matrix[row][column];
            }
        }
    }

    //-------------------------------------
    // Function
    // Name:    floydWarshall
    // Input: 	none
    // Output:	the matrix containing the all pairs shortest paths lengths
    //          updates shortestPathsLengths to determine the lengths of the all pairs shortest paths
    //-------------------------------------
    public int[][] floydWarshall(){
        initializeShortestPathsLengths();
        // Iterate over intermediate vertices
        for (int k = 0; k < numVertices; k++){
            // Iterate over the vertices for which we want the shortest path
            for (int i = 0; i < numVertices; i++){
                for (int j = 0; j < numVertices; j++){
                    if (i == j || shortestPathsLengths[i][k] == Integer.MAX_VALUE || shortestPathsLengths[k][j] == Integer.MAX_VALUE){
                        continue;
                    }
                    else if (shortestPathsLengths[i][j] > shortestPathsLengths[i][k] + shortestPathsLengths[k][j]){
                        shortestPathsLengths[i][j] = shortestPathsLengths[i][k] + shortestPathsLengths[k][j];
                    }
                }
            }
        }
        return shortestPathsLengths;
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
                if (row != column && matrix[row][column] != Integer.MAX_VALUE){
                    System.out.print((column+1) + " (weight " + matrix[row][column] + ") ");
                }
            }
			System.out.println(); 
		} 

        System.out.println();
        System.out.println(toString());
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
                if (row != column && matrix[row][column] != Integer.MAX_VALUE){
                    if (!firstElem) {
                        result += ",";
                    } else {
                        firstElem = false;
                    }
                    result += "{{" + (row+1) + "," + (column+1) + "}," + matrix[row][column] + "}";
                }
            }
        }
        result += "\n}\n}";
        return result;
    }

    //-------------------------------------
    // Function
    // Name:    printShortestPathsLengths
    // Input: 	none
    //          assumes floydWarshall has already been called
    // Output:	prints the contents of the matrix shortestPathsLengths
    //-------------------------------------
    public void printShortestPathsLengths(){
        for (int row = 0; row < numVertices; row++){
            for (int column = 0; column < numVertices; column++){
                System.out.print(shortestPathsLengths[row][column] + " ");
            }
            System.out.println();
        }
        /*
        // Alternative method for printing if import java.util.Arrays
        for (int[] row : shortestPathsLengths){
            System.out.println(Arrays.toString(row));
        }
        */
    }

    // File I/O
    //-------------------------------------
    // Function
    // Name:    writeShortestPathsLengthsToFile
    // Input: 	the name of the file to which to write the shortest paths lengths
    //          assumes floydWarshall has already been called
    // Output:	writes the shortest paths lengths of the graph to the file in the format of a matrix
    //          Ex. 0 INF 4
    //              5  0  2
    //              10 3  3
    //-------------------------------------
    public void writeShortestPathsLengthsToFile(String filename){
        try {
            String toWrite = "";
            for (int row = 0; row < numVertices; row++){
                for (int column = 0; column < numVertices; column++){
                    toWrite += shortestPathsLengths[row][column] + " ";
                }
                toWrite += "\n";
            }
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(toWrite);
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
