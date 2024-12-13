import java.util.LinkedList;
import java.util.Queue;

/************************  BST.java  **************************
 *                 generic binary search tree
 */

public class BST<T extends Comparable<? super T>> {
    protected BSTNode<T> root;

    public BST() {
        root = null;
    }

    //ADDED METHOD FOR AVL TREES
    public BST(BSTNode<T> node) {
        root = node;
    }

    public void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T el) {
        BSTNode<T> p = root, prev = null;
        while (p != null) {  // find a place for inserting new node;
            prev = p;
            if (el.compareTo(p.el) < 0)
                p = p.left;
            else p = p.right;
        }
        if (root == null)    // tree is empty;
            root = new BSTNode<T>(el);
        else if (el.compareTo(prev.el) < 0)
            prev.left  = new BSTNode<T>(el);
        else prev.right = new BSTNode<T>(el);
    }

    protected Student search(int id) {
        BSTNode<Student> p = (BSTNode<Student>) root;
        while (p != null)
            if (id == p.el.id)
                return p.el;
            else if (id < p.el.id)
                p = p.left;
            else p = p.right;
        return null;
    }

    public void deleteByCopying(T el) {
        BSTNode<T> node, p = root, prev = null;
        while (p != null && !p.el.equals(el)) {  // find the node p
            prev = p;                           // with element el;
            if (el.compareTo(p.el) < 0)
                p = p.left;
            else p = p.right;
        }
        node = p;
        if (p != null && p.el.equals(el)) {
            if (node.right == null)             // node has no right child;
                node = node.left;
            else if (node.left == null)         // no left child for node;
                node = node.right;
            else {
                BSTNode<T> tmp = node.left;    // node has both children;
                BSTNode<T> previous = node;    // 1.
                while (tmp.right != null) {    // 2. find the rightmost
                    previous = tmp;            //    position in the
                    tmp = tmp.right;           //    left subtree of node;
                }
                node.el = tmp.el;              // 3. overwrite the reference
                //    to the element being deleted;
                if (previous == node)          // if node's left child's
                    previous.left  = tmp.left; // right subtree is null;
                else previous.right = tmp.left; // 4.
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node;
        }
        else if (root != null)
            System.out.println("el " + el + " is not in the tree");
        else System.out.println("the tree is empty");
    }


}
