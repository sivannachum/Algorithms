import java.util.List;
import java.util.ArrayList;

//-----------------------------------------------------
// Author: 		Sivan Nachum
// Date: 		Feb 22, 2021
// Description:	Unit test with command line parameters
//			    for splitting of an unsorted list around a pivot value x
//-----------------------------------------------------
public class Level3Problem2List {
    public static void main(String[] args) {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        for (int i = 0; i < args.length-1; i++){
            intList.add(Integer.parseInt(args[i]));
        }
        int x = Integer.parseInt(args[args.length-1]);
        List<List<Integer>> result = splitListPivotX(intList, x);
        System.out.println("list="+intList);
        System.out.println("x="+x);
        System.out.println("resultLess="+result.get(0));
        System.out.println("resultGreater="+result.get(1));
    }

    //-------------------------------------
    // Function
    // Name:    splitListPivotX
    // Input: 	unsorted list
    // Output:	sub-lists with values less than and greater than x
    // Method:	iterative
    //-------------------------------------
    public static List<List<Integer>> splitListPivotX(List<Integer> intList, int x) {
        List<Integer> lessX = new ArrayList<Integer>();
        List<Integer> greaterX = new ArrayList<Integer>();
        for (int i = 0; i < intList.size(); i++){
            if (intList.get(i) < x){
                lessX.add(intList.get(i));
            }
            else if (intList.get(i) > x){
                greaterX.add(intList.get(i));
            }
        }
        List<List<Integer>> toReturn = new ArrayList<List<Integer>>();
        toReturn.add(lessX);
        toReturn.add(greaterX);
        return toReturn;
    }
}