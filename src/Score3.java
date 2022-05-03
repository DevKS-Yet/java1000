//[문제3] list를 다양한 기준으로 정렬하기 위해 Comaprator를 구현한 클래스를 구현하세요.
//ClassTotalComparator - 반별로 총점이 높은 순에서 낮은 순으로 정렬(반은 오름차순, 총점은 내림차순)
//ClassStudentNo - 반, 번호 순으로 내림차순 정렬

//[예제SungJukEx3.java]

import java.util.*;

class Score3 {
    public static void main(String[] args) {
        ArrayList<Student3> list = new ArrayList<Student3>();

        // 이름, 반, 번호, 국어, 수학, 영어
        list.add(new Student3("남궁성", 3,2,100,100,100));
        list.add(new Student3("왕자바", 3,1,90,100,80));
        list.add(new Student3("자바왕", 3,3,70,100,100));
        list.add(new Student3("킹왕짱", 1,2,100,60,90));
        list.add(new Student3("자바짱", 1,1,100,100,100));
        list.add(new Student3("최고수", 1,3,100,80,60));
        list.add(new Student3("홍길동", 2,1,50,80,100));
        list.add(new Student3("일지매", 2,3,70,80,100));
        list.add(new Student3("변강쇠", 2,4,80,80,85));
        list.add(new Student3("이원구", 2,2,90,90,90));

        System.out.println("[반별 총점높은 순으로 정렬]");
        Collections.sort(list, new ClassTotalComparator()); // 반, 총점 순으로 정렬
        printList(list);

        System.out.println();

        System.out.println("[반, 번호 순으로 정렬]");
        Collections.sort(list, new ClassStudentNoComparator()); // 반, 번호 순으로 정렬
        printList(list);
    }

    public static void printList(List<Student3> list) {
        System.out.println("이름\t반\t번호\t국어\t수학\t영어\t총점 ");
        System.out.println("====================================================");

        for(Student3 s : list) {
            System.out.println(s);
        }

        System.out.println("====================================================");
    }
}

class Student3 implements Comparable<Student3> {
    String name = "";
    int classNo = 0;
    int studentNo = 0;
    int koreanScore = 0;
    int mathScore = 0;
    int englishScore = 0;

    int total = 0;

    Student3(String name, int classNo, int studentNo, int koreanScore, int mathScore, int englishScore) {
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

    public int compareTo(Student3 obj) {
        return obj.total - this.total;
//             return this.name.compareTo(obj.name); // 이름기준으로 오름차순 정렬
    }
/* 제네릭스(Generics) 사용하지 않은 버젼
      public int compareTo(Object obj) {
            int result = -1;

            if(obj instanceof Student) {
                  Student tmp = (Student)obj;

                  result = tmp.total - this.total;
            }

            return result;
      }
*/
} // end of class Student

class ClassTotalComparator implements Comparator<Student3> {
    public int compare(Student3 s1, Student3 s2) {
//        코드를 완성하세요.
        int result = s1.classNo - s2.classNo;
        if (result == 0) {
            result = s2.total - s1.total;
        }
        return result;
    }
}

class ClassStudentNoComparator implements Comparator<Student3> {
    public int compare(Student3 s1, Student3 s2) {
//        코드를 완성하세요.
//        int result = s1.classNo - s2.classNo;
        int result = Integer.compare(s1.classNo, s2.classNo);
        if (result == 0) {
            result = Integer.compare(s1.studentNo, s2.studentNo);
        }
        return result;
    }
}

 

//[실행결과]
//[반별 총점높은 순으로 정렬]
//이름    반     번호    국어    수학    영어    총점
//====================================================
//자바짱  1       1       100     100     100     300   <----- 1반 1등
//킹왕짱  1       2       100     60      90      250
//최고수  1       3       100     80      60      240
//이원구  2       2       90      90      90      270    <------ 2반 1등
//일지매  2       3       70      80      100     250
//변강쇠  2       4       80      80      85      245
//홍길동  2       1       50      80      100     230
//남궁성  3       2       100     100     100     300  <------- 3반 1등
//왕자바  3       1       90      100     80      270
//자바왕  3       3       70      100     100     270
//====================================================

//[반, 번호 순으로 정렬]
//이름    반     번호    국어    수학    영어    총점
//====================================================
//자바짱  1       1       100     100     100     300
//킹왕짱  1       2       100     60      90      250
//최고수  1       3       100     80      60      240
//홍길동  2       1       50      80      100     230
//이원구  2       2       90      90      90      270
//일지매  2       3       70      80      100     250
//변강쇠  2       4       80      80      85      245
//왕자바  3       1       90      100     80      270
//남궁성  3       2       100     100     100     300
//자바왕  3       3       70      100     100     270
//====================================================