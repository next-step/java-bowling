package bowling.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public String getName() {
        System.out.printf("3 english letters?: ");
        return scanner.nextLine();
    }

    public int getPoint(int frameCount) {
        System.out.printf(String.format("%d 프레임 투구 : ", frameCount));
        return Integer.parseInt(scanner.nextLine().trim());
    }

}
