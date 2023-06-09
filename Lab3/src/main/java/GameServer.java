import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Node {
    int id;
    List<Edge> edges;

    public Node(int id) {
        this.id = id;
        this.edges = new ArrayList<>();
    }
}

class Edge {
    Node source;
    Node destination;
    int latency;

    public Edge(Node source, Node destination, int latency) {
        this.source = source;
        this.destination = destination;
        this.latency = latency;
    }
}

public class GameServer {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            nodes.add(new Node(i + 1));
        }

        List<Node> clients = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int nodeId = scanner.nextInt();
            clients.add(nodes.get(nodeId - 1));
        }

        // Перевірка, чи є ребра в графі
        if (M == 0) {
            System.out.println(0);
            scanner.close();
            return;
        }

        for (int i = 0; i < M; i++) {
            int startNode = scanner.nextInt();
            int endNode = scanner.nextInt();
            int latency = scanner.nextInt();

            Node start = nodes.get(startNode - 1);
            Node end = nodes.get(endNode - 1);

            start.edges.add(new Edge(start, end, latency));
            end.edges.add(new Edge(end, start, latency));
        }

        int maxLatency = INF;

        for (Node server : nodes) {
            int[][] distances = calculateDistances(server, nodes);

            int serverLatency = calculateMaxLatency(distances, clients);
            maxLatency = Math.min(maxLatency, serverLatency);
        }

        System.out.println(maxLatency);

        scanner.close();
    }

    private static int[][] calculateDistances(Node server, List<Node> nodes) {
        int[][] distances = new int[nodes.size()][nodes.size()];

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                distances[i][j] = INF;
            }
        }

        for (Node node : nodes) {
            distances[node.id - 1][node.id - 1] = 0;

            for (Edge edge : node.edges) {
                distances[edge.source.id - 1][edge.destination.id - 1] = edge.latency;
            }
        }

        for (Node k : nodes) {
            for (Node i : nodes) {
                for (Node j : nodes) {
                    if (distances[i.id - 1][k.id - 1] != INF && distances[k.id - 1][j.id - 1] != INF) {
                        distances[i.id - 1][j.id - 1] = Math.min(distances[i.id - 1][j.id - 1],
                                distances[i.id - 1][k.id - 1] + distances[k.id - 1][j.id - 1]);
                    }
                }
            }
        }

        return distances;
    }

    private static int calculateMaxLatency(int[][] distances, List<Node> clients) {
        int maxLatency = 0;

        for (Node client : clients) {
            for (Node otherClient : clients) {
                maxLatency = Math.max(maxLatency, distances[client.id - 1][otherClient.id - 1]);
            }
        }

        return maxLatency;
    }
}



