/*
Seth Richard, Raquel Hodgeson
Programming Project 1
CSC 3102 - Dr. Shah
10/18/19
 */

public class AVLTree {
    private class Node {
        public int key, height, size, bf;
        public Node leftChild, rightChild, parent;
        public boolean heightInc = false;

        public Node(int _key) {
            key = _key;
            parent = null;
            height = 1;
            bf = 0;
            size = 1;
        }

        public void rightRotate(Node x) {
            Node y = x.leftChild;
            transplant(y, y.rightChild);
            transplant(x, y);
            y.rightChild = x;
            x.parent = y;
            x.size = 1 + sumSize(x.leftChild, x.rightChild);
            y.size = 1 + sumSize(y.leftChild, y.rightChild);
            x.height = 1 + maxHeight(x.leftChild, x.rightChild);
            y.height = 1 + maxHeight(y.leftChild, x.rightChild);
        }

        public void leftRotate(Node x) {
            Node y = x.rightChild;
            transplant(y, y.leftChild);
            transplant(x, y);
            y.leftChild = x;
            x.parent = y;
            x.size = 1 + sumSize(x.leftChild, x.rightChild);
            y.size = 1 + sumSize(y.leftChild, y.rightChild);
            x.height = 1 + maxHeight(x.leftChild, x.rightChild);
            y.height = 1 + maxHeight(y.leftChild, y.rightChild);
        }

        public void leftRightRotate(Node x) {
            leftRotate(x.leftChild);
            rightRotate(x);
        }

        public void rightLeftRotate(Node x) {
            rightRotate(x.rightChild);
            leftRotate(x);
        }

        private int sumSize(Node x, Node y) {
            int sum = 0;
            if (x != null) {
                sum += x.size;
            }
            if (y != null) {
                sum += y.size;
            }
            return sum;
        }
        
        // Gets the maximum height of two nodes.
        private int maxHeight(Node L, Node R) {
            if (L != null && R != null) {
                if (L.height < R.height) {
                    return R.height;
                } else {
                    return L.height;
                }
            } else if (L == null && R == null) {
                return 0;
            } else if (L == null) {
                return R.height;
            } else {
                return L.height;
            }
        }

        // Rearranges two nodes and stitches their pointers back together.
        public void transplant(Node x, Node y) {
            if (x.parent == null) {
                root = y;
            } else if (x.parent.leftChild == x) {
                x.parent.leftChild = y;
            } else {
                x.parent.rightChild = y;
            }
            if (y != null) {
                y.parent = x.parent;
            }
        }

        // Driver method for recurseInsert so that the method can be called with only a key.
        public void insert(int _key) {
            Node insertNode = new Node(_key);
            recurseInsert(root, insertNode);
        }

        // Recursive method to insert a new node into the AVL Tree
        private void recurseInsert(Node x, Node z) {
            if (x.key < z.key) {
                if (x.rightChild != null) {
                    recurseInsert(x.rightChild, z);
                    x.size++;
                }
                else {
                    x.rightChild = z;
                    z.parent = x;
                    z.bf = 0;
                    heightInc = true;
                    x.size++;
                }
                if (heightInc) {
                    //case 2.1
                    if (x.bf == 0 && (x.leftChild != null || x.rightChild != null)) {
                        x.bf = -1;
                    }
                    //case 2.2
                    else if (x.bf == 1) {
                        x.bf = 0;
                        heightInc = false;
                    }
                    //case 2.3
                    else {
                        //first subcase
                        if (x.rightChild.bf == -1) {
                            leftRotate(x);
                            x.bf = x.parent.bf = 0;
                            heightInc = false;
                        }
                        //second subcase
                        else if (x.rightChild.bf == 1) {
                            int b = x.rightChild.leftChild.bf;
                            rightLeftRotate(x);
                            x.parent.bf = 0;
                            if (b == 0)
                                x.bf = x.parent.rightChild.bf = 0;
                            else if (b == 1) {
                                x.bf = 0;
                                x.parent.rightChild.bf = -1;
                            } else if (b == -1) {
                                x.bf = 1;
                                x.parent.rightChild.bf = 0;
                            }
                            heightInc = false;
                        }
                    }
                }
                } else if (x.key > z.key) {
                    if (x.leftChild != null) {
                        recurseInsert(x.leftChild, z);
                        x.size++;
                    }
                    else {
                        x.leftChild = z;
                        z.parent = x;
                        z.bf = 0;
                        heightInc = true;
                        x.size++;
                    }
                    if (heightInc) {
                        //case 2.1
                        if (x.bf == 0 && (x.leftChild != null || x.rightChild != null)) {
                            x.bf = 1;
                        }
                        //case 2.2
                        else if (x.bf == -1) {
                            x.bf = 0;
                            heightInc = false;
                        }
                        //case 2.3
                        else {
                            //first subcase
                            if (x.leftChild.bf == 1) {
                                rightRotate(x);
                                x.bf = x.parent.bf = 0;
                                heightInc = false;
                            } else if (x.leftChild.bf == -1) {
                                //second subcase (this SHOULD work)
                                int c = x.leftChild.rightChild.bf;
                                leftRightRotate(x);
                                x.parent.bf = 0;
                                if (c == 0)
                                    x.bf = x.parent.leftChild.bf = 0;
                                else if (c == 1) {
                                    x.bf = -1;
                                    x.parent.leftChild.bf = 0;
                                } else if (c == -1) {
                                    x.bf = 0;
                                    x.parent.leftChild.bf = 1;
                                }
                                heightInc = false;
                            }
                        }
                    }
                }
            }
        }

