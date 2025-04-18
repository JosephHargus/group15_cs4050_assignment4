import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int qty;
        Heap<Edge> heap;
        ArrayList<ArrayList<Edge>> connections = null;

        File f = new File("src/main/resources/graph.txt").getAbsoluteFile();

        try {
            Scanner reader = new Scanner(f);

            if (reader.hasNextLine()) {
                qty = Integer.parseInt(reader.nextLine()); //number of vertices
                connections = new ArrayList<ArrayList<Edge>>();
                for (int i = 0; i < qty; i++) {
                    connections.add(new ArrayList<Edge>());
                }

                //once you've acquired the number of vertices
                while (reader.hasNextLine()) {
                    if (!reader.hasNextLine()) break; // avoid crash
                    String[] array = reader.nextLine().split(" "); //reading the line

                    //converting to ints and float
                    int src = Integer.parseInt(array[0]);
                    int dest = Integer.parseInt(array[1]);
                    float weight = Float.parseFloat(array[2]);

                    //writing out the edge in either direction
                    Edge e = new Edge(dest, weight);
                    connections.get(src - 1).add(e);
                    e = new Edge(src, weight);
                    connections.get(dest - 1).add(e);
                }
            }

            //The list of edges before using Prim's algorithm
            System.out.println("The input graph is represented in an adjacent list as: ");
            for(int k=0;k < connections.size(); k++) {
                adjacentList(connections.get(k),k+1);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.getMessage();
        }
    }

    /**
     * This method produces all connected vertices to a given vertex n.
     * @author Kaitlyn Self
     * @param adj All edges connected to element n
     * @param n This is the id of the source vertex
     */
    public static void adjacentList(ArrayList<Edge> adj, int n) {
        System.out.print(n);
        for(Edge e: adj) {
            System.out.print(" --> (" + e.destination + ", " + e.weight + ")");
        }
        System.out.print("\n");
    }
}
