//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 19, 2021
// Description:	Unit test with command line parameters
//			    for splitting of an unsorted array into positive/negative sub-arrays
//-----------------------------------------------------
import java.util.Arrays;
public class Level3Problem1 {
    //-------------------------------------
    // Main: 		unit test
    // Input:		command line params: an array length followed by the array elements
    // Output:	    none
    //			    prints positive and negative sub-arrays of the given array
    // Method:	    invokes splitArrayPosNeg, prints positive and negative sub-arrays of the given array
    //-------------------------------------
    public static void main(String[] args) {
        int[] arr = new int[Integer.parseInt(args[0])];
        for (int i = 0; i < Integer.parseInt(args[0]); i++){
            arr[i] = Integer.parseInt(args[i+1]);
        }
        int[][] result = splitArrayPosNeg(arr);
        System.out.println("arr="+Arrays.toString(arr));
        System.out.println("resultPositive="+Arrays.toString(result[0]));
        System.out.println("resultNegative="+Arrays.toString(result[1]));
    }

    //-------------------------------------
    // Function
    // Name:    splitArrayPosNeg
    // Input: 	unsorted array
    // Output:	positive and negative sub-arrays of the given array
    //          Note: 0 goes with the positive elements
    // Method:	iterative
    //-------------------------------------
	public static int[][] splitArrayPosNeg(int[] arr) {
        boolean[] negativeIndices = new boolean[arr.length];
        int negativeCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                negativeCount++;
                negativeIndices[i] = true;
            }
        }
        int[] negatives = new int[negativeCount];
        int[] positives = new int[arr.length-negativeCount];
        int negIndex = 0;
        int posIndex = 0;
        for (int i = 0; i < arr.length; i++){
            if (negativeIndices[i]) {
                negatives[negIndex] = arr[i];
                negIndex++;
            } else {
                positives[posIndex] = arr[i];
                posIndex++;
            }
        }
        return new int[][] {positives, negatives};
	}
}
