import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public Main() throws FileNotFoundException {
    }

    public static void main(String[] args){
        //Student s = new Student(202278640, "Yousef", "Abdelaziz", "09/01/2005", "SO");
        //System.out.println(s);
        loadData();
    }

    public static void loadData(){
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("students-details (1).csv"))) {
            String line;
            String firstline= br.readLine();
            DataBase db = new DataBase();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Student newStudent = new Student(Integer.parseInt(values[0]), values[1], values[1], values[3], values[4]);
                System.out.println(newStudent);
                db.addStudent(newStudent);
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
