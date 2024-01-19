package run;

import entity.Category;
import entity.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Store {
    public static Scanner sc = new Scanner(System.in);
    public static List<Category> categoryList = new ArrayList<>();

    public static List<Product> productList = new ArrayList<>();

    public static void main(String[] args) {
        do {
            mainMenu();
            System.out.println("Mời bạn chọn menu");
            try {
                int menu = Integer.parseInt(sc.nextLine());
                switch (menu) {
                    case 1:
                        categoryMenu();

                        break;
                    case 2:
                        productMenu();
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.err.println("Mời bạn nhập số nguyên từ 1-3");
                }
            } catch (Exception e) {
                System.err.println("Nhập số từ 1-3");
            }
        } while (true);
    }

    public static void mainMenu() {
        System.out.println("⮚\t===== QUẢN LÝ KHO =====\n" +
                "1.\tQuản lý danh mục\n" +
                "2.\tQuản lý sản phẩm\n" +
                "3.\tThoát\n");
    }

    public static void categoryMenu() {
        boolean check;

        do {
          check = true;
            System.out.println("⮚\t===== QUẢN LÝ DANH MỤC =====\n" +
                    "1.\tThêm mới danh mục\n" +
                    "2.\tCập nhật danh mục\n" +
                    "3.\tXóa danh mục\n" +
                    "4.\tTìm kiếm danh mục theo tên danh mục\n" +
                    "5.\tThống kê số lượng sp đang có trong danh mục\n" +
                    "6.\tQuay lại\n");
            System.out.println("Mời bạn chọn !");
            try {
                int categoryChose = Integer.parseInt(sc.nextLine());
                switch (categoryChose) {
                    case 1:
                        inputCategory(sc);
                        break;
                    case 2:
                        updateCategoryById(sc);
                        break;
                    case 3:
                        deleteCategoryById(sc);
                        break;
                    case 4:
                        findCategoryByName(sc);
                        break;
                    case 5:

                        break;
                    case 6:
                        check = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-6");
                }
            } catch (Exception e) {
                System.err.println("Mời bạn chọn từ 1-6");
            }
        } while (check);
    }



    public static void inputCategory(Scanner sc) {
        System.out.println("Nhập vào số lượng danh mục cần thêm");
        do {
            try {
                int numberOfCategory = Integer.parseInt(sc.nextLine());
                if (numberOfCategory > 0) {
                    for (int i = 0; i < numberOfCategory; i++) {
                        System.out.println("Nhập danh mục thứ "+(i+1));
                        Category category = new Category();
                        category.inputData(sc);
                        categoryList.add(category);
                    }
                    System.out.println("Bạn đã nhập thành công!");
                    return;
                } else System.err.println("Mời bạn nhập số nguyên lớn hơn 0");
            } catch (Exception e) {
                System.err.println("Số lượng danh mục cần thêm là số nguyên không phải ký tự");
            }
        } while (true);
    }

    public static int indexCategoryId(int categoryId) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryId == categoryList.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public static void updateCategoryById(Scanner sc) {
        System.out.println("Mời bạn nhập ID của danh mục cần cập nhật");
        boolean check = true;
        do {
            try {
                int updateCategoryId = Integer.parseInt(sc.nextLine());
                int indexUpdate = indexCategoryId(updateCategoryId);
                if (indexUpdate >= 2) {
                    categoryList.get(indexUpdate).nameInput(sc);
                    categoryList.get(indexUpdate).descriptionInput(sc);
                    categoryList.get(indexUpdate).statusInput(sc);
                    System.out.println("Bạn đã update thành công danh mục có id: " + updateCategoryId);
                    return;
                } else System.err.println("ID không tồn tại, Vui lòng nhập lại");
            } catch (Exception e) {
                System.err.println("ID là một số không phải ký tự");
            }
        } while (check);
    }


    public static void deleteCategoryById(Scanner sc) {
        System.out.println("Mời bạn nhập ID của danh mục xóa");
        boolean check = true;
        do {
            try {
                int deleteCategoryId = Integer.parseInt(sc.nextLine());
                int indexDelete = indexCategoryId(deleteCategoryId);
                if (indexDelete >= 0) {
                    for (int i = 0; i < productList.size(); i++) {
                        if (categoryList.get(indexDelete).getId() == productList.get(i).getCategoryId()) {
                            System.out.println(" Danh mục đang có sản phẩm tham chiếu, Không thể xóa");
                            return;
                        } else
                            check = false;
                    }
                    if (!check) {
                        categoryList.remove(indexDelete);
                        System.out.println("Bạn đã xóa thành công danh mục có id: " + deleteCategoryId);
                        check = true;
                    return;
                    }
                } else System.err.println("ID không tồn tại, Vui lòng nhập lại");
            } catch (Exception e) {
                System.err.println("ID là một số không phải ký tự");
            }
        } while (check);
    }

    public static void findCategoryByName(Scanner sc) {
        System.out.println("Nhập tên danh mục cần tìm kiếm");
        String nameCategory = sc.nextLine();
        int count = 0;
        for (int i = 0; i < categoryList.size(); i++) {
            if (nameCategory.toUpperCase().contains(categoryList.get(i).getName())) {
                categoryList.get(i).displayData();
                count++;

            }
        }
        if (count == 0) {
            System.out.println("Danh mục bạn tìm không tồn tại!");
        }
    }


    public static void productMenu() {

        boolean check = true;
        while (check) {
            check = true;
            System.out.println("⮚\t===== QUẢN LÝ SẢN PHẨM =====\n" +
                    "1.\tThêm mới sản phẩm\n" +
                    "2.\tCập nhật sản phẩm\n" +
                    "3.\tXóa sản phẩm\n" +
                    "4.\tHiển thị sản phẩm theo tên A-Z\n" +
                    "5.\tHiển thị sản phẩm theo lợi nhuận từ cao-thấp\n" +
                    "6.\tTìm kiếm sản phẩm\n" +
                    "7.\tQuay lại\n");
            System.out.println("Mời bạn chọn!");
            try {
                int productMenuChose = Integer.parseInt(sc.nextLine());
                switch (productMenuChose) {
                    case 1:
                        productInput(sc);
                        break;
                    case 2:
                        updatePrpduct(sc);
                        break;
                    case 3:
                        deleteProduct(sc);
                        break;
                    case 4:
                        showProductAndSortByname();
                        break;
                    case 5:
                        showProductAndSortByprofit();
                        break;
                    case 6:
                        findProduct();
                        break;
                    case 7:
                        check = false;
                        break;
                    default:
                        System.out.println("Vui lòng nhập trong khoảng từ 1-7");

                }
            } catch (Exception e) {
                System.err.println("Vui lòng chọn từ 1-7");
            }
        }
    }

    private static void findProduct() {
        System.out.println("Nhập vào tên hoặc giá trị nhập xuất bạn muốn tìm kiếm");
        String findProduct = sc.nextLine();
        int count = 0;
        for (int i = 0; i < productList.size(); i++) {
            String importFind = String.valueOf(productList.get(i).getInportPrice());
            String exportFine = String.valueOf(productList.get(i).getExportPrice());
            if (findProduct.contains(productList.get(i).getName()) || findProduct.contains(importFind) || importFind.contains(exportFine)) {
                productList.get(i).displayData();
                count++;
            } else System.out.println("Không có sản phẩm nào trùng");
        }
    }

    private static void showProductAndSortByprofit() {
        productList.sort(Comparator.comparingDouble(Product::getProfit));
        for (Product p : productList) {
            p.displayData();
        }
    }

    public static void showProductAndSortByname() {
        productList.sort(Comparator.comparing(Product::getName));
        for (Product p : productList) {
            p.displayData();
        }
    }

    public static void productInput(Scanner sc) {
        boolean check = true;
        System.out.println("Nhập số lượng sản phẩm cần thêm:");
        while (check) {
            try {
                int numberOfproductInput = Integer.parseInt(sc.nextLine());
                if (numberOfproductInput > 0) {
                    for (int i = 0; i < numberOfproductInput; i++) {

                        Product product = new Product();
                        product.inputData(sc);
                        productList.add(product);
                    }
                    check = false;
                } else System.err.println("Mời bạn nhập số nguyên lớn hơn 0!");
            } catch (Exception e) {
                System.err.println("Mời bạn nhập số nguyên!");
            }

        }
    }

    public static int indexProductId(String productId) {
        for (int i = 0; i < productList.size(); i++) {
            if (productId.toUpperCase().equals(productList.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    public static void updatePrpduct(Scanner sc) {
        System.out.println("Nhập Id sản phẩm cần cập nhật");
        int indexUpdateByID = indexProductId(sc.nextLine());
        if (indexUpdateByID > 0) {
            Product product = new Product();
            productList.get(indexUpdateByID).nameInput(sc);
            productList.get(indexUpdateByID).exportPriceInput(sc);
            productList.get(indexUpdateByID).profitInput();
            productList.get(indexUpdateByID).descriptionInput(sc);
            productList.get(indexUpdateByID).statusInput(sc);
            productList.get(indexUpdateByID).categoryIdInput(sc);

        } else System.out.println("ID không tồn tại!");
    }

    public static void deleteProduct(Scanner sc) {
        System.out.println("Nhập Id sản phẩm cần xóa");
        int indexDeleteById = indexProductId(sc.nextLine());
        if (indexDeleteById > 0) {
            Product product = new Product();
            productList.remove(indexDeleteById);
        } else System.out.println("ID không tồn tại!");
    }
}
