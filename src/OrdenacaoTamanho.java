import java.util.Scanner;

public class OrdenacaoTamanho {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < N; i++) {
            String[] palavras = scanner.nextLine().strip().split(" ");
            ordenar(palavras);
            System.out.println(String.join(" ", palavras));
        }
    }

    public static int lengthDiff(String a, String b) {
        return b.length() - a.length();
    }

    public static void ordenar(String[] palavra) {
        for (int i = 1; i < palavra.length; i++) {
            int j = i;
            int k = j - 1;

            while (k > -1 && lengthDiff(palavra[j], palavra[k]) < 0) {
                String temp = palavra[j];
                palavra[j] = palavra[k];
                palavra[k] = temp;
                j--;
                k--;
            }
        }
    }
}
