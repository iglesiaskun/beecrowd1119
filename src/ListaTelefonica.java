import java.util.Arrays;
import java.util.Scanner;

class Numeros implements Comparable<Numeros> {
    String num;

    public Numeros(String num) {
        this.num = num;
    }

    @Override
    public int compareTo(Numeros other) {
        return this.num.compareTo(other.num);
    }
}

public class ListaTelefonica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            Numeros[] array = new Numeros[n];
            for (int i = 0; i < n; i++) {
                String num = scanner.nextLine();
                array[i] = new Numeros(num);
            }

            Arrays.sort(array);

            Numeros comp = array[0];
            int cont = 0;

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < array[i].num.length(); j++) {
                    if (comp.num.charAt(0) != array[i].num.charAt(0)) {
                        comp = array[i];
                        break;
                    } else if (comp.num.charAt(j) == array[i].num.charAt(j)) {
                        cont++;
                    } else {
                        comp = array[i];
                        break;
                    }
                }
            }

            System.out.println(cont);
        }

        scanner.close();
    }
}
