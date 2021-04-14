package bowling.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public String getName() {
        System.out.printf("3 english letters?: ");
        return scanner.nextLine();
    }

    public int getPoint() {
        System.out.println("점수를 입력하세요");
        return Integer.parseInt(scanner.nextLine().trim());
    }

}
