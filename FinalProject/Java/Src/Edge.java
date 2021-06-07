//-----------------------------------------------------
// Author: 		Zampa Provenzale
// Date: 		April 28, 2021
// Description:	Java code to represent an edge in a graph. The edge can be weighted or unweighted.
//-----------------------------------------------------
public class Edge {
	private int[] edge;
	private int weight;
	
	//Constructors
	//-------------------------------------
    // Constructor
    // Name:    Edge
    // Input: 	none
    // Output:	none
    //          creates an Edge object to represent an edge between two vertices
    //-------------------------------------
	public Edge() {
		edge = new int[2];
	}
	
	//-------------------------------------
    // Constructor
    // Name:    Edge
    // Input: 	the two vertices to connect
    // Output:	none
    //          creates an Edge object to represent an edge between the two vertices
    //-------------------------------------
	public Edge(int v1, int v2) {
		edge = new int[2];
		this.setEdge(v1, v2);
	}
	
	//-------------------------------------
    // Constructor
    // Name:    Edge
    // Input: 	the two vertices to connect and the weight for the edge between them
    // Output:	none
    //          creates an Edge object to represent a weighted edge between the two vertices
    //-------------------------------------
	public Edge(int v1, int v2, int newWeight) {
		edge = new int[2];
		this.setEdge(v1, v2);
		this.setWeight(newWeight);
	}
	
	//Getters
	//-------------------------------------
    // Function
    // Name:    get
    // Input: 	the vertex of the edge to get (0 or 1)
    // Output:	that vertex
    //-------------------------------------
	public int get(int i) {
		return edge[i];
	}
	
	//-------------------------------------
    // Function
    // Name:    getWeight
    // Input: 	none
    // Output:	the weight of the edge
    //-------------------------------------
	public int getWeight() {
		return weight;
	}
	
	//Setters
	//-------------------------------------
    // Function
    // Name:    setEdge
    // Input: 	two vertex IDs
    // Output:	none
	//			sets this edge's vertices to the given vertices
    //-------------------------------------
	public void setEdge(int v1, int v2) {
		this.edge[0] = v1;
		this.edge[1] = v2;
	}
	
	//-------------------------------------
    // Function
    // Name:    setWeight
    // Input: 	the new weight for the edge
    // Output:	none
	//			sets this edge's weight to the given weight
    //-------------------------------------
	public void setWeight(int newWeight) {
		this.weight = newWeight;
	}
}
