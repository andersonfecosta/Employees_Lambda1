package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Employee> employees = new ArrayList<>();

        System.out.print("Enter full file path: ");
        String txt = sc.nextLine();
        System.out.print("Enter salary: ");
        double sal = sc.nextDouble();

        try (BufferedReader br = new BufferedReader(new FileReader(txt))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String email = fields[1];
                double salary = Double.parseDouble(fields[2]);
                employees.add(new Employee(name,email,salary));
                line = br.readLine();
            }

            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

            List<String> emails = employees.stream()
                    .filter(p -> p.getSalary() > sal)
                    .map(p -> p.getEmail())
                    .sorted(comp)
                    .collect(Collectors.toList());

            System.out.println("Email of people whose salary is more than " + String.format("%.2f",sal));
            emails.forEach(System.out::println);

            double sum = employees.stream()
                    .filter(p -> p.getName().charAt(0) == 'M')
                    .map(p -> p.getSalary())
                    .reduce(0.0,(x,y) -> x+y);

            System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f",sum));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
