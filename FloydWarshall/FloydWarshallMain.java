//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 21, 2021
// Description:	Main program to read a DGraphEdgeList from a file, create a DGraphAdjMatrix object, 
//              and compute the lengths of the all pairs shortest paths using the Floyd-Warshall Algorithm
//-----------------------------------------------------
import java.util.LinkedList;
import java.io.IOException;

public class FloydWarshallMain {
    //-------------------------------------
    // Main: 		
    // Input:		A filename to read a graph from and a folder to write the lengths of the all pairs shortest paths to
    // Output:	    Main program to read a DGraphEdgeList from a file, create a DGraphAdjMatrix object, 
    //              and compute the lengths of the all pairs shortest paths using the Floyd-Warshall Algorithm
    //              Outputs the lengths of the all pairs shortest paths using the Floyd-Warshall Algorithm to the given folder
    // Method:	    Keeps DGraphEdgeList and DGraphAdjMatrix objects and invokes their methods to perform requests
    //-------------------------------------
    public static void main(String[] args) {
        DGraphEdgeList dEdgeGraph = null;
        DGraphAdjMatrix dAdjGraph = null;

        String inputFilePath = args[0];
		String outputFileFolder = args[1];
		
		String absInputFilePath = ProjUtils.makeAbsoluteInputFilePath(inputFilePath);
		// String absInputGrfFilePath = ProjUtils.makeAbsoluteInputFilePathWithExt(inputFilePath,"grf");
		// String absOutputGrfFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"grf");
        // Adjust to file type you are outputting, e.g. cmp for components, lev for bfs levels
		String absOutputSpvFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"spv");

        dEdgeGraph = new DGraphEdgeList(absInputFilePath);

        System.out.println("\nEdge List Graph Representation:");
        dEdgeGraph.printGraph();

        dAdjGraph = dEdgeGraph.convertToDAdjMatrix();
        System.out.println("\nAdjacency Matrix Graph Representation:");
        dAdjGraph.printGraph();

        dAdjGraph.floydWarshall();
        System.out.println("\nThe lengths of the all pairs shortest paths:");
        dAdjGraph.printShortestPathsLengths();

        dAdjGraph.writeShortestPathsLengthsToFile(absOutputSpvFilePath);

        /*
        // Copy grf to output folder
        try {
			ProjUtils.copyFileUsingStream(absInputGrfFilePath,absOutputGrfFilePath);
		}  
		catch (IOException e) {
            System.out.println("ERROR: IOException.");
            e.printStackTrace();
		}
        */
	} 
}
