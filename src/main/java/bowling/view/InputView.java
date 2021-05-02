package bowling.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public String player() {
        System.out.printf("플레이어 이름은(3 english letters)?: ");
        return scanner.next();
    }

    public int pitch(int frameNo) {
        System.out.printf("%d프레임 투구 : ", frameNo);
        return scanner.nextInt();
    }
}
