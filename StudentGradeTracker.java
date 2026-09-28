import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(String.in);
        
        System.out.println("=== Student Grade Tracker ===");
        
        while (true) {
            System.out.print("\nEnter student name (or type 'exit' to finish): ");
            String name = scanner.nextLine();
            
            if (name.equalsIgnoreCase("exit")) {
                break;
            }
            
            System.out.print("Enter grade for " + name + ": ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // Clear buffer
            
            // Add student to the list
            students.add(new Student(name, grade));
        }
        
        // Check if any student data was entered
        if (students.isEmpty()) {
            System.out.println("\nNo student records entered.");
            return;
        }
        
        // Calculations
        double total = 0;
        double highest = students.get(0).grade;
        double lowest = students.get(0).grade;
        String highestStudent = students.get(0).name;
        String lowestStudent = students.get(0).name;
        
        System.out.println("\n--- Summary Report ---");
        for (Student s : students) {
            System.out.printf("Student: %-15s | Grade: %.2f\n", s.name, s.grade);
            total += s.grade;
            
            if (s.grade > highest) {
                highest = s.grade;
                highestStudent = s.name;
            }
            if (s.grade < lowest) {
                lowest = s.grade;
                lowestStudent = s.name;
            }
        }
        
        double average = total / students.size();
        
        // Final Analysis Display
        System.out.println("----------------------");
        System.out.printf("Total Students : %d\n", students.size());
        System.out.printf("Average Grade  : %.2f\n", average);
        System.out.printf("Highest Grade  : %.2f (%s)\n", highest, highestStudent);
        System.out.printf("Lowest Grade   : %.2f (%s)\n", lowest, lowestStudent);
        System.out.println("----------------------");
        
        scanner.close();
    }
}