    private Node root;

    public AVLTree() {
        root = null;
    }

    public AVLTree(int _key) {
        root = new Node(_key);
    }

    // Driver method for insert so that the method can be called without a given node.
    public void insert(int _key) {
        if (root != null) {
            root.insert(_key);
        }
        else {
            root = new Node(_key);
        }
    }

    // Driver method for recurseSearch so that the method can be called in the main method.
    public int search(int _key) {
        return recurseSearch(root, _key).key;
    }

    // Searches for the node with the key _key in the tree rooted at x.
    public Node recurseSearch(Node x, int _key) {
        if (x == null) {
            return null;
        }
        if (x.key == _key) {
            return x;
        }
        if (x.key > _key) {
            return recurseSearch(x.leftChild, _key);
        }
        return recurseSearch(x.rightChild, _key);
    }

    // Driver method for recurseSelect so that the method can be called from the main method.
    public int select(int i) {
        return recurseSelect(root, i).key;
    }

    // Returns the node that is the ith element from the minimum in the tree.
    private Node recurseSelect(Node x, int i) {
        if (x == null) {
            //throw exception or just give statement?
            System.out.print("Error: no item found.\n");
            return null;
        }
        int leftChildSize = 0;
        if (x.leftChild != null) {
            leftChildSize = x.leftChild.size;
        }

            if (leftChildSize >= i)
                return recurseSelect(x.leftChild, i);
            if (leftChildSize + 1 == i)
                return x;
            return recurseSelect(x.rightChild, i - 1 - leftChildSize);
    }

    // Driver method for recurseRank so that the method can be called from the main method without a node.
    public int rank(int _key) {
        return recurseRank(root, _key);
    }

    // Returns the "rank" of a node with the key _key.
    private int recurseRank(Node x, int _key) {
        if (x == null) {
            return 0;
        }
        if (_key < x.key) {
            return recurseRank(x.leftChild, _key);
        }
        if (_key == x.key) {
            if (x.leftChild != null) {
                return x.leftChild.size + 1;
            }
            else {
                return 1;
            }
        }
        int leftSize = 0;
        if (x.leftChild != null) {
            leftSize = x.leftChild.size;
        }
        return leftSize + 1 + recurseRank(x.rightChild,_key);
    }

    // Driver method for successor so that the method can be called in the main method.
    public int successor(int _key) {
        return successor(recurseSearch(root, _key)).key;
    }

    // Returns the successor of the node x.
    public Node successor(Node x) {
        if (x.rightChild != null) {
            return minimum(x.rightChild);
        }

        Node y = x.parent;

        while (y != null && x == y.rightChild) {
            x = y;
            y = y.parent;
        }

        return y;
        //return new Node(_key, null);
    }

    // Driver method for predecessor so that the method can be called in the main method.
    public int predecessor(int _key) {
        return predecessor(recurseSearch(root, _key)).key;
    }

    // Returns the predecessor of the node x.
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

    // Driver method for minimum so that the method can be called without a given node from the main method.
    // If no elements are in tree returns -1
    public int minimum() {
        return (root != null) ? minimum(root).key : -1;
    }

    // Returns the node with the minimum key in the tree.
    public Node minimum(Node x) {
        //go to leftmost child
        while (x.leftChild != null) {
            x = x.leftChild;
        }
        return x;
    }
    
    // Driver method for maximum so that the method can be called without a given node from the main method.
    // If no elements are in the tree returns -1
    public int maximum() {
        return (root != null) ? maximum(root).key : -1;
    }

    // Returns the node with the maximum key in the tree.
    public Node maximum(Node x) {
        //go to rightmost child
        while (x.rightChild != null) {
            x = x.rightChild;
        }
        return x;
    }

    // Driver method for inOrder so that the method can be called from the main method without a given node.
    public String inOrder() {
        String value = inOrder(root);
        value = value.substring(0, value.length() - 2);
        return value;
    }

    // Traverses the tree in order.
    public String inOrder(Node x) {
        // Visit left side, root, then right side
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
}
