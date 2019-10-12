package sort;


/**
 * 希尔排序
 * 平均时间复杂度 取决于增量序列
 * 最好时间复杂度  O(n)
 * 最坏时间复杂度  O(n^2)
 * 空间复杂度     O(1)
 * 为非稳定排序
 * @param <T>
 */
public class ShellSort<T extends Comparable<T>> implements Sorter<T>
{
    @Override
    public void sort(T[] a)
    {
        int n = a.length;
        int skipStep = 1;
        while (skipStep < n/3)
            skipStep = skipStep*3 + 1;
        while (skipStep >= 1)
        {
            //归并排序  从每个序列得第2个元素开始
            for(int i = skipStep; i < n; i++)
            {
                T  e = a[i];
                //查找比较步长为skipStep
                int j;
                for(j = i; j >= skipStep && a[j-1].compareTo(e) > 0; j -= skipStep)
                {
                    a[j] = a[j - skipStep];
                }
                a[j] = e;
            }
            skipStep /= 3;
        }
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
