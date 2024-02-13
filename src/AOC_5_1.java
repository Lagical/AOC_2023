import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AOC_5_1 {
	
	static String filePath = "AOC_2023.txt";
	static long currentNum = 0;
	static boolean first = true;
	
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
            // Step 1: Read the text file
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            List<Long[]> maps = new ArrayList<>();
            Long[] seeds = null;
            List<List<Long[]>> allMaps = new ArrayList<>();

            // Step 2: Find the line containing the seeds
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("seeds:")) {
                    // Extract numbers after "seeds:" and parse into a long array
                    String[] seedsArray = line.substring("seeds:".length()).trim().split("\\s+");
                    seeds = new Long[seedsArray.length];

                    for (int i = 0; i < seedsArray.length; i++) {
                        seeds[i] = Long.parseLong(seedsArray[i]);
                    }               

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
            
            
            //if og luku on 
                 
            for(int i = 0; i < seeds.length; i++) {
            	currentNum = seeds[i];            	
            	for (int z = 0; z < allMaps.size(); z++) {
            		first = true;
                	for (int j = 0; j < allMaps.get(z).size(); j++) {
                		if(first == true) {
                        	if(currentNum < allMaps.get(z).get(j)[1]+allMaps.get(z).get(j)[2] && currentNum > allMaps.get(z).get(j)[1]-1){
                        		first = false;
                        		//System.out.println(currentNum + "MXDDSDAD"); 
                        		//System.out.println("SUMMA MINKÄ ALLA PITÄÄ OLLA " + (allMaps.get(z).get(j)[1]+allMaps.get(z).get(j)[2]-1));
                        		if(allMaps.get(z).get(j)[1]<allMaps.get(z).get(j)[0]) {
                        			//System.out.println((allMaps.get(z).get(j)[0]-allMaps.get(z).get(j)[1]) + "TESTIIIIIIIIIII");
                        			currentNum += (allMaps.get(z).get(j)[0]-allMaps.get(z).get(j)[1]);
                        			//System.out.println(currentNum+  " LOOPIS POSI???");
                        		} else {
                        			//System.out.println((allMaps.get(z).get(j)[0]+allMaps.get(z).get(j)[1]) + "TESTIIIIIIIIIII");
                        			//System.out.println(currentNum + " " + allMaps.get(z).get(j)[1] +" "+ allMaps.get(z).get(j)[0] + " KRIIIITTINEN");
                        			currentNum = (currentNum - allMaps.get(z).get(j)[1] + allMaps.get(z).get(j)[0]);                  			
                        		}
                        		
                        	}
                		}                  	
                 	//System.out.println(currentNum + " PYSYY SAMANA");
                    }
                }
            	System.out.println(currentNum);
            }
            //System.out.println(seeds[0]);
            //System.out.println(maps.get(0)[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}