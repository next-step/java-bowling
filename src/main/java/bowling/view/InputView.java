package bowling.view;

import bowling.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final static String GET_PLAYER_NAME = "플레이어 의 이름은(3 english letters)? : ";
    private final static String GET_FRAME_SCORE = "프레임 투구 : ";
    private final static String HOW_MANY_PEOPLE = "How many people? : ";

    private final static Scanner scanner = new Scanner(System.in);

    public static String getPlayerName() {
        System.out.print(GET_PLAYER_NAME);
        return scanner.nextLine();
    }

    public static Integer getHowMany() {
        System.out.print(HOW_MANY_PEOPLE);
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<User> getPlayerNames(int howMany) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            StringBuilder sb = new StringBuilder(GET_PLAYER_NAME);
            sb.insert(5, i + 1);
            System.out.print(sb);
            users.add(new User(scanner.nextLine()));
        }
        return users;
    }

    public static int getScore(int nowFrame) {
        StringBuilder sb = new StringBuilder();
        sb.append(nowFrame)
                .append(GET_FRAME_SCORE);
        System.out.print(sb);
        return Integer.parseInt(scanner.nextLine());
    }
}