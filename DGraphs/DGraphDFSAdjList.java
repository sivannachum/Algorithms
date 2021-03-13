//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 9, 2021
// Description:	Java code to create an directional Graph representation via an adjacency list
//              The graph can run the DFS algorithm and keeps track of which vertices have been visited
//-----------------------------------------------------
package DGraphs;
import java.util.LinkedList;

public class DGraphDFSAdjList extends DGraphAdjList {
    private boolean[] visited;

    // Constructors
    //-------------------------------------
    // Constructor
    // Name:    DGraphDFSAdjList
    // Input: 	the number of vertices for the graph
    // Output:	none
    //          creates an object of type DGraphDFSAdjList with the specified number of vertices
    //-------------------------------------
    public DGraphDFSAdjList(int numVertices){
        super(numVertices);
        visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++){
            visited[i] = false;
        }
    }

    //-------------------------------------
    // Constructor
    // Name:    DGraphDFSAdjList
    // Input: 	the adjacency lists for the graph
    // Output:	none
    //          creates an object of type DGraphDFSAdjList with the given adjacency lists and the corresponding number of vertices
    //-------------------------------------
    public DGraphDFSAdjList(LinkedList<Integer>[] adjLists){
        super(adjLists);
        int numVertices = super.getNumVertices();
        visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++){
            visited[i] = false;
        }
    }

    //-------------------------------------
    // Constructor
    // Name:    DGraphDFSAdjList
    // Input: 	the number of vertices and edges for the graph
    // Output:	none
    //          creates an object of type DGraphDFSAdjList with the specified number of vertices and edges; the graph is random
    //-------------------------------------
    public DGraphDFSAdjList(int numVertices, int numEdges){
        super(numVertices, numEdges);
        visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++){
            visited[i] = false;
        }
    }

    // Getters
    //-------------------------------------
    // Function
    // Name:    getVisited
    // Input: 	none
    // Output:	the boolean array which indicates which vertices in the graph have been visited
    //-------------------------------------
    public boolean[] getVisited(){
        return visited;
    }

    //-------------------------------------
    // Function
    // Name:    getVisitedVertices
    // Input: 	none
    // Output:	the vertices which have been visited
    //-------------------------------------
    public LinkedList<Integer> getVisitedVertices(){
        LinkedList<Integer> visitedVertices = new LinkedList<Integer>();
        for (int vertex = 0; vertex < visited.length; vertex++){
            if (visited[vertex]){
                visitedVertices.add(vertex);
            }
        }
        return visitedVertices;
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setNumVertices
    // Input: 	the new number of vertices for the graph
    // Output:	none
    //          sets the number of vertices to the given input, creates a new cleared adjacency list and visited array
    //-------------------------------------
    @Override
    public void setNumVertices(int numVertices){
        super.setNumVertices(numVertices);
        visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++){
            visited[i] = false;
        }
    }

    //-------------------------------------
    // Function
    // Name:    setAdjacencyList
    // Input: 	an adjacency list
    // Output:	none
    //          sets the graph's adjacency list to the given input, updates the number of vertices accordingly, 
    //          and creates a new cleared visited array
    //-------------------------------------
    @Override
    public void setAdjacencyList(LinkedList<Integer>[] newList){
        super.setAdjacencyList(newList);
        int numVertices = newList.length;
        visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++){
            visited[i] = false;
        }
    }

    //-------------------------------------
    // Function
    // Name:    setVisited
    // Input: 	a vertex
    // Output:	none
    //          sets that vertex as visited
    //-------------------------------------
    public void setVisited(int vertex){
        visited[vertex] = true;
    }

    // Testers
    // Function
    // Name:    isVisited
    // Input: 	a vertex number
    // Output:	true if the vertex has been visited, false otherwise
    //-------------------------------------
    public boolean isVisited(int vertex){
        return visited[vertex];
    }    

    // Depth First Search
    //-------------------------------------
    // Function
    // Name:    dfs
    // Input: 	the root vertex from which to conduct depth first search
    // Output:	none
    //-------------------------------------
    @Override
    public void dfs(int root){
        for (int i = 0; i < visited.length; i++){
            visited[i] = false;
        }
        dfsR(root);
        System.out.println();
        printVisitedVertices();
    }

    //-------------------------------------
    // Function
    // Name:    dfsR
    // Input: 	the vertex that we are looking at currently
    // Output:	none
    //          Conducts depth-first search recursively
    //          prints updates along the way
    //-------------------------------------
    public void dfsR(int vertex){
        if (isVisited(vertex)){
            System.out.println("Vertex " + (vertex+1) + " already visited");
            return;
        }
        setVisited(vertex);
        System.out.println("\nVisiting vertex " + (vertex+1));
        printGraph();

        LinkedList<Integer> neighbors = super.getNeighbors(vertex);
        for (int i = 0; i < super.getNumNeighbors(vertex); i++){
            dfsR(neighbors.get(i));
        }
    }

    // Modifiers
    //-------------------------------------
    // Function
    // Name:    addVertices
    // Input: 	the number of vertices to add
    // Output:	none
    //          adds num vertices to the graph
    //-------------------------------------
    @Override
    public void addVertices(int num){
        int oldNumVertices = super.getNumVertices();
        super.addVertices(num);
        boolean[] newVisited = new boolean[oldNumVertices+num];
        int i = 0;
        for (i = 0; i < oldNumVertices; i++){
            newVisited[i] = visited[i];
        }
        while (i < oldNumVertices+num){
            newVisited[i] = false;
            i++;
        }
        visited = newVisited;
    }

    //-------------------------------------
    // Function
    // Name:    deleteVertex
    // Input: 	the number of the vertex to delete
    // Output:	none
    //          deletes the vertex from the graph
    //-------------------------------------
    @Override
    public void deleteVertex(int num){
        super.deleteVertex(num);
        int numVertices = super.getNumVertices();
        boolean[] newVisited = new boolean[numVertices];
        int i = 0;
        for (i = 0; i < numVertices; i++){
            if (i < num){
                newVisited[i] = visited[i];
            } else {
                break;
            }
        }
        while (i < numVertices){
            newVisited[i] = visited[i+1];
            i++;
        }
        visited = newVisited;
    }

    // Functions for printing
    //-------------------------------------
    // Function
    // Name:    printGraph
    // Input: 	none
    // Output:	none
    //          prints out the adjacency list representation of the graph and what vertices have been visited
    //-------------------------------------
	public void printGraph(){
		super.printGraph();
        String result = "\n";
        int numVertices = super.getNumVertices();
        for (int vertex = 0; vertex < numVertices; vertex++){
            if (visited[vertex]){
                result += (vertex+1) + ": visited\n";
            }
            else {
                result += (vertex+1) + ": not visited\n";
            }
        }
        System.out.println(result);
	}

    //-------------------------------------
    // Function
    // Name:    printVisitedVertices
    // Input: 	none
    // Output:	none
    //          prints the visited vertices
    //-------------------------------------
    public void printVisitedVertices(){
        LinkedList<Integer> visitedVertices = getVisitedVertices();
        int counter = visitedVertices.size();
        int i = visitedVertices.size()-1;
        while (counter > 0){
            int element = visitedVertices.remove(i);
            element++;
            visitedVertices.addFirst(element);
            counter--;
        }
        System.out.println("Visited vertices: " + visitedVertices);
    }
}