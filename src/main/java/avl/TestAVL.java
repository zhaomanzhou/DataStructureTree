package avl;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestAVL
{
    @Test
    public void testRightRotate()
    {
//        AVLTree<Integer, Integer> tree = new AVLTree<>();
//        AVLTree<Integer, Integer>.Node nodey = tree.new Node(1, 1);
//        AVLTree<Integer, Integer>.Node nodex = tree.new Node(2, 1);
//        AVLTree<Integer, Integer>.Node nodez = tree.new Node(3, 1);
//        AVLTree<Integer, Integer>.Node node3 = tree.new Node(4, 1);
//        AVLTree<Integer, Integer>.Node node4 = tree.new Node(5, 1);
//        nodey.left = nodex;
//        nodey.right = node4;
//        nodex.left = nodez;
//        nodex.right = node3;
//        //tree.rightRotate(nodey);
//
//        int a = 3;
    }

    @Test
    public void testAdd()
    {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // Collections.sort(words);




            // Test AVL Tree
            long startTime = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {


                    avl.add(word, 1);
            }

            for(String word: words)
                avl.contains(word);

            long endTime = System.nanoTime();
            Assert.assertTrue(avl.isBalanceTree());
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");
        }

        System.out.println();
    }

    @Test
    public void testDelete()
    {
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // Collections.sort(words);




            // Test AVL Tree
            long startTime = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {


                avl.add(word, 1);
            }

            for(String word: words)
            {
                avl.delete(word);
                Assert.assertTrue(avl.isBalanceTree());
            }

            long endTime = System.nanoTime();
            Assert.assertTrue(avl.isBalanceTree());
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");
        }

        System.out.println();
    }
}
