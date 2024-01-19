package entity;

import run.Store;

import java.util.Scanner;

public class Product implements IProduct {
    private String id;
    private String name;
    private double inportPrice;
    private double exportPrice;
    private double profit;
    private String description;
    private boolean status;
    private int categoryId;

    public Product() {
    }

    public Product(String id, String name, double inportPrice, double exportPrice, double profit, String description, boolean status, int categoryId) {
        this.id = id;
        this.name = name;
        this.inportPrice = inportPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInportPrice() {
        return inportPrice;
    }

    public void setInportPrice(double inportPrice) {
        this.inportPrice = inportPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void inputData(Scanner sc) {
        idInput(sc);
        nameInput(sc);
        importPriceInput(sc);
        exportPriceInput(sc);
        profitInput();
        descriptionInput(sc);
        statusInput(sc);
        categoryIdInput(sc);
    }

    public void idInput(Scanner sc) {
        System.out.println("Nhập ID của sản phẩm gồm 4 ký tự bắt đầu bằng chữ P ");
        boolean check = true;
        do {
            String idInput = sc.nextLine();
            if (!idInput.equals(this.id)) {
                if (idInput.length() == 4 && idInput.toUpperCase().startsWith("P")) {
                    this.id = idInput.toUpperCase();
                    check = false;
                } else System.err.println("ID không đủ 4 ký tự hoặc không bắt đầu bằng chữ P, Mời bạn nhập lại ");
            } else System.err.println("ID đã tồn tại!, Mời nhập lại");
        } while (check);
    }

    public void nameInput(Scanner sc) {
        System.out.println("Nhập tên của sản phẩm gồm 6-30 ký tự");
        boolean checkname = true;
        do {
            String nameInput = sc.nextLine();
            if (!nameInput.toUpperCase().equals(this.name)) {
                if (nameInput.length() >= 6 && nameInput.length() <= 30) {
                    this.name = nameInput.toUpperCase();
                    checkname = false;
                } else System.err.println("Tên không đủ 6 ký tự hoặc dài quá 30 ký tự! Mời bạn nhập lại");
            } else System.err.println("Tên đã tồn tại!, Mời bạn nhập lại");
        } while (checkname);
    }

    public void importPriceInput(Scanner sc) {
        System.out.println("Nhập giá nhập vào của sản phẩm");
        boolean checkimportPrice = true;
        while (checkimportPrice) {
            try {
                double importPriceInput = Double.parseDouble(sc.nextLine());
                if (importPriceInput > 0) {
                    this.inportPrice = importPriceInput;
                    checkimportPrice = false;
                } else System.err.println("Giá lớn hơn không, Mời bạn nhập lại");
            } catch (Exception e) {
                System.err.println("Giá là số không phải ký tự!, Mời bạn nhập lại");
            }

        }
    }

    public void exportPriceInput(Scanner sc) {
        System.out.println("Mời bạn nhập giá bán");
        boolean checkExportPrice = true;
        while (checkExportPrice) {
            try {
                double exportPriceInput = Double.parseDouble(sc.nextLine());

                if (exportPriceInput / this.inportPrice > IProduct.MIN_INTEREST_RATE && exportPriceInput > 0) {
                    this.exportPrice = exportPriceInput;
                    checkExportPrice = false;
                } else
                    System.err.println("Giá bán lớn hơn giá nhập vào ít nhất " + MIN_INTEREST_RATE + " lần và lớn hơn 0!, Mời bạn nhập lại");
            } catch (Exception e) {
                System.err.println("Giá bán là số không phải ký tự");
            }
        }
    }

    public void profitInput() {
        this.profit = this.exportPrice - this.inportPrice;
    }

    public void descriptionInput(Scanner sc) {
        System.out.println("Mô tả sản phẩm");
       boolean check = true;
       do {
           check = true;
           String descriptionInput = sc.nextLine();
            if (descriptionInput != null) {
                this.description = descriptionInput;
                check =false;
            } else System.err.println("Mời bạn nhập mô tả sản phẩm, không được để trống ");
        }while (check);
    }

    public void statusInput(Scanner sc) {
        System.out.println("Mời bạn nhập \"true\" nếu hoạt động, \"false\" nếu không hoạt động");

        boolean check = true;
        do {
            check = true;
            String statusInput = sc.nextLine();
            if (statusInput.toLowerCase().equals("true") || statusInput.toLowerCase().equals("false")) {
                this.status = Boolean.parseBoolean(statusInput);
                check = false;
            } else System.err.println("Nhập true hoặc false, Mời bạn nhập lại");
        } while (check);


    }

    public void categoryIdInput(Scanner sc) {
        for (int i = 0; i < Store.categoryList.size(); i++) {
            System.out.println(Store.categoryList.get(i).getId() + " : " + Store.categoryList.get(i).getName());
        }

        System.out.println("Mời bạn nhập categoryID: ");
        try {
            int categoryIdInput = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < Store.categoryList.size(); i++) {
                if (categoryIdInput == Store.categoryList.get(i).getId()) {
                    this.categoryId = categoryIdInput;
                } else System.err.println("CategoryID không tồn tại");
            }
        } catch (Exception e) {
            System.err.println("ID là một số không phải ký tự");
        }
    }

    @Override
    public void displayData() {
        System.out.printf("Sản phẩm %s - ID: %s - Giá nhập: %d - Giá bán - %d - lợi nhuận sản phẩm: %d - Mô tả sản phẩm: %s - Trạng thái sản phẩm %s",
                this.name, this.id, this.inportPrice, this.exportPrice, this.profit, this.description, this.status ? "Còn hàng" : "Ngừng kinh doanh");
    }

    @Override
    public void calProfit() {

    }
}
