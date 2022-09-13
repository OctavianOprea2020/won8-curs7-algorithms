import java.util.Arrays;

public class PairsAndTripletsSorted {
    static int nPairs = 0;
    static int nTriplets = 0;
    static boolean sortArray = true;           // Sort the array?

    public static void main(String[] args) {
        int[] nums = {-1, 8, 7, 2, -2, 5, 3, 0, 1, -1, 2, 3, 2, 3, 6, 4, 5, 5, 4, 6, 0};
        int[] target = {10, 0, -3, 20, 30};

        boolean printExtraDetails = true;   // Print extra details rows?
        boolean printOneRow = false;        // Print one or many rows?

        // Number of Pairs/Triplets per row
        int noOfPairsOrTripletsPerRow = 10; // Change the Number of Pairs/Triplets per row here if printOneRow is false
        if ((noOfPairsOrTripletsPerRow <= 0) || (noOfPairsOrTripletsPerRow > 50)) { // 1 <= noOfPairsOrTripletsPerRow <= 50
            noOfPairsOrTripletsPerRow = 15; // Default: 15
        }

        // Print Array
        printArray(nums, "Array:");

        // Sort Array?
        if (sortArray) {
            // Sort the array
            Arrays.sort(nums, 0, nums.length);

            // Print Sorted Array
            System.out.println();
            printArray(nums, "Sorted Array:");
        }

        // Pairs
        System.out.println();
        System.out.println("Pairs:");
        System.out.println("------");

        String strPairsOrTriplets = "";
        String[] strPairsArray;

        int index = 1;
        for (int indexTarget: target) {
            nPairs = 0;
            System.out.println();
            System.out.println("(" + (index++) + ") nr = " + indexTarget + ":");
            strPairsOrTriplets = printAndBuildListOfPairs(nums, indexTarget, printExtraDetails);

            if (nPairs == 0) { // No Pairs found
                System.out.printf("These are no pairs having sum = %d%n", indexTarget);
            } else {
                // Print one or multiple rows?
                if (printOneRow || (nPairs <= noOfPairsOrTripletsPerRow)) { // Print one row
                    System.out.printf("These are the pairs having sum = %d:%n%s%n(Total: %d pairs)%n", indexTarget, strPairsOrTriplets, nPairs);
                } else { // Print multiple rows: noOfPairsOrTripletsPerRow per row
                    System.out.printf("These are the pairs having sum = %d:%n", indexTarget);

                    int itemNo = 1;
                    strPairsArray = strPairsOrTriplets.split(";");
                    for (String strItem : strPairsArray) {
                        if (((noOfPairsOrTripletsPerRow == 1) || (itemNo % noOfPairsOrTripletsPerRow == 1)) && (itemNo > 1)) { // Reached number of items per row: go to the next row
                            System.out.println();
                            System.out.print("...");
                        }

                        System.out.printf("%s", strItem);
                        itemNo++;

                        if (itemNo <= strPairsArray.length) { // Add ";" after item, but not after the last one
                            System.out.print(";");
                        }
                    }

                    System.out.printf("%n(Total: %d pairs)%n", nPairs);
                }
            }
        }

        // Triplets
        System.out.println();
        System.out.println("Triplets:");
        System.out.println("---------");

        strPairsOrTriplets = "";
        String[] strTripletsArray;

        index = 1;
        for (int indexTarget: target) {
            nTriplets = 0;
            System.out.println();
            System.out.println("(" + (index++) + ") nr = " + indexTarget + ":");
            strPairsOrTriplets = printAndBuildListOfTriplets(nums, indexTarget, printExtraDetails);

            if (nTriplets == 0) { // No Triplets found
                System.out.printf("These are no triplets having sum = %d%n", indexTarget);
            } else {
                // Print one or multiple rows?
                if (printOneRow || (nTriplets <= noOfPairsOrTripletsPerRow)) { // Print one row
                    System.out.printf("These are the triplets having sum = %d:%n%s%n(Total: %d triplets)%n", indexTarget, strPairsOrTriplets, nTriplets);
                } else { // Print multiple rows: noOfPairsOrTripletsPerRow per row
                    System.out.printf("These are the triplets having sum = %d:%n", indexTarget);

                    int itemNo = 1;
                    strTripletsArray = strPairsOrTriplets.split(";");
                    for (String strItem : strTripletsArray) {
                        if (((noOfPairsOrTripletsPerRow == 1) || (itemNo % noOfPairsOrTripletsPerRow == 1)) && (itemNo > 1)) { // Reached number of items per row: go to the next row
                            System.out.println();
                            System.out.print("...");
                        }

                        System.out.printf("%s", strItem);
                        itemNo++;

                        if (itemNo <= strTripletsArray.length) { // Add ";" after item, but not after the last one
                            System.out.print(";");
                        }
                    }

                    System.out.printf("%n(Total: %d triplets)%n", nTriplets);
                }
            }
        }
    }

