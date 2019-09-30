public class AVLTree {
    private class Node {
        public int key, height, size, balanceFactor;
        public Node leftChild, rightChild, parent;

        //public Comparable data;

        public Node(int _key, Node _parent) {
            key = _key;
            parent = _parent;
        }

        public void insert(int _key) {
            if (key > _key) {
                // Add to the left
                if (leftChild != null) {
                    leftChild.insert(_key);
                }
                else {
                    leftChild = new Node(_key, this);
                }
            }
            else if (key < _key) {
                // Add to the right
                if (rightChild != null) {
                    rightChild.insert(_key);
                }
                else {
                    rightChild = new Node(_key, this);
                }
            }
        }
    }
    private Node root;

    public AVLTree() {
        root = null;
    }

    public AVLTree(int _key) {
        root = new Node(_key, null);
    }

    public void insert(int _key) {
        if (root != null) {
            root.insert(_key);
        }
        else {
            root = new Node(_key, null);
        }



//        else if (root.key > _key) {
//            // Add to left side of tree
//            if (root.leftChild != null) {
//                root.leftChild.insert(_key);
//            }
//            else {
//                root.leftChild = new Node(_key, root);
//            }
//        }
//        else if (root.key < _key){
//            // Add to the right side of the tree
//            if (root.rightChild != null) {
//
//            }
//        }
    }

    public Node search(int _key) {

        return new Node(_key, null);
    }

    public Node successor(int _key) {

        return new Node(_key, null);
    }

    public Node select(int i) {
        return new Node(i, null);
    }

    public void rank(int _key) {

    }

    public Node predessor(int _key) {
        //wouldn't we want to just return parent here?
        return new Node(_key, null);
    }

    public void miniumum() {
        //go to leftmost child
    }

    public void maximum() {
        //go to rightmost child
    }

    public void inOrder() {

    }
}
