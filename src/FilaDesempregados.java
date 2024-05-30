import java.util.Scanner;

class Node {
    int id;
    Node anterior;
    Node proxima;

    Node(int id) {
        this.id = id;
    }
}

enum Direction {
    HORARIO,
    ANTIHORARIO
}

public class FilaDesempregados {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int m = scanner.nextInt();

            if (n == 0 && k == 0 && m == 0)
                break;

            Node list = criarListaCircular(n);
            Node K = list;
            Node M = list.anterior;

            while (getNodeCount(list) > 2) {
                K = move(K, Direction.HORARIO, k);
                M = move(M, Direction.ANTIHORARIO, m);

                Node nextK = (K.proxima == M) ? M.proxima : K.proxima;
                Node nextM = (M.anterior == K) ? K.anterior : M.anterior;

                if (K == M) {
                    System.out.printf("%3d,", K.id);
                } else {
                    System.out.printf("%3d%3d,", K.id, M.id);
                    list = removerNode(list, M);
                }

                list = removerNode(list, K);

                K = nextK;
                M = nextM;
            }

            if (getNodeCount(list) == 2) {
                K = move(K, Direction.HORARIO, k);
                M = move(M, Direction.ANTIHORARIO, m);

                if (K == M) {
                    System.out.printf("%3d,%3d\n", K.id, K.proxima.id);
                } else {
                    System.out.printf("%3d%3d\n", K.id, K.proxima.id);
                }
            } else {
                System.out.printf("%3d\n", list.id);
            }
        }
        scanner.close();
    }

    static Node criarListaCircular(int size) {
        Node start = null;
        Node nodePrev = null;

        for (int i = 1; i <= size; ++i) {
            Node node = new Node(i);
            if (start == null) {
                start = node;
            } else {
                nodePrev.proxima = node;
                node.anterior = nodePrev;
            }
            nodePrev = node;
        }

        start.anterior = nodePrev;
        nodePrev.proxima = start;

        return start;
    }

    static Node removerNode(Node list, Node node) {
        Node prev = node.anterior;
        Node next = node.proxima;

        if (node == list) {
            list = next;
        }
        prev.proxima = next;
        next.anterior = prev;

        return list;
    }

    static int getNodeCount(Node list) {
        int count = 1;
        Node current = list;
        while (current.proxima != list) {
            current = current.proxima;
            count++;
        }
        return count;
    }

    static Node move(Node node, Direction direction, int steps) {
        for (int i = 1; i < steps; i++) {
            node = (direction == Direction.HORARIO) ? node.proxima : node.anterior;
        }
        return node;
    }
}
