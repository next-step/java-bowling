package step3.view;

import step3.domain.Frame;

import java.util.Scanner;

public class BowlingInputView implements InputView{
    private final static Scanner scanner = new Scanner(System.in);
    private final static String QUESTION_FRAME_PITCHES = "%s 프레임 투구 :";
    public static final String QUESTION_PLAYER_NAME = "플레이어 이름은?(3 english letters)?:";

    @Override
    public String getPlayerName() {
        System.out.println(QUESTION_PLAYER_NAME);
        return scanner.nextLine();
    }

    @Override
    public int getPitchesCount(Frame frame) {
        System.out.printf(QUESTION_FRAME_PITCHES, frame.getFrameNo()+1);
        return Integer.parseInt(scanner.nextLine());
    }
}
