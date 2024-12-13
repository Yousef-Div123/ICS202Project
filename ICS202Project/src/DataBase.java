import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Hashtable;

public class DataBase {
    Hashtable<String, ArrayList<Student>> levelTable;
    AVLTree<Student> idTree;
    CustomeAVLTree<String> firstNameTree;
    CustomeAVLTree<String> lastNameTree;


    public DataBase(){
        levelTable = new Hashtable<String, ArrayList<Student>>();
        firstNameTree = new CustomeAVLTree<>();
        lastNameTree = new CustomeAVLTree<>();
        idTree = new AVLTree<Student>();
    }

    public boolean addStudent(Student student){
        idTree.insertAVL(student);
        firstNameTree.insertAVL(student.getFirstName(), student);
        lastNameTree.insertAVL(student.getLastName(), student);
        if(levelTable.containsKey(student.getUniversityLevel())){
            levelTable.get(student.getUniversityLevel()).add(student);
        }else{
            ArrayList<Student> newArray = new ArrayList<>();
            newArray.add(student);
            levelTable.put(student.getUniversityLevel(), newArray);
        }
        return true;
    }

    public boolean deleteStudent(Student student){
        idTree.deleteAVL(student);
        firstNameTree.deleteAVL(student.getFirstName(), student);
        lastNameTree.deleteAVL(student.getLastName(), student);
        levelTable.get(student.getUniversityLevel()).remove(student);
        return true;
    }

    public ArrayList<Student> getStudentsByFirstName(String firstName){
        CustomeBSTNode node = firstNameTree.search(firstName);
        if(node != null)
            return node.students;
        return null;
    }
    public ArrayList<Student> getStudentsByLastName(String lastName){
        CustomeBSTNode node = lastNameTree.search(lastName);
        if(node != null)
            return node.students;
        return null;
    }
    public ArrayList<Student> getStudentsByLevel(String level){
        return levelTable.get(level);
    }
    public Student getStudentById(int id){
        Student student = idTree.search(id);
        return student;
    }

    public void updateFirstName(Student student, String newFirstName){
        String currFirstName = student.getFirstName();
        student.setFirstName(newFirstName);
        firstNameTree.deleteAVL(currFirstName, student);
        firstNameTree.insertAVL(newFirstName, student);

    }

    public void updateLastName(Student student, String newLastName){
        String currLastName = student.getLastName();
        student.setLastName(newLastName);
        lastNameTree.deleteAVL(currLastName, student);
        lastNameTree.insertAVL(newLastName, student);

    }

    public void updateLevel(Student student, String newLevel){
        String currLevel = student.getUniversityLevel();
        levelTable.get(currLevel).remove(student);
        levelTable.get(newLevel).add(student);
        student.setUniversityLevel(newLevel);
    }

    public void updateBirthDate(Student student, String newDate){
        student.setDateOfBirth(newDate);
    }

}
