import java.lang.reflect.Array;
import java.util.ArrayList;

/************************  BSTNode.java  **************************
 *             node of a generic binary search tree
 */

public class CustomeBSTNode<T extends Comparable<? super T>> {
    protected T el;
    protected ArrayList<Student> students;
    protected CustomeBSTNode<T> left, right;
    public CustomeBSTNode() {
        left = right = null;
    }
    public CustomeBSTNode(T el, ArrayList<Student> students) {
        this(el, students,null,null);
    }
    public CustomeBSTNode(T el, ArrayList<Student> students, CustomeBSTNode<T> lt, CustomeBSTNode<T> rt) {
        this.el = el; left = lt; right = rt;this.students = students;
    }
    public String toString(){
        return students.toString();
    }
}

