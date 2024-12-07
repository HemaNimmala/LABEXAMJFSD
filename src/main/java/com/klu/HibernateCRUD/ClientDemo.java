https://github.com/HemaNimmala/LABEXAMJFSD.gitpackage com.klu.HibernateCRUD;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) {
        
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Insert Department");
            System.out.println("2. Delete Department");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insertDepartment(session, scanner);
                    break;
                case 2:
                    deleteDepartment(session, scanner);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        sessionFactory.close();
    }

    public static void insertDepartment(Session session, Scanner scanner) {
        Transaction transaction = session.beginTransaction();

        Department department = new Department();

        scanner.nextLine(); 
        System.out.print("Enter Department Name: ");
        department.setName(scanner.nextLine());

        System.out.print("Enter Department Location: ");
        department.setLocation(scanner.nextLine());

        System.out.print("Enter HoD Name: ");
        department.setHodName(scanner.nextLine());

        session.save(department);

        transaction.commit();
        System.out.println("Department inserted successfully");
    }

    public static void deleteDepartment(Session session, Scanner scanner) {
        Transaction transaction = session.beginTransaction();

        System.out.print("Enter Department ID to delete: ");
        int departmentId = scanner.nextInt();

        String hql = "DELETE FROM Department WHERE departmentId = ?1";
        int result = session.createQuery(hql).setParameter(1, departmentId).executeUpdate();

        if (result > 0) {
            System.out.println("Department deleted successfully!");
        } else {
            System.out.println("Department not found!");
        }

        transaction.commit();
    }
}

