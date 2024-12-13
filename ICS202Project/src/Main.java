import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static DataBase db;
    public Main() throws FileNotFoundException {
    }

    public static void main(String[] args){
//        db = new DataBase();
        loadData();
        menu();
        Student s2 = new Student("78456" , "Al-Otaibi", "Abdullah", "23/11/2004", "SO")
    }

    public static void loadData(){
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\mryoy\\OneDrive\\Desktop\\KFUPM\\2-Sophomore\\241\\ICS202\\project\\ICS202Project\\ICS202Project\\src\\students-details.csv"))) {
            String line;
            String firstline= br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Student newStudent = new Student(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4]);
                db.addStudent(newStudent);
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void menu(){
        System.out.println("Welcome to KFUPM student Management System!!");
        System.out.println("\n---- Main Menu ----");
        System.out.println("1. Search Student");
        System.out.println("2. Add New Student");
        System.out.println("3. Show students in an academic level");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        Scanner scrn = new Scanner(System.in);
        int choice = scrn.nextInt();
        if(choice == 1){
            searchStudent();
        }
        else if (choice == 2){
            addNewStudent();
        }
        else if (choice == 3){
            showAcademiclevels();
        }
        else if (choice == 4){
            return;
        }
    }

    private static void showAcademiclevels() {
        Scanner scrn = new Scanner(System.in);
        System.out.println("\n---- students in academic level ----");
        System.out.println("Enter the academic level (FO, SO, JR, SR): : ");
        String level = scrn.nextLine();
        System.out.println(db.getStudentsByLevel(level));
        menu();
    }

    private static void addNewStudent() {
        Scanner scrn = new Scanner(System.in);
        System.out.println("\n---- Add student ----");
        System.out.println("enter id: ");
        int id = scrn.nextInt();
        System.out.println("enter first name: ");
        String firstName = scrn.nextLine();
        System.out.println("enter last name: ");
        String lastName = scrn.nextLine();
        System.out.println("enter date of birth name: ");
        String dateOfBirth = scrn.nextLine();
        System.out.println("enter academic level (FO, SO, JR, SR): ");
        String level = scrn.nextLine();

        Student s = new Student(id, lastName, firstName, dateOfBirth, level);
        db.addStudent(s);
        menu();
    }

    private static void searchStudent() {
        System.out.println("\n---- Search Options ----");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by First Name");
        System.out.println("3. Search by Last Name");
        System.out.println("4. Return to Main Menu");

        Scanner scrn = new Scanner(System.in);
        int choice = scrn.nextInt();
        if(choice == 1){
            System.out.println("enter the student's ID: ");
            int id = scrn.nextInt();
            Student student = db.getStudentById(id);
            if(student != null){
                System.out.println(student);
                subMenu(student);
            }
            else{
                System.out.println("Student not found!!!!!!");
                menu();
            }
        }
        else if (choice == 2){
            System.out.println("enter the student's first name: ");
            scrn.nextLine();
            String name = scrn.nextLine();
            ArrayList<Student> students = db.getStudentsByFirstName(name);
            if(students != null){
                System.out.println(students);
                menu();
            }
            else{
                System.out.println("Student not found!!!!!!");
                menu();
            }
        }
        else if (choice == 3){
            System.out.println("enter the student's last name: ");
            scrn.nextLine();
            String name = scrn.nextLine();
            ArrayList<Student> students = db.getStudentsByLastName(name);
            if(students != null){
                System.out.println(students);
                menu();
            }
            else{
                System.out.println("Student not found!!!!!!");
                menu();
            }
        }
        else if (choice == 4){
            menu();
        }
    }

    private static void subMenu(Student student) {
        System.out.println("\n---- More Options ----");
        System.out.println(student);
        System.out.println("1. Edit Student Details");
        System.out.println("2. Delete Student");
        System.out.println("3. Return to Main Menu");
        Scanner scrn = new Scanner(System.in);
        int choice = scrn.nextInt();

        if(choice == 1){
            System.out.println("\n---- Edit Options ----");
            System.out.println("1. Edit First Name");
            System.out.println("2. Edit Last Name");
            System.out.println("3. Edit Date of Birth");
            System.out.println("4. Edit level");
            System.out.println("5. Return to sub menu");

            scrn = new Scanner(System.in);
            choice = scrn.nextInt();
            scrn.nextLine();
            if(choice == 1){
                System.out.println("Enter new first name: ");
                String newName = scrn.nextLine();
                db.updateFirstName(student, newName);
                subMenu(student);
            }
            else if (choice == 2){
                System.out.println("Enter new last name: ");
                String newName = scrn.nextLine();
                db.updateLastName(student, newName);
                subMenu(student);
            }
            else if (choice == 3){
                System.out.println("Enter new date of birth: ");
                String newDate = scrn.nextLine();
                db.updateBirthDate(student, newDate);
                subMenu(student);
            }
            else if (choice == 4){
                System.out.println("Enter new level(FR, SO, JR, SR): ");
                String newLevel = scrn.nextLine();
                while (!newLevel.equals("FR") && !newLevel.equals("SO") && !newLevel.equals("JR") && !newLevel.equals("SR")){
                    System.out.println("Incorrect input, try again...");
                    System.out.println("Enter new level(FO, SO, JR, SR): ");
                    newLevel = scrn.nextLine();
                }
                db.updateLevel(student, newLevel);
                subMenu(student);
            }
            else if(choice == 5){
                subMenu(student);
            }

        }
        else if (choice == 2){
            db.deleteStudent(student);
            menu();
        }
        else if (choice == 3){
            menu();
        }

    }
}
