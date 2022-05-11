// 1008

import java.util.Scanner;

public class BaekJoon0002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] tmp = input.split(" ");
        double a = Double.parseDouble(tmp[0]);
        double b = Double.parseDouble(tmp[1]);

        System.out.println(a/b);
    }
}
