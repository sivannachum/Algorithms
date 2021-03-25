//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 21, 2021
// Description:	Java code to create an directional Graph representation via an adjacency list
//              The graph can run the DFS algorithm and keeps track of which vertices have been visited
//              Note: this class is generally only for use within the DGraphAdjList class
//-----------------------------------------------------
import java.util.LinkedList;

public class DGraphDFSAdjList extends DGraphAdjList {
    // Vertices are WHITE before discovery, GRAY when discovered, and BLACK when finished
    private String[] vertexColor;
    private int[] vertexDiscoveryTime;
    // the vertices in order of finishing times from most to least recent
    private int[] reversedFinishingTimes;
    // keeps track of where we are in the reversedFinishingTimes array
    private int finishingTimesIndex;
    private int time;
    private LinkedList<DEdge> coloredEdges;

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
        vertexColor = new String[numVertices];
        vertexDiscoveryTime = new int[numVertices];
        reversedFinishingTimes = new int[numVertices];
        for (int i = 0; i < numVertices; i++){
            vertexColor[i] = "WHITE";
            vertexDiscoveryTime[i] = -1;
            reversedFinishingTimes[i] = -1;
        }
        finishingTimesIndex = numVertices-1;
        time = 0;
        coloredEdges = new LinkedList<DEdge>();
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
        vertexColor = new String[numVertices];
        vertexDiscoveryTime = new int[numVertices];
        reversedFinishingTimes = new int[numVertices];
        for (int i = 0; i < numVertices; i++){
            vertexColor[i] = "WHITE";
            vertexDiscoveryTime[i] = -1;
            reversedFinishingTimes[i] = -1;
        }
        finishingTimesIndex = numVertices-1;
        time = 0;
        coloredEdges = new LinkedList<DEdge>();
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
        vertexColor = new String[numVertices];
        vertexDiscoveryTime = new int[numVertices];
        reversedFinishingTimes = new int[numVertices];
        for (int i = 0; i < numVertices; i++){
            vertexColor[i] = "WHITE";
            vertexDiscoveryTime[i] = -1;
            reversedFinishingTimes[i] = -1;
        }
        finishingTimesIndex = numVertices-1;
        time = 0;
        coloredEdges = new LinkedList<DEdge>();
    }

    // Getters
    //-------------------------------------
    // Function
    // Name:    getVisitedVertices
    // Input: 	none
    // Output:	the vertices which have been visited
    //-------------------------------------
    public LinkedList<Integer> getVisitedVertices(){
        LinkedList<Integer> visitedVertices = new LinkedList<Integer>();
        for (int vertex = 0; vertex < vertexColor.length; vertex++){
            if (isVisited(vertex)){
                visitedVertices.add(vertex);
            }
        }
        return visitedVertices;
    }

    //-------------------------------------
    // Function
    // Name:    getVertexDiscoveryTime
    // Input: 	a vertex
    // Output:	the vertex's discovery time
    //-------------------------------------
    public int getVertexDiscoveryTime(int vertex){
        return vertexDiscoveryTime[vertex];
    }

    //-------------------------------------
    // Function
    // Name:    getVertexColor
    // Input: 	a vertex
    // Output:	the vertex's color
    //-------------------------------------
    public String getVertexColor(int vertex){
        return vertexColor[vertex];
    }

    //-------------------------------------
    // Function
    // Name:    getColoredEdges
    // Input: 	none
    // Output:	the colored edges from the DFS
    //-------------------------------------
    public LinkedList<DEdge> getColoredEdges(){
        return coloredEdges;
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setNumVertices
    // Input: 	the new number of vertices for the graph
    // Output:	none
    //          sets the number of vertices to the given input, 
    //          creates a new cleared adjacency list, vertexColor array, vertexDiscoveryTime array, reversedFinishingTimes array
    //          coloredEdges list, and resets the time and finishingTimesIndex
    //-------------------------------------
    @Override
    public void setNumVertices(int numVertices){
        super.setNumVertices(numVertices);
        vertexColor = new String[numVertices];
        vertexDiscoveryTime = new int[numVertices];
        reversedFinishingTimes = new int[numVertices];
        for (int i = 0; i < numVertices; i++){
            vertexColor[i] = "WHITE";
            vertexDiscoveryTime[i] = -1;
            reversedFinishingTimes[i] = -1;
        }
        finishingTimesIndex = numVertices-1;
        time = 0;
        coloredEdges = new LinkedList<DEdge>();
    }

    //-------------------------------------
    // Function
    // Name:    setAdjacencyList
    // Input: 	an adjacency list
    // Output:	none
    //          sets the graph's adjacency list to the given input, updates the number of vertices accordingly, 
    //          creates a new cleared adjacency list, vertexColor array, vertexDiscoveryTime array, reversedFinishingTimes array,
    //          coloredEdges list, and resets the time and finishingTimesIndex
    //-------------------------------------
    @Override
    public void setAdjacencyList(LinkedList<Integer>[] newList){
        super.setAdjacencyList(newList);
        int numVertices = newList.length;
        vertexColor = new String[numVertices];
        vertexDiscoveryTime = new int[numVertices];
        reversedFinishingTimes = new int[numVertices];
        for (int i = 0; i < numVertices; i++){
            vertexColor[i] = "WHITE";
            vertexDiscoveryTime[i] = -1;
            reversedFinishingTimes[i] = -1;
        }
        finishingTimesIndex = numVertices-1;
        time = 0;
        coloredEdges = new LinkedList<DEdge>();
    }

    //-------------------------------------
    // Function
    // Name:    setVertexDiscoveryTime
    // Input: 	a vertex and a time
    // Output:	none
    //          sets the given vertex's discovery time to the given time
    //-------------------------------------
    public void setVertexDiscoveryTime(int vertex, int time){
        vertexDiscoveryTime[vertex] = time;
    }

    //-------------------------------------
    // Function
    // Name:    setVertexColor
    // Input: 	a vertex and a color
    // Output:	none
    //          sets the given vertex's color to the given color
    //-------------------------------------
    public void setVertexColor(int vertex, String color){
        vertexColor[vertex] = color;
    }

    // Testers
    //-------------------------------------
    // Function
    // Name:    isVisited
    // Input: 	a vertex number
    // Output:	true if the vertex has been visited, false otherwise
    //-------------------------------------
    public boolean isVisited(int vertex){
        return getVertexColor(vertex).equals("GRAY") || getVertexColor(vertex).equals("BLACK");
    }    

    // Depth First Search
    //-------------------------------------
    // Function
    // Name:    dfs
    // Input: 	the root vertex
    // Output:	none
    //          conducts depth-first search
    //-------------------------------------
    public void dfs(int root){
        dfsR(root);
        for (int vertex = 0; vertex < super.getNumVertices(); vertex++){
            if (!isVisited(vertex)){
                dfsR(vertex);
            }
        }
    }

    //-------------------------------------
    // Function
    // Name:    dfsTree
    // Input: 	the root vertex
    // Output:	returns the dfs tree for this root vertex as an instance of DGraphAdjList
    //-------------------------------------
    @Override
    public DGraphAdjList dfsTree(int root){
        dfsR(root);
        DGraphAdjList tree = new DGraphAdjList(super.getNumVertices());
        for (DEdge edge : getColoredEdges()){
            if (edge.getColor().equals("RED")){
                tree.addEdge(edge.getFirstVertex(), edge.getSecondVertex());
            }
        }
        return tree;
    }

    //-------------------------------------
    // Function
    // Name:    dfsR
    // Input: 	the vertex that we are looking at currently
    // Output:	none
    //          Conducts depth-first search recursively
    //-------------------------------------
    public void dfsR(int vertex){
        time = time + 1;
        setVertexDiscoveryTime(vertex, time);
        setVertexColor(vertex, "GRAY");
        LinkedList<Integer> neighbors = super.getNeighbors(vertex);
        for (int i = 0; i < super.getNumNeighbors(vertex); i++){
            String neighborColor = getVertexColor(neighbors.get(i));
            if (neighborColor.equals("WHITE")){
                // Tree edge
                coloredEdges.add(new DEdge(vertex, neighbors.get(i), "RED"));
                dfsR(neighbors.get(i));
            }
            else if (neighborColor.equals("GRAY")){
                // Back edge
                coloredEdges.add(new DEdge(vertex, neighbors.get(i), "GRAY"));
            }
            else {
                int currVertexDiscoveryTime = getVertexDiscoveryTime(vertex);
                int neighborDiscoveryTime = getVertexDiscoveryTime(neighbors.get(i));
                if (currVertexDiscoveryTime < neighborDiscoveryTime){
                    // Forward edge
                    coloredEdges.add(new DEdge(vertex, neighbors.get(i), "GREEN"));
                }
                else {
                    // Cross edge
                    coloredEdges.add(new DEdge(vertex, neighbors.get(i), "BLUE"));
                }
            }
        }
        setVertexColor(vertex, "BLACK");
        reversedFinishingTimes[finishingTimesIndex] = vertex;
        finishingTimesIndex--;
    }

    //-------------------------------------
    // Function
    // Name:    dfsPrint
    // Input: 	the root vertex
    // Output:	none
    //          conducts depth-first search
    //          prints updates along the way
    //-------------------------------------
    @Override
    public void dfsPrint(int root){
        dfsPrintR(root);
        for (int vertex = 0; vertex < super.getNumVertices(); vertex++){
            if (!isVisited(vertex)){
                dfsPrintR(vertex);
            }
        }
    }

    //-------------------------------------
    // Function
    // Name:    dfsPrintR
    // Input: 	the vertex that we are looking at currently
    // Output:	none
    //          Conducts depth-first search recursively
    //          prints updates along the way
    //-------------------------------------
    public void dfsPrintR(int vertex){
        System.out.println("\nVisiting vertex " + (vertex+1));
        time = time + 1;
        setVertexDiscoveryTime(vertex, time);
        setVertexColor(vertex, "GRAY");
        printGraph();
        LinkedList<Integer> neighbors = super.getNeighbors(vertex);
        for (int i = 0; i < super.getNumNeighbors(vertex); i++){
            String neighborColor = getVertexColor(neighbors.get(i));
            if (neighborColor.equals("WHITE")){
                // Tree edge
                coloredEdges.add(new DEdge(vertex, neighbors.get(i), "RED"));
                dfsPrintR(neighbors.get(i));
            }
            else if (neighborColor.equals("GRAY")){
                // Back edge
                coloredEdges.add(new DEdge(vertex, neighbors.get(i), "GRAY"));
            }
            else {
                int currVertexDiscoveryTime = getVertexDiscoveryTime(vertex);
                int neighborDiscoveryTime = getVertexDiscoveryTime(neighbors.get(i));
                if (currVertexDiscoveryTime < neighborDiscoveryTime){
                    // Forward edge
                    coloredEdges.add(new DEdge(vertex, neighbors.get(i), "GREEN"));
                }
                else {
                    // Cross edge
                    coloredEdges.add(new DEdge(vertex, neighbors.get(i), "BLUE"));
                }
            }
        }
        setVertexColor(vertex, "BLACK");
    }

    // Topological Sort
    //-------------------------------------
    // Function
    // Name:    topologicalSortDFS
    // Input: 	none
    // Output:	returns a topological sort of the graph; assumes the graph is a DAG
    //          uses DFS to make the topological sort
    //-------------------------------------
    public int[] topologicalSortDFS(){
        for (int vertex = 0; vertex < super.getNumVertices(); vertex++){
            if (!isVisited(vertex)){
                dfsR(vertex);
            }
        }
        return reversedFinishingTimes;
    }

    // Testers
    //-------------------------------------
    // Function
    // Name:    isAcyclic
    // Input: 	none
    // Output:	true if the graph is acyclic, false otherwise
    //          a directed graph is acyclic if and only if a DFS of the graph yields no back edges
    //-------------------------------------
    public boolean isAcyclic(){
        dfs(0);
        for (DEdge edge : getColoredEdges()){
            // if the edge is a back edge
            if (edge.getColor().equals("GRAY")){
                return false;
            }
        }
        return true;
    }

    // Functions for printing
    //-------------------------------------
    // Function
    // Name:    printGraph
    // Input: 	none
    // Output:	none
    //          prints out the adjacency list representation of the graph and vertices' colors
    //-------------------------------------
	public void printGraph(){
		super.printGraph();
        String result = "\n";
        int numVertices = super.getNumVertices();
        for (int vertex = 0; vertex < numVertices; vertex++){
            result += (vertex+1) + ": " + getVertexColor(vertex) + "\n";
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