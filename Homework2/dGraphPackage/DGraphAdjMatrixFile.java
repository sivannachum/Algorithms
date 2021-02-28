//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 25, 2021
// Description:	Unit test with command line parameters,
//              for DGraphAdjMatrix
//-----------------------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DGraphAdjMatrixFile {
    //-------------------------------------
    // Main: 		unit test - referenced w3 schools for basic file I/O at https://www.w3schools.com/java/java_files_read.asp
    // Input:		command line param: the name or filepath of the file to read the graph from
    // Output:	    none
    //			    prints the DGraphAdjMatrix made from the file input
    // Method:	    creates a DGraphAdjMatrix object and invokes methods from that class
    //-------------------------------------
    public static void main(String[] args){
        try {
            File graphFile = new File(args[0]);
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
            DGraphAdjMatrix graph = new DGraphAdjMatrix(Integer.parseInt(cleanData));

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
                    graph.addEdge(Integer.parseInt(u)-1, Integer.parseInt(v)-1);
                    continue;
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

            graph.printGraph();
            graph.toFile();

            // At this point, we have gotten all the information we need from the file
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}
