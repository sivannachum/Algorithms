//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		March 10, 2021
// Description:	Java code to represent an edge in an directed graph
//-----------------------------------------------------
// Referenced Java oracle doc on Comparable interface: https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html
public class DEdge implements Comparable<DEdge> {
    private int u;
    private int v;
    private String color;
    
    //-------------------------------------
    // Constructor
    // Name:    DEdge
    // Input: 	the vertices to connect
    // Output:	none
    //          creates a DEdge object to represent an edge between these vertices
    //-------------------------------------
    public DEdge(int u, int v){
        this.u = u;
        this.v = v;
        this.color = null;
    }

    //-------------------------------------
    // Constructor
    // Name:    DEdge
    // Input: 	the vertices to connect and the color of the edge between them
    // Output:	none
    //          creates a DEdge object to represent an edge between these vertices
    //-------------------------------------
    public DEdge(int u, int v, String color){
        this.u = u;
        this.v = v;
        this.color = color;
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

    //-------------------------------------
    // Function
    // Name:    getColor
    // Input: 	none
    // Output:	the color of this edge
    //-------------------------------------
    public String getColor(){
        return color;
    }

    // Testers
    //-------------------------------------
    // Function - referenced Java oracle doc on Sets to realize that I could use make a HashSet<Edge> if I implemented this method
    //            (for some reason that didn't actually work out, but I still use this method nonetheless for checking if a graph is simple)
    //            https://docs.oracle.com/javase/7/docs/api/java/util/Set.html
    // Name:    equals
    // Input: 	the edge to compare this one to
    // Output:	true if the edges are equal (not considering color), false otherwise
    //-------------------------------------
    public boolean equals(Object obj){
        DEdge connection = (DEdge)obj;
        return (this.getFirstVertex() == connection.getFirstVertex()) && (this.getSecondVertex() == connection.getSecondVertex());
    }

    //-------------------------------------
    // Function
    // Name:    compareTo
    // Input: 	the edge to compare this one with
    // Output:	-1 if this edge is less than the input edge, 1 if it is greater, 0 if they are the same edge
    //          Note that color of edges is not considered
    //-------------------------------------
    @Override
    public int compareTo(DEdge connection){
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

    //-------------------------------------
    // Function
    // Name:    toString
    // Input: 	none
    // Output:	a string representation of this edge
    //-------------------------------------
    public String toString(){
        String result = "(" + (u+1) + ", " + (v+1) + ", ";
        if (color == null){
            result += "null";
        }
        else{
            result += color;
        }
        result += ")";
        return result;
    }

}
