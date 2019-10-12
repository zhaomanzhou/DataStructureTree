package sort;

import java.util.Random;

/**
 * 快速排序
 *
 * 平均时间复杂度O(nlogn)
 * 最好时间复杂度O(nlogn)
 * 最差情况 数组全有序  时间复杂度O(n^2)
 * @param <T>
 */
public class QuickSort<T extends Comparable<T>> implements Sorter<T> {

    private Random random = new Random();
    private InsertionSort<T> insertionSort = new InsertionSort<>();

    @Override
    public void sort(T[] a)
    {
        //quickSort(a, 0, a.length - 1);
        quickSort3Ways(a, 0, a.length-1);
    }


    /**
     * 三路快速排序处理a[l...r]
     * 将a[l...r]分为 <midValue; =midValue; >midValue三部分
     * 之后递归处理大于和小于部分
     */
    private void quickSort3Ways(T[] a, int l, int r)
    {
        if(r - l < 15)
        {
            insertionSort.sort(a, l, r);
            return;
        }
        if(l >= r)
            return;

        //partition
        swap(a, l, random.nextInt(r-l+1) + l);
        T midValue = a[l];

        int lt = l;
        int gt = r+1;
        int i = l+1;

        while (i < gt)
        {
            int comp = a[i].compareTo(midValue);
            if(comp < 0)
                swap(a, ++lt, i);
            else if(comp > 0)
            {
                swap(a, --gt, i);
                //注意交换的值在右边
                i--;
            }
            i++;
        }

        swap(a, l, lt);

        quickSort3Ways(a, l, lt-1);
        quickSort3Ways(a, gt, r);
    }


    //对a[l....r]部分进行快速排序
    private void quickSort(T[] a, int l, int r)
    {
        if(l >= r)
            return;
        //int p = partition(a, l, r);
        int p = partitionBetter1(a, l, r);
        quickSort(a, l, p-1);
        quickSort(a, p+1, r);
    }

    //对a[l...r]进行partition操作，
    //处理后a[l...p-1]内的元素全部小于a[p]
    //a[p+1...r]内的元素全部大于a[p]
    private int partition(T[] a, int l, int r)
    {
        T midValue = a[l];
        //p为partition分割的下标
        int p = l+1;
        for(int i = l+1; i <= r; i++)
        {
            if(a[i].compareTo(midValue) < 0)
            {
                swap(a, p++, i);
            }
        }
        swap(a, l, p-1);
        return p-1;
    }

    /**
     * 快速排序优化
     * 基准数随机选择
     * 可以解决近乎有序数据的时间问题
     * <img src="三路快排.png" />
     */
    private int partitionBetter1(T[] a, int l, int r)
    {
        int index = random.nextInt(r - l + 1) + l;
        swap(a, l, index);

        T midValue = a[l];
        int i = l;
        int j = r+1;
        while (true)
        {
            while (a[++i].compareTo(midValue) <= 0) if(i >= r) break;
            while (a[--j].compareTo(midValue) >= 0) if(j <= l) break;
            if(i >= j)
                break;
            swap(a, i, j);
        }
        swap(a, l, j);
        return j;
    }


    @Override
    public String getName() {
        return "Quick sort";
    }
}
