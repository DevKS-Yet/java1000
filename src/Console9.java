//[문제9] 디렉토리를 변경하는 cd명령을 구현하라. 명령어의 형식은 'cd DIRECTORY'이며
//DIRECTORY는 현재 디렉토리의 하위 디렉토리이거나 현재 디렉토리를 의미하는 '.' 또는
//조상 디렉토리를 의미하는 '..'이 될 수 있다.(실행결과 참고)

//[예제ConsoleEx9.java]

import java.io.*;
import java.util.*;
import java.util.regex.*;

class Console9 {
    static String[] argArr;                         // 입력한 매개변수를 담기위한 문자열배열
    static LinkedList q = new LinkedList(); // 사용자가 입력한 내용을 저장할 큐(Queue)
    static final int MAX_SIZE = 5;              // Queue에 최대 5개까지만 저장되도록 한다.

    static File curDir;

    static {
        try {
            curDir = new File(System.getProperty("user.dir"));
        } catch(Exception e) {}
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in); // 한번만 생성해서 재사용하면 되므로 반복문 밖으로 이동

        while(true) {
            try {
                String prompt = curDir.getCanonicalPath() + ">>";
                System.out.print(prompt);

                // 화면으로부터 라인단위로 입력받는다.
                String input = s.nextLine();

                save(input);

                input = input.trim(); // 입력받은 값에서 불필요한 앞뒤 공백을 제거한다.
                argArr = input.split(" +");

                String command = argArr[0].trim();

                if("".equals(command)) continue;

                command = command.toLowerCase(); // 명령어를 소문자로 바꾼다.

                if(command.equals("q")) { // q 또는 Q를 입력하면 실행종료한다.
                    System.exit(0);
                } else if(command.equals("history")) {
                    history();
                } else if(command.equals("dir")) {
                    dir();
                } else if(command.equals("type")) {
                    type();
                } else if(command.equals("find")) {
                    find();
                } else if(command.equals("find2")) {
                    find2();
                } else if(command.equals("cd")) {
                    cd();
                } else {
                    for(int i=0; i < argArr.length;i++) {
                        System.out.println(argArr[i]);
                    }
                }
            } catch(Exception e) {
                System.out.println("입력오류입니다.");
            }
        } // while(true)
    } // main

    public static void save(String input) {
        if (input==null || input.equals("")) return;
        q.add(input);
        if (q.size() > MAX_SIZE) q.remove();
    }

    public static void history() {
        q.stream().forEach(System.out::println);
    }

    public static void dir() {
        switch (argArr.length) {
            case 1 :
                for (File f : curDir.listFiles()) {
                    if (curDir.isDirectory()) System.out.println("[" + f.getName() + "]");
                    else f.getName();
                }
                break;
            case 2 :
                String pattern = argArr[1].toUpperCase();
                pattern = pattern.replace(".", "\\.");
                pattern = pattern.replace("*", ".*");
                pattern = pattern.replace("?", ".{1}");
                Pattern p = Pattern.compile(pattern);
                for (File f : curDir.listFiles()) {
                    String tmp = f.getName().toUpperCase();
                    Matcher m = p.matcher(tmp);
                    if (m.matches()) {
                        if (f.isDirectory()) System.out.println("[" + f.getName() + "]");
                        else f.getName();
                    }
                }
        }
    } // dir()

    public static void type() throws IOException {
        if (argArr.length != 2) {
            System.out.println("Usage : type FILE_NAME");
            return;
        }
        String fileName = argArr[1];
        File tmp = new File(curDir, fileName);
        if (tmp.exists()) {
            FileReader fr = new FileReader(tmp);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            for (int i=1; (line=br.readLine())!=null; i++) {
                System.out.println(line);
            }
        } else System.out.println(fileName + " 존재하지 않는 파일입니다.");
        return;
    }


    public static void find() throws IOException {
        if(argArr.length!=3) {
            System.out.println("USAGE : find KEYWORD FILE_NAME");
            return;
        }

        String keyword = argArr[1];
        String fileName = argArr[2];

        File tmp = new File(curDir, fileName);

        if(tmp.exists()) {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String line = "";

            for(int i=1;(line = br.readLine())!=null;i++) {
                // keyword를 포함한 라인을 출력한다.
                if(line.indexOf(keyword)!=-1)
                    System.out.println(i+":"+line);
            }
        } else {
            System.out.println(fileName + " 존재하지 않는 파일입니다.");
        }
        return;
    }

    public static void find2() throws IOException {
        if(argArr.length!=3) {
            System.out.println("USAGE : find2 KEYWORD FILE_NAME");
            return;
        }

        String keyword = argArr[1];
        String pattern = argArr[2];

        pattern = pattern.toUpperCase();
        pattern = pattern.replace(".","\\.");
        pattern = pattern.replace("*",".*");
        pattern = pattern.replace("?",".{1}");

        Pattern p = Pattern.compile(pattern);

        for(File f : curDir.listFiles()) {
            String tmp = f.getName().toUpperCase();
            Matcher m = p.matcher(tmp);

            if(m.matches()) {
                if(f.isDirectory()) continue;

                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String line = "";

                System.out.println("----------------"+f.getName());
                for(int i=1;(line = br.readLine())!=null;i++) {
                    // keyword를 포함한 라인을 출력한다.
                    if(line.indexOf(keyword)!=-1)
                        System.out.println(i+":"+line);
                }
            }
        } // for

        return;
    }

    public static void cd() {

        if(argArr.length==1) {
            System.out.println(curDir);
            return;
        } else if(argArr.length > 2) {
            System.out.println("USAGE : cd directory");
            return;
        }

        String subDir = argArr[1];

//         다음의 코드를 완성하세요.
//         1. 입력된 디렉토리(subDir)가 ".."이면,
        if ("..".equals(subDir)) {
//              1.1  현재 디렉토리의 조상 디렉토리를 얻어서 현재 디렉토리로 지정한다.
//                    (File클래스의 getParentFile()을 사용)
            if (curDir.getParentFile() == null) {
                System.out.println("유효하지 않은 디렉토리입니다.");
            } else {
                curDir = curDir.getParentFile();
            }
        } else if (".".equals(subDir)) {
//         2. 입력된 디렉토리(subDir)가 "."이면,
//             단순히 현재 디렉토리의 경로를 화면에 출력한다.
            System.out.println(curDir);
        } else {
//         3. 1 또는 2의 경우가 아니면, 입력된 디렉토리(subDir)가 현재 디렉토리의 하위디렉토리인지 확인한다.
//             3.1 확인결과가 true이면, 현재 디렉토리(curDir)을 입력된 디렉토리(subDir)로 변경한다.
//             3.2 확인결과가 false이면, "유효하지 않은 디렉토리입니다."고 화면에 출력한다.
            File newDir = new File(curDir, subDir);
            if (newDir.exists() && newDir.isDirectory()) curDir = newDir;
            else System.out.println("유효하지 않는 디렉토리입니다.");
        }

    } // cd()
} // class

 

//[실행결과] - 현재 작업중인 폴더가 C:\java1000\work\Console일 경우
//C:\java1000\work\Console>>cd
//C:\java1000\work\Console
//C:\java1000\work\Console>>cd .
//C:\java1000\work\Console
//C:\java1000\work\Console>>cd ..
//C:\java1000\work>>cd console
//C:\java1000\work\Console>>q