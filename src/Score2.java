//[문제2] Student클래스가 Comparable인터페이스를 구현해서, list를 총점(total) 내림차순으로 정렬되도록 하는 예제입니다.
//아래의 코드를 완성하세요.

//[예제SungJukEx2.java]

import java.util.*;

class SungJukEx2 {
    public static void main(String[] args) {
        ArrayList<Student2> list = new ArrayList<Student2>();

        // 이름, 반, 번호, 국어, 수학, 영어
        list.add(new Student2("남궁성", 3,2,100,100,100));
        list.add(new Student2("왕자바", 3,1,90,100,80));
        list.add(new Student2("자바왕", 3,3,70,100,100));
        list.add(new Student2("킹왕짱", 1,2,100,60,90));
        list.add(new Student2("자바짱", 1,1,100,100,100));
        list.add(new Student2("최고수", 1,3,100,80,60));
        list.add(new Student2("홍길동", 2,1,50,80,100));
        list.add(new Student2("일지매", 2,3,70,80,100));
        list.add(new Student2("변강쇠", 2,4,80,80,85));
        list.add(new Student2("이원구", 2,2,90,90,90));

        Collections.sort(list);
        printList(list);
    }

    public static void printList(List<Student2> list) {
        System.out.println("이름\t학년\t반\t국어\t수학\t영어\t총점 ");
        System.out.println("====================================================");

        for(Student2 s : list) {
            System.out.println(s);
        }

        System.out.println("====================================================");
    }
}

class Student2 implements Comparable<Student2> {
    String name = "";
    int classNo = 0;
    int studentNo = 0;
    int koreanScore = 0;
    int mathScore = 0;
    int englishScore = 0;
    int total = 0;

    Student2(String name, int classNo, int studentNo, int koreanScore, int mathScore, int englishScore) {
        this.name = name;
        this.classNo = classNo;
        this.studentNo = studentNo;
        this.koreanScore = koreanScore;
        this.mathScore = mathScore;
        this.englishScore = englishScore;

        total = koreanScore + mathScore + englishScore;
    }

    public String toString() {
        return name + "\t"
                + classNo + "\t"
                + studentNo + "\t"
                + koreanScore + "\t"
                + mathScore + "\t"
                + englishScore + "\t"
                + total + "\t";
    }

    public int compareTo(Student2 obj) {
//        코드를 완성하세요.
//        return obj.total - this.total;
        if (this.total < obj.total) {
            return 1;
        } else if (this.total == obj.total) {
            return 0;
        } else {
            return -1;
        }
    }
} // end of class Student

//[실행결과] - 이전 결과와는 달리, 총점이 높은순서에서 낮은 순서로 내림차순 정렬되어있음을 확인하세요.
//이름    반     번호    국어    수학    영어    총점
//====================================================
//남궁성  3       2       100     100     100     300
//자바짱  1       1       100     100     100     300
//왕자바  3       1       90      100     80      270
//자바왕  3       3       70      100     100     270
//이원구  2       2       90      90      90      270
//킹왕짱  1       2       100     60      90      250
//일지매  2       3       70      80      100     250
//변강쇠  2       4       80      80      85      245
//최고수  1       3       100     80      60      240
//홍길동  2       1       50      80      100     230
//====================================================