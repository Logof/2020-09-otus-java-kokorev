import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Создание объектов ---");

        DIYArrayList<Integer> diyArrayList1 = new DIYArrayList<>();
        System.out.println("1 конструктор: " + diyArrayList1.toString());

        DIYArrayList<Integer> diyArrayList2 = new DIYArrayList<>(30);
        System.out.println("2 конструктор: " + diyArrayList2.toString());

        DIYArrayList<String> diyArrayList3 = new DIYArrayList<>(Arrays.asList("Строка 1",
                                                                              "Строка 2",
                                                                              "Строка 3",
                                                                              "Строка 4",
                                                                              "Строка 5",
                                                                              "Строка 6",
                                                                              "Строка 7",
                                                                              "Строка 8",
                                                                              "Строка 9"));
        System.out.println("3 конструктор: " + diyArrayList3.toString());


        System.out.println("\r\n\r\n\r\n--- тестируем Collections.addAll(Collection<? super T> c, T... elements) ---");
        System.out.println("Добаление списка элементов {\"Новая строка 1\", \"Новая строка 2\", \"Новая строка 3\", \"Новая строка 4\", \"Новая строка 5\"}");
        Collections.addAll(diyArrayList3, "Новая строка 1", "Новая строка 2", "Новая строка 3", "Новая строка 4", "Новая строка 5");
        System.out.println("После метода addAll: " + diyArrayList3.toString());


        System.out.println("\r\n\r\n\r\n--- тестируем Collections.static <T> void copy(List<? super T> dest, List<? extends T> src) ---");
        DIYArrayList<String> diyArrayDest = new DIYArrayList<>(Arrays.asList("1", "2", "3"));
        System.out.println("DIYArrayList(dest) " + diyArrayDest.toString());
        System.out.println("DIYArrayList(source) " + diyArrayList3.toString());
        Collections.copy(diyArrayList3, diyArrayDest);
        System.out.println("После метода copy: " + diyArrayList3.toString());

        System.out.println("\r\n\r\n\r\n--- тестируем Collections.static <T> void sort(List<T> list, Comparator<? super T> c) ---");
        Collections.sort(diyArrayList3);
        System.out.println("После сортировки: " + diyArrayList3.toString());

        for (int i = 0; i < 30; i++) {
            diyArrayList2.add(new Random().nextInt(30));
        }
        System.out.println("\r\n---------------------------------------------------");
        System.out.println("Сгенерированный список int для упорядочивания: " + diyArrayList2);
        Collections.sort(diyArrayList2);
        System.out.println("После сортировки: " + diyArrayList2);
    }


}