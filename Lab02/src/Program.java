import java.util.Scanner;

import static java.lang.System.in;

public class Program {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Read all products");
            System.out.println("2. Read detail of a product");
            System.out.println("3. Add a new product");
            System.out.println("4. Upload a product");
            System.out.println("5. Delete a product by id");
            System.out.println("6. Exits");
            System.out.println("Your run: ");


            Scanner sc = new Scanner(in);
            int run = sc.nextInt();

            int id;
            String name;
            int price;
            String year;


            switch (run) {
                case 1 -> productDAO.readAll();
                case 2 -> {
                    int id_2 = sc.nextInt();
                    productDAO.read(id_2);
                }
                case 3 -> {
                    System.out.println("Nhap Id");
                    id = sc.nextInt();
                    System.out.println("Nhap name");
                    name = sc.next();
                    System.out.println("Nhap price");
                    price = sc.nextInt();
                    System.out.println("Nhap year");
                    year = sc.next();
                    productDAO.add(new Product(id, name, price, year));
                }
                case 4 -> {
                    System.out.println("Enter id");
                    id = sc.nextInt();
                    System.out.println("Enter name");
                    name = sc.next();
                    System.out.println("Enter price");
                    price = sc.nextInt();
                    System.out.println("Enter year");
                    year = sc.next();
                    productDAO.update(new Product(id, name, price, year));
                }
                case 5 -> {
                    id = sc.nextInt();
                    productDAO.delete(id);
                }
                case 6 -> exit = true;
            }
        }

    }
}
