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

public class GraphImplementation extends GraphAbstract {

    // Main entry point
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

        /*
        // EXTRA CREDIT: Print all shortest paths
        for (int from = 0; from < cities.size(); from++) {
            for (int to = 0; to < cities.size(); to++)
                if (from != to) {
                    String fromCity = cities.get(from).name;
                    String toCity = cities.get(to).name;
                    System.out.print("Shortest Path: ");
                    impl.shortestPath(fromCity, toCity);
                }
        }
        */
    }

    // Reads mileage chart from CSV file
    public void readGraph(String filename) {
    	ArrayList<String> data = readFile(filename);
    	String[] cityNames = data.get(0).split(",");
    	for(String city: cityNames) {
    		if (!city.isEmpty()) {
    			cities.add(new GraphNode(city));    			
    		}
    	}
    	
		for (int i = 0; i < cities.size(); i++) {
			String [] edges = data.get(i+1).split(",");
			
			// TODO sort edges
			
			for (int j = 1; j < edges.length; j++) {
				if (!edges[j].isEmpty()) {
					cities.get(i).edges.add(new GraphEdge(i, j-1, Integer.parseInt(edges[j])));					
				}
			}
		}
		
		
		
		
		for (int i = 0; i < cities.size(); i++) {
			for (int j = 0; j < cities.get(i).edges.size(); j++) {
				System.out.print(cities.get(i).edges.get(j).fromIndex + ", " + cities.get(i).edges.get(j).toIndex + ", "+ cities.get(i).edges.get(j).mileage + "; ");
			}
			System.out.println();
		}
		

        // YOUR CODE HERE
    }

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
        
        // YOUR CODE HERE
        // Write distances graph
        output.add("}");
        writeFile(filename, output);
    }



    public void depthFirst(String startCity) {
        // YOUR CODE HERE
    }

    // Recursive helper method
    public void depthFirst(int index, ArrayList<Integer> visited) {
        // YOUR CODE HERE
    }

    public void breadthFirst(String startCity) {
        // YOUR CODE HERE
    }


    public void shortestPath(String fromCity, String toCity) {
        // YOUR CODE HERE
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