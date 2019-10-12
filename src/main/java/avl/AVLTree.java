package avl;


import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V>
{
    public class Node
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


    public boolean contains(K key) {
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


    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y)
    {

//         Node x = y.left;
//        Node T3 = x.right;
//
//        // 向右旋转过程
//        x.right = y;
//        y.left = T3;


        Node x = y.left;
        y.left = x.right;
        x.right = y;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y)
    {
        Node x = y.right;
        y.right = x.left;
        x.left = y;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
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

        //计算平衡银子
        int balanceFactor = getBalanceFactor(node);
        if(balanceFactor > 1)
        {
            //LL
            if(getBalanceFactor(node.left) >= 0 )
                return rightRotate(node);
            //LR
            else if(getBalanceFactor(node.left) < 0)
            {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if(balanceFactor < -1)
        {
            //RR
            if(getBalanceFactor(node.right) >= 0)
                return leftRotate(node);
            //RL
            else if(getBalanceFactor(node.right) < 0)
            {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    public void delete(K k)
    {
        if(contains(k))
        {
            delete(root, k);
        }
    }
    private Node min(Node root)
    {
        if(root.left == null)
            return root;
        else
            return min(root.left);
    }

    //在以node为根的AVL树中删除K对应的节点，返回删除后的AVL树的根
    private Node delete(Node node, K key)
    {
        if(node == null)
            return null;

        Node retNode;
        if(key.compareTo(node.key) > 0)
        {
            node.right = delete(node.right, key);
            retNode = node;
        }else if(key.compareTo(node.key) < 0)
        {
            node.left = delete(node.left, key);
            retNode = node;
        }else
        {
            if(node.left == null)
            {
                retNode = node.right;
                node.right = null;
                size--;
            }else if(node.right == null)
            {
                retNode = node.left;
                node.left = null;
                size--;
            }else
            {
                Node min = min(node.right);
                min.right = delete(node.right, min.key);
                min.left = node.left;
                node.left = node.right = null;
                retNode = min;
            }
        }

        if(retNode == null)
            return null;


        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;

    }

}
