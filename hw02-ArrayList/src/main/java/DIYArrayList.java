import java.util.*;
import java.util.function.UnaryOperator;

public class DIYArrayList<T> implements List<T> {
    private static final Object[] EMPTY_ITEM = {};
    private Object[] array;
    private int curSize;

    public DIYArrayList(int size) {
        if (size >= 0) {
            array = new Object[size];
            this.curSize = 0;
        }
        else {
            throw new IllegalArgumentException("Invalid DIYArrayList size: " + size);
        }
    }

    public DIYArrayList() {
        new DIYArrayList(0);
    }

    /*
    * copy-past из ArrayList с корректировкой на свои переменные
    * */
    public DIYArrayList(Collection<? extends T> source) {
        array = source.toArray();
        if ((curSize = array.length) != 0) {
            if (array.getClass() != Object[].class)
                array = Arrays.copyOf(array, curSize, Object[].class);
        } else {
            array = EMPTY_ITEM;
        }
    }

    @Override
    public int size() {
        return this.curSize;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, curSize);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder().append("Размер = ").append(getLength()).append("; [");

        for (int i = 0; i < curSize; i++) {
            str.append(" " + array[i]);
            if (i < curSize - 1) {
                str.append(", ");
            }
        }
        str.append(" ]");
        return str.toString();
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) array, 0, curSize, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean add(T t) {
        if (curSize == array.length) {

            int length = array.length;

            if (array.length > 0) {
                array = Arrays.copyOf(array, array.length+10);
            }
        }

        array[curSize] = t;
        curSize++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        T oldValue = (T) array[index];
        array[index] = element;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Unsupported method");
    }

    @Override
    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException("Unsupported method");
    }

    public int getLength() {
        if (array == null) {return 0;}

        return array.length;
    }
}
