package bowling.view;

import bowling.domain.Player;

import java.util.Scanner;

public class InputView {

    private static final String INPUT_NAME_MESSAGE = "플레이어 이름은(3 english letters)?:";
    private static final String INPUT_SCORE_MESSAGE = "프레임 투구 :";

    Scanner sc = new Scanner(System.in);

    public Player displayPlayerNameInputMessage() {
        System.out.print(INPUT_NAME_MESSAGE);
        return new Player(sc.next());
    }

    public int displayScoreInputMessage(int frameCount) {
        frameCount++;
        System.out.print(frameCount + INPUT_SCORE_MESSAGE);
        return sc.nextInt();
    }

}
