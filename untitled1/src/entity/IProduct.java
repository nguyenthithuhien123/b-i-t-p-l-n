package entity;

import java.util.Scanner;

public interface IProduct {
    float MIN_INTEREST_RATE = 0.2f;//lãi suất nhỏ nhất trên từng sản phẩm

    void inputData(Scanner sc);

    void displayData();

    void calProfit();//Tính lợi nhuận trên tất cả sản phẩm
}
