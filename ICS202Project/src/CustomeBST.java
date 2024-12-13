import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/************************  CustomeBST.java  **************************
 *                 generic binary search tree
 */

public class CustomeBST<T extends Comparable<? super T>> {
    protected CustomeBSTNode<T> root;

    public CustomeBST() {
        root = null;
    }

    // ADDED METHOD FOR AVL TREES
    public CustomeBST(CustomeBSTNode<T> node) {
        root = node;
    }

    public void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T el, Student student) {
        CustomeBSTNode<T> node = search(el);

        if(node == null){
            CustomeBSTNode<T> p = root, prev = null;
            while (p != null) {  // find a place for inserting new node;
                prev = p;
                if (el.compareTo(p.el) < 0)
                    p = p.left;
                else p = p.right;
            }
            ArrayList<Student> arrayList = new ArrayList<>();
            arrayList.add(student);
            if (root == null)    // tree is empty;
                root = new CustomeBSTNode<T>(el, arrayList);
            else if (el.compareTo(prev.el) < 0)
                prev.left  = new CustomeBSTNode<T>(el,arrayList);
            else prev.right = new CustomeBSTNode<T>(el, arrayList);
        }
        else{
            node.students.add(student);
        }
    }


    protected CustomeBSTNode<T> search(T el) {
        CustomeBSTNode<T> p = root;
        while (p != null)
            if (el.equals(p.el)) {
                return p;
            }
            else if (el.compareTo(p.el) < 0)
                p = p.left;
            else p = p.right;
        return null;
    }



    public void deleteByCopying(T el, Student student) {
        CustomeBSTNode<T> node = search(el);
        if(node != null){
            if(node.students.size() == 1){
                deleteByCopying(el);
            }
            else{
                node.students.remove(student);
            }
        }
    }
    private void deleteByCopying(T el) {
        CustomeBSTNode<T> node, p = root, prev = null;
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
                CustomeBSTNode<T> tmp = node.left;    // node has both children;
                CustomeBSTNode<T> previous = node;    // 1.
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
