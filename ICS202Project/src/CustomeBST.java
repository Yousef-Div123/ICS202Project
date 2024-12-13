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

    public void levelOrderTraversalByLevels(){
        levelOrderTraversalByLevels(root);
    }

    private void levelOrderTraversalByLevels(CustomeBSTNode<T> root){
        Queue<CustomeBSTNode<T>> q = new LinkedList<>();
        int levelNodes = 0;
        if(root==null)
            return;

        q.add(root);
        while(!q.isEmpty()){
            levelNodes = q.size();
            while(levelNodes>0){
                CustomeBSTNode<T> n = q.remove();
                System.out.print(" " + n.el);
                if(n.left != null)
                    q.add(n.left);
                if(n.right != null)
                    q.add(n.right);
                levelNodes--;
            }
            System.out.println("");
        }
    }

//    public void recInsert(T el, Student student) {
//        root = recInsert(root, el, student);
//    }
//
//    protected CustomeBSTNode<T> recInsert(CustomeBSTNode<T> p, T el, Student student ) {
//        ArrayList<Student> arrayList = new ArrayList<>();
//        arrayList.add(student);
//        if (p == null)
//            p = new CustomeBSTNode<T>(el, arrayList);
//        else if (el.compareTo(p.el) < 0)
//            p.left  = recInsert(p.left, el, student);
//        else p.right = recInsert(p.right, el, student);
//        return p;
//    }

    public boolean isInTree(T el) {
        return search(el) != null;
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

    public void preorder() {
        preorder(root);
    }

    public void inorder() {
        inorder(root);
    }

    public void postorder() {
        postorder(root);
    }

    protected void visit(CustomeBSTNode<T> p) {
        System.out.print(p.el + " ");
    }

    protected void inorder(CustomeBSTNode<T> p) {
        if (p != null) {
            inorder(p.left);
            visit(p);
            inorder(p.right);
        }
    }

    protected void preorder(CustomeBSTNode<T> p) {
        if (p != null) {
            visit(p);
            preorder(p.left);
            preorder(p.right);
        }
    }

    protected void postorder(CustomeBSTNode<T> p) {
        if (p != null) {
            postorder(p.left);
            postorder(p.right);
            visit(p);
        }
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

    public void deleteByMerging(T el) {
        CustomeBSTNode<T> tmp, node, p = root, prev = null;
        while (p != null && !p.el.equals(el)) {  // find the node p
            prev = p;                           // with element el;
            if (el.compareTo(p.el) < 0)
                p = p.right;
            else p = p.left;
        }
        node = p;
        if (p != null && p.el.equals(el)) {
            if (node.right == null) // node has no right child: its left
                node = node.left;  // child (if any) is attached to its parent;
            else if (node.left == null) // node has no left child: its right
                node = node.right; // child is attached to its parent;
            else {                  // be ready for merging subtrees;
                tmp = node.left;   // 1. move left
                while (tmp.right != null) // 2. and then right as far as
                    tmp = tmp.right;      //    possible;
                tmp.right =        // 3. establish the link between
                        node.right;    //    the rightmost node of the left
                //    subtree and the right subtree;
                node = node.left;  // 4.
            }
            if (p == root)
                root = node;
            else if (prev.left == p)
                prev.left = node;
            else prev.right = node; // 5.
        }
        else if (root != null)
            System.out.println("el " + el + " is not in the tree");
        else System.out.println("the tree is empty");
    }

    public void iterativePreorder() {
        CustomeBSTNode<T> p = root;
        Stack<CustomeBSTNode<T>> travStack = new Stack<CustomeBSTNode<T>>();
        if (p != null) {
            travStack.push(p);
            while (!travStack.isEmpty()) {
                p = travStack.pop();
                visit(p);
                if (p.right != null)
                    travStack.push(p.right);
                if (p.left  != null)        // left child pushed after right
                    travStack.push(p.left);// to be on the top of the stack;
            }
        }
    }

}
