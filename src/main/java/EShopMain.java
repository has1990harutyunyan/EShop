import manager.CategoryManger;
import model.Category;

import java.util.Scanner;

public class EShopMain {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static CategoryManger categoryManger = new CategoryManger();


    public static void main(String[] args) {

        boolean isRun = true;
        while (isRun) {
            System.out.println("Enter 0 to Exit");
            System.out.println("Enter 1 to add a category");
            System.out.println("Enter 2 to edit category by id");
            System.out.println("Enter 3 to delete category by id");
            System.out.println("Enter 4 to add a product");
            System.out.println("Enter 5 to edit a product by id");
            System.out.println("Enter 6 to edit a product by id");
            System.out.println("Enter 7 to print the sum of products");
            System.out.println("Enter 8 to print the max price of products");
            System.out.println("Enter 9 to print the min price of products");
            System.out.println("Enter 10 to print the avg price of products");

            String command = SCANNER.nextLine();
            switch (command) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    addCategory();
                    break;
                case "2":
                    editCategory();
                    break;
                case "3":
                    deleteCategory();
                    break;
                case "4":
                    addProduct();


            }


        }

    }

    private static void addProduct() {
        for (Category category : categoryManger.getAll()) {
            System.out.println(category);
        }
        System.out.println("Please enter category id");


    }

    private static void deleteCategory() {
        for (Category category : categoryManger.getAll()) {
            System.out.println(category);
        }
        System.out.println("Please enter category id");
        String id = SCANNER.nextLine();
        categoryManger.deleteById(Integer.parseInt(id));
    }

    private static void editCategory() {
        for (Category category : categoryManger.getAll()) {
            System.out.println(category);
        }
        System.out.println("Please enter category id");
        String id = SCANNER.nextLine();
        Category categoryById = categoryManger.getCategoryById(Integer.parseInt(id));
        if (categoryById != null) {
            Category category = new Category();
            System.out.println("Please enter new name");
            String name = SCANNER.nextLine();
            category.setId(Integer.parseInt(id));
            category.setName(name);
            categoryManger.update(category);

        }

    }

    private static void addCategory() {
        System.out.println("Please, enter category name");
        String name = SCANNER.nextLine();
        Category category = new Category();
        category.setName(name);
        categoryManger.save(category);
    }


}
