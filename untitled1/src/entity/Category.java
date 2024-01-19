package entity;

import java.util.Scanner;

public class Category implements ICategory {
    /**
     * int id: mã danh mục (phải là số nguyên lớn hơn 0, duy nhất)
     * String name: tên danh mục (không trùng nhau, từ 6-30 kí tự)
     * String description: mô tả danh mục (không bỏ trống)
     * boolean status: trạng thái danh mục (chỉ nhận true/false khi nhập)
     */

    private int id;
    private String name;
    private String description;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public void inputData(Scanner sc) {
        idInput(sc);
        nameInput(sc);
        descriptionInput(sc);
        statusInput(sc);
    }

    public void idInput(Scanner sc) {
        System.out.println("Mời bạn nhập id: ");
        boolean check = true;
        while (check) {
            try {
                int idInput = Integer.parseInt(sc.nextLine());
                if (this.id != idInput) {
                    if (idInput > 0) {
                        this.id = idInput;
                        check = false;
                    } else System.err.println("Mời bạn nhập Id là số nguyên dương lớn hơn 0");
                } else System.err.println("ID đã tồn tại");
            } catch (Exception e) {
                System.err.println("Mời bạn nhập ID là một số không phải ký tự");
            }
        }
    }

    public void nameInput(Scanner sc) {
        do {
            System.out.println("Mời bạn nhập tên danh dục: ");
            String nameInput = sc.nextLine();
            if (!nameInput.toUpperCase().equals(this.name)) {
                if (nameInput.length() >= 6 && nameInput.length() <= 30) {
                    this.name = nameInput.toUpperCase();

                }
            } else System.err.println("Tên danh mục đã bị trùng!, Mời bạn nhập lại!");
        } while (this.name == null);
    }

    public void descriptionInput(Scanner sc) {
        System.out.println("Mời bạn nhập mô tả sp");
        do {
            String descriptioninput = sc.nextLine();
            if (descriptioninput.length()!=0) {
                this.description = descriptioninput;
            } else System.err.println("Mời bạn nhập mô tả sản phẩm!");
        } while (this.description == null);
    }

    public void statusInput(Scanner sc) {
        System.out.println("Mời bạn nhập \"true\" nếu hoạt động, \"false\" nếu không hoạt động");

        boolean check = true;
        do {
            String statusInput = sc.nextLine();
            if (statusInput.toLowerCase().equals("true") || statusInput.toLowerCase().equals("false")) {
                this.status = Boolean.parseBoolean(statusInput);
                check = false;
            } else System.err.println("Nhập true hoặc false, Mời bạn nhập lại");
        } while (check);

    }

    @Override
    public void displayData() {
        System.out.printf("Tên danh mục: %s - ID : %d - Mô tả danh mục : %s - Trạng thái danh mục = %b",
                this.name, this.id, this.description, this.status ? "Đang hoạt động" : "Không hoạt động");
    }
}
/**
 * int id: mã danh mục (phải là số nguyên lớn hơn 0, duy nhất)
 * String name: tên danh mục (không trùng nhau, từ 6-30 kí tự)
 * String description: mô tả danh mục (không bỏ trống)
 * boolean status: trạng thái danh mục (chỉ nhận true/false khi nhập)
 */