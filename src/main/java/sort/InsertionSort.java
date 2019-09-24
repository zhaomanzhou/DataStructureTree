package sort;


/**
 * 插入排序，不稳定，数据是否有序对性能有很大影响
 * 最好时间复杂度O(n)       数据为全有序
 * 最坏时间复杂度O(n^2)
 * 平均时间复杂度O(n^2)
 * 不稳定排序算法
 *时间复杂度O(1)
 *
 * 使用场景
 *       -操作系统日志
 *       -在其他排序算法中作为子过程进行优化
 *
 */
public class InsertionSort<T extends Comparable<T>> implements Sorter<T>
{
    @Override
    public void sort(T[] a)
    {
        int n = a.length;
        for(int i = 1; i < n; i++)
        {
            T e = a[i];
            int j;
            for(j = i; j > 0 && a[j].compareTo(a[j-1]) < 0; j--)
            {
                a[j] =a[j-1];
            }
            a[j] = e;

        }
    }


    /**
     * 最原始得插入排序，没有进行优化
     * @param a
     */
    public void sort_raw(T[] a)
    {
        int n = a.length;
        for(int i = 1; i < n; i++)
        {
            for(int j = i; j > 0 ; j--)
            {
                if(a[j].compareTo(a[j-1]) < 0)
                {
                    swap(a, j, j-1);
                }else
                {
                    break;
                }
            }
        }
    }

    @Override
    public String getName()
    {
        return "Insertion sort";
    }
}
