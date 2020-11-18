import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DIYArrayListTest {
    List<Integer> intArrDst;
    List<Integer> intArrSrc;

    private static void getArrayData(List<Integer> array){
        for(int i = 0; i < array.size(); i++) {
            array.add(i, (int)Math.round(Math.random()*20));
        }
    }

    public static void main(String[] args) {

        List<Integer> intArrSrc = new DIYArrayList<>();

        Integer[] testArray = new Integer[20];
        for(int i = 0; i < Arrays.stream(testArray).count(); i++) {
            testArray[i] = (int)Math.round(Math.random()*20);
        }
        Collections.addAll(intArrSrc, testArray);
        System.out.println("iArrSrc: " + intArrSrc.toString());

        List<Integer> intArrDst = new DIYArrayList<>(20);
        getArrayData(intArrDst);
        System.out.println("iArrDest before: " + intArrDst.toString());

        // Проверка Collections.static <T> void copy(List<? super T> dest, List<? extends T> src)
        try{
            Collections.copy(intArrDst, intArrSrc);
        } catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        System.out.println("iArrDest  after: " + intArrDst.toString());

        // Проверка Collections.addAll(Collection<? super T> c, T... elements)
        Collections.addAll(intArrSrc, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println("iArrSrc: " + intArrSrc.toString());

        // Проверка Collections.static <T> void sort(List<T> list, Comparator<? super T> c)
        Collections.sort(intArrSrc);
        System.out.println("iArrSrc: "  + intArrSrc.toString());
    }
}
