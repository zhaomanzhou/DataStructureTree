package sort;

import java.lang.reflect.Array;

/**
 * 归并排序
 * 最好时间复杂度 O(nlogn)
 * 最坏时间复杂度 O(nlogn)
 * 平均时间复杂度 O(nlogn)
 * 空间复杂度    O(n)
 */
public class MergeSort<T extends Comparable<T>> implements Sorter<T>{

    private InsertionSort<T> insertionSort = new InsertionSort<T>();
    @Override
    public void sort(T[] a)
    {
        mergeSort(a, 0, a.length-1);
    }


    /**
     * 使用递归将a[l....r]内得元素进行排序
     */
    private void mergeSort(T[] a, int l, int r)
    {
//        if(l >= r)
//            return;
        // 优化
        if(r - l < 15)
        {
            insertionSort.sort(a, l, r);
            return;
        }

        int mid = l + (r - l)/2;
        mergeSort(a, l, mid);
        mergeSort(a, mid+1, r);

        //优化
        if(a[mid].compareTo(a[mid+1]) > 0)
            merge(a, l, mid, r);
    }


    /**
     * 自底向上进行归并
     * 没有数组下标取索引操作，比较适合链表
     * @param a
     */
    public void sortBottomUp(T[] a)
    {
        int n = a.length;
        //size为归并划分的小数组大小
        for(int size = 1; size <= n; size *= 2)
        {
            for (int i = 0; i + size < n; i += size*2)
            {
                merge(a, i, i+size-1, Math.min(i+size*2-1, n-1));
            }
        }
    }

    /*
     *  将a[l...mid]和 a[mid+1...r]两部分进行合并
     */
    private void merge(T[] a, int l, int mid, int r)
    {
        //将a中指定位置数据暂存到temp里
        T[] temp = (T[]) Array.newInstance(a.getClass().getComponentType(), r-l+1);
        System.arraycopy(a, l, temp, 0, r-l+1);


        //两个数组合并位置下标
        int leftIndex = l, rightIndex = mid+1;
        //合并
        for(int i = l; i <= r; i++)
        {
            //左边部分数组完成了归并
            if(leftIndex > mid)
            {
                a[i] = temp[rightIndex - l];
                rightIndex++;
            }

            //右边部分完成归并
            else if(rightIndex > r)
            {
                a[i] = temp[leftIndex - l];
                leftIndex++;
            }
            //左边哨兵位置元素比右边哨兵位置元素小
            else if(temp[leftIndex - l].compareTo(temp[rightIndex - l]) < 0)
            {
                a[i] = temp[leftIndex - l];
                leftIndex++;
            }else
            {
                a[i] = temp[rightIndex - l];
                rightIndex++;
            }




        }
    }

    @Override
    public String getName() {
        return "Merge sort";
    }
}
