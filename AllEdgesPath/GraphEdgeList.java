//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 10, 2021
// Description:	Java code to create an undirectional Graph representation via an edge list
//-----------------------------------------------------
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphEdgeList {
    private int numVertices;
    private ArrayList<Edge> edges;

    // Constructors
    //-------------------------------------
    // Constructor
    // Name:    GraphEdgeList
    // Input: 	the name of the file from which to read the graph
    // Output:	none
    //          creates an object of type GraphEdgeList based on the information in the given file
    //-------------------------------------
    public GraphEdgeList(String filename){
        try {
            File graphFile = new File(filename);
            Scanner myReader = new Scanner(graphFile);
            // The first line is the number of vertices
            String data = myReader.nextLine();
            
            // Initialize the graph with the proper number of vertices
            this.numVertices = Integer.parseInt(data);
            this.edges = new ArrayList<Edge>();

            // Subsequent lines are edges or blank
            data = myReader.nextLine();
            while (!data.equals("")){
                String u = "";
                String v = "";
                boolean buildingU = true;

                for (int i = 0; i < data.length(); i++){
                    if (data.charAt(i) == ' '){
                        buildingU = false;
                        continue;
                    }
                    else if (buildingU) {
                        u += data.charAt(i);
                    }
                    else {
                        v += data.charAt(i);
                    }
                }

                addEdge(Integer.parseInt(u)-1, Integer.parseInt(v)-1);
                data = myReader.nextLine();

            }
            // At this point, we have gotten all the information we need from the file
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            java.lang.System.exit(0);
        }
    }

    // Modifiers
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

    // Converters
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