/************************  BSTNode.java  **************************
 *             node of a generic binary search tree
 */

public class BSTNode<T extends Comparable<? super T>> {
    protected T el;
    protected Student student;
    protected BSTNode<T> left, right;
    public BSTNode() {
        left = right = null;
    }
    public BSTNode(T el, Student student) {
        this(el, student,null,null);
    }
    public BSTNode(T el,Student student, BSTNode<T> lt, BSTNode<T> rt) {
        this.el = el; left = lt; right = rt;this.student = student;
    }

}

