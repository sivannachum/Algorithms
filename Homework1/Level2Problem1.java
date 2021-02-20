//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 19, 2021
// Description:	Unit test with command line parameters
//			    for iterative placement of new array element in a sorted array
//-----------------------------------------------------
import java.util.Arrays;
public class Level2Problem1 {
    //-------------------------------------
    // Main: 		unit test
    // Input:		command line params: an array length followed by the (sorted) array elements followed by the element to insert
    // Output:	    none
    //			    prints a new sorted array with the same elements as the given array, as well as the added element 
    // Method:	    invokes placeNewElement, prints a new sorted array with the same elements as the given array,
    //              as well as the added element 
    //-------------------------------------
    public static void main(String[] args) {
        int[] arr = new int[Integer.parseInt(args[0])];
        for (int i = 0; i < Integer.parseInt(args[0]); i++){
            arr[i] = Integer.parseInt(args[i+1]);
        }
        int elem = Integer.parseInt(args[arr.length+1]);
        int[] result = placeNewElement(arr, elem);
        System.out.println("arr="+Arrays.toString(arr));
        System.out.println("elem="+elem);
        System.out.println("result="+Arrays.toString(result));
    }

    //-------------------------------------
    // Function
    // Name:    placeNewElement
    // Input: 	sorted array
    // Output:	a new sorted array with the same elements as the given array, as well as the added element
    // Method:	iterative
    //-------------------------------------
	public static int[] placeNewElement(int[] arr, int elem) {
        int[] result = new int[arr.length+1];
        int i = 0;
        for (int member : arr) {
            if (member < elem) {
                result[i] = member;
            } else {
                break;
            }
            i++;
        }
        result[i] = elem;
        i++;
        while (i < result.length){
            result[i] = arr[i-1];
            i++;
        }
        return result;
	}
}
