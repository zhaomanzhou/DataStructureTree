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
            for(j = i; j > 0 && a[j - 1].compareTo(e) > 0; j--)
            {
                a[j] =a[j-1];
            }
            a[j] = e;

        }
    }


    public void sort(T[] a, int l, int r)
    {
        int n = r - l + 1;
        for(int i = l; i <= r; i++)
        {
            T e = a[i];
            int j;
            for(j = i; j > l && a[j - 1].compareTo(e) > 0; j--)
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


/*
为什么对小数据量直接插入排序要比快排更快呢？
不过，更更关键的，当我们使用大O来描述算法的复杂度的时候，是忽略常数项的。
大O刻画的是当数据规模无穷大的时候，算法性能的趋势。他只是一个趋势，不是精确的时间。
我们说O(nlogn)的算法比O(n^2)的算法快，是因为当n无穷大的时候，哪怕O(nlogn)的算法是T = 1000000nlogn，
而O(n^2)的算法是T = 2n^2，总有一个n，会使得1000000nlogn < 2n^2，并且随着n逐渐增大，
这个差距越来越大（解方程，试试看这个n在哪里？）。但是，当n比较小的时候，就不一定了：）
比如n=8的时候，1000000nlogn = 24000000；而2n^2只有128：）


插入排序法就是一个常数项非常小的排序算法，小于大多数排序。同时，对于有序（或者近乎有序）的数据，
插入排序还可以进化成为O(n)的算法（因为第二层循环可以提前终止），而小数据量的数组，拥有更高的概率是有序的。
所以，可以作为在数据量比较低的时候的一种优化手段：）
 */