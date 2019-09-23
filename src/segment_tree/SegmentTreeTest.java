package segment_tree;

public class SegmentTreeTest
{
    public static void main(String[] args)
    {
        Integer[] arr = new Integer[]{1, -4 ,2, 3, -7, 5};
        SegmentTree<Integer> tree = new SegmentTree<Integer>(arr, (a, b) -> a + b);

        System.out.println(tree);

        System.out.println(tree.query(0, 1));
        System.out.println(tree.query(2, 4));

    }
}
