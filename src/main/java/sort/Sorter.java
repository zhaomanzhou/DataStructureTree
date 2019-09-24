package sort;

public interface Sorter<T extends Comparable<T>>
{
    void sort(T[] a);

    default void swap(T[] a, int i, int j)
    {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * @return 排序器得名称
     */
    default String getName()
    {
        return "";
    }
}
