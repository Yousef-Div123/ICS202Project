import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Hashtable;

public class DataBase {
    Hashtable<String, ArrayList<Student>> levelTable;
    AVLTree<Integer> idTree;

    public DataBase(){
        levelTable = new Hashtable<String, ArrayList<Integer>>();
        idTree = new AVLTree<Integer>();
    }

    public boolean addStudent(Student student){
        return true;
    }

    public boolean deleteStudent(Student student){
        return true;
    }

    public ArrayList<Student> getStudentsByFirstName(String firstName){}
    public ArrayList<Student> getStudentsByLastName(String lastName){}
    public ArrayList<Student> getStudentsByLevel(String level){}
    public Student getStudentById(int id){}



}
