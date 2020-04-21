package seul.bowling.view;

import seul.bowling.domain.Frame;
import seul.bowling.domain.Frames;

import java.util.Scanner;

public class InputView {
    private static final String PEOPLE_NUMBER = "How many people?:\n";
    private static final String PLAYER_NAME = "플레이어 %d의 이름은?(3 english letters):\n";
    private static final String PLAYER_TURN = "%s's turn : %d\n";

    private Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String inputPeopleNumber() {
        System.out.println(PEOPLE_NUMBER);

        return scanner.nextLine();
    }

    public String inputPlayerName(int index) {
        System.out.printf(PLAYER_NAME, index);

        return scanner.nextLine();
    }

    public int inputClearPin(String name, Frames frames) {
        int lastFrameIndex = frames.currentFrameIndex();

        boolean endFrame = false;
        if (!frames.isEmpty()) {
            Frame frame = frames.getFrame(lastFrameIndex);
            endFrame = frame.endFrame();
        }

        if (endFrame) {
            lastFrameIndex++;
        }

        System.out.printf(PLAYER_TURN, name, ++lastFrameIndex);

        return scanner.nextInt();
    }
}
