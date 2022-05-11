// 10998

import java.util.Scanner;

public class BaekJoon0001 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] tmp = input.split(" ");
        int num1 = Integer.parseInt(tmp[0]);
        int num2 = Integer.parseInt(tmp[1]);

        System.out.println(num1 * num2);
    }
}
