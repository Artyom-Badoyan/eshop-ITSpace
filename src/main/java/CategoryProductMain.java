import commands.Commands;
import manager.CategoryManager;
import manager.ProductManager;
import model.Category;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class CategoryProductMain implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static CategoryManager categoryManager = new CategoryManager();
    private static ProductManager productManager = new ProductManager();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT -> isRun = false;
                case Add_Category -> addCategory();
                case CHANGE_CATEGORY_BY_ID -> changeCategoryById();
                case DELETE_CATEGORY_BY_iD -> deleteCategoryById();
                case ADD_PRODUCT -> addProduct();
                case CHANGE_PRODUCT_BY_ID -> changeProductById();
                case DELETE_PRODUCT_BY_ID -> deleteProductById();
                case PRINT_SUM_OF_PRODUCTS -> printSumOfProducts();
                case PRINT_MAX_OF_PRICE_PRODUCT -> printMaxOfPriceProduct();
                case PRINT_MIN_OF_PRICE_PRODUCT -> printMinOfPriceProduct();
                case PRINT_AVG_OF_PRICE_PRODUCT -> printAvgOfPriceProduct();
            }
        }
    }

    private static void deleteProductById() {
        List<Product> productList = productManager.getAllProduct();
        for (Product product : productList) {
            System.out.println(product);
        }
        System.out.println("Please inout product id");
        int id = Integer.parseInt(scanner.nextLine());
        productManager.removeById(id);
        System.out.println("Product removed");
    }

    private static void changeProductById() {
        List<Product> productList = productManager.getAllProduct();
        for (Product product : productList) {
            System.out.println(product);
        }
        System.out.println("Please input product id");
        int id = Integer.parseInt(scanner.nextLine());
        if (productManager.getByIdProduct(id) != null) {
            System.out.println("Please input product name,description,price,quantity,categoryId");
            String productStr = scanner.nextLine();
            String[] productData = productStr.split(",");
            Product product = new Product();
            product.setId(id);
            product.setName(productData[0]);
            product.setDescription(productData[1]);
            product.setPrice(Integer.parseInt(productData[2]));
            product.setQuantity(Integer.parseInt(productData[3]));
            product.setCategoryId(Integer.parseInt(productData[4]));
            productManager.update(product);
            System.out.println("Product was updated");
        } else {
            System.out.println("Product does not exists");
        }
    }

    private static void addProduct() {
        System.out.println("please input product name,description,price,quantity,categoryId");
        String productStr = scanner.nextLine();
        String[] productData = productStr.split(",");
        Product product = new Product();
        try {
            product.setName(productData[0]);
            product.setDescription(productData[1]);
            product.setPrice(Integer.parseInt(productData[2]));
            product.setQuantity(Integer.parseInt(productData[3]));
            product.setCategoryId(Integer.parseInt(productData[4]));
            productManager.saveProductDB(product);
        } catch (NumberFormatException e) {
            System.out.println("Please enter correct data");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter correct data");
        }
    }

    private static void deleteCategoryById() {
        List<Category> categoryList = categoryManager.getAllCategory();
        for (Category category : categoryList) {
            System.out.println(category);
        }
        System.out.println("Please inout category id");
        int id = Integer.parseInt(scanner.nextLine());
        categoryManager.removeById(id);
        System.out.println("Category removed");
    }

    private static void changeCategoryById() {
        List<Category> categoryList = categoryManager.getAllCategory();
        for (Category category : categoryList) {
            System.out.println(category);
        }
        System.out.println("Please input category id");
        int id = Integer.parseInt(scanner.nextLine());
        if (categoryManager.getByIdCategory(id) != null) {
            System.out.println("Please input category name");
            String categoryStr = scanner.nextLine();
            Category category = new Category();
            category.setId(id);
            category.setName(categoryStr);
            categoryManager.update(category);
            System.out.println("Category was updated");
        } else {
            System.out.println("Category does not exists");
        }
    }

    private static void addCategory() {
        System.out.println("please input category name");
        String categoryData = scanner.nextLine();
        Category category = new Category();
        category.setName(categoryData);
        categoryManager.saveCategoryDB(category);
    }

    private static void printAvgOfPriceProduct() {
        productManager.Avg();
    }

    private static void printMinOfPriceProduct() {
        productManager.Min();
    }

    private static void printMaxOfPriceProduct() {
        productManager.Max();
    }

    private static void printSumOfProducts() {
        productManager.sum();
    }
}