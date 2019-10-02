public class AVLTree {
    private class Node {
        public int key, height, size, bf;
        public Node leftChild, rightChild, parent;
        public boolean heightInc = false;

        //public Comparable data;

        public Node(int _key, Node _parent) {
            key = _key;
            parent = _parent;
            height = 1;
            bf = 0;
        }

        public void rightRotate(Node x) {
            Node y = x.leftChild;
            transplant(y, y.rightChild);
            transplant(x, y);
            y.rightChild = x;
            x.parent = y;
            x.height = 1 + max(x.leftChild, x.rightChild);
            y.height = 1 + max(y.leftChild, x.rightChild);
        }
        

        private int max(Node L, Node R) {
            if (L.height < R.height) {
                return R.height;
            }
            else {
                return L.height;
            }
        }

        public void transplant(Node x, Node y) {
            if (x.parent == null) {
                root = y;
            }
            else if (x.parent.leftChild == x) {
                x.parent.rightChild = y;
            }
            else {
                x.parent.rightChild = y;
            }
            if (y != null) {
                y.parent = x.parent;
            }
//            Node y = x.leftChild;
//            transplant(y, y.right);
//            transplant(x, y);
//            y.rightChild = x;
//            x.parent = y;
        }

        public void insert(int _key) {
            // TODO: Add updates to height and balance factor and rotation stuff.

            Node insertNode = new Node(_key,parent);
            recurseInsert(root,insertNode);
            heightInc = false;


//            if (key > _key) {
//                // Add to the left
//                if (leftChild != null) {
//                    leftChild.insert(_key);
//                }
//                else {
//                    leftChild = new Node(_key, this);
//                }
//            }
//            else if (key < _key) {
//                // Add to the right
//                if (rightChild != null) {
//                    rightChild.insert(_key);
//                }
//                else {
//                    rightChild = new Node(_key, this);
//                }
//            }
        }

        private void recurseInsert(Node x, Node z) {
            if (x.key < z.key) {
                if (x.rightChild != null)
                    recurseInsert(x.rightChild, z);
                else {
                    x.rightChild = z;
                    z.bf = 0;
                    heightInc = true;
                }
                if (heightInc) {
                    //case 2.1
                    if (x.bf == 0)
                        x.bf = -1;
                    //case 2.2
                    else if (x.bf == 1) {
                        x.bf = 0;
                        heightInc = false;
                    }
                }
                //case 2.3
                else{
                    //first subcase
                    if (x.rightChild.bf == -1) {
                        leftRotate(x);
                        x.bf = x.parent.bf = 0;
                        heightInc = false;
                    }
                    //second subcase
                    else if (x.rightChild.bf == 1) {
                        int b = x.rightChild.leftChild.bf;
                        leftRightRotate(x);
                        x.parent.bf = 0;
                        if (b == 0)
                            x.bf = x.parent.rightChild.bf = 0;
                        else if (b == 1) {
                            x.bf = 0;
                            x.parent.rightChild.bf = -1;
                        }
                        else if (b == -1) {
                            x.bf = 1;
                            x.parent.rightChild.bf = 0;
                        }
                        heightInc = false;
                    }
                }
            } else if (x.key > z.key) {
                if (x.leftChild != null)
                    recurseInsert(x.leftChild, z);
                else {
                    x.leftChild = z;
                    z.bf = 0;
                    heightInc = true;
                }
                if (heightInc) {
                    //case 2.1
                    if (x.bf == 0)
                        x.bf = 1;
                    //case 2.2
                    else if (x.bf == -1) {
                        x.bf = 0;
                        heightInc = false;
                    }
                    //case 2.3
                    else {
                        //first subcase
                        if (x.leftChild.bf == -1)
                    }
                }
                else {

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

    public int search(int _key) {
        return search(root, _key).key;
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

    public int successor(int _key) {
        return successor(search(root, _key)).key;
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
        return recurseSelect(root, i);
    }

    private Node recurseSelect(Node x, int i) {
        if (x == null)
            //throw exception or just give statement?
            System.out.print("Error: no item found.");
        if (x.leftChild.size >= 1)
            return recurseSelect(x.leftChild, i);
        if (x.leftChild.size + 1 == i)
            return x;
        return recurseSelect(x.rightChild, i-1-x.leftChild.size);
    }

    public int rank(int _key) {
        return recurseRank(root, _key);
    }

    private int recurseRank(Node x, int k) {
        if (x == null)
            return 0;
        if (k < x.key)
            return recurseRank(x.leftChild,k);
        if (k == x.key)
            return x.leftChild.size + 1;
        return x.leftChild.size + 1 + recurseRank(x.rightChild,k);
    }


    public Node predecessor(Node x) {
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

    public String inOrder() {
        String value = inOrder(root);
        value = value.substring(0, value.length() - 2);
        return value;
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

    public int getbalanceFactor(Node x) {
        //finds balance factor
        if (x == null)
            return 0;
        return height(x.leftChild) - height(x.rightChild);
    }
}
