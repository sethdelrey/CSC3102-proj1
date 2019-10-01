//basically just using this to brainstorm
public class AVLTree2 {
    private class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1;
        }


    }

    //AVL tree class
    Node root;

    //gets height of tree
    public int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
        //confused as how this would give height.
        // would need to look at children?
    }

    //gets max of 2 integers
    public int max(int a, int b) {
        if (a > b)
            return a;
        else
            return b;
    }

    //right rotate with root y
    public Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        //rotation
        x.right = y;
        y.left = T2;

        //update heights
        y.height = max(height(y.left), height(y.right)) +1;
        x.height = max(height(x.left), height(x.right)) +1;

        //return new root
        return x;
    }
}
