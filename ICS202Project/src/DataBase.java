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
        idTree.insert(student.getId());
        if(levelTable.containsKey(student.getUniversityLevel())){
            levelTable.get(student.getUniversityLevel()).add(student.getId());
        }else{
            ArrayList<Integer> newArray = new ArrayList<>();
            newArray.add(student.getId());
            levelTable.put(student.getUniversityLevel(), newArray);
        }
        return true;
    }

    public boolean deleteStudent(Student student){
        idTree.deleteAVL(student.getId());
        return true;
    }

    public ArrayList<Student> getStudentsByFirstName(String firstName){
        return null;
    }
    public ArrayList<Student> getStudentsByLastName(String lastName){
        return null;
    }
    public ArrayList<Student> getStudentsByLevel(String level){
        return null;
    }
    public Student getStudentById(int id){
        return null;
    }



}
