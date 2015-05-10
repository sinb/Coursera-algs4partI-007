import java.util.Random;
public class KnuthShuffle {
	private static long seed = System.currentTimeMillis();
	private static Random random = new Random(seed);
	public static void knuthShuffle (int[] arr) {
		int N = arr.length;
        for (int i = 0; i < N; i++) {
            int r = random.nextInt(i + 1);    
            int temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
        }		
	}
	public static void main(String[] args) {
		int[] arr = new int[100];
		for(int i = 0; i < 100; i++) {
			arr[i] = i+1;
		}
		knuthShuffle (arr);
		for(int i = 0; i < 100; i++) {
			System.out.println(arr[i]);
		}
	}
}
