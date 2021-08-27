package bowling.step2.outputView;

import bowling.step2.domain.Frame;
import bowling.step2.domain.Lane;

import java.util.List;

public class OutputView {
    public static void printNow(Lane lane) {
        List<Frame> scoreOfLane = lane.getScoreOfLane();
        System.out.println(scoreOfLane);
    }
}
