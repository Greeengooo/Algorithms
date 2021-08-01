import java.util.NoSuchElementException;

/*
         *******************************************************
         *LIGHT IMPLEMENTATION OF RED-BLACK BINARY SEARCH TREE *
         *******************************************************
 */

public class RedBlackBST<Key extends Comparable<Key>, Value> {
private static final boolean RED = true;
private static final boolean BLACK = false;

private Node root;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        boolean color;

        private Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }

    }


    public RedBlackBST() {
    }

    public boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        while(x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
           else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException();
        if (val == null)  {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val,  RED); //create a new Node if h equals null  (BASE)

                /* Simple Binary Search put*/
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if(cmp > 0) h.right = put(h.right, key, val);
        else h.val = val; // cmp == 0

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) h = flipColors(h);

        return h;
    }

    /*          MINIMUMS/MAXIMUMS         */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

   /*DELETION*/

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("null argumnet");
        if (!contains(key)) return;

        //if both root children are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {                            //LEFT
            if (isRed(h.left) && !isRed(h.left.left))              //push red right if necessary
                h = moveRedLeft(h);                                //move down (left)
            h.left = delete(h.left, key);
        }else {                                                    //RIGHT or EQUAL
            if (isRed(h.left))                                     //rotate to push red right
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))     //EQUAL(at bottom)
                return null;                                        //delete node
            if (isRed(h.right) && !isRed(h.right.left))             //push red right if necessary
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {                        //EQUAL(not at bottom)
                Node x  = min(h.right);
                h.key = x.key;                                      //replace current node with successor value
                h.val = x.val;
                h.right = deleteMin(h.right);                       //delete value
            }
            else h.right = delete(h.right, key);                    //move down (right)
        }
        return balance(h);                                          //fix right-leaning red links and eliminate 4-nodes on the way up
    }

   public void deleteMin() {
       if (isEmpty()) throw new NoSuchElementException("BST underflow");

       // if both children of root are black, set root to red
       if (!isRed(root.left) && !isRed(root.right))
           root.color = RED;

       root = deleteMin(root);
       if (!isEmpty()) root.color = BLACK;
   }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

   public void deleteMax() {
        if(isEmpty()) throw new NoSuchElementException("Can't perform an operation");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (isEmpty()) root.color = BLACK;
   }

   private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);
        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
   }

    /*SIBLING SHARING*/
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h) {
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        return h;
    }

    /*ROTATIONS*/
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        return x;
    }

    private Node flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
        return h;
    }
}