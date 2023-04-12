import manager.CategoryManger;
import manager.ProductManager;
import model.Category;
import model.Product;

import java.util.Scanner;

public class EShopMain {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final CategoryManger CATEGORY_MANGER = new CategoryManger();
    private static final ProductManager PRODUCT_MANAGER = new ProductManager();


    public static void main(String[] args) {

        boolean isRun = true;
        while (isRun) {
            System.out.println("Enter 0 to Exit");
            System.out.println("Enter 1 to add a category");
            System.out.println("Enter 2 to edit category by id");
            System.out.println("Enter 3 to delete category by id");
            System.out.println("Enter 4 to add a product");
            System.out.println("Enter 5 to edit a product by id");
            System.out.println("Enter 6 to delete a product by id");
            System.out.println("Enter 7 to print the sum of products");
            System.out.println("Enter 8 to print the max price of products");
            System.out.println("Enter 9 to print the min price of products");
            System.out.println("Enter 10 to print the avg price of products");

            String command = SCANNER.nextLine();
            switch (command) {
                case "0" -> isRun = false;
                case "1" -> addCategory();
                case "2" -> editCategory();
                case "3" -> deleteCategory();
                case "4" -> addProduct();
                case "5" -> editProduct();
                case "6" -> deleteProduct();
                case "7" -> printSumOfProducts();
                case "8" -> printMaxPriceOfProducts();
                case "9" -> printMinPriceOfProducts();
                case "10" -> printAvgPriceOfProducts();
            }


        }

    }

    private static void printAvgPriceOfProducts() {
        System.out.println(PRODUCT_MANAGER.getAvgPrice());
    }

    private static void printMinPriceOfProducts() {
        System.out.println(PRODUCT_MANAGER.getMinPrice());
    }

    private static void printMaxPriceOfProducts() {
        System.out.println(PRODUCT_MANAGER.getMaxPrice());
    }

    private static void printSumOfProducts() {
        System.out.println(PRODUCT_MANAGER.getSum());
    }

    private static void deleteProduct() {
        for (Product product : PRODUCT_MANAGER.getAll()) {
            System.out.println(product);
        }
        System.out.println("Please enter product id");
        String id = SCANNER.nextLine();
        PRODUCT_MANAGER.deleteById(Integer.parseInt(id));
    }

    private static void editProduct() {
        for (Product product : PRODUCT_MANAGER.getAll()) {
            System.out.println(product);
        }
        System.out.println("Please, enter product id");
        String productId = SCANNER.nextLine();
        Product productById = PRODUCT_MANAGER.getById(Integer.parseInt(productId));
        if (productById != null) {
            for (Category category : CATEGORY_MANGER.getAll()) {
                System.out.println(category);
            }
            System.out.println("Please enter category id");
            String categoryId = SCANNER.nextLine();
            System.out.println("Please, enter product name, description, price and quantity");
            String productDataStr = SCANNER.nextLine();
            String[] productData = productDataStr.split(",");
            Product product = new Product();
            product.setName(productData[0]);
            product.setDescription(productData[1]);
            product.setPrice(Double.parseDouble(productData[2]));
            product.setQuantity(Integer.parseInt(productData[3]));
            product.setCategory(CATEGORY_MANGER.getCategoryById(Integer.parseInt(categoryId)));
            product.setId(Integer.parseInt(productId));
            PRODUCT_MANAGER.update(product);


        }
    }

    private static void addProduct() {
        for (Category category : CATEGORY_MANGER.getAll()) {
            System.out.println(category);
        }
        System.out.println("Please enter category id");
        String categoryId = SCANNER.nextLine();
        System.out.println("Please, enter product name, description, price and quantity");
        String productDataStr = SCANNER.nextLine();
        String[] productData = productDataStr.split(",");
        Product product = new Product();
        product.setName(productData[0]);
        product.setDescription(productData[1]);
        product.setPrice(Double.parseDouble(productData[2]));
        product.setQuantity(Integer.parseInt(productData[3]));
        product.setCategory(CATEGORY_MANGER.getCategoryById(Integer.parseInt(categoryId)));
        PRODUCT_MANAGER.add(product);
        System.out.println("Product is added");

        for (Product value : PRODUCT_MANAGER.getAll()) {
            System.out.println(value);

        }


    }

    private static void deleteCategory() {
        for (Category category : CATEGORY_MANGER.getAll()) {
            System.out.println(category);
        }
        System.out.println("Please enter category id");
        String id = SCANNER.nextLine();
        CATEGORY_MANGER.deleteById(Integer.parseInt(id));
    }

    private static void editCategory() {
        for (Category category : CATEGORY_MANGER.getAll()) {
            System.out.println(category);
        }
        System.out.println("Please enter category id");
        String id = SCANNER.nextLine();
        Category categoryById = CATEGORY_MANGER.getCategoryById(Integer.parseInt(id));
        if (categoryById != null) {
            Category category = new Category();
            System.out.println("Please enter new name");
            String name = SCANNER.nextLine();
            category.setId(Integer.parseInt(id));
            category.setName(name);
            CATEGORY_MANGER.update(category);

        }

    }

    private static void addCategory() {
        System.out.println("Please, enter category name");
        String name = SCANNER.nextLine();
        Category category = new Category();
        category.setName(name);
        CATEGORY_MANGER.save(category);
    }


}
