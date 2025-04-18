import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int qty;
        Heap<Edge> heap;
        Edge[] connections;

        File f = new File("src/main/resources/graph.txt").getAbsoluteFile();
        //print true if f exists
        //System.out.println(f.exists());
        try {
            //File f = new File("graph.txt");
            Scanner reader = new Scanner(f);
            //System.out.print("we here\n");
            if (reader.hasNextLine()) {
                qty = Integer.parseInt(reader.nextLine()); //number of vertices
                connections = new Edge[qty];
                //System.out.println(Edge qty + "\n");

                //Integer[] array = new Integer[3];
                //once you've acquired the number of vertices
                while (reader.hasNextLine()) {
                    for (int i=0; i<qty; i++) {
                        if (!reader.hasNextLine()) break; // avoid crash
                        String[] array = reader.nextLine().split(" ");
                        System.out.println(Arrays.toString(array));
                        int src = Integer.parseInt(array[0]);
                        int dest = Integer.parseInt(array[1]);
                        float weight = Float.parseFloat(array[2]);
                        Edge e = new Edge(src, dest, weight);
                        connections[i] = e;
                        //System.out.println(e);
                    }
                }
            }

            //heap = new Heap<Edge>(connections,qty);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.getMessage();
        }
    }
}
