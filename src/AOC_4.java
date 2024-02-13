import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC_4 {

	static String filePath = "AOC_2023.txt";
	static int score = 0;
	
    public static void main(String[] args) {
    	try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(":");

                if (parts.length == 2 && parts[0].trim().startsWith("Card")) {                   
                    String[] numbersBefore = parts[1].split("\\|")[0].trim().split("\\s+");
                    String[] numbersAfter = parts[1].split("\\|")[1].trim().split("\\s+");

                    int cardValue = 1;
                    int matches = 0; 
                    
                    for (String numberBefore : numbersBefore) {
                        for (String numberAfter : numbersAfter) {
                            if (numberBefore.equals(numberAfter)) {
                                matches++;
                                break;
                            }
                        }
                    }

                    if (matches > 0) {
                        for (int i = 1; i <= matches; i++) {
                            cardValue *= 2;
                        }
                    } else {
                    	cardValue = 0;
                    }                   
                    System.out.println("Card: " + parts[0].trim() + " | Matches: " + matches + " | Card Value: " + cardValue);
                    score += cardValue / 2;

                }
            }
            System.out.println(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println(numbersBefore);
    }
}
