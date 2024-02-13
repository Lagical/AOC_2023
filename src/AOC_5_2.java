import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOC_5_2 {

	static String filePath = "AOC_2023.txt";
	static long currentNum = 0;
	static boolean first = true;
	static boolean found = false;
	
	 private static List<Long[]> parseMap(BufferedReader reader) throws IOException {
	        List<Long[]> map = new ArrayList<>();
	        String line;
	        while ((line = reader.readLine()) != null && !line.isEmpty()) {
	            String[] numbers = line.split("\\s+");
	            Long[] currentMap = new Long[numbers.length];
	            for (int i = 0; i < numbers.length; i++) {
	                currentMap[i] = Long.parseLong(numbers[i]);
	            }
	            map.add(currentMap);
	        }
	        return map;
	    }
	
    public static void main(String[] args) {
    	try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            long[] seeds = {
                    4043382508L, 113348245L, 3817519559L, 177922221L, 3613573568L,
                    7600537L, 773371046L, 400582097L, 2054637767L, 162982133L,
                    2246524522L, 153824596L, 1662955672L, 121419555L, 2473628355L,
                    846370595L, 1830497666L, 190544464L, 230006436L, 483872831L
                };
            List<List<Long[]>> allMaps = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("seeds:")) {           
                } else {
                	if (line.startsWith("seed-to-soil map:")) {
                        allMaps.add(parseMap(reader));
                    } else if (line.startsWith("soil-to-fertilizer map:")) {
                        allMaps.add(parseMap(reader));
                    } else if (line.startsWith("fertilizer-to-water map:")) {
                        allMaps.add(parseMap(reader));
                    } else if (line.startsWith("water-to-light map:")) {
                        allMaps.add(parseMap(reader));
                    } else if (line.startsWith("light-to-temperature map:")) {
                        allMaps.add(parseMap(reader));
                    } else if (line.startsWith("temperature-to-humidity map:")) {
                        allMaps.add(parseMap(reader));
                    } else if (line.startsWith("humidity-to-location map:")) {
                        allMaps.add(parseMap(reader));
                    }
                }
            }            
            reader.close();           
                 
            for(int i = 0; found != true; i++){
            	currentNum = i;
            	for (int z = 6; z != -1; z--) {
            		first = true;
                	for (int j = 0; j < allMaps.get(z).size(); j++) {
                		if(first == true) {
                        	if(currentNum >= allMaps.get(z).get(j)[0] && currentNum < allMaps.get(z).get(j)[0]+allMaps.get(z).get(j)[2]){
                        		first = false;
                        		if(allMaps.get(z).get(j)[1]>allMaps.get(z).get(j)[0]) {
                        			currentNum += (allMaps.get(z).get(j)[1]-allMaps.get(z).get(j)[0]);
                        		} else {
                        			currentNum = (allMaps.get(z).get(j)[1] + (currentNum - allMaps.get(z).get(j)[0]));                  			
                        		}
                        		
                        	}
                		}                  	
                    }
                }            
            	System.out.println(currentNum);
            	for(int x = 0; x < 19; x += 2) {
            		if((currentNum >= seeds[x] && currentNum <= (seeds[x]+seeds[x+1]))) {
                		System.out.println(currentNum + " ENSIMMÄINEN MATCH");
                		System.out.println(i + " location");
                		found = true;              	
            	}            	
            	}          
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
