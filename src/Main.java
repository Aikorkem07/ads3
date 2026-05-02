import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        // Task 1: Anagram Sort Checker
        System.out.println("--- Task 1: Anagram Sort Checker ---");
        System.out.print("Enter first string: ");
        String firstString = inputScanner.next();
        System.out.print("Enter second string: ");
        String secondString = inputScanner.next();
        verifyAnagram(firstString, secondString);

        // Task 2: K-th Smallest Element
        System.out.println("\n--- Task 2: K-th Smallest Element ---");
        System.out.print("Enter array size: ");
        int arraySize = inputScanner.nextInt();
        int[] elementsArray = new int[arraySize];
        System.out.print("Enter elements separated by spaces: ");
        for (int index = 0; index < arraySize; index++) {
            elementsArray[index] = inputScanner.nextInt();
        }
        System.out.print("Enter k (to find k-th smallest): ");
        int kValue = inputScanner.nextInt();
        findKthSmallestElement(elementsArray, kValue);

        // Task 3: Median Element
        System.out.println("\n--- Task 3: Median Element ---");
        System.out.print("Enter array size for median: ");
        int medianArraySize = inputScanner.nextInt();
        int[] medianArray = new int[medianArraySize];
        System.out.print("Enter elements separated by spaces: ");
        for (int index = 0; index < medianArraySize; index++) {
            medianArray[index] = inputScanner.nextInt();
        }
        calculateMedian(medianArray);

        // Task 4: Optimal Shipping Capacity
        System.out.println("\n--- Task 4: Optimal Shipping Capacity ---");
        System.out.print("Enter number of packages: ");
        int packageCount = inputScanner.nextInt();
        int[] packageWeights = new int[packageCount];
        System.out.print("Enter weights of packages separated by spaces: ");
        for (int index = 0; index < packageCount; index++) {
            packageWeights[index] = inputScanner.nextInt();
        }
        System.out.print("Enter number of days: ");
        int requiredDays = inputScanner.nextInt();
        findOptimalCapacity(packageWeights, requiredDays);

        inputScanner.close();
    }

    /*Check if strings are anagrams.*/
    public static void verifyAnagram(String firstString, String secondString) {
        if (firstString.length() != secondString.length()) {
            System.out.println("Output: NO");
            return;
        }

        char[] firstChars = firstString.toCharArray();
        char[] secondChars = secondString.toCharArray();

        sortCharArray(firstChars);
        sortCharArray(secondChars);

        boolean isAnagram = true;
        for (int index = 0; index < firstChars.length; index++) {
            if (firstChars[index] != secondChars[index]) {
                isAnagram = false;
                break;
            }
        }

        if (isAnagram) {
            System.out.println("Output: YES");
        } else {
            System.out.println("Output: NO");
        }
    }

    /*Sort char array using bubble sort.*/
    public static void sortCharArray(char[] charArray) {
        int length = charArray.length;
        for (int outer = 0; outer < length - 1; outer++) {
            for (int inner = 0; inner < length - outer - 1; inner++) {
                if (charArray[inner] > charArray[inner + 1]) {
                    char temp = charArray[inner];
                    charArray[inner] = charArray[inner + 1];
                    charArray[inner + 1] = temp;
                }
            }
        }
    }

    /* Find k-th smallest element.*/
    public static void findKthSmallestElement(int[] array, int k) {
        if (k < 1 || k > array.length) {
            System.out.println("Invalid k value provided.");
            return;
        }
        sortIntArray(array);
        int kThSmallest = array[k - 1];
        System.out.println("Output: " + kThSmallest);
    }

    /* Sort int array using bubble sort. */
    public static void sortIntArray(int[] array) {
        int length = array.length;
        for (int outer = 0; outer < length - 1; outer++) {
            for (int inner = 0; inner < length - outer - 1; inner++) {
                if (array[inner] > array[inner + 1]) {
                    int temp = array[inner];
                    array[inner] = array[inner + 1];
                    array[inner + 1] = temp;
                }
            }
        }
    }

    /*Calculate and print median value.*/
    public static void calculateMedian(int[] array) {
        sortIntArray(array);
        int length = array.length;
        if (length == 0) {
            System.out.println("Array is empty.");
            return;
        }
        if (length % 2 == 1) {
            System.out.println("Output: " + array[length / 2]);
        } else {
            double median = (array[length / 2 - 1] + array[length / 2]) / 2.0;
            System.out.println("Output: " + median);
        }
    }

    /*Find optimal capacity via binary search.*/
    public static void findOptimalCapacity(int[] weights, int days) {
        int leftCapacity = 0;
        int rightCapacity = 0;
        for (int weight : weights) {
            leftCapacity = Math.max(leftCapacity, weight);
            rightCapacity += weight;
        }

        int minimumCapacity = rightCapacity;

        while (leftCapacity <= rightCapacity) {
            int middleCapacity = leftCapacity + (rightCapacity - leftCapacity) / 2;
            if (canShipInDays(weights, days, middleCapacity)) {
                minimumCapacity = middleCapacity;
                rightCapacity = middleCapacity - 1;
            } else {
                leftCapacity = middleCapacity + 1;
            }
        }
        System.out.println("Output: " + minimumCapacity);
    }

    /* Check if shipment is possible within days.*/
    public static boolean canShipInDays(int[] weights, int days, int capacity) {
        int currentDays = 1;
        int currentWeight = 0;

        for (int weight : weights) {
            if (currentWeight + weight > capacity) {
                currentDays++;
                currentWeight = weight;
            } else {
                currentWeight += weight;
            }
        }

        return currentDays <= days;
    }
}