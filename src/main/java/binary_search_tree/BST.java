package binary_search_tree;


import java.util.LinkedList;
import java.util.Queue;

public class BST<K extends Comparable<K>, V>
{
    private class Node{
        K key;
        V value;
        Node left, right;

        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }


    private Node root;

    private int size;

    public BST()
    {
        root = null;
        size = 0;
    }

    public void insert(K key, V value)
    {
        root = insert(root, key, value);
    }

    /**
     * 向以Node为根的二叉搜索树中插入新节点，返回插入新节点后的
     * 二叉搜索树的根
     */
    private Node insert(Node root, K key, V value)
    {
        if(root == null)
        {
            size++;
            return new Node(key, value);
        }else if(key.compareTo(root.key) > 0)
        {
            root.right =  insert(root.right, key, value);
        }else if(key.compareTo(root.key) < 0)
        {
            root.left = insert(root.left, key ,value);
        }else
        {
            root.value = value;
        }
        return root;

    }

    public boolean contain(K key) {
        return contain(root, key);
    }

    /**
     * 被递归调用来判断是否包含指定key的函数
     */
    private boolean contain(Node root, K key)
    {
        if(root == null)
            return false;
        if(key.compareTo(root.key) > 0)
        {
            return contain(root.right, key);
        }else if(key.compareTo(root.key) < 0)
        {
            return contain(root.left, key);
        }else
        {
            return true;
        }

    }

    public V search(K key) {
        return search(root, key);
    }

    private V search(Node root, K key)
    {
        if(root == null)
            return null;
        if(key.compareTo(root.key) > 0)
        {
            return search(root.right, key);
        }else if(key.compareTo(root.key) < 0)
        {
            return search(root.left, key);
        }else
        {
            return root.value;
        }
    }

    public void delete(K key)
    {
        delete(root, key);
    }

    /**
     * 删除指定节点作为树的根的二叉搜索树中键值为Key的节点
     * 返回删除节点后的新的二分搜索树的根。
     */
    private Node delete(Node root, K key)
    {
        if(root == null)
            return null;
        if(key.compareTo(root.key) > 0)
        {
            root.right = delete(root.right, key);
            return root;
        }else if(key.compareTo(root.key) < 0)
        {
            root.left = delete(root.left, key);
            return root;
        }else
        {
            //此时该Node为要删除的Node
            if(root.left == null)
            {
                size--;
                return root.right;
            }
            if(root.right == null)
            {
                size--;
                return root.left;
            }

            //找到该节点右边部分的最小节点
            Node successor = min(root.right);
            successor.right = deleteMin(root.right);
            successor.left = root.left;
            return successor;
        }
    }


    /**
     * 删除以root为节点的二叉搜索树的最小节点
     * 返回删除的节点
     */
    private Node deleteMin(Node root)
    {
        if(root.left == null)
        {
            return root.right;
        }

        root.left = deleteMin(root.left);

        return root;
    }

    private Node min(Node root)
    {
        if(root.left == null)
            return root;
        else
            return min(root.left);
    }



    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }





    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 层序遍历(Level Order),又称广度优先遍历
     */
    public void levelOrder() {
        if(root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty())
        {
            Node node = queue.poll();
            System.out.print(node.key + " ");
            if(node.left != null)
                queue.offer(node.left);
            if(node.right != null)
                queue.offer(node.right);
        }
    }


    /*****************************************************************************
     * 前、中、后序遍历的私有辅助函数：前中后序遍历的差别在于访问当前节点是在一开始、中间还是最后
     * ***************************************************************************/

    /**
     * 对以node为根的二叉搜索树进行前序遍历
     */
    private void preOrder(Node node) {
        if(node != null)
        {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    /**
     * 对以node为根的二叉搜索树进行中序遍历
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    /**
     * 对以node为根的二叉搜索树进行后序遍历
     */
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }


}
