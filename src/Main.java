import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LinkedList<String> resultados = new LinkedList<>();
        int n = 10;
        int k = 4;
        int m = 3;
        resultados.add(filaDeDesempregados(n, k, m));


        for (String resultado : resultados) {
            System.out.println(resultado);
        }

    }

    public static String filaDeDesempregados(int n, int k, int m) {
        LinkedList<Integer> candidatos = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            candidatos.add(i);
        }

        LinkedList<String> saida = new LinkedList<>();
        ListIterator<Integer> itHorario = candidatos.listIterator();
        ListIterator<Integer> itAntihorario = candidatos.listIterator(candidatos.size());

        while (!candidatos.isEmpty()) {
            // Movendo no sentido horário
            int removidoHorario = moverHorario(itHorario, k, candidatos);

            // Movendo no sentido anti-horário
            int removidoAntihorario = moverAntihorario(itAntihorario, m, candidatos);

            if (removidoHorario == removidoAntihorario) {
                saida.add(String.valueOf(removidoHorario));
                // Remover elemento selecionado por ambos iteradores
                removerElemento(itHorario, removidoHorario, candidatos);
                itAntihorario = candidatos.listIterator(candidatos.size());
            } else {
                saida.add(removidoHorario + " " + removidoAntihorario);
                // Remover elementos selecionados por iteradores separados
                removerElemento(itHorario, removidoHorario, candidatos);
                removerElemento(itAntihorario, removidoAntihorario, candidatos);
            }

            // Reinicializar iteradores se necessário
            if (!candidatos.isEmpty()) {
                if (!itHorario.hasNext()) {
                    itHorario = candidatos.listIterator();
                }
                if (!itAntihorario.hasPrevious()) {
                    itAntihorario = candidatos.listIterator(candidatos.size());
                }
            }
        }

        return String.join(", ", saida);
    }

    private static int moverHorario(ListIterator<Integer> itHorario, int k, LinkedList<Integer> candidatos) {
        for (int i = 0; i < k; i++) {
            if (!itHorario.hasNext()) {
                itHorario = candidatos.listIterator();
            }
            itHorario.next();
        }
        itHorario.previous(); // Ajusta o iterador para o elemento removido
        return itHorario.next();
    }

    private static int moverAntihorario(ListIterator<Integer> itAntihorario, int m, LinkedList<Integer> candidatos) {
        for (int i = 0; i < m; i++) {
            if (!itAntihorario.hasPrevious()) {
                itAntihorario = candidatos.listIterator(candidatos.size());
            }
            itAntihorario.previous();
        }
        itAntihorario.next(); // Ajusta o iterador para o elemento removido
        return itAntihorario.previous();
    }

    private static void removerElemento(ListIterator<Integer> iterator, int elemento, LinkedList<Integer> candidatos) {
        while (iterator.hasNext()) {
            if (iterator.next() == elemento) {
                iterator.remove();
                break;
            }
        }
    }
}
