package sort;

/**
 * 选择排序
 * 平均时间复杂度O(n^2)
 * 最坏时间复杂度O(n^2)
 * 最好时间复杂度O(n^2)
 * 为不稳定排序
 * 空间复杂度O(1)
 */
public class SelectionSort<T extends Comparable<T>> implements Sorter<T>
{
    @Override
    public void sort(T[] a)
    {
        int n = a.length;
        for(int i = 0; i < n - 1; i++)
        {
            //最小值对应的下标
            int minIndex = i;
            for(int j = i + 1; j < n; j++)
            {
                if(a[j].compareTo(a[minIndex]) < 0)
                    minIndex = j;
            }
            if(minIndex != i)
            {
                swap(a, i, minIndex);
            }
        }
    }

    @Override
    public String getName()
    {
        return "Selection sort";
    }
}
