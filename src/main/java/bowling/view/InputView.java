package bowling.view;

import bowling.domain.Player;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static Player inputPlayers() {
        System.out.println("플레이어 이름은(3 english letters)?:");
        return new Player(scanner.nextLine());
    }

    public static int inputThrowCount(int frameNo) {
        StringBuilder sb = new StringBuilder();
        sb.append(frameNo)
                .append("프레임 투구 : ");
        System.out.println(sb);
        return scanner.nextInt();
    }
}
