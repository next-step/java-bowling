package camp.nextstep.edu.rebellion.bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static List<String> getPlayers(int numberOfPlayers) {
        List<String> players = new ArrayList<>();
        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.print("플레이어 " + i + "의 이름을 입력해주세요 (영문자만, 최대 3글자) ");
            players.add(getLine());
        }
        return players;
    }

    public static int getHitScore(String playerName) {
        System.out.print(playerName + " 님 투구 차례 입니다 : ");
        return getInt();
    }

    public static int getNumberOfPlayers() {
        System.out.print("몇 명의 플레이어가 게임에 참가하나요 ");
        return getInt();
    }

    private static int getInt() {
        if (SCANNER.hasNextInt()) {
            return SCANNER.nextInt();
        }
        throw new IllegalArgumentException("입력 값이 없거나 숫자형식이 아닙니다");
    }

    private static String getLine() {
        if (SCANNER.hasNextLine()) {
            return SCANNER.next().trim();
        }
        throw new IllegalArgumentException("입력 값이 없습니다");
    }
}
