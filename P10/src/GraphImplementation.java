// GraphImplementation.java - supplied code for graph assignment
// Author: Christopher Martin
// Date:   11/29/17
// Class:  CS165
// Email:  cgmar@rams.colostate.edu

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Implementation of the GraphAbstract class.
 * Provides methods for reading/writing graphs, and performing traversals and shortest path calculations.
 */
public class GraphImplementation extends GraphAbstract {

    // Main entry point
    /**
     * Main method to execute the graph operations.
     *
     * @param args Command line arguments. Expects input distance file and output graph file.
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("usage: java GraphImplementation <distanceFile> <graphFile>");
            System.exit(-1);
        }

        // Instantiate code
        GraphImplementation impl = new GraphImplementation();

        // Read distances chart
        System.out.println("Reading Chart: " + args[0]);
        impl.readGraph(args[0]);
        System.out.println();

        // Write distances graph
        System.out.println("Writing Graph: " + args[1]);
        impl.writeGraph(args[1]);
        System.out.println();

        // Print depth first search
        System.out.println("Depth First Search:");
        impl.depthFirst("Fort Collins");
        System.out.println();

        // Print breadth first search
        System.out.println("Breadth First Search:");
        impl.breadthFirst("Aspen");
        System.out.println();

        
        // EXTRA CREDIT: Print all shortest paths
        System.out.println("Shortest Path: ");
        for (int from = 0; from < cities.size(); from++) {
            for (int to = 0; to < cities.size(); to++)
                if (from != to) {
                    String fromCity = cities.get(from).name;
                    String toCity = cities.get(to).name;
                    impl.shortestPath(fromCity, toCity);
                }
        }
        
    }

    // Reads mileage chart from CSV file
    /**
     * Reads a graph from a CSV file containing city names and mileages.
     * Populates the cities and mileages lists.
     *
     * @param filename The path to the CSV file.
     */
    public void readGraph(String filename) {
    	ArrayList<String> data = readFile(filename);
    	
    	String[] cityNames = data.get(0).split(",");
    	for(String city: cityNames) {
    		if (!city.isEmpty()) {
    			cities.add(new GraphNode(city));    			
    		}
    	}
    	
    	int fromIndex = 0;
    	for (GraphNode city : cities) {
    		String [] edgeData = data.get(fromIndex + 1).split(",");
    		for (int toIndex = fromIndex + 1; toIndex < cities.size(); toIndex++) {
    			mileages.add(new GraphEdge(fromIndex, toIndex, Integer.parseInt(edgeData[toIndex + 1])));
    			city.edges.add(new GraphEdge(fromIndex, toIndex, Integer.parseInt(edgeData[toIndex + 1])));
    			cities.get(toIndex).edges.add(new GraphEdge(toIndex, fromIndex, Integer.parseInt(edgeData[toIndex + 1])));
    		}
    		city.edges.sort(null);
    		fromIndex++;
    	}
    	
    	mileages.sort(null);

    }

    /**
     * Writes the graph to a file in DOT format.
     *
     * @param filename The path to the output file.
     */
    public void writeGraph(String filename) {

        ArrayList<String> output = new ArrayList<>();
        output.add("digraph BST {");
        output.add("    ratio = 1.0;");
        output.add("    node [style=filled]");
        output.add("    node [fillcolor=darkslateblue]");
        output.add("    node [fixedsize=true]");
        output.add("    node [shape=oval]");
        output.add("    node [width=6]");
        output.add("    node [height=4]");
        output.add("    node [fontname=Arial]");
        output.add("    node [fontsize=60]");
        output.add("    node [fontcolor=white]");
        output.add("    edge [dir=none]");
        output.add("    edge [penwidth=24]");
        output.add("    edge [fontname=Arial]");
        output.add("    edge [fontsize=110]");
        
        int cityIndex = 0;
        for (GraphNode city: cities) {   	
        	output.add("    Node" + cityIndex + " [label=\"" + city.name + "\"];");
        	cityIndex++;
        }
        	

    	for (GraphEdge edge: mileages) {
    		if (!(edge.fromIndex >= edge.toIndex)) {
    			String color;
    			if (edge.mileage <= 100) {
    				color = "green";
    			}
    			else if (edge.mileage <= 200) {
    				color = "blue";
    			}
    			else if (edge.mileage <= 300) {
    				color = "magenta";
    			}
    			else {
    				color = "red";
    			}
    			
    			output.add("    Node" + edge.fromIndex + " -> Node" + edge.toIndex + " [label=\"" + edge.mileage + "\" color=\"" + color + "\"]");
    		}
    	}

        output.add("}");
        writeFile(filename, output);
    }

