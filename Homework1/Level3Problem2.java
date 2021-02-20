//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 19, 2021
// Description:	Unit test with command line parameters
//			    for splitting of an unsorted array around a pivot value x
//-----------------------------------------------------
import java.util.Arrays;
public class Level3Problem2 {
    //-------------------------------------
    // Main: 		unit test
    // Input:		command line params: an array length followed by the array elements then the value x to split around
    // Output:	    none
    //			    prints sub-arrays with values less than and greater than x
    // Method:	    invokes splitArrayPivotX, prints sub-arrays with values less than and greater than x
    //-------------------------------------
    public static void main(String[] args) {
        int[] arr = new int[Integer.parseInt(args[0])];
        for (int i = 0; i < Integer.parseInt(args[0]); i++){
            arr[i] = Integer.parseInt(args[i+1]);
        }
        int x = Integer.parseInt(args[arr.length+1]);
        int[][] result = splitArrayPivotX(arr, x);
        System.out.println("arr="+Arrays.toString(arr));
        System.out.println("x="+x);
        System.out.println("resultLess="+Arrays.toString(result[0]));
        System.out.println("resultGreater="+Arrays.toString(result[1]));
    }

    //-------------------------------------
    // Function
    // Name:    splitArrayPivotX
    // Input: 	unsorted array
    // Output:	sub-arrays with values less than and greater than x
    // Method:	iterative
    //-------------------------------------
	public static int[][] splitArrayPivotX(int[] arr, int x) {
        int numLess = 0;
        int numGreater = 0;
        for (int elem : arr) {
            if (elem < x) {
                numLess++;
            } else if (elem > x) {
                numGreater++;
            }
        }
        int[] lessX = new int[numLess];
        int[] greaterX = new int[numGreater];
        int lessIndex = 0;
        int greaterIndex = 0;
        for (int elem : arr){
            if (elem < x) {
                lessX[lessIndex] = elem;
                lessIndex++;
            } else if (elem > x) {
                greaterX[greaterIndex] = elem;
                greaterIndex++;
            }
        }
        return new int[][] {lessX, greaterX};
	}
}
