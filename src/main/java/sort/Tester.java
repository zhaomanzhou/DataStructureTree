package sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tester
{


    @Test
    public void testShellSort()
    {
        ShellSort<Integer> sorter = new ShellSort<>();
        Integer[] data = generateArray(10, 100);
        Long start = System.currentTimeMillis();
        sorter.sort_raw(data);
        assertIsSorted(data);
        Long end = System.currentTimeMillis();
        System.out.println(sorter.getName() + ": " + (end - start)/1000.0 + " s");
    }

    @Test
    public void testSelectionSort()
    {
        testSort(new SelectionSort<Integer>());
    }


    @Test
    public void testInsertionSortRaw()
    {
        InsertionSort<Integer> sorter = new InsertionSort<>();
        Integer[] data = generateArray(1000, 100);
        Long start = System.currentTimeMillis();
        sorter.sort_raw(data);
        assertIsSorted(data);
        Long end = System.currentTimeMillis();
        System.out.println(sorter.getName() + ": " + (end - start)/1000.0 + " s");
    }

    public void testSort(Sorter sorter)
    {
        Integer[] data = generateArray(10, 100);
        Long start = System.currentTimeMillis();
        sorter.sort(data);
        assertIsSorted(data);
        Long end = System.currentTimeMillis();
        System.out.println(sorter.getName() + ": " + (end - start)/1000.0 + " s");
        
    }

    /**
     * 判断数组是否有序
     * @param a
     * @return
     */
    private void assertIsSorted(Integer[] a)
    {
        for (int i = 0; i < a.length-1; i++)
        {
            if(a[i] > a[i+1])
            {
                throw new RuntimeException("Not sorted");
            }
        }
    }

    /**
     * 生成一个随机数组
     * @param length 数组的长度
     * @param bound 数据值得上界
     * @return 生成的数组
     */
    private Integer[] generateArray(int length, int bound)
    {
        Random r = new Random();
        Integer[] data = new Integer[length];
        for(int i = 0; i < data.length; i++)
        {
            data[i] = r.nextInt(bound);
        }
        return data;
    }


    private Integer[] generateSortedArray(int length)
    {
        return (Integer[]) IntStream.range(0, length).boxed().toArray();
    }
}
