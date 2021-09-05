package bowling.step2;

import bowling.step2.domain.Lane;
import bowling.step2.inputview.InputView;
import bowling.step2.outputview.OutputView;

public class App {
    public static void main(String[] args) {
        String name = InputView.getName();
        Lane lane = Lane.of(name);

        int currentNo = 1;

        do {
            int pitch = InputView.getPitch(currentNo);
            lane.pitch(pitch);
            OutputView.showResult(lane);

            if (lane.frameFinished()) {
                lane.nextFrame();
                currentNo++;
            }
        } while (currentNo <= 10);
    }
}
