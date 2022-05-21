package bowling.ui;

import bowling.domain.Player;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Player promptPlayer() {
        System.out.print("플레이어 이름을 입력해주세요(3 english letters): ");
        return new Player(SCANNER.nextLine());
    }

    public static int promptPinNo(int frameNo) {
        System.out.print(frameNo + "프레임 투구 : ");
        return Integer.parseInt(SCANNER.nextLine());
    }
}
