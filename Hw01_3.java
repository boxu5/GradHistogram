package hw02;

// done in 7/2; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hw01_3 {
	public static final int maxScore = 100;

	public static void main(String[] args) throws FileNotFoundException {
		String input = null;
		int i = 0;
		String filePath = "C:\\Users\\chao\\Documents\\GT\\cs1331\\hw1\\grades.csv";
		String[] lineArray = null;

		// read the file
		Scanner scan = new Scanner(new File(filePath));

		// calculate the length of the array
		while (scan.hasNext()) {
			String line = scan.nextLine();
			++i;
		}
		int x = 0;
		int[] array = new int[i];
		// scan.reset();

		Scanner scan1 = new Scanner(new File(filePath));
		while (scan1.hasNext()) {
			String line = scan1.nextLine();
			lineArray = line.split(",");
			// only fetch the number from array(second column)
			array[x] = Integer.parseInt(lineArray[1].trim());
			++x;		
		}
		scan1.close();
		Hw01_3 test = new Hw01_3();

		System.out.println("Grades Loaded!");
		Scanner reader = new Scanner(System.in);
  
		System.out.print("Enter a number smaller than 100 and could divided by 100:");
		input = reader.next();
		int input1 = Integer.parseInt(input);
		int[] counts = test.CountColPerRow(array, input1);
		test.displayCounts(counts);
		test.reverseCount(counts);

		int[][] initArray = test.init2DArray(counts);

		int[][] finalArray = test.reportByScore(initArray, array);

		String[][] finalArraywithComment = test.finalReport(finalArray, maxScore, input1);

		test.print2DArray(finalArraywithComment);
         
		reader.close();
	}

	public int[][] init2DArray(int[] counts) {
		int b = counts.length;
		int[][] init2DArray = new int[b][];
		for (int index = 0; index < b; index++) {
			init2DArray[index] = new int[counts[index]];
		}

		return init2DArray;

	}

	public void print2DArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {

			System.out.println();
			for (int j = 0; j < array[i].length; j++) {
				System.out.print("[" + array[i][j] + "] ");
			}
		}
	}

	public void print2DArray(String[][] array) {
		for (int i = 0; i < array.length; i++) {

			System.out.println();

			for (int j = 0; j < array[i].length; j++) {

				if (j == 0) {
					if (i == 0) {
						System.out.print(array[i][j] + " |");
					} else {
						System.out.print(array[i][j] + "\t |");
					}
				} else {
					System.out.print("[" + array[i][j] + "] ");
				}

			}

		}

	}

	// report the grades by rows
	public int[][] reportByScore(int array[][], int[] array1) {

		int b = array.length - 1;
		int a = maxScore / b;
		for (int i = 0; i < array1.length; i++) {
			if (array1[i] != 0) {
				array[(b - (array1[i] - 1) / a - 1)][availableSpace(array, (b - (array1[i] - 1) / a - 1))] = array1[i];
			} else {
				array[b][availableSpace(array, b)] = array1[i];
			}

		}

		return array;
	}

	public String[][] finalReport(int[][] array, int maxScore, int input1) {

		String[][] stringArray = new String[array.length][];

		for (int i = 0; i < array.length; i++) {

			stringArray[i] = new String[array[i].length + 1];
		}

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length + 1; j++) {
				if (j != 0) {
					stringArray[i][j] = Integer.toString(array[i][j - 1]);
				} else {
					stringArray[i][j] = (Integer.toString(maxScore - i * input1) + " - "
							+ Integer.toString(maxScore - (i + 1) * input1 + 1));
				}
			}

		}

		return stringArray;

	}

	public int availableSpace(int[][] array, int inCurRow) {

		int col = 0;
		for (int i = 0; i < array[inCurRow].length; i++) {
			if (array[inCurRow][i] != 0) {
				continue;
			}
			col = i;
			break;
		}
		return col;
	}

	public int[] CountColPerRow(int array[], int i) {
		int a = maxScore / i + 1;
		int[] counts = new int[a];
		// System.out.print(i);
		for (int x = 0; x < array.length; x++) {
			if (array[x] != 0) {
				counts[((array[x] - 1) / i) + 1]++;
			} else {
				counts[0]++;
			}
		}
		return counts;
	}

	public void displayCounts(int[] counts) {
		for (int i = 0; i < counts.length; i++) {

		}
	}

	// grade 91-100, 81-90; 71-80.....
	public int[] reverseCount(int counts[]) {

		int i = 0, j = counts.length - 1;
		while (i < j) {
			int temp = 0;
			temp = counts[i];
			counts[i] = counts[j];
			counts[j] = temp;
			++i;
			--j;
		}
		return counts;
	}
}
