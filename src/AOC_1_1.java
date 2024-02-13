
// import necessary packages
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AOC_1_1 {

	static String filePath = "AOC_2023.txt";
	static boolean gear = false;
	static int gearNumber = 0;
	static int touching = 0;
	static int firstNum = 0;
	static int gearSum = 0;
	
	/*public static Set<Character> findUniqueCharacters(char[][] grid) {
        Set<Character> uniqueCharacters = new HashSet<>();

        // Iterate through the grid
        for (char[] row : grid) {
            for (char cell : row) {
                // Check if the character is not a dot and not a digit
                if (cell != '.' && !Character.isDigit(cell)) {
                    uniqueCharacters.add(cell);
                }
            }
        }

        return uniqueCharacters;
    }*/
	
    public static List<Integer> extractNumbers(char[][] grid, char symbol) {
        List<Integer> extractedNumbers = new ArrayList<>();

        // Iterate through the grid
        for (int i = 0; i < grid.length; i++) {
            List<Integer> currentGroup = new ArrayList<>();

            for (int j = 0; j < grid[i].length; j++) {

                // Check if the current cell contains the specified symbol
                if (grid[i][j] == symbol) {
                	
                	if(symbol == '*') {
                		gear = true;
                		System.out.println(i + " i " + j + " j " + " * sijainti");
                		gearNumber++;
                		touching = 0;
                		firstNum = 0;
                	} else {
                		gear = false;
                	}

                	
                    // Check adjacent cells (up, down, left, right)
                    checkAndAddNumber(currentGroup, grid, i - 1, j); // Up
                    checkAndAddNumber(currentGroup, grid, i + 1, j); // Down
                    checkAndAddNumber(currentGroup, grid, i, j - 1); // Left
                    checkAndAddNumber(currentGroup, grid, i, j + 1); // Right

                    // Check diagonally (up-left, up-right, down-left, down-right)
                    checkAndAddNumber(currentGroup, grid, i - 1, j - 1); // Up-left
                    checkAndAddNumber(currentGroup, grid, i - 1, j + 1); // Up-right
                    checkAndAddNumber(currentGroup, grid, i + 1, j - 1); // Down-left
                    checkAndAddNumber(currentGroup, grid, i + 1, j + 1); // Down-right

                }
            }
            
            if (!currentGroup.isEmpty()) {           	
                for (int num : currentGroup) {
                    extractedNumbers.add(num);
                }
            }

        }

        return extractedNumbers;
    }

    private static void checkAndAddNumber(List<Integer> currentGroup, char[][] grid, int row, int col) {
        // Check if the cell is within the grid boundaries
    	if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length) {

    	    // Check if the cell contains a digit
    	    if (Character.isDigit(grid[row][col])) {
    	        // Extract multi-digit number in both directions (left and right)
    	        int num = 0;
    	        int colIndex = col;
    	        int multiplier = 1;
    	        boolean encounteredZero = false;
    	        

    	        // Extract to the left
    	        while (colIndex >= 0 && Character.isDigit(grid[row][colIndex])) {
    	            int digit = Character.getNumericValue(grid[row][colIndex]);

    	            if (digit == 0) {
    	                if (colIndex == col) {
    	                    multiplier = 10;
    	                } else if (encounteredZero && colIndex == col - 1) {
    	                    multiplier = 100;
    	                }
    	                encounteredZero = true;
    	            } else {
    	                encounteredZero = false;
    	            }
    	            
    	            
    	            num = num * 10 + digit;
    	            grid[row][colIndex] = '.';
    	            colIndex--;
    	        }

    	        if (multiplier > 1) {
    	            num = reverseNumber(num);
    	            num *= multiplier;
    	        } else {
    	            num = reverseNumber(num);
    	        }

    	        // Reset colIndex to the original value
    	        colIndex = col + 1;
    	
                // Extract to the right
                while (colIndex < grid[row].length && Character.isDigit(grid[row][colIndex])) {
                    num = num * 10 + Character.getNumericValue(grid[row][colIndex]);
                    grid[row][colIndex] = '.';
                    colIndex++;
                }
                touching++;
                System.out.println(touching);
                if (touching == 1) {
                	firstNum = num;
                }
                if(touching == 2) {
                	System.out.println(firstNum + "eka numero");
                	System.out.println(num + "toka numero");
                	gearSum += firstNum*num;
                }
                currentGroup.add(num);
            }
        }
    }

    public static char[][] readGridFromFile(String filePath) throws IOException {
        List<char[]> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.toCharArray());
            }
        }

        char[][] grid = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i);
        }

        return grid;
    }
    
    private static int reverseNumber(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return reversed;
    }

    public static void main(String[] args) {
        try {
            char[][] grid = readGridFromFile(filePath);           
            char symbolToCheck = '*';
            List<Integer> extractedNumbers = extractNumbers(grid, symbolToCheck);            
            System.out.println("Numbers adjacent to '" + symbolToCheck + "': " + extractedNumbers);
            System.out.println(gearSum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

