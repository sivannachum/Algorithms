//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 30, 2021
// Description:	Main program to read an EdgeListGraph from a file, create a MatchingGraph object, 
//              and compute a stable matching using the Gale-Shapley algorithm
//-----------------------------------------------------
import java.io.IOException;

public class StableMatchingMain {
    //-------------------------------------
    // Main: 		
    // Input:		A filename to read a graph from and a folder to write the stable matching edges to
    // Output:	    Main program to read an EdgeListGraph from a file, create a MatchingGraph object, 
    //              and compute a stable matching using the Gale-Shapley algorithm
    //              Outputs edges which create a stable matching to the given folder
    // Method:	    Keeps EdgeListGraph and MatchingGraph objects and invokes their methods to perform requests
    //-------------------------------------
    public static void main(String[] args) {
        EdgeListGraph dEdgeGraph = null;
        MatchingGraph match = null;

        String inputFilePath = args[0];
		String outputFileFolder = args[1];
		
		String absInputFilePath = ProjUtils.makeAbsoluteInputFilePath(inputFilePath);
		String absInputGrfFilePath = ProjUtils.makeAbsoluteInputFilePathWithExt(inputFilePath,"grf");
		String absOutputGrfFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"grf");
		String absOutputStbFilePath = ProjUtils.makeAbsoluteOutputFilePathWithExt(inputFilePath,outputFileFolder,"stb");

        dEdgeGraph = new EdgeListGraph(absInputFilePath);

        System.out.println("\nEdge List Graph Representation:");
        dEdgeGraph.printGraph();

        match = dEdgeGraph.convertToMatchingGraph();

        Edge[] stableEdges = match.galeShapley();
        String toPrint = "{";
        boolean firstElem = true;
        for (Edge connection : stableEdges){
            if (!firstElem){
                toPrint += ",";
            }
            else {
                firstElem = false;
            }
            toPrint += "{" + (connection.get(0)+1) +  "," + (connection.get(1)+1) + "}";
        }
        toPrint += "}";

        System.out.println("\nThe edges which create a stable matching:\n" + toPrint);

        match.writeStableEdgesToFile(absOutputStbFilePath);

        // Copy grf to output folder
        try {
			ProjUtils.copyFileUsingStream(absInputGrfFilePath,absOutputGrfFilePath);
		}  
		catch (IOException e) {
            System.out.println("ERROR: IOException.");
            e.printStackTrace();
		}
	} 
}
