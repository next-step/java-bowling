package bowling.refactoring.view;

import java.util.*;

public class InputView {

    private static final String PLAYER_TEXT = "플레이어 ";
    private static final String NAME_INPUT_TEXT = "의 이름은?(3 english letters): ";
    private static final String FRAME_SCORE_INPUT_TEXT = "'s turn : ";
    private static final String HOW_MANY_PEOPLE = "How many people? ";
    private static final Scanner sc = new Scanner(System.in);


    public static int inputPlayerCount(){
        System.out.print(HOW_MANY_PEOPLE);
        return sc.nextInt();
    }
    public static List<String> inputPlayerName(int count) {
        List<String> names = new ArrayList<>();
        for(int i=1;i<=count;i++){
            System.out.print(PLAYER_TEXT +i+ NAME_INPUT_TEXT);
            names.add(sc.next());
        }
        return names;
    }

    public static int inputFallenPinCounts(String name) {
        System.out.print(System.lineSeparator() + name + FRAME_SCORE_INPUT_TEXT);
        return sc.nextInt();
    }
}
