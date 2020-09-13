package bowling;

import bowling.frame.domain.Frame;
import bowling.frame.domain.Frames;
import bowling.pin.domain.Pins;
import bowling.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {
        String player = InputView.inputPlayer();

        while (Frames.TOTAL_FRAME_NUMBER < 11) {
            Pins pins = null;
            List<Frame> frame = new ArrayList<>();
            Frames frames = new Frames(frame);

            for (int i = 1; i <= Frames.PITCH_COUNT; i++) {
                String point = InputView.inputPitch(i);
                pins = Pins.playPitch(point, i);
                Frames.addFrame(frame, pins);
                System.out.println(frames);
            }
        }
    }
}
