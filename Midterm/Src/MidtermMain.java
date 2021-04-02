//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 31, 2021
// Description:	Main program to read a DGraphEdgeList from a file, create a DGraphAdjList object, 
//              and compute the strongly connected components
//-----------------------------------------------------
import java.util.Scanner;

public class MidtermMain {
    //-------------------------------------
    // Main: 		
    // Input:		Given over time; program asks the user for filenames to either read from or write to
    // Output:	    Prints updates about task execution along the way:
    //              Main program to read a DGraphEdgeList from a file, create a DGraphAdjList object, 
    //              and compute the strongly connected components
    // Method:	    Keeps a DGraphEdgeList and DGraphAdjList object and invokes their methods to perform requests
    //-------------------------------------
    public static void main(String[] args) {
        DGraphEdgeList edgeGraph = null;
        DGraphAdjList adjGraph = null;
        Scanner scan = new Scanner(System.in);
        String filename;
        String reply;

        System.out.println("\nPlease input the name or filepath of the file from which to read the graph.");
        filename = scan.nextLine();
        edgeGraph = new DGraphEdgeList(filename);

        System.out.println("\nInput anything to print the graph or q to quit.");
        reply = scan.nextLine();
        if (reply.equals("q")){
            java.lang.System.exit(0);
        }
        System.out.println("\nEdge List Graph Representation:");
        edgeGraph.printGraph();

        System.out.println("\nInput anything to convert the graph to an adjacency list representation and print it or q to quit.");
        reply = scan.nextLine();
        if (reply.equals("q")){
            java.lang.System.exit(0);
        }
        adjGraph = edgeGraph.convertToDAdjList();
        System.out.println("\nAdjacency List Graph Representation:");
        adjGraph.printGraph();

        System.out.println("\nInput anything to compute and print the strongly connected components of the graph or q to quit.");
        reply = scan.nextLine();
        if (reply.equals("q")){
            java.lang.System.exit(0);
        }
        adjGraph.scc();
        System.out.println("\nStrongly Connected Components:");
        adjGraph.printSCC();

        System.out.println("\nPlease input the name or filepath of the file to which to write the SCCs.");
        filename = scan.nextLine();
        adjGraph.writeSCCsToFile(filename);

        scan.close();
	} 
}
