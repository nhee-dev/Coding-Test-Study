import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static int max = Integer.MIN_VALUE;
	private static List<Character> list = new ArrayList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int length = Integer.parseInt(br.readLine());
		String[] arr = new String[length];
		
		int[] output;
		boolean[] visit;
		
		for(int i=0;i<length;i++) {
			String str = br.readLine();
			arr[i] = str;
			for (int j = 0; j < arr[i].length(); j++) {
				char ch = arr[i].charAt(j);
				if(!list.contains(ch)) {
					list.add(ch);
				}
			}
		}
		output = new int[list.size()];
		visit =  new boolean[list.size()];
		
		dfs(arr,output,visit,0,length,list.size());
		System.out.println(max);
	}

	private static void dfs(String[] arr, int[] output, boolean[] visit, int depth, int length, int size) {
		if(depth==size) {
			int sum = 0;
			for (int i = 0; i < length; i++) {
				 String num="";
				 for (int j = 0; j < arr[i].length(); j++) {
					 num = num + String.valueOf(output[list.indexOf(arr[i].charAt(j))]);
				 }
				 sum+= Integer.parseInt(num);
			}
			max = Math.max(max, sum);
			return;
		}
		for(int i=0;i<size;i++) {
			if(!visit[i]) {
				visit[i]=true;
				output[depth]=9-i;
				dfs(arr,output,visit,depth+1,length,size);
				output[depth]=0;
				visit[i]=false;
			}
		}
	}

}
