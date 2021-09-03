package bowling.step2;

import bowling.step2.domain.Lane;
import bowling.step2.inputView.InputView;
import bowling.step2.outputView.OutputView;

public class App {
    public static void main(String[] args) {
        String name = InputView.getName();
        Lane lane = Lane.of(name);

        int pitch;
        int currentNo = 1;

        do {
            pitch = InputView.getPitch(currentNo);
            lane.pitch(pitch);
            OutputView.showResult(lane);

            if (lane.frameFinished()) {
                lane.nextFrame();
                currentNo++;
            }
        } while (currentNo <= 10);
    }
}
