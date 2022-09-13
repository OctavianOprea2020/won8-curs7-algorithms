public class PairsAndTriplets {
    static int nPairs = 0;
    static int nTriplets = 0;

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
        printArray(nums);

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

    public static void printArray(int[] intArray) {
        if (intArray.length == 0) {
            return;
        }

        int i = 0;
        System.out.println("Array is:");
        for (int myInt: intArray) {
            System.out.printf("array[%2d] = %2d%n", i++, myInt);
        }
    }

    public static String printAndBuildListOfPairs(int[] intArray, int nr, boolean showDetails) {
        if (intArray.length <= 1) { // No Pairs
            return "";
        }

        String sPairs = "";
        String line = "";
        boolean firstValNeg  = false;
        boolean secondValNeg = false;

        for (int i = 0; i <= intArray.length - 2; i++) {
            for (int j = i + 1; j <= intArray.length - 1; j++) {
                //System.out.println("i=" + i + ", j=" + j);
                if (intArray[i] + intArray[j] == nr) {
                    if (showDetails) {
                        firstValNeg  = (intArray[i] < 0);
                        secondValNeg = (intArray[j] < 0);
                        line = String.format("array[%2d] + array[%2d] = %s%2d%s + %s%2d%s = %d",
                                i, j,  (firstValNeg ? "(" : " "), intArray[i], (firstValNeg ? ")" : " "),
                                (secondValNeg ? "(" : " "), intArray[j], (secondValNeg ? ")" : " "), nr);
                        System.out.println(line);
                    }
                    if (sPairs.length() > 0) { // Add ";" (separator) starting with the second pair
                        sPairs += ";";
                    }
                    sPairs += "(" + intArray[i] + "," + intArray[j] + ")";
                    nPairs++;
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
        String line = "";
        boolean firstValNeg  = false;
        boolean secondValNeg = false;
        boolean thirdValNeg  = false;

        for (int i = 0; i <= intArray.length - 3; i++) {
            for (int j = i + 1; j <= intArray.length - 2; j++) {
                for (int k = j + 1; k <= intArray.length - 1; k++) {
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
                }
            }
        }

        return sTriplets;
    }
}
