import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int qty;
        Heap<Edge> heap;
        Edge[] connections;
        try {
            File f = new File("graph.txt");
            Scanner reader = new Scanner(f);
            if (reader.hasNextLine()) {
                qty = Integer.parseInt(reader.nextLine());
            }
            while (reader.hasNextLine()) {
                int index = reader.nextInt();
                System.out.println(index);
            }
            heap = new Heap<Edge>(connections,qty);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
}
