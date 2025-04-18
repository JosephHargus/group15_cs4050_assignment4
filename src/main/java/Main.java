import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int qty;
        Heap<Edge> heap;
        Edge[] connections;

        File f = new File("src/main/resources/graph.txt").getAbsoluteFile();
        System.out.println(f.exists());
        try {
            //File f = new File("graph.txt");
            Scanner reader = new Scanner(f);
            System.out.print("we here");
            if (reader.hasNextLine()) {
                qty = Integer.parseInt(reader.nextLine()); //number of vertices
                connections = new Edge[qty];
                System.out.println(qty);
                //once you've acquired the number of vertices
                while (reader.hasNextLine()) {
                    for (int i=0; i<qty; i++) {
                        int src = reader.nextInt();
                        int dest = reader.nextInt();
                        int weight = reader.nextInt();
                        Edge e = new Edge(src, dest, weight);
                        connections[i] = e;
                        System.out.println(e);
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
