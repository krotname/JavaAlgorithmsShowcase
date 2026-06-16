package other;


import java.util.ArrayList;
import java.util.List;


// Выводит в консоль все возможные перестановки из массива
// Размещение без повторений (3,3)
// Упорядоченная выборка, элементам в которой повторяться запрещенно

public class Permutations {

    private final List<int[]> result = new ArrayList<>();

    private static void arrSwap(int[] pa, int i, int j) {
        int k = pa[i];
        pa[i] = pa[j];
        pa[j] = k;
    }

    private void arrOut(int[] pa) {
        int[] clone = pa.clone();
        for (int i = 0; i < clone.length; i++) {
            clone[i] *= 11;
        }
        result.add(clone);
    }

    public void permutations(int[] pa, int i) {
        if (i == pa.length - 1) {
            arrOut(pa);
        } else {
            for (int j = i; j < pa.length; j++) {
                arrSwap(pa, i, j);
                permutations(pa, i + 1);
                arrSwap(pa, i, j);
            }
        }
    }

    public List<int[]> result() {
        return result.stream()
                .map(int[]::clone)
                .toList();
    }
}
