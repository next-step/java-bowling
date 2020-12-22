package bowling;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

/**
 * Created : 2020-12-16 오전 7:52
 * Developer : Seo
 */
public class Application {
    public static final int LAST_FRAME = 10;

    public static void main(String[] args) {
        Users users = InputView.getUsers();
        Frames frames = new Frames();
        ResultView.init(frames, users);
        bowling(frames, users);
    }

    private static void bowling(Frames frames, Users users) {
        for (int frameNo = 0; frameNo < LAST_FRAME; frameNo++) {
            usersStroke(frameNo, frames, users);
            frames.next(frameNo);
        }
    }

    private static void usersStroke(int frameNo, Frames frames, Users users) {
        for (int userIndex = 0; userIndex < users.getUsers().size(); userIndex++) {
            Pins pins = InputView.getPins(users.getUsers().get(userIndex).getName());
            Frame frame = frames.get(frameNo);
            frame.bowl(pins);
            ResultView.print(frames, users);
            secondStroke(frameNo, frames, users, userIndex);
        }
    }

    private static void secondStroke(int frameNo, Frames frames, Users users, int userIndex) {
        Frame frame = frames.get(frameNo);
        State state = frame.getLastState();
        if (!state.isFinished()) {
            Pins secondPins = InputView.getSecondPins(state, users.getUsers().get(userIndex).getName());
            frame.secondBowl(userIndex, state, secondPins);
            ResultView.print(frames, users);
        }
    }
}
