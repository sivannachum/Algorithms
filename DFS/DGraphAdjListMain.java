//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 21, 2021
// Description:	Main program to interactively create a DGraphAdjList object
//-----------------------------------------------------
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class DGraphAdjListMain {
    //-------------------------------------
    // Main: 		unit test
    // Input:		given over time; program asks the user what they want to do and does it,
    //              then asks them again what they want to do
    // Output:	    depends on what the user asks the program to do;
    //              the program can initialize an empty graph,
    //              initialize a graph with a number of vertices specified by the user, initialize a random graph,
    //              add or remove edges between the vertices of a graph, check for the existence of an edge, add or remove vertices,
    //              print the graph or properties of it, or quit
    // Method:	    keeps a DGraphAdjList object and invokes its methods to perform requests
    //-------------------------------------
    public static void main(String[] args) {
        DGraphAdjList graph = null;
        Scanner scan = new Scanner(System.in);
        String reply;

        while (true){
            System.out.print("\nWhat would you like to do?\n" + 
                                "i = initialize an empty graph\n" +
                                "iv = initialize a graph with a set number of vertices\n" +
                                "r = create a random graph\n" +
                                "e = insert an edge\n" +
                                "re = remove an edge\n" + 
                                "he = check if the graph has a certain edge\n" +
                                "v = insert vertices\n" +
                                "dv = delete a vertex AND adjust vertex numbers\n" +
                                "rv = remove a vertex of out-degree zero without adjusting vertex numbers\n" +
                                "id = get a vertex of in-degree 0, if one exists\n" +
                                "od = get a vertex of out-degree 0, if one exists\n" +
                                "dfs = run dfs from a selected vertex\n" +
                                "tr = get the dfs tree for a selected vertex\n" +
                                "ts = get a topological sorting of the graph using DFS\n" +
                                "tsk = get a topological sorting of the graph using Kahn's algorithm\n" +
                                "ch = check if a given ordered list of vertices is a topological sort for the graph\n" +
                                "rev = see the reversed graph\n" +
                                "gp = learn about graph properties\n" +
                                "p = print graph\n" +
                                "rf = read a graph from a file\n" +
                                "wf = write the graph to a file\n" +
                                "q = quit\n");
            while ((reply = scan.nextLine()).equals("")){
                ;
            }

            // quit
            if (reply.equals("q")){
                break;
            }

            // initialize an empty graph
            else if (reply.equals("i")){
                graph = new DGraphAdjList();
            }
            // initialize a graph with a number of vertices specified by the user
            else if (reply.equals("iv")){
                System.out.println("\nHow many vertices would you like?");
                try{
                    int V = scan.nextInt();
                    if (V < 0){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    graph = new DGraphAdjList(V);
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // create a random graph
            else if (reply.equals("r")){
                System.out.println("\nHow many vertices and edges would you like?\n" + 
                                    "Example input for 3 vertices and 5 edges: 3 5");
                try{
                    int V = scan.nextInt();
                    int E = scan.nextInt();
                    if (V < 0 || E < 0){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    graph = new DGraphAdjList(V, E);
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            else if (graph == null){
                System.out.println("\nPlease initialize a graph first.");
            }

            // insert an edge
            else if (reply.equals("e")){
                System.out.println("\nWhat vertices would you like to connect?\n" + 
                                    "Example input to connect vertices 2 and 1 by an edge: 2 1");
                try{
                    int u = scan.nextInt();
                    int w = scan.nextInt();
                    if (u < 1 || w < 1 || u > graph.getNumVertices() || w > graph.getNumVertices()){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    graph.addEdge(u-1, w-1); 
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // remove an edge
            else if (reply.equals("re")){
                System.out.println("\nWhat vertices would you like to disconnect?\n" + 
                "Example input to remove the edge between vertices 2 and 1: 2 1");
                try{
                    int u = scan.nextInt();
                    int w = scan.nextInt();
                    if (u < 1 || w < 1 || u > graph.getNumVertices() || w > graph.getNumVertices()){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    graph.removeEdge(u-1, w-1); 
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // check if the graph has a certain edge
            else if (reply.equals("he")){
                System.out.println("\nWhat vertices would you like to check for an edge between?\n" + 
                "Example input to check for an edge between vertices 2 and 1: 2 1");
                try{
                    int u = scan.nextInt();
                    int w = scan.nextInt();
                    if (u < 1 || w < 1 || u > graph.getNumVertices() || w > graph.getNumVertices()){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    if (graph.hasEdge(u-1, w-1)){
                        System.out.println("\nThere is an edge between vertex " + u + " and vertex " + w +".");
                    }
                    else{
                        System.out.println("\nThere is not an edge between vertex " + u + " and vertex " + w +".");
                    }
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // insert vertices
            else if (reply.equals("v")){
                System.out.println("\nHow many vertices would you like to insert?");
                try{
                    int v = scan.nextInt();
                    if (v < 0){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    graph.addVertices(v); 
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // delete a vertex AND adjust vertex numbers
            else if (reply.equals("dv")){
                System.out.println("\nWhat vertex would you like to delete? Note that this action will alter vertex numbers.");
                try{
                    int v = scan.nextInt();
                    if (v < 1 || v > graph.getNumVertices()){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    graph.deleteVertex(v-1); 
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // remove a vertex WITHOUT adjusting vertex numbers
            else if (reply.equals("rv")){
                System.out.println("\nWhat vertex would you like to remove? Please ensure that this vertex has out-degree zero.");
                try{
                    int v = scan.nextInt();
                    if (v < 1 || v > graph.getNumVertices()){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    if (graph.getNumNeighbors(v-1) != 0){
                        System.out.println("Vertex " + v + " does not have out-degree zero.");
                        continue;
                    }
                    graph.removeVertex(v-1); 
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // get a vertex of in-degree 0, if one exists
            else if (reply.equals("id")){
                int vertex = graph.getVertexInDegreeZero();
                if (vertex == -1){
                    System.out.println(vertex);
                }
                else{
                    System.out.println(vertex+1);
                }
            }

            // get a vertex of out-degree 0, if one exists
            else if (reply.equals("od")){
                int vertex = graph.getVertexOutDegreeZero();
                if (vertex == -1){
                    System.out.println(vertex);
                }
                else{
                    System.out.println(vertex+1);
                }
            }

            // run dfs on the graph
            else if (reply.equals("dfs")){
                System.out.println("\nWhat vertex would you like to start dfs from?");
                try{
                    int v = scan.nextInt();
                    if (v < 1 || v > graph.getNumVertices()){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    graph.dfsPrint(v-1);
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // get the dfs tree for a selected vertex
            else if (reply.equals("tr")){
                System.out.println("\nWhat vertex would you like to get the dfs tree for?");
                try{
                    int v = scan.nextInt();
                    if (v < 1 || v > graph.getNumVertices()){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    DGraphAdjList tempGraph = graph.dfsTree(v-1);
                    System.out.println("\nTree Graph:");
                    tempGraph.printGraph();
                    System.out.println();
                    System.out.println(tempGraph);
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // get a topological sorting of the graph using DFS
            else if (reply.equals("ts")){
                if (!graph.isAcyclic()){
                    System.out.println("\nThis graph is cyclic. No topological sorting is possible.");
                    continue;
                }
                System.out.println();
                int[] sorted = graph.topologicalSortDFS();
                for (int vertex : sorted){
                    System.out.print(vertex+1 + " ");
                }
                System.out.println();
            }

            // get a topological sorting of the graph using Kahn's algorithm
            else if (reply.equals("tsk")){
                if (!graph.isAcyclic()){
                    System.out.println("\nThis graph is cyclic. No topological sorting is possible.");
                    continue;
                }
                System.out.println();
                int[] sorted = graph.topologicalSortKahn();
                for (int vertex : sorted){
                    System.out.print(vertex+1 + " ");
                }
                System.out.println();
            }

            // check if a given ordered list of vertices is a topological sort for the graph
            else if (reply.equals("ch")){
                if (graph.getNumVertices() == 0){
                    System.out.println("\nNo topological sorting is possible since there are no vertices.");
                    continue;
                }
                if (!graph.isAcyclic()){
                    System.out.println("\nThis graph is cyclic. No topological sorting is possible.");
                    continue;
                }
                System.out.println("\nPlease enter the ordering of vertices you would like to check.\n" + 
                                    "Make sure to include all vertices and not to repeat any.\n" + 
                                    "Example input for a graph with 4 vertices: 4 1 3 2");
                int count = 0;
                int[] orderedList = new int[graph.getNumVertices()];
                Set<Integer> orderedSet = new HashSet<Integer>();
                boolean toBreak = false;
                try{
                    while (count < graph.getNumVertices()){
                        int v = scan.nextInt();
                        if (v < 1 || v > graph.getNumVertices() || orderedSet.contains(v)){
                            System.out.println("\nImproper input, try again.");
                            toBreak = true;
                        }
                        orderedList[count] = v-1;
                        orderedSet.add(v);
                        count++;
                    }
                    if (toBreak){
                        continue;
                    }
                    if (graph.isTopologicalSort(orderedList)){
                        System.out.println("\nThe given ordering is a topological sort of the DAG.");
                    }
                    else {
                        System.out.println("\nThe given ordering is not a topological sort of the DAG.");
                    }
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // see the reversed graph
            else if (reply.equals("rev")){
                DGraphAdjList reverse = graph.reverseGraph();
                System.out.println();
                reverse.printGraph();
                System.out.println();
                System.out.println(reverse);
            }

            // learn about graph properties
            else if (reply.equals("gp")){
                if (graph.isEmpty()){
                    System.out.println("\nGraph is empty.");
                }
                else{
                    System.out.println("\nGraph is not empty.");
                }
                if (graph.isAcyclic()){
                    System.out.println("Graph is a DAG (directed acyclic graph).");
                }
                else{
                    System.out.println("Graph is not a DAG (directed acyclic graph); the graph contains a cycle.");
                }
            }

            // print the graph
            else if (reply.equals("p")){
                System.out.println();
                graph.printGraph();
                System.out.println();
                System.out.println(graph);
            }

            // read a graph from a file
            else if (reply.equals("rf")){
                System.out.println("\nWhat is the name of the file you would like to read the graph from?");
                String filename = scan.nextLine();
                graph.readFromFile(filename);
            }

            // write the graph to a file
            else if (reply.equals("wf")){
                System.out.println("\nWhat is the name of the file you would like to write the graph to?");
                String filename = scan.nextLine();
                graph.writeToFile(filename);
            }

            else{
                System.out.println("\nPlease input one of the given options.");
            }
            
        }
        scan.close();
	} 
}
