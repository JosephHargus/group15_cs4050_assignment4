import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int qty;
        //Heap<Edge> heap;
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

            //Prim's Alg
            //@author Evan Trejo
            int numVerticies = connections.size();
            Float[] key = new Float[numVerticies];
            int[] parent = new int[numVerticies];

            for (int i = 0; i < numVerticies; i++){
                key[i] = Float.MAX_VALUE; //infinity
                parent[i] = -1;
            }
            key[0] = 0f; //Index 0

            Heap<Float> heap = new Heap<>(key, numVerticies);
            
            System.out.println("\nRunning Prim algorithm");
            while (true) {
                int u = heap.min_id();
                if (u == -1) break;
            
                heap.delete_min();
            
                for (Edge edge : connections.get(u)) {
                    int v = edge.destination - 1; //zero-based
                    float weight = edge.weight;
            
                    if (heap.in_heap(v) && weight < key[v]) {
                        key[v] = weight;        //update key array
                        parent[v] = u;
                        heap.decrease_key(v, weight);
                    }
                }
            }
            
            //Print MST
            //@author Evan Trejo
            System.out.println("DONE");
            System.out.println("\nMinimum Spanning Tree:");
            float totalWeight = 0;
            for (int i = 1; i < numVerticies; i++) {
                if (parent[i] != -1) {
                    float weight = key[i];
                    totalWeight += weight;
                    System.out.printf("(%d, %d) weight: %.2f\n", parent[i] + 1, i + 1, weight);
                }
            }
            System.out.printf("\n");
            //System.out.printf("Total weight of MST: %.2f\n\n", totalWeight);

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
