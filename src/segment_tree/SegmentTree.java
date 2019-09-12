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


    /**
     * 返回区间[L, R]的值
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL, int queryR)
    {
        assert queryL >= 0 && queryL < data.length;
        assert queryR >= 0 && queryR < data.length;
        return query(0, 0, data.length-1, queryL, queryR);
    }

    /**
     * 在以trteeID为根的线段树中[l...r]的范围内，搜索区间[queryL...queryR]的值
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR)
    {
        if(l == queryL && r == queryR)
            return tree[treeIndex];
        int mid = l + (r - l)/2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        //查询区间在右部
        if(queryL >= mid + 1)
        {
            return query(rightTreeIndex, mid+1, r, queryL, queryR);
        }

        //查询区间在左部
        else if(queryR <= mid)
        {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        //左边右边都有
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);

        return operator.operate(leftResult, rightResult);

    }


    public void update(int index, E val)
    {
        assert index >= 0 && index < data.length;
        set(0, 0, data.length-1, index, val);
    }


    /**
     * 在以treeIndex为根的线段树更新index值为val
     */
    private void set(int treeIndex, int l, int r, int index, E val)
    {
        if(l == r)
        {
            tree[treeIndex] = val;
            return;
        }
        int mid = l + (r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(index >= mid+1)
        {
            set(treeIndex, mid+1, r, index, val);
        }else
        {
            set(treeIndex, l, mid, index, val);
        }

        tree[treeIndex] = operator.operate(tree[leftTreeIndex], tree[rightTreeIndex]);

    }



    public int size()
    {
        return data.length;
    }


    public E get(int index)
    {
        assert index >= 0 && index < data.length;
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
