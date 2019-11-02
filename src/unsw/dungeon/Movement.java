package unsw.dungeon;
import java.util.*; 
import java.lang.*; 
import java.io.*; 

class Movement {
	private int graphDimension;
	private int graph[][];
	private int src;
	
	ArrayList<ArrayList<Entity>> map = new ArrayList<ArrayList<Entity>>();
	
	public Movement(int height, int width, ArrayList<ArrayList<Entity>> map) {
		this.graphDimension = height*width;
		this.map = map;
		this.graph = new int[height*width][height*width];
		generateGraph(height,width);
	}
	
	/*
	 * Generate an adjacency matrix for every dirt square
	 * Graph is width*height by width*height
	 * If a square i is reachable from square j, graph[i][j] = 1
	 * and graph[j][i] = 1
	 */
	public void generateGraph(int height, int width) {
		for(int i = 0; i < map.size(); i++) {
		    for(int j = 0; j < map.get(i).size(); j++) {
		    	//find the enemy and set it as the source
		    	if (map.get(i).get(j) instanceof Enemy) {
		    		src =i*width+j;
		    	}
		    	//see if adjacent squares are reachable
		    	if (!(map.get(i).get(j) instanceof Wall)) {
		    		if (i != 0 && !(map.get(i-1).get(j) instanceof Wall)) {
		    			graph[i*width+j][(i-1)*width+j] = 1;
		    			graph[(i-1)*width+j][i*width+j] = 1;
		    		}
		    			
		    		if (i != height-1 && !(map.get(i+1).get(j) instanceof Wall)) {
		    			graph[i*width+j][(i+1)*width+j] = 1;
		    			graph[(i+1)*width+j][i*width+j] = 1;
		    		}
		    			
		    		if (j != 0 && !(map.get(i).get(j-1) instanceof Wall)) {
		    			graph[i*width+j][i*width+(j-1)] = 1;
		    			graph[i*width+(j-1)][i*width+j] = 1;
		    		}
		    			
		    		if (j != width-1 && !(map.get(i).get(j+1) instanceof Wall)) {
		    			graph[i*width+j][i*width+(j+1)] = 1;
		    			graph[i*width+(j+1)][i*width+j] = 1;
		    		}
		    	}
		    }
		}
	}
	
	 /*
	  * Find the distances to all reachable squares
	  * and return the shortest distance
	  */
	 public int minDistance(int dist[], Boolean sptSet[]) { 
	        // Initialize min value 
	        int min = Integer.MAX_VALUE, min_index = -1;
	  
	        for (int v = 0; v < graphDimension; v++) 
	            if (sptSet[v] == false && dist[v] <= min) { 
	                min = dist[v]; 
	                min_index = v; 
	            } 
	        return min_index; 
	 } 
/*	
	void printSolution(int dist[]) { 
	        System.out.println("Vertex \t\t Distance from Source"); 
	        for (int i = 0; i < graphDimension; i++) 
	            System.out.println(i + " \t\t " + dist[i]); 
	} 
*/	
	 
	 /*
	  * Function that implements Dijkstra's algorithm
	  */
	 public int[] dijkstra() { 
			//dist[i] is shortest distance from src to i
			int dist[] = new int[graphDimension];

			//from[i] holds the previous step before reaching i
			int from[] = new int[graphDimension];
			
			for (int i = 0; i < graphDimension; i++) {
				from[i] = -1;
			}
			from[src] = src;
			
			// sptSet[i] will true if vertex i is included in shortest 
			// path tree or shortest distance from src to i is finalized 
			Boolean sptSet[] = new Boolean[graphDimension]; 

			// Initialize all distances as INFINITE and stpSet[] as false 
			for (int i = 0; i < graphDimension; i++) { 
				dist[i] = Integer.MAX_VALUE; 
				sptSet[i] = false; 
			} 

			// Distance of source vertex from itself is always 0 
			dist[src] = 0; 

			// Find shortest path for all vertices 
			for (int count = 0; count < graphDimension - 1; count++) { 
				// Pick the minimum distance vertex from the set of vertices 
				// not yet processed. u is always equal to src in first 
				// iteration. 
				int u = minDistance(dist, sptSet); 

				// Mark the picked vertex as processed 
				sptSet[u] = true; 
				// Update dist value of the adjacent vertices of the 
				// picked vertex. 
				for (int v = 0; v < graphDimension; v++) 

					// Update dist[v] only if is not in sptSet, there is an 
					// edge from u to v, and total weight of path from src to 
					// v through u is smaller than current value of dist[v] 
					if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) { 
						dist[v] = dist[u] + graph[u][v]; 
						from[v] = u;
					}
			} 
			return from;
		} 
}
