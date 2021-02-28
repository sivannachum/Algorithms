//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 22, 2021
// Description:	Java code to create a directional Graph representation via an adjacency matrix
//-----------------------------------------------------
import java.util.Random;

public class DGraphAdjMatrix {
    private int vertices;
    private int[][] matrix;

    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjMatrix
    // Input: 	none
    // Output:	none
    //          creates an object of type DGraphAdjMatrix with no vertices or edges
    //-------------------------------------
    public DGraphAdjMatrix(){
        this.vertices = 0;
        this.matrix = null;
    }

    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjMatrix
    // Input: 	the number of vertices for the graph
    // Output:	none
    //          creates an object of type DGraphAdjMatrix with the specified number of vertices
    //-------------------------------------
    public DGraphAdjMatrix(int vertices){
        this.vertices = vertices;
        this.matrix = new int[vertices][vertices];
    }

    //-------------------------------------
    // Constructor
    // Name:    DGraphAdjMatrix
    // Input: 	the number of vertices and edges for the graph
    // Output:	none
    //          creates an object of type DGraphAdjMatrix with the specified number of vertices and edges, if possible;
    //          the graph is random
    //-------------------------------------
    public DGraphAdjMatrix(int vertices, int edges){
        this.vertices = vertices;
        matrix = new int[vertices][vertices];
        if (this.vertices != 0){
            if (edges >= vertices*vertices){
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
    //          adds a directed edge between the vertices u and v in the adjacency matrix
    //-------------------------------------
    public void addEdge(int u, int v){
        matrix[u][v] = 1;
    }

    //-------------------------------------
    // Function
    // Name:    removeEdge
    // Input: 	two vertices u and v
    // Output:	none
    //          removes a directed edge between the vertices u and v in the adjacency matrix
    //-------------------------------------
    public void removeEdge(int u, int v){
        matrix[u][v] = 0;
    }

    //-------------------------------------
    // Function
    // Name:    isEmpty
    // Input: 	none
    // Output:	true if the graph is empty (has no edges), false otherwise
    //-------------------------------------
    public boolean isEmpty(){
        if (vertices == 0){
            return true;
        }
        else{
            return false;
        }
        /*
        boolean empty = true;
        for (int row = 0; row < vertices; row++){
            for (int column = 0; column < vertices; column++){
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
    // Name:    printGraph
    // Input: 	none
    // Output:	none
    //          prints out the adjacency matrix representation of the the graph
    //-------------------------------------
	public void printGraph(){
		for (int row = 0; row < vertices; row++) { 
            System.out.print("Directed edges exist from vertex " + row + " to: ");
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
            for (int column = 0; column < vertices; column++){
                if (matrix[row][column] == 1){
                    if (!firstElem) {
                        result += ",";
                    } else {
                        firstElem = false;
                    }
                    result += "{" + row + "," + column + "}";
                }
            }
        }
        result += "\n}\n}";
        return result;
    }

    ///-------------------------------------
    // Function - referenced w3schools at https://www.w3schools.com/java/java_files_create.asp
    // Name:    toFile
    // Input: 	none
    // Output:	none
    //          writes the serialized version of the graph to a file
    //-------------------------------------
    public void toFile(){
        try {
            FileWriter myWriter = new FileWriter("graph.txt");
            myWriter.write(toString());
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    /*
    //-------------------------------------
    // Function
    // Name:    convertToDAdjList
    // Input: 	none
    // Output:	a DGraphAdjList representation of the same graph
    //-------------------------------------
    public DGraphAdjList convertToDAdjList(){
        DGraphAdjList adjList = new DGraphAdjList(vertices);
        for (int row = 0; row < vertices; row++) {
            for (int column = 0; column < vertices; column++){
                if (matrix[row][column] == 1){
                    adjList.addEdge(row, column);
                }
            }
        }
        return adjList;
    }

    //-------------------------------------
    // Function
    // Name:    convertToDEdgeList
    // Input: 	none
    // Output:	a DGraphEdgeList representation of the same graph
    //-------------------------------------
    public DGraphEdgeList convertToEdgeList(){
        DGraphEdgeList edgeList = new DGraphEdgeList(vertices);
        for (int row = 0; row < vertices; row++) {
            for (int column = 0; column < vertices; column++){
                if (matrix[row][column] == 1){
                    edgeList.addEdge(row, column);
                }
            }
        }
        return edgeList;
    }
    */
}
