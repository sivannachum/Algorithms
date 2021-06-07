//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		April 27, 2021
// Description:	Java code to represent a Trainer in a directed bipartite graph representation
//-----------------------------------------------------

public class Trainer {
    private int number;
    private int[] preferenceList;
    private int preferenceListIndex;
    
    //-------------------------------------
    // Constructor
    // Name:    Trainer
    // Input: 	the number of the Trainer and the total number of pokemon it can choose from
    // Output:	none
    //          creates a Trainer object with the given number
    //-------------------------------------
    public Trainer(int number, int numPokemon){
        this.number = number;
        preferenceList = new int[numPokemon];
        preferenceListIndex = -1;
    }

    // Getters
    //-------------------------------------
    // Function
    // Name:    getNumber
    // Input: 	none
    // Output:	the number of this trainer
    //-------------------------------------
    public int getNumber(){
        return number;
    }

    //-------------------------------------
    // Function
    // Name:    getProposalNumber
    // Input: 	none
    // Output:	the number of the pokemon to whom the trainer will propose
    //-------------------------------------
    public int getProposalNumber(){
        preferenceListIndex++;
        return preferenceList[preferenceListIndex];
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setPreference
    // Input: 	a pokemon number and a ranking
    // Output:	none
    //          sets the pokemon in this trainer's preference list with the given ranking
    //-------------------------------------
    public void setPreference(int pokemon, int ranking){
        preferenceList[ranking] = pokemon;
    }
}
