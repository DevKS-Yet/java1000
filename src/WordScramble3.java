//[문제3] 문제2의 예제를 변경해서, 문제를 맞추더라도 프로그램이 종료되지 않고 다음문제를 보여주도록 하세요.

import java.util.*;

class WordScramble3 {
    public static void main(String[] args) {
        String[] strArr = { "CHANGE", "LOVE", "HOPE", "VIEW"};

        // 코드를 넣어 완성하세요.

        // hint : while문을 중첩해서(2개의 while문) 작성하세요.

        escape :
        while (true) {
            String answer = getAnswer(strArr);
            String question = getScrambledWord(answer);
            while (true) {
                System.out.println("Question : " + question);
                System.out.print("You answer is : ");

                Scanner sc = new Scanner(System.in);

                String input = sc.next().toUpperCase();

                if (input.equals("Q")) {
                    break escape;
                }
                if (input.equals(answer)) {
                    System.out.println("정답입니다.");
                    break;
                }
            }
        }

    } // main

    public static String getAnswer(String[] strArr) {
        int idx = (int)(Math.random()*strArr.length);
        return strArr[idx];
    }

    public static String getScrambledWord(String str) {
        char[] chArr = str.toCharArray();

        for(int i=0;i < str.length();i++) {


            int idx = (int)(Math.random()*str.length());

            char tmp = chArr[i];
            chArr[i] = chArr[idx];
            chArr[idx] = tmp;
        }

        return new String(chArr);
    } // scramble(String str)
}

//[실행결과]
//Question :IWVE
//Your answer is :ievw
//ievw은/는 정답이 아닙니다. 다시 시도해보세요.
//Question :IWVE
//Your answer is :view
//정답입니다.
//Question :HOEP
//Your answer is :hope
//정답입니다.
//Question :GNCAEH
//Your answer is :change
//정답입니다.
//Question :HECNAG
//Your answer is :q