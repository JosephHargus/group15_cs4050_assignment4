/**
 * @author Kaitlyn Self
 *
 *
 */
public class Edge extends Number implements Comparable<Edge> {
    int destination;
    float weight;

    /**
     * Constructor
     * @param destination the other end of the vert-to-vert connection
     * @param weight the weight of the edge
     */
    public Edge(int destination, float weight) {
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return weight;
    }

    @Override
    public double doubleValue() {
        return 0;
    }

    @Override
    public int compareTo(Edge o) {
        return Float.compare(this.weight, o.weight);
    }
}
