//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 24, 2021
// Description:	Java code to create an undirectional Graph representation via an adjacency matrix
//-----------------------------------------------------
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphAdjMatrix {
    private int vertices;
    private int[][] matrix;

    //-------------------------------------
    // Constructor
    // Name:    GraphAdjMatrix
    // Input: 	none
    // Output:	none
    //          creates an object of type GraphAdjMatrix with no vertices or edges
    //-------------------------------------
    public GraphAdjMatrix(){
        this.vertices = 0;
        this.matrix = null;
    }

    //-------------------------------------
    // Constructor
    // Name:    GraphAdjMatrix
    // Input: 	the number of vertices for the graph
    // Output:	none
    //          creates an object of type GraphAdjMatrix with the specified number of vertices
    //-------------------------------------
    public GraphAdjMatrix(int vertices){
        this.vertices = vertices;
        this.matrix = new int[vertices][vertices];
    }

    //-------------------------------------
    // Constructor
    // Name:    GraphAdjMatrix
    // Input: 	the number of vertices and edges for the graph
    // Output:	none
    //          creates an object of type GraphAdjMatrix with the specified number of vertices and edges, if possible;
    //          the graph is random
    //-------------------------------------
    public GraphAdjMatrix(int vertices, int edges){
        this.vertices = vertices;
        matrix = new int[vertices][vertices];
        if (vertices != 0){
            int maxNumberEdges = 0;
            for (int i = 1; i <= vertices; i++){
                maxNumberEdges += i;
            }
            if (edges >= maxNumberEdges){
                for (int row = 0; row < vertices; row++){
                    for (int column = 0; column < vertices; column++){
                        matrix[row][column] = 1;
                    }
                }
            } else {
                int i = 0;
                int u = 0;
                int w = 0;
                Random rand = new Random();
                while (i < edges){
                    u = rand.nextInt(vertices);
                    w = rand.nextInt(vertices);
                    while (matrix[u][w] == 1){
                        u = rand.nextInt(vertices);
                        w = rand.nextInt(vertices);
                    }
                    matrix[u][w] = 1;
                    matrix[w][u] = 1;
                    i++;
                }
            }
        }
    }

    //-------------------------------------
    // Function
    // Name:    getNumVertices
    // Input: 	none
    // Output:	the number of vertices in the graph
    //-------------------------------------
    public int getNumVertices(){
        return vertices;
    }

    //-------------------------------------
    // Function
    // Name:    setNumVertices
    // Input: 	the new number of vertices for the graph
    // Output:	none
    //          sets the number of vertices to the given input, creates a new cleared adjaceny matrix
    //-------------------------------------
    public void setNumVertices(int numVertices){
        vertices = numVertices;
        matrix = new int[vertices][vertices];
    }

    //-------------------------------------
    // Function
    // Name:    addVertices
    // Input: 	the number of vertices to add
    // Output:	none
    //          adds num vertices to the graph
    //-------------------------------------
    public void addVertices(int num){
        int[][] newMatrix = new int[vertices+num][vertices+num];
        for (int row = 0; row < vertices; row++){
            for (int column = 0; column < vertices; column++){
                newMatrix[row][column] = matrix[row][column];
            }
        }
        matrix = newMatrix;
        vertices += num;
    }

    //-------------------------------------
    // Function
    // Name:    deleteVertex
    // Input: 	the number of the vertex to delete
    // Output:	none
    //          deletes the vertex from the graph
    //-------------------------------------
    public void deleteVertex(int num){
        int[][] newMatrix = new int[vertices-1][vertices-1];
        for (int row = 0; row < vertices; row++){
            for (int column = 0; column < vertices; column++){
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
        vertices -= 1;
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

    //-------------------------------------
    // Function
    // Name:    setAdjacencyMatrix
    // Input: 	a n x n adjacency matrix
    // Output:	none
    //          sets the graph's adjacency matrix to the given input, updates the number of vertices accordingly
    //-------------------------------------
    public void setAdjacencyMatrix(int[][] newMatrix){
        vertices = newMatrix.length;
        matrix = newMatrix;
    }

    //-------------------------------------
    // Function
    // Name:    addEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          adds an undirected edge between the vertices u and v in the adjacency matrix
    //-------------------------------------
    public void addEdge(int u, int v){
        matrix[u][v] = 1;
        matrix[v][u] = 1;
    }

    //-------------------------------------
    // Function
    // Name:    removeEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          removes an undirected edge between the vertices u and v in the adjacency matrix
    //-------------------------------------
    public void removeEdge(int u, int v){
        matrix[u][v] = 0;
        matrix[v][u] = 0;
    }

    //-------------------------------------
    // Function
    // Name:    hasEdge
    // Input: 	two vertices u and v
    // Output:	true if the vertices have an edge between them, false otherwise
    //-------------------------------------
    public boolean hasEdge(int u, int v){
        if (matrix[u][v] == 1){
            return true;
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
        return vertices == 0;
        /*
        boolean empty = true;
        for (int row = 0; row < vertices; row++){
            for (int column = row; column < vertices; column++){
                if (matrix[row][column] == 1){
                    empty = false;
                    break;
                }
            }
        }
        return empty;
        */
    }

    //-------------------------------------
    // Function
    // Name:    isSimple
    // Input: 	none
    // Output:	true if the graph is simple (has no self-loops or multiple edges between vertices), false otherwise
    //          since an adjacency matrix representation cannot have multiple edges, this function
    //          basically acts as a hasLoop function
    //-------------------------------------
    public boolean isSimple(){
        boolean simple = true;
        for (int i = 0; i < vertices; i++){
            if (matrix[i][i] == 1){
                simple = false;
                break;
            }
        }
        return simple;
    }

    //-------------------------------------
    // Function
    // Name:    printGraph
    // Input: 	none
    // Output:	none
    //          prints out the adjacency matrix representation of the graph
    //-------------------------------------
	public void printGraph(){
		for (int row = 0; row < vertices; row++) { 
            System.out.print("Edges exist from vertex " + row + " to: ");
            for (int column = 0; column < vertices; column++){
                if (matrix[row][column] == 1){
                    System.out.print(column + " ");
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
        String result = "{\n" + vertices + ",\n{\n";
        boolean firstElem = true;
        for (int row = 0; row < vertices; row++){
            for (int column = row; column < vertices; column++){
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

    //-------------------------------------
    // Function - referenced w3schools code at https://www.w3schools.com/java/java_files_create.asp
    // Name:    writeToFile
    // Input: 	none
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

    //-------------------------------------
    // Function
    // Name:    convertToAdjList
    // Input: 	none
    // Output:	a GraphAdjList representation of the same graph
    //-------------------------------------
    public GraphAdjList convertToAdjList(){
        GraphAdjList adjList = new GraphAdjList(vertices);
        for (int row = 0; row < vertices; row++) {
            for (int column = row; column < vertices; column++){
                if (matrix[row][column] == 1){
                    adjList.addEdge(row, column);
                }
            }
        }
        return adjList;
    }

    //-------------------------------------
    // Function
    // Name:    convertToEdgeList
    // Input: 	none
    // Output:	a GraphEdgeList representation of the same graph
    //-------------------------------------
    public GraphEdgeList convertToEdgeList(){
        GraphEdgeList edgeList = new GraphEdgeList(vertices);
        for (int row = 0; row < vertices; row++) {
            for (int column = row; column < vertices; column++){
                if (matrix[row][column] == 1){
                    edgeList.addEdge(row, column);
                }
            }
        }
        return edgeList;
    }
}
