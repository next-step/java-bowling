package bowling;

import bowling.ball.domain.Ball;
import bowling.frame.domain.Frame;
import bowling.frame.domain.Frames;
import bowling.pin.domain.Pin;
import bowling.pin.domain.Pins;
import bowling.state.domain.State;
import bowling.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {
//        String player = InputView.inputPlayer();

        while (Frames.TOTAL_FRAME_NUMBER  < 11) {
            List<Pin> pinList = new ArrayList<>();
            List<Frame> frameList = new ArrayList<>();

            for (int i = 1; i <= Frames.PITCH_COUNT; i++) {
                String inputPitchPoint = InputView.inputPitch(Frames.TOTAL_FRAME_NUMBER);

                Ball ball = Ball.pitch(inputPitchPoint, i);
                Pin pin = Pin.pitchResult(pinList, ball);
                Pins pins = Pins.eachPitchResult(pinList, pin);

                Frames frames = Frames.nextFrame(frameList, pins);
                if (pinList.get(0).getState().equals(State.STRIKE)) {
                    Ball ball1 = Ball.pitch("0", 2);
                    Pin pin1 = Pin.pitchResult(pinList, ball1);
                    Pins pins1 = Pins.eachPitchResult(pinList, pin1);

                    Frames.nextFrame(frameList, pins1);
                    System.out.println(frames);
                    break;
                }
                System.out.println(frames);
            }
        }
    }
}
