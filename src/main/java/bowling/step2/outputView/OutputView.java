package bowling.step2.outputView;

import bowling.step2.domain.Frame;
import bowling.step2.domain.Lane;
import bowling.step2.domain.PitchResult;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printNow(Lane lane) {
        List<Frame> scoreOfLane = lane.getScoreOfLane();
        List<List<PitchResult>> collect = scoreOfLane.stream()
                .map(PitchResult::findResultOf)
                .collect(Collectors.toList());

        System.out.println(collect);
    }
}
