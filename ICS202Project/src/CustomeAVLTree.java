import java.util.ArrayList;

public class CustomeAVLTree<T extends Comparable<? super T>> extends CustomeBST<T> {

    protected int height;

    public CustomeAVLTree() {
        super();
        height = -1;
    }

    public CustomeAVLTree(CustomeBSTNode<T> root) {
        super(root);
        height = -1;
    }

    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(CustomeBSTNode<T> node) {
        if(node == null)
            return -1;
        else
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private CustomeAVLTree<T> getLeftAVL() {
        CustomeAVLTree<T> leftsubtree = new CustomeAVLTree<T>(root.left);
        return leftsubtree;
    }

    private CustomeAVLTree<T> getRightAVL() {
        CustomeAVLTree<T> rightsubtree = new CustomeAVLTree<T>(root.right);
        return rightsubtree;
    }

    protected int getBalanceFactor() {
        if(isEmpty())
            return 0;
        else
            return getRightAVL().getHeight() - getLeftAVL().getHeight();
    }

    public void insertAVL(T el, Student student)  {
        super.insert(el, student);
        this.balance();
    }

    public void deleteAVL(T el, Student student) {
        //Q1
        this.deleteByCopying(el, student);
        this.balance();
    }

    protected void balance()
    {
        if(!isEmpty())
        {
            getLeftAVL().balance();
            getRightAVL().balance();

            adjustHeight();

            int balanceFactor = getBalanceFactor();

            if(balanceFactor == -2) {
                if(getLeftAVL().getBalanceFactor() < 0)
                    rotateRight();
                else
                    rotateLeftRight();
            }

            else if(balanceFactor == 2) {
                if(getRightAVL().getBalanceFactor() > 0)
                    rotateLeft();
                else
                    rotateRightLeft();
            }
        }
    }

    protected void adjustHeight()
    {
        if(isEmpty())
            height = -1;
        else
            height = 1 + Math.max(getLeftAVL().getHeight(), getRightAVL().getHeight());
    }

    protected void rotateRight() {
        //Q1
        CustomeBSTNode<T> tempNode = root.right;
        root.right = root.left;
        root.left = root.right.left;
        root.right.left = root.right.right;
        root.right.right = tempNode;

        T val = (T) root.el;
        ArrayList<Student> list = root.students;
        root.el = root.right.el;
        root.students = root.right.students;
        root.right.el = val;
        root.right.students = list;

        getRightAVL().adjustHeight();
        adjustHeight();
    }

    protected void rotateLeft() {
        CustomeBSTNode<T> tempNode = root.left;
        root.left = root.right;
        root.right = root.left.right;
        root.left.right = root.left.left;
        root.left.left = tempNode;

        T val = (T) root.el;
        ArrayList<Student> list = root.students;
        root.el = root.left.el;
        root.students = root.left.students;
        root.left.el = val;
        root.left.students = list;

        getLeftAVL().adjustHeight();
        adjustHeight();
    }

    protected void rotateLeftRight()
    {
        //Q1
        getLeftAVL().rotateLeft();
        getLeftAVL().adjustHeight();
        this.rotateRight();
        this.adjustHeight();
    }

    protected void rotateRightLeft()
    {
        getRightAVL().rotateRight();
        getRightAVL().adjustHeight();
        this.rotateLeft();
        this.adjustHeight();
    }

}