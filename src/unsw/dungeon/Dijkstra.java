package unsw.dungeon;
import java.util.*; 
import java.lang.*; 
import java.io.*; 

/**
 * A class that helps to determines the shortest path to a location
 * using the Dijkstra algorithm. It contains a 1 to 1 entity map as 
 * well as an adjacency matrix generated from this map. The
 * class has a method which calculates the shortest path
 * from the source attribute.
 */
public class Dijkstra{
	private int graphDimension;
	private int height;
	private int width;
	private int graph[][];
	private int src;
	private int from[];
	ArrayList<ArrayList<Entity>> map = new ArrayList<ArrayList<Entity>>();
	
	/**
	 * Constructs a Dijkstra object
	 * @param height : height of the dungeon
     * @param width : width of the dungeon
     * @param map : 1 to 1 entity map of the dungeon
	 */
	public Dijkstra(int height, int width, ArrayList<ArrayList<Entity>> map) {
		this.graphDimension = height*width;
		this.height = height;
		this.width = width;
		this.map = map;
		this.graph = new int[height*width][height*width];
		generateGraph(height,width);
	}
	
	/**
	 * Generate an adjacency matrix for every dirt square
	 * Graph is width*height by width*height
	 * If a square i is reachable from square j, graph[i][j] = 1
	 * and graph[j][i] = 1
	 * @param height : height of the dungeon
	 * @param width : width of the dungeon
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
	
	 /**
	  * Find the distances to all reachable squares
	  * and return the shortest distance
	  * @param dist : array of distances to each vertex
	  * @param sptSet : array indicating if vertex is reachable
	  * @return : index of the minimum reachable distance
	  */
	 public int minDistance(int dist[], Boolean sptSet[]) { 
		 // Initialise minimum value to max integer value,
		 //set minimum index to -1
		 int min = Integer.MAX_VALUE;
		 int min_index = -1;
	        
        //for all the vertices, check if it is reachable and
        //save the minimum distance and index
        for (int v = 0; v < graphDimension; v++) 
            if (sptSet[v] == false && dist[v] <= min) { 
                min = dist[v]; 
                min_index = v; 
            } 
        return min_index; 
	 } 

	 /**
	  * Function that implements Dijkstra's algorithm
	  * @param e : entity that is the source
	  */
	 public void dijkstra(Entity e) { 
		 src = e.getY()*width+e.getX();
		//dist[i] is shortest distance from src to i
		int dist[] = new int[graphDimension];
		
		//from[i] holds the previous step before reaching i
		int from[] = new int[graphDimension];
		
		//initialise the traceback array to -1
		for (int i = 0; i < graphDimension; i++) {
			from[i] = -1;
		}
		//source is always reachable from source
		from[src] = src;
		
		// sptSet[i] will be true if vertex i is included in shortest 
		// path tree or shortest distance from src to i is finalized 
		Boolean sptSet[] = new Boolean[graphDimension]; 
		
		// Initialize all distances to be max integer and stpSet[] as false 
		for (int i = 0; i < graphDimension; i++) { 
			dist[i] = Integer.MAX_VALUE; 
			sptSet[i] = false; 
		} 
		
		// Distance of source vertex from itself is always 0 
		dist[src] = 0; 
		
		// Find shortest path for all vertices 
		for (int i = 0; i < graphDimension - 1; i++) { 
			// Pick the minimum distance vertex from the set of vertices 
			// not yet processed. u is always equal to src in first 
			// iteration. 
			int u = minDistance(dist, sptSet); 
		
			// Mark the picked vertex as processed 
			sptSet[u] = true; 
			
			// Update dist value of the adjacent vertices of the 
			// picked vertex. 
			for (int v = 0; v < graphDimension; v++) {		
				// Update dist[v] only if is not in sptSet, there is an 
				// edge from u to v, and total weight of path from src to 
				// v through u is smaller than current value of dist[v] 
				// fill in the traceback array
				if (!sptSet[v] && graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v] && dist[u] != Integer.MAX_VALUE) { 
					dist[v] = dist[u] + graph[u][v]; 
					from[v] = u;
				}
			}
		} 
		this.from = from;
	}
	 
	public int[][] getGraph(){
		return graph;
	}
	
	public int[] getFrom() {
		return from;
	}
}