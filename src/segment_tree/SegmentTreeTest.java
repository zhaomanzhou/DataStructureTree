package segment_tree;

public class SegmentTreeTest
{
    public static void main(String[] args)
    {
        Integer[] arr = new Integer[]{1, 2, 3, 5};
        SegmentTree<Integer> tree = new SegmentTree<Integer>(arr, (a, b) -> a + b);

        System.out.println(tree);

    }
}
