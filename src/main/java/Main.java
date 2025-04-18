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
        //print true if f exists
        //System.out.println(f.exists());
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
                    String[] array = reader.nextLine().split(" ");
                    //System.out.println(Arrays.toString(array));
                    int src = Integer.parseInt(array[0]);
                    int dest = Integer.parseInt(array[1]);
                    float weight = Float.parseFloat(array[2]);
                    Edge e = new Edge(src, dest, weight);
                    connections.get(src - 1).add(e);
                    e = new Edge(dest, src, weight);
                    connections.get(dest - 1).add(e);

                    //System.out.println(e);
                }
            }

            System.out.println("The input graph is represented in an adjacent list as: ");
            for(int k=0;k < connections.size(); k++) {
                System.out.print(k+1);
                for(Edge e: connections.get(k)) {
                    System.out.print(" --> (" + e.destination + ", " + e.weight + ")");
                }
                System.out.println("\n");
            }

            //heap = new Heap<Edge>(connections,qty);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.getMessage();
        }
    }
}
