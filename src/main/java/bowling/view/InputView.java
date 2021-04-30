package bowling.view;

import java.util.Scanner;

public class InputView {
    private final static String GET_PLAYER_NAME = "플레이어 이름은(3 english letters)? : ";
    private final static String GET_FRAME_SCORE = "프레임 투구 : ";

    private final static Scanner scanner = new Scanner(System.in);

    public static String getPlayerName() {
        System.out.print(GET_PLAYER_NAME);
        return scanner.nextLine();
    }

    public static int getScore(int nowFrame) {
        StringBuilder sb = new StringBuilder();
        sb.append(nowFrame)
                .append(GET_FRAME_SCORE);
        System.out.print(sb);
        return Integer.parseInt(scanner.nextLine());
    }
}
