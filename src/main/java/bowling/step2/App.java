package bowling.step2;

import bowling.step2.domain.Lane;
import bowling.step2.domain.TryNo;
import bowling.step2.inputView.InputView;
import bowling.step2.outputView.OutputView;

public class App {
    public static void main(String[] args) {
        String name = InputView.getName();
        Lane lane = Lane.of(name);

        int count;

        for (int i = 1; i <= 10; i++) {
            count = InputView.getPitch(i);
            lane.pitch(i, TryNo.FIRST, count);
            OutputView.printNow(lane);
            if (i != 10 && count == 10) continue;

            count = InputView.getPitch(i);
            lane.pitch(i, TryNo.SECOND, count);
            OutputView.printNow(lane);
        }

        if (lane.isNotAbleToPitchAdditional()) {
            return;
        }

        count = InputView.getPitch(10);
        lane.pitch(10, TryNo.ADDITIONAL, count);
        OutputView.printNow(lane);
    }
}
