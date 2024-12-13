public class Main {
    public static void main(String[] args){
        Student s = new Student(202278640, "Abdelaziz", "Yousef", "09/01/2005", "SO");
        DataBase db = new DataBase();
        db.addStudent(s);
    }
}
