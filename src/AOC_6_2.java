public class AOC_6_2 {

	public static void main(String[] args) {
		
		Long [] time = {
				 54L,81L,70L,88L
			};
		Long [] distance = {
				446L,1292L,1035L,1007L
				};
		long winningTimes = 0;	
		


        long result1 = joinNumbers(time);
        long result2 = joinNumbers(distance);

        System.out.println("Result 1: " + result1);
        System.out.println("Result 2: " + result2);
		

			for(int t = 1; t < result1; t++) {
				if((result1-t) * t > result2) {
				winningTimes++;
				}							
		}
		System.out.println(winningTimes);	
	}
	
    public static Long joinNumbers(Long[] array) {
        // Convert each element to a string and concatenate
        StringBuilder concatenatedString = new StringBuilder();
        for (Long num : array) {
            concatenatedString.append(num);
        }

        // Parse the concatenated string into a Long
        return Long.parseLong(concatenatedString.toString());
    }
}