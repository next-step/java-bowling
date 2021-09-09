package bowling.view;

import java.util.Scanner;

public class InputView {

    private InputView() {
    }

    public static String userName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("플레이어 이름은 : ");
        String name = sc.nextLine();
        return name;
    }

    public static int downPinsCount() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
