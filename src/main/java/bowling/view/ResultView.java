package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.pins.Pins;
import bowling.domain.pins.Status;
import bowling.domain.referee.Referee;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class ResultView {

    private ResultView() {
    }

    public static final String SPLIT_BAR = " | ";

    public static void showCurrentFrameInfo(int frame) {
        System.out.println(frame + "프레임 투구 ");
    }

    public static void showHead() {
        System.out.println(SPLIT_BAR + "NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void showPlayerFrame(Player player, Referee referee) {
        Frames frames = referee.framesOfPerson(player);
        for (Frame frame : frames) {
            showCurrentFrameScore(frame.pins());
        }
        System.out.println();
    }

    public static void showCurrentFrameScore(Pins pins) {
        Status status = pins.status();

        if (status == Status.READY) {
            return;
        }

        if (status == Status.MISS) {
            System.out.print(pins.numberOfPinDowns().score() + SPLIT_BAR);
            return;
        }

        System.out.print(status.getLetter() + SPLIT_BAR);
    }

    public static void showPersonNameOnBoard(Player person) {
        System.out.printf("%s%s%s", SPLIT_BAR, person.name(), SPLIT_BAR);
    }

    public static void showPlayerScore(Player player, Referee referee) {
        System.out.print(SPLIT_BAR + "    " + SPLIT_BAR);

        Frames frames = referee.framesOfPerson(player);
        Scores scores = frames.scores();

        Score total = Score.create();

        for (Score score : scores) {
            total = total.sumWithScores(score);

            if (score.checked()) {
                System.out.printf("%3d " + SPLIT_BAR, total.score());
            }
        }

        System.out.println();
    }
}
