public class AOC_6_1 {

	public static void main(String[] args) {
		
		int [] time = {
			 54,81,70,88
		};
		int [] distance = {
			446,1292,1035,1007
			};
		int winningTimes = 0;
		int currentTimes = 1;
		
		for(int i = 0; i < 4; i++) {
			for(int t = 1; t < time[i]; t++) {
				if((time[i]-t) * t > distance[i]) {
				winningTimes++;
				}				
			}
			currentTimes = currentTimes * winningTimes;
			winningTimes = 0;
		}
		System.out.println(currentTimes);	
	}
}
