package segment_tree;


/*
* 线段树
* 如果区间有n个元素， 数组表示最少4n个节点，最好情况可以用2n个节点的空间
*
* 线段树不考虑添加元素，即区间固定，使用4n的固定区间大小即可
* */

public class SegmentTree<E>
{
    private E[] data;
    private E[] tree;
    private Operator<E> operator;

    public SegmentTree(E[] arr, Operator<E> operator)
    {
        data = (E[]) new Object[arr.length];
        this.operator = operator;
        System.arraycopy(arr, 0 , data, 0, arr.length);
        tree = (E[]) new Object[4*arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }


    //在treeIndex的位置创建表示区间[l....r]的线段树
    private void buildSegmentTree(int treeIndex, int left, int right)
    {
        if(left == right)
        {
            tree[treeIndex] = data[left];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);


        int mid = left + (right - left)/2;
        buildSegmentTree(leftTreeIndex, left, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, right);

        tree[treeIndex] = operator.operate(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    public int size()
    {
        return data.length;
    }


    public E get(int index)
    {
        assert index > 0 || index < data.length;
        return data[index];
    }

    //返回完全二叉树的数组表示中， 一个索引所表示的元素的左孩子节点的索引
    public int leftChild(int index)
    {
        return 2*index + 1;
    }
    //返回完全二叉树的数组表示中， 一个索引所表示的元素的右孩子节点的索引
    public  int rightChild(int indedx)
    {
        return 2*indedx + 2;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 0; i < tree.length; i++)
        {
            if(tree[i] == null)
            {
                sb.append("null");
            }else
            {
                sb.append(tree[i]);
            }
            if(i != tree.length-1)
            {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
