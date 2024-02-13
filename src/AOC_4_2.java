import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC_4_2 {

	static String filePath = "AOC_2023.txt";
	static boolean first = true;
	static int cards = 0;
	
	
    public static void main(String[] args) {
    	try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int[] copies = new int[223];
            for (int i = 0; i < copies.length; i++) {
                copies[i] = 1;}
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                
                if (parts.length == 2 && parts[0].trim().startsWith("Card")) {
                    // Extract numeric part from parts[0]
                    String cardNumberString = parts[0].trim().substring("Card".length()).trim();
                   // System.out.println(cardNumberString+ "CARD NUMBER STRING");
                    try {
                        int cardNumber = Integer.parseInt(cardNumberString);
                        
                        String[] numbersBefore = parts[1].split("\\|")[0].trim().split("\\s+");
                        String[] numbersAfter = parts[1].split("\\|")[1].trim().split("\\s+");

                        int matches = 0;                        
                            for (String numberBefore : numbersBefore) {
                                for (String numberAfter : numbersAfter) {
                                    if (numberBefore.equals(numberAfter)) {
                                        matches++;
                                        break;
                                    }
                                }
                            }
                        // System.out.println("Card: " + cardNumber + " | Matches: " + matches);
                        int saveCard = cardNumber;
                        
                        for (int i = 0; i < matches; i++) {
                        	cardNumber++;
                            copies[cardNumber] += copies[saveCard];
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid card number format: " + cardNumberString);
                    }
                }
            }
            
            for (int i = 0; i < copies.length; i++) {
            	cards += copies[i];
            }
            System.out.println(cards);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
