package sort;

import java.util.Arrays;

public class ShellSort<T extends Comparable<T>> implements Sorter<T>
{
    @Override
    public void sort(T[] a)
    {

    }

    /**
     * 希尔排序原始增量序列 N/2
     * @param a
     */
    public void sort_raw(T[] a)
    {
        int n = a.length;
        int skipStep = n/2;   //增量序列
        while (skipStep >= 1)
        {

            //归并排序  从每个序列得第2个元素开始
            for(int i = skipStep; i < n; i++)
            {
                //查找比较步长为skipStep
                for(int j = i; j >= skipStep; j -= skipStep)
                {

                    if(a[j].compareTo(a[j-skipStep]) < 0)
                    {
                        swap(a, j, j-skipStep);
                    }else
                    {
                        break;
                    }
                }
            }
            skipStep /= 2;
        }
        
    }

    @Override
    public String getName()
    {
        return "Shell sort";
    }
}
