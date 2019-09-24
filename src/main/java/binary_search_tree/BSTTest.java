package binary_search_tree;

import java.util.Random;

public class BSTTest
{

    public static void main1(String[] args)
    {
        BST<Integer, Integer> bst = new BST<>();
        bst.insert(12, null);
        bst.insert(8, null);
        bst.insert(18, null);
        bst.insert(5, null);
        bst.insert(11, null);
        bst.insert(17, null);
        bst.insert(4, null);
        bst.delete(8);
        bst.levelOrder();
    }

    public static void main(String[] args)
    {
        BST<Integer, Integer> bst = new BST<>();
        Random random = new Random();

        int N = 10000;
        for (int i = 0; i < N; i++) {
            int key = random.nextInt(N);
            // key和value设置成一样地.
            // 注意这里因为key是随机产生地，所以可能会出现key相同覆盖的情况,
            // 因此所有的键合并起来一定是order数组的子集，所以下面的删除最后一定是0
            bst.insert(key, key);
        }

        // order存储的都是key
        Integer[] order = new Integer[N];
        for (int i = 0; i < N; i++) {
            order[i] = i;
        }
        // 打乱order数组
        for (int i = order.length - 1; i >= 0; i--) {
            int pos = (int) (Math.random() * (i + 1));
            int t = order[pos];
            order[pos] = order[i];
            order[i] = t;
        }

        for (int i = 0; i < N; i++) {
            if (bst.contain(order[i])) {
                bst.delete(order[i]);
                System.out.println("After Remove " + order[i] + ", size = " + bst.getSize());
            }
        }

        System.out.println("bst size = " + bst.getSize());
    }
}
