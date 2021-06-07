//-----------------------------------------------------
// Author: 		Zampa Provenzale
// Date: 		April 27, 2021
// Description:	Java code to represent a Pokemon in a directed bipartite graph representation
//-----------------------------------------------------

public class Pokemon {
    private int number;
    private int[] preferenceList;
    private int currMatch;
    
    //------------------------------------
    // Constructor
    // Name:    Pokemon
    // Input: 	the number of the Pokemon and the total number of trainers it can choose from
    // Output:	none
    //          creates a Pokemon object with the given number
    //-------------------------------------
    public Pokemon(int number, int numTrainers){
        this.number = number;
        preferenceList = new int[numTrainers];
        currMatch = -1;
    }

    // Getters
    //-------------------------------------
    // Function
    // Name:    getNumber
    // Input: 	none
    // Output:	the number of this pokemon
    //-------------------------------------
    public int getNumber(){
        return number;
    }
    
    //-------------------------------------
    // Function
    // Name:    getCurrMatch
    // Input: 	none
    // Output:	the pokemon's current match
    //-------------------------------------
    public int getCurrMatch() {
    	return this.currMatch;
    }

    // Setters
    //-------------------------------------
    // Function
    // Name:    setPreference
    // Input: 	a trainer number and a ranking
    // Output:	none
    //          sets the trainer in this pokemon's preference list with the given ranking
    //-------------------------------------
    public void setPreference(int trainer, int ranking){
        preferenceList[trainer] = ranking;
    }

    //-------------------------------------
    // Function
    // Name:    choosePartner
    // Input: 	the number of the trainer which is proposing
    // Output:	the number of the trainer which is rejected
    //-------------------------------------
    public int choosePartner(int proposer){
        if (this.currMatch == -1) {
            this.currMatch = proposer;
            return -1;
        } else if (preferenceList[proposer] < preferenceList[this.currMatch]){
            int rejected = this.currMatch;
            this.currMatch = proposer;
            return rejected;
        } else if (preferenceList[this.currMatch] < preferenceList[proposer]){
            return proposer;
        } else {
        	System.out.println("error in choosePartner");
        	return -1;
        }
    }
}
