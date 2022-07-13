package bowling.Input;

import bowling.Frame;

import java.util.Scanner;

public class InputView {
    private String name;
    private static int count = 1;

    public static String question() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        while (name.length() != 3) {
            System.out.println("플레이어 이름은 3자로 입력해주세요!");
            name = scanner.nextLine();
        }

        return name;
    }

    public static Integer settingCount(Integer index) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(index + " 프레임 투구 : ");
        return scanner.nextInt();
    }
}
