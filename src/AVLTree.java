public class AVLTree {
    private class Node {
        public int key, height, size, balanceFactor;
        public Node leftChild, rightChild, parent;

        //public Comparable data;

        public Node(int _key, Node _parent) {
            key = _key;
            parent = _parent;
            height = 1;
            balanceFactor = 0;
        }

        public void insert(int _key) {
            // TODO: Add updates to height and balance factor and rotation stuff.
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

    public Node getRoot() {
        return root;
    }

    public int getKey(Node x) {
        return x.key;
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

    public Node search(Node x, int k) {
        if (x == null) {
            return null;
        }
        if (x.key == k) {
            return x;
        }
        if (x.key > k) {
            return search(x.leftChild, k);
        }
        return search(x.rightChild, k);
    }

    public Node successor(Node x) {
        if (x.rightChild != null) {
            return miniumum(x.rightChild);
        }

        Node y = x.parent;

        while (y != null && x == y.rightChild) {
            x = y;
            y = y.parent;
        }

        return y;
        //return new Node(_key, null);
    }

    public Node select(int i) {
        return new Node(i, null);
    }

    public void rank(int _key) {

    }

    public Node predessor(Node x) {
        // This is what he had in the slides?
        if (x.leftChild != null) {
            return maximum(x.leftChild);
        }

        Node y = x.parent;
        while (y != null && x == y.leftChild) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public Node miniumum(Node x) {
        //go to leftmost child
        while (x.leftChild != null) {
            x = x.leftChild;
        }
        return x;
    }

    public Node maximum(Node x) {
        //go to rightmost child
        while (x.rightChild != null) {
            x = x.rightChild;
        }
        return x;
    }

    public String inOrder(Node x) {
        // visit left side, root, then right side
        String retVal = "";
        if (x == null) {
            return "";
        }

        if (x.leftChild != null) {
            retVal += inOrder(x.leftChild);
        }

        retVal += x.key + ", ";

        if (x.rightChild != null) {
            retVal += inOrder(x.rightChild);
        }

        return retVal;
    }

    public int height(Node x) {
        //working on this
        if (x == null)
            return 0;
        return x.height;
    }

    public int balanceFactor(Node x) {
        //finds balance factor
        if (x == null)
            return 0;
        return height(x.leftChild) - height(x.rightChild);
    }
}