    public static void printArray(int[] intArray, String title) {
        if (intArray.length == 0) {
            return;
        }

        if (title.length() > 0) {
            System.out.println(title);
        }

        int i = 0;
        for (int myInt: intArray) {
            System.out.printf("array[%2d] = %2d%n", i++, myInt);
        }
    }

    public static String printAndBuildListOfPairs(int[] intArray, int nr, boolean showDetails) {
        if (intArray.length <= 1) { // No Pairs
            return "";
        }

        String sPairs = "";
        String firstValueNeg = "";
        String secondValueNeg = "";
        String line = "";

        for (int i = 0; i <= intArray.length - 2; i++) {
            if (sortArray && (intArray[i] > nr)) {
                if (showDetails) {
                    System.out.printf("   (array[%2d] = %d > %d => break the first loop for the rest of the values)%n   ..........%n", i, intArray[i], nr);
                }
                break; // Array is Sorted: Skip the rest
            }
            for (int j = i + 1; j <= intArray.length - 1; j++) {
                if (showDetails) {
                    firstValueNeg  = String.format("%s%2d%s", ((intArray[i] < 0) ? "(" : " ") , intArray[i] , ((intArray[i] < 0) ? ")" : " "));
                    secondValueNeg = String.format("%s%2d%s", ((intArray[j] < 0) ? "(" : " ") , intArray[j] , ((intArray[j] < 0) ? ")" : " "));
                    line = String.format("array[%2d] + array[%2d] = %s + %s = %2d", i, j, firstValueNeg, secondValueNeg, intArray[i] + intArray[j]);

                    if (intArray[i] + intArray[j] == nr) { // Found
                        System.out.printf("Found: %s%n", line);
                    } else if (sortArray && (intArray[i] + intArray[j] > nr)) { // Break
                        System.out.printf("   (%s > %d => break the second loop for the rest of the values)%n   ..........%n", line, nr);
                        //break; // Array is Sorted: Skip the rest
                    } else { // intArray[i] + intArray[j] < nr: do nothing (continue)
                        System.out.printf("   (%s < %d => continue)%n", line, nr);
                    }
                }

                if (intArray[i] + intArray[j] == nr) {
                    if (sPairs.length() > 0) { // Add ";" (separator) starting with the second pair
                        sPairs += ";";
                    }
                    sPairs += "(" + intArray[i] + "," + intArray[j] + ")";
                    nPairs++;
                } else if (sortArray && (intArray[i] + intArray[j] > nr)) {
                    break; // Array is Sorted: Skip the rest
                }
            }
        }

        return sPairs;
    }

