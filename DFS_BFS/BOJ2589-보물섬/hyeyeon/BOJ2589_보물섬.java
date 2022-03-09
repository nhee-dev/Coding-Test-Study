package A0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2589_º¸¹°¼¶ {
	
	static char[][] map;
	static int N,M;
	static int max;
	static boolean[][] visited;
	
	static class Node {
        int x;
        int y;
        int cost;
        
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
	
	static boolean isWall(int x,int y) {
		if(x<0||x>=N||y<0||y>=M) return true;
		return false;
	}
	
	static int bfs(int x, int y) {
		int[] dx = {0,0,-1,1};
		int[] dy = {-1,1,0,0};
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0));
        visited[x][y] = true;
        
        int len = 0;
        while(!q.isEmpty()) {
            Node current = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if(!isWall(nx,ny)) {
                    if(visited[nx][ny] == false && map[nx][ny] == 'L') {
                        q.offer(new Node(nx, ny, current.cost + 1));
                        len = Math.max(len, current.cost + 1);
                        visited[nx][ny] = true;
                    }
                }
            }
           
        }
        return len;
        
    }
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		map=new char[N][M];
		for(int i=0;i<N;i++) {
			char[] a = br.readLine().toCharArray();
			map[i]=a;
		}
		
		max=0;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]=='L') {
					visited = new boolean[N][M]; 
					int len = bfs(i,j);
					max = Math.max(max, len);
				}
			}
		}
		
		System.out.println(max);
	
		
		
	}
}
