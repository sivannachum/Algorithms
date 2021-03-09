//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 1, 2021
// Description:	Java code to represent an edge in an undirected graph
//-----------------------------------------------------
// Referenced Java oracle doc on Comparable interface: https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html
package Graphs;

public class Edge implements Comparable<Edge> {
    private int u;
    private int v;
    
    //-------------------------------------
    // Constructor
    // Name:    Edge
    // Input: 	the vertices to connect
    // Output:	none
    //          creates an Edge object to represent an edge between these vertices
    //-------------------------------------
    public Edge(int u, int v){
        if (u < v){
            this.u = u;
            this.v = v;
        }
        else{
            this.u = v;
            this.v= u;
        }
    }

    // Getters
    //-------------------------------------
    // Function
    // Name:    getFirstVertex
    // Input: 	none
    // Output:	the first vertex in the edge
    //-------------------------------------
    public int getFirstVertex(){
        return u;
    }

    //-------------------------------------
    // Function
    // Name:    getSecondVertex
    // Input: 	none
    // Output:	the second vertex in the edge
    //-------------------------------------
    public int getSecondVertex(){
        return v;
    }

    // Testers
    //-------------------------------------
    // Function - referenced Java oracle doc on Sets to realize that I could use make a HashSet<Edge> if I implemented this method
    //            (for some reason that didn't actually work out, but I still use this method nonetheless for checking if a graph is simple)
    //            https://docs.oracle.com/javase/7/docs/api/java/util/Set.html
    // Name:    equals
    // Input: 	the edge to compare this one to
    // Output:	true if the edges are equal, false otherwise
    //-------------------------------------
    public boolean equals(Object obj){
        Edge connection = (Edge)obj;
        return (this.getFirstVertex() == connection.getFirstVertex()) && (this.getSecondVertex() == connection.getSecondVertex());
    }

    //-------------------------------------
    // Function
    // Name:    compareTo
    // Input: 	the edge to compare this one with
    // Output:	-1 if this edge is less than the input edge, 1 if it is greater, 0 if they are the same edge
    //-------------------------------------
    @Override
    public int compareTo(Edge connection){
        if (this.getFirstVertex() < connection.getFirstVertex()){
            return -1;
        }
        else if (this.getFirstVertex() == connection.getFirstVertex()){
            if (this.getSecondVertex() < connection.getSecondVertex()){
                return -1;
            }
            else if (this.getSecondVertex() == connection.getSecondVertex()){
                return 0;
            }
            else {
                return 1;
            }
        }
        else{
            return 1;
        }
    }

}