    private int getIndex(String city) {
    	int index;
    	for (index = 0; index < cities.size(); index++) {
    		if (cities.get(index).name.equals(city)) {
        		break;
        	}
    	}
    	
    	return index;
    }

    /**
     * Performs a depth-first search traversal of the graph starting from the specified city.
     * Prints the names of visited cities to the console.
     *
     * @param startCity The name of the city to start the traversal from.
     */
    public void depthFirst(String startCity) {
    	int index = getIndex(startCity);
        depthFirst(index, new ArrayList<Integer>(cities.size()));
    }

    // Recursive helper method
    /**
     * Recursive helper method for depth-first search.
     *
     * @param index The index of the current city.
     * @param visited List of visited city indices.
     */
    public void depthFirst(int index, ArrayList<Integer> visited) {
    	visited.add(index);
    	System.out.println("Visited " + cities.get(index).name);
    	
        for (GraphEdge edge: cities.get(index).edges) {
        	boolean visitedCity = false;
        	for (int i = 0; i < visited.size(); i++) {
        		if (visited.get(i).equals(edge.toIndex)) {
        			visitedCity = true;
        		}
        	}
        	if (!visitedCity) {
        		depthFirst(edge.toIndex, visited);
        	}
        }
    }

    /**
     * Performs a breadth-first search traversal of the graph starting from the specified city.
     * Prints the names of visited cities to the console.
     *
     * @param startCity The name of the city to start the traversal from.
     */
    public void breadthFirst(String startCity) {
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        ArrayList<Integer> visited = new ArrayList<Integer>(cities.size());
        int cityIndex = getIndex(startCity);
        queue.add(cityIndex);
        visited.add(cityIndex);
        
        while (!queue.isEmpty()) {
        	cityIndex = queue.poll();
        	System.out.println("Visited " + cities.get(cityIndex).name);
        	for (GraphEdge e: cities.get(cityIndex).edges) {
        		boolean visitedCity = false;
            	for (int i = 0; i < visited.size(); i++) {
            		if (visited.get(i).equals(e.toIndex)) {
            			visitedCity = true;
            		}
            	}
            	if (!visitedCity) {
            		queue.offer(e.toIndex);
            		visited.add(e.toIndex);
            	}
        	}
        }
        
    }


    /**
     * Calculates and prints the shortest path distance between two cities using Dijkstra's algorithm.
     *
     * @param fromCity The name of the starting city.
     * @param toCity The name of the destination city.
     */
    public void shortestPath(String fromCity, String toCity) {
    	int from = getIndex(fromCity);
    	int to = getIndex(toCity);
    	
    	
        ArrayList<Integer> min = new ArrayList<Integer>();
        ArrayList<Integer> prev = new ArrayList<Integer>();
        ArrayList<Integer> unv = new ArrayList<Integer>();
        
        for (int i = 0; i < cities.size(); i++) {
        	min.add(Integer.MAX_VALUE);
        	prev.add(-1);
        	unv.add(i);
        }
        min.set(from, 0);
        
        while (!unv.isEmpty()) {
        	
        	Integer minSK = Integer.MAX_VALUE;
        	int unvIndex = -1;
        	for (int i = 0; i < unv.size(); i++) {
        		if (min.get(unv.get(i)) < minSK) {
        			minSK = min.get(unv.get(i));
        			unvIndex = i;
        		}
        	}
        	unv.remove(unvIndex);
        	
        	int indexSK = min.indexOf(minSK);
	        
	        // update shortest distances
	        for (GraphEdge e: cities.get(indexSK).edges) {
	        	int cost = minSK + e.mileage;
	        	
	        	if (cost < min.get(e.toIndex)) {
	        		min.set(e.toIndex, cost);
	        		prev.set(e.toIndex, indexSK);
	        	}
	        }
        }
        
        System.out.println("[" + fromCity + ", " + toCity + "] (Mileage " + min.get(to) + ")");
    }

    // Helper functions

    /**
     * Reads the contents of file to {@code ArrayList}
     * @param filename the file to read from
     * @return an ArrayList of the contents
     */
    static ArrayList<String> readFile(String filename) {
        ArrayList<String> contents = new ArrayList<>();
        try(Scanner reader = new Scanner(new File(filename))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (!line.isEmpty())
                    contents.add(line);
            }
        } catch (IOException e) {
            System.err.println("Cannot read chart: " + filename);
        }
        return contents;
    }

    /**
     * Write contents of {@code ArrayList} to file
     * @param filename the name of the file to write to
     * @param contents an ArrayList of contents to write
     */
    static void writeFile(String filename, ArrayList<String> contents) {
        try(PrintWriter writer = new PrintWriter(filename)) {
            for (String line : contents)
                writer.println(line);
        } catch (IOException e) {
            System.err.println("Cannot write graph: " + filename);
        }
    }
}
