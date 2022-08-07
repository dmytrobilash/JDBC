import java.sql.*;
import java.util.*;


public class Main {
    public static CarsDAO carsDAO;
    public static OwnersDAO ownersDAO;


    public static void main(String[] args) {

        try {
            var connection = ConnectionFactory.getConnection();
            carsDAO = new CarsDAO(connection);
            ownersDAO = new OwnersDAO(connection);
            System.out.println("Successfully connecting to DB");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        Scanner in = new Scanner(System.in);

        String str = "";

        //System.out.println(str instanceof String);


        while (true) {
            System.out.println("\n0 - Close the program");
            System.out.println("1 - Insert to Owners");
            System.out.println("2 - Insert to Cars");
            System.out.println("3 - Out of a owners by car model");
            str = in.nextLine();
            int i = Integer.parseInt(str);
            if (i == 0) {
                System.out.println("You closed the program");
                return;
            }
            if (i == 1) {
                System.out.println("Insert into Owners table");
                String fname = "";
                String lname = "";
                String ageString = "";
                String sex = "";
                int age = 0;
                System.out.println("Write the firstname of an owner");

                fname = in.nextLine();

                System.out.println("Write the lastname of an owner");

                lname = in.nextLine();

                System.out.println("Write the age of an owner");

                ageString = in.nextLine();
                age = Integer.parseInt(ageString);


                System.out.println("Write the sex of an owner");

                sex = in.nextLine();

                insertToOwners(1, fname, lname, age, sex);

            } else if (i == 2) {

                System.out.println("Insert into Cars");
                String manufacturer = "1";
                String model = "2";
                String yearOfProducingString = "3";
                String color = "4";
                String priceString = "5";
                String ownerIdString = "6";

                int yearOfProducing = 0;
                double price = 0;
                int ownerId = 0;

                System.out.println("Write the Manufacturer of a car");

                manufacturer = in.nextLine();

                System.out.println("Write the model of a car");

                model = in.nextLine();

                System.out.println("Write the year of producing");//

                yearOfProducingString = in.nextLine();
                yearOfProducing = Integer.parseInt(yearOfProducingString);


                System.out.println("Write the color of a car");

                color = in.nextLine();

                System.out.println("Write the price of a car");

                priceString = in.nextLine();
                price = Double.parseDouble(priceString);

                System.out.println("Write the owner Id of a car");

                ownerIdString = in.nextLine();
                ownerId = Integer.parseInt(ownerIdString);

                insertToCars(1, manufacturer, model, yearOfProducing, color, price, ownerId);
                System.out.println("/n");


            } else if (i == 3) {
                System.out.println("Write model of a car");
                String model = "";
                model = in.nextLine();
                selectFromOwnersByModel(model);

            } else
                System.out.println("You chose an unknown operation");
        }

    }

    private static void insertToOwners(int id, String fname, String lname, int age, String sex) {
        var insertOwner = new Owners(id,
                fname,
                lname,
                age,
                sex);
        if (ownersDAO.save(insertOwner)) {
            System.out.println("Created new branch");
        } else {
            System.out.println("Failed to create new branch");
        }
    }

    private static void insertToCars(int id, String manufacturer, String model, int yearOfProducing, String color, double price, int ownerId) {
        var insertCar = new Cars(0,
                manufacturer,
                model,
                yearOfProducing,
                color,
                price,
                ownerId);
        if (carsDAO.save(insertCar)) {
            System.out.printf("Created new branch");
        } else {
            System.out.printf("Failed to create new branch: %s%n", insertCar.getId());
        }
    }

    private static void selectFromOwnersByModel(String model) {
        ownersDAO.getOwnersByCarModel(model).forEach(value -> System.out.println(value));
    }

}

