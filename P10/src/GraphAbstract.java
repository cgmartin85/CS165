// GraphAbstract.java - abstract class for graph assignment
// Author: Chris Wilcox
// Date:   4/16/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

import java.util.ArrayList;

/**
 * Abstract class representing a graph data structure for cities and mileages.
 * Provides the structure for nodes and edges, and abstract methods for
 * graph operations.
 */
public abstract class GraphAbstract {

    // Represents a graph edge
    /**
     * Inner class representing a graph edge.
     * Implements Comparable to allow sorting by mileage.
     */
    public class GraphEdge implements Comparable<GraphEdge> {
        /** Index of the starting city. */
        public int fromIndex; // index of "from" city
        /** Index of the destination city. */
        public int toIndex; // index of "to" city
        /** Mileage (weight) of the edge. */
        public int mileage; // mileage between cities

        /**
         * Constructs a new GraphEdge.
         *
         * @param from Index of the source city.
         * @param to Index of the destination city.
         * @param mileage The distance between the cities.
         */
        public GraphEdge (int from, int to, int mileage) {
            this.fromIndex = from;
            this.toIndex = to;
            this.mileage = mileage;
        }

        /**
         * Compares this edge to another edge based on mileage.
         *
         * @param edge The edge to compare to.
         * @return A negative integer, zero, or a positive integer as this edge is less than, equal to, or greater than the specified edge.
         */
        public int compareTo(GraphEdge edge) {
            return this.mileage - edge.mileage;
        }
    }

    // Represents a graph node (and incident edges)
    /**
     * Inner class representing a graph node (city).
     */
    public class GraphNode {
        /** Name of the city. */
        public String name; // City name
        /** List of edges connected to this city. */
        public ArrayList<GraphEdge> edges; // Distances

        /**
         * Constructs a new GraphNode.
         *
         * @param name The name of the city.
         */
        public GraphNode(String name) {
            this.name = name;
            edges = new ArrayList<>();
        }

        /**
         * Checks if this node is equal to another object.
         * Equality is based on the city name.
         *
         * @param object The object to compare to.
         * @return true if the objects are equal, false otherwise.
         */
        public boolean equals(Object object) {
            return object instanceof GraphNode && ((GraphNode) object).name.equals(this.name);
        }
    }
    
    // Graph data structures
    /** Static list of all cities (nodes) in the graph. */
    public static ArrayList<GraphNode> cities = new ArrayList<>();
    /** Static list of all mileages (edges) in the graph. */
    public static ArrayList<GraphEdge> mileages = new ArrayList<>();


    /**
     * Reads mileage chart from CSV file and builds lists of nodes (cities) and edges (distances).
     * <p>
     * The first line contains all the cities which should be represented as {@link GraphNode}s <br>
     * The successive lines start with a city, followed by a list of mileages to other cities.
     * <p>
     * To avoid redundancy, not all the values are filled in, ignore empty entries. <br>
     * When you read a mileage, for example from Fort Collins to Pueblo, create only one
     * entry in the mileages array, but add the edge to both cities.
     * <p>
     * First extract all the edges, then sort the edges by mileage, then add the edges
     * associated with each node.
     * @param filename the CSV file
     */
    public abstract void readGraph(String filename);

    /**
     * Writes mileage graph to DOT file.
     * <p>
     * Traverses the data structures created above and writes nodes and edges in GraphViz format.
     * You will build the GraphViz format into an {@code ArrayList}, which will then be written to file
     * using the {@link GraphImplementation#writeFile(String, ArrayList)} method.
     * <p>
     * Use the provided example and the following directions to implement this method:
     * <ul>
     *     <li> All attributes of nodes and edges that are identical are put into the header of the .dot file.
     *     <li> The nodes are labeled Node0, Node1, etc., in the same order as the input file,
     *          and they have city names as labels.
     *     <li> The edges are then written, in the format Node0 -> Node1, etc. and labeled with a distance and color.
     * </ul>
     * The edge color should be green for {@code ≤100 miles}, blue for {@code ≤200} miles,
     * magenta for {@code ≤300 miles}, and red otherwise.
     * <p>
     * HINT: Match the file format exactly as provided in order to pass automated grading!
     * @param filename the output file name
     */
    public abstract void writeGraph(String filename);
    
    // Print graph in depth first search order
    /**
     * Performs a depth-first search traversal of the graph starting from the specified city.
     * Prints the visited cities.
     *
     * @param startCity The name of the city to start the traversal from.
     */
    public abstract void depthFirst(String startCity);

    // Print graph in breadth first search order
    /**
     * Performs a breadth-first search traversal of the graph starting from the specified city.
     * Prints the visited cities.
     *
     * @param startCity The name of the city to start the traversal from.
     */
    public abstract void breadthFirst(String startCity);

    // Calculate and print shortest path
    /**
     * Calculates and prints the shortest path between two cities.
     *
     * @param fromCity The name of the starting city.
     * @param toCity The name of the destination city.
     */
    public abstract void shortestPath(String fromCity, String toCity);
}
