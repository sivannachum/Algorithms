//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 26, 2021
// Description:	Main program to interactively create a DGraphAdjMatrix object
//-----------------------------------------------------
import java.util.InputMismatchException;
import java.util.Scanner;

public class DGraphAdjMatrixMain {
        //-------------------------------------
    // Main: 		unit test
    // Input:		given over time; program asks the user what they want to do
    //              and does it, then asks them again what they want to do
    // Output:	    depends on what the user asks the program to do;
    //              the program can initialize a graph with a number of vertices specified by the user, initialize a random graph,
    //              add or remove edges between the vertices of a graph, add or remove vertices,
    //              print the graph or properties of it, or quit
    // Method:	    keeps a DGraphAdjMatrix object and invokes its methods to perform requests
    //-------------------------------------
    public static void main(String[] args) {
        DGraphAdjMatrix graph = null;
        Scanner scan = new Scanner(System.in);
        String reply;

        while (true){
            System.out.print("\nWhat would you like to do?\n" + 
                                "i = initialize an empty graph\n" +
                                "iv = initialize a graph with a set number of vertices\n" +
                                "r = create a random graph\n" +
                                "e = insert an edge\n" +
                                "re = remove an edge\n" + 
                                "v = insert vertices\n" +
                                "dv = delete a vertex\n" +
                                "gp = learn about graph properties\n" +
                                "p = print graph\n" +
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
                graph = new DGraphAdjMatrix();
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
                    graph = new DGraphAdjMatrix(V);
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
                    graph = new DGraphAdjMatrix(V, E);
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // insert an edge
            else if (reply.equals("e")){
                if (graph == null){
                    System.out.println("\nPlease initialize a graph first.");
                    continue;
                }
                if (graph.getNumVertices() == 0){
                    System.out.println("\nCannot add edges to a graph with 0 vertices.");
                    continue;
                }
                System.out.println("\nWhat vertices would you like to connect?\n" + 
                                    "Example input to connect vertices 0 and 1 by an edge: 0 1");
                try{
                    int u = scan.nextInt();
                    int w = scan.nextInt();
                    if (u < 0 || w < 0 || u > graph.getNumVertices()-1 || w > graph.getNumVertices()-1){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    graph.addEdge(u, w); 
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // remove an edge
            else if (reply.equals("re")){
                if (graph == null){
                    System.out.println("\nPlease initialize a graph first.");
                    continue;
                }
                System.out.println("\nWhat vertices would you like to disconnect?\n" + 
                "Example input to remove the edge between vertices 0 and 1: 0 1");
                try{
                    int u = scan.nextInt();
                    int w = scan.nextInt();
                    if (u < 0 || w < 0 || u > graph.getNumVertices()-1 || w > graph.getNumVertices()-1){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    graph.removeEdge(u, w); 
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }

            // insert vertices
            else if (reply.equals("v")){
                if (graph == null){
                    System.out.println("\nPlease initialize a graph first.");
                    continue;
                }
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

            // delete a vertex
            else if (reply.equals("dv")){
                if (graph == null){
                    System.out.println("\nPlease initialize a graph first.");
                    continue;
                }
                System.out.println("\nWhat vertex would you like to delete?");
                try{
                    int v = scan.nextInt();
                    if (v < 0 || v > graph.getNumVertices()-1){
                        System.out.println("\nImproper input, try again.");
                        continue;
                    }
                    graph.deleteVertex(v); 
                } catch(InputMismatchException e) {
                    System.out.println("\nImproper input, try again.");
                }
            }
            
            // learn about graph properties
            else if (reply.equals("gp")){
                if (graph == null){
                    System.out.println("\nPlease initialize a graph first.");
                    continue;
                }
                if (graph.isEmpty()){
                    System.out.println("\nGraph is empty.");
                }
                else{
                    System.out.println("\nGraph is not empty.");
                }
            }

            // print the graph
            else if (reply.equals("p")){
                if (graph == null){
                    System.out.println("\nPlease initialize a graph first.");
                    continue;
                }
                System.out.println();
                graph.printGraph();
                System.out.println(graph.toString());
            }

            else{
                System.out.println("\nPlease input one of the given options.");
            }
        }
        scan.close();
	} 
}
