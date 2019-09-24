package avl;


import java.util.ArrayList;
import java.util.Map;

public class AVLTree<K extends Comparable<K>, V>
{
    private class Node
    {
        K key;
        V value;
        int height;
        Node left, right;

        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
            this.height = 1;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public AVLTree()
    {
        root = null;
        size = 0;
    }

    public int height(Node node)
    {
        if (node == null)
        {
            return 0;
        }
        return node.height;
    }

    //计算平衡因子
    private int getBalanceFactor(Node node)
    {
        if(node == null)
            return 0;

        return height(node.left) - height(node.right);
    }


    //判断该树是否为BST树
    public boolean isBST()
    {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for(int i = 0; i < keys.size()-1; i++)
        {
            if(keys.get(i).compareTo(keys.get(i+1)) > 0)
                return false;
        }
        return true;
    }

    public boolean isBalanceTree()
    {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node)
    {
        if(node == null)
            return true;
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //将node结点为根树的元素按中序遍历存到keys里
    private void inOrder(Node node, ArrayList<K> keys)
    {
        if(node == null)
            return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public void add(K key, V value)
    {
        add(root, key, value);
    }

    /**
     * 向以node为根的AVL树中插入元素
     * 返回插入新节点后AVL树的根
     */
    private Node add(Node node, K key, V value)
    {
        if(node == null)
        {
            size++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) > 0)
        {
            node.right = add(node.right, key, value);
        } else if(key.compareTo(node.key) < 0)
        {
            node.left = add(node.left, key, value);
        }else
        {
            node.value = value;
        }
        //更新height
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }

}
