// 1330

import java.util.Scanner;

public class BaekJoon0009 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] strArr = (input.split(" "));
        int a = Integer.parseInt(strArr[0]);
        int b = Integer.parseInt(strArr[1]);

        if (a < b) System.out.println("<");
        else if (a > b) System.out.println(">");
        else System.out.println("==");
    }
}
