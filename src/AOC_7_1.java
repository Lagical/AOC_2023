import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AOC_7_1 {

	static String filePath = "AOCTEST_2023.txt";
	 
	public static void main(String[] args) {
		List<String[]> stringArrays = new ArrayList<>();
		Map<Character, Integer> letterToNumberMap = new HashMap<>();
        letterToNumberMap.put('T', 10);
        letterToNumberMap.put('J', 11);
        letterToNumberMap.put('Q', 12);
        letterToNumberMap.put('K', 13);
        letterToNumberMap.put('A', 14);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Extract the first 5 characters
                String firstFiveChars = line.substring(0, Math.min(5, line.length()));

                // Split each character of the first 5 characters into its own index
                String[] firstFiveCharsArray = firstFiveChars.split("(?!^)");

                // Split the rest of the line based on the space delimiter
                String[] parts = line.substring(5).split(" ");

                // Combine the first five characters with the rest of the array elements
                List<String> result = new ArrayList<>();
                
                // Add characters from 'firstFiveCharsArray' to 'result' excluding empty strings
                for (String character : firstFiveCharsArray) {
                    if (!character.isEmpty()) {
                        result.add(character);
                    }
                }

                // Add the rest of the elements from 'parts' to 'result'
                for (String part : parts) {
                    if (!part.isEmpty()) {
                        result.add(part);
                    }
                }

                // Convert 'result' list to an array
                String[] resultArray = result.toArray(new String[0]);

                // Add the modified array to the list
                stringArrays.add(resultArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Now 'stringArrays' contains each line with the first 5 characters split into their own indices
        // You can access and process them like this:
        for(int y = 0; y < stringArrays.size(); y++) {
        	String[] moro = stringArrays.get(y);
            for (int i = 0; i < moro.length; i++) {
            	System.out.print(moro[i]+",");
            }
            System.out.println();
        }       
    }
}