    public static String printAndBuildListOfTriplets(int[] intArray, int nr, boolean showDetails) {
        if (intArray.length <= 2) { // No Triplets
            return "";
        }

        String sTriplets = "";
        String firstValueNeg = "";
        String secondValueNeg = "";
        String thirdValueNeg = "";
        String line = "";
        boolean firstValNeg  = false;
        boolean secondValNeg = false;
        boolean thirdValNeg  = false;

        for (int i = 0; i <= intArray.length - 3; i++) {
            if (sortArray && (intArray[i] > nr)) {
                if (showDetails) {
                    System.out.printf("   (array[%2d] = %d > %d => break the first loop for the rest of the values)%n   ..........%n", i, intArray[i], nr);
                }
                break; // Array is Sorted: Skip the rest
            }
            for (int j = i + 1; j <= intArray.length - 2; j++) {
                if (sortArray) {
                    if (intArray[j] > nr) {
                        if (showDetails) {
                            System.out.printf("   (array[%2d] = %d > %d => break the second loop for the rest of the values)%n   ..........%n", j, intArray[j], nr);
                        }
                        break; // Array is Sorted: Skip the rest
                    } else if (intArray[i] + intArray[j] > nr) {
                        if (showDetails) {
                            firstValueNeg  = String.format("%s%2d%s", ((intArray[i] < 0) ? "(" : " ") , intArray[i] , ((intArray[i] < 0) ? ")" : " "));
                            secondValueNeg = String.format("%s%2d%s", ((intArray[j] < 0) ? "(" : " ") , intArray[j] , ((intArray[j] < 0) ? ")" : " "));
                            line = String.format("array[%2d] + array[%2d] = %s + %s = %2d", i, j, firstValueNeg, secondValueNeg, intArray[i] + intArray[j]);
                            System.out.printf("   (%s > %d => break the second loop for the rest of the values)%n   ..........%n", line, nr);
                        }
                        break; // Array is Sorted: Skip the rest
                    }
                }
                for (int k = j + 1; k <= intArray.length - 1; k++) {
                    /*
                    if (intArray[i] + intArray[j] + intArray[k] == nr) {
                        if (showDetails) {
                            firstValNeg  = (intArray[i] < 0);
                            secondValNeg = (intArray[j] < 0 );
                            thirdValNeg  = (intArray[k] < 0);
                            line = String.format("array[%2d] + array[%2d] + array[%2d] = %s%2d%s + %s%2d%s + %s%2d%s = %d",
                                    i, j, k, (firstValNeg ? "(" : " "), intArray[i], (firstValNeg ? ")" : " "),
                                    (secondValNeg ? "(" : " "), intArray[j], (secondValNeg ? ")" : " "),
                                    (thirdValNeg ? "(" : " "), intArray[k], (thirdValNeg ? ")" : " "), nr);
                            System.out.println(line);
                        }
                        if (sTriplets.length() > 0) { // Add ";" (separator) starting with the second triplet
                            sTriplets += ";";
                        }
                        sTriplets += "(" + intArray[i] + "," + intArray[j] + "," + intArray[k] + ")";
                        nTriplets++;
                    }
                    */
                    if (showDetails) {
                        firstValueNeg  = String.format("%s%2d%s", ((intArray[i] < 0) ? "(" : " ") , intArray[i] , ((intArray[i] < 0) ? ")" : " "));
                        secondValueNeg = String.format("%s%2d%s", ((intArray[j] < 0) ? "(" : " ") , intArray[j] , ((intArray[j] < 0) ? ")" : " "));
                        thirdValueNeg  = String.format("%s%2d%s", ((intArray[k] < 0) ? "(" : " ") , intArray[k] , ((intArray[k] < 0) ? ")" : " "));
                        line = String.format("array[%2d] + array[%2d] + array[%2d] = %s + %s + %s = %2d",
                                i, j, k, firstValueNeg, secondValueNeg, thirdValueNeg, intArray[i] + intArray[j] + intArray[k]);

                        if (intArray[i] + intArray[j] + intArray[k] == nr) {
                            System.out.printf("Found: %s%n", line);
                        } else if (sortArray && (intArray[i] + intArray[j] + intArray[k] > nr)) {
                            System.out.printf("   (%s > %d => break the third loop for the rest of the values)%n   ..........%n", line, nr);
                            //break; // Array is Sorted: Skip the rest
                        } else { // intArray[i] + intArray[j] + intArray[k] < nr: do nothing (continue)
                            System.out.printf("   (%s < %d => continue)%n", line, nr);
                        }
                    }

                    if (intArray[i] + intArray[j] + intArray[k] == nr) {
                        if (sTriplets.length() > 0) { // Add ";" (separator) starting with the second triplet
                            sTriplets += ";";
                        }
                        sTriplets += "(" + intArray[i] + "," + intArray[j] + "," + intArray[k] + ")";
                        nTriplets++;
                    } else if (sortArray && (intArray[i] + intArray[j] + intArray[k] > nr)) {
                        break; // Array is Sorted: Skip the rest
                    }

                }
            }
        }

        return sTriplets;
    }
}
