package step4.view;

import step4.domain.Player;

import java.util.Scanner;

public class BowlingInputView implements InputView{
    private final static Scanner scanner = new Scanner(System.in);
    private final static String QUESTION_FRAME_PITCHES = "%s's turn :";
    public static final String QUESTION_PLAYER_NAME = "플레이어 %d의 이름은?(3 english letters)?:";
    public static final String HOW_MANY_PEOPLE = "How many people? ";

    @Override
    public String getPlayerName(int index) {
        System.out.printf((QUESTION_PLAYER_NAME) + "%n", index);
        return scanner.nextLine();
    }

    @Override
    public int getPitchesCount(Player player) {
        System.out.printf(QUESTION_FRAME_PITCHES, player.getName());
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public int getNumberOfParticipants() {
        System.out.println(HOW_MANY_PEOPLE);
        return Integer.parseInt(scanner.nextLine());
    }
}
