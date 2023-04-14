package commands;

public interface Commands {
    String EXIT = "0";
    String Add_Category = "1";
    String CHANGE_CATEGORY_BY_ID = "2";
    String DELETE_CATEGORY_BY_iD = "3";
    String ADD_PRODUCT = "4";
    String CHANGE_PRODUCT_BY_ID = "5";
    String DELETE_PRODUCT_BY_ID = "6";
    String PRINT_SUM_OF_PRODUCTS = "7";
    String PRINT_MAX_OF_PRICE_PRODUCT = "8";
    String PRINT_MIN_OF_PRICE_PRODUCT = "9";
    String PRINT_AVG_OF_PRICE_PRODUCT = "10";


    static void printCommands() {
        System.out.println("Please input " + EXIT + " for exit");
        System.out.println("Please input " + Add_Category + " for add category");
        System.out.println("Please input " + CHANGE_CATEGORY_BY_ID + " for change category by id");
        System.out.println("Please input " + DELETE_CATEGORY_BY_iD + " for delete category by id");
        System.out.println("Please input " + ADD_PRODUCT + " for add product");
        System.out.println("Please input " + CHANGE_PRODUCT_BY_ID + " for change product by id");
        System.out.println("Please input " + DELETE_PRODUCT_BY_ID + " for delete product by id");
        System.out.println("Please input " + PRINT_SUM_OF_PRODUCTS + " for print sum of products");
        System.out.println("Please input " + PRINT_MAX_OF_PRICE_PRODUCT + " for print max of price product");
        System.out.println("Please input " + PRINT_MIN_OF_PRICE_PRODUCT + " for print min of price product");
        System.out.println("Please input " + PRINT_AVG_OF_PRICE_PRODUCT + " for print avg of price product");
    }
}