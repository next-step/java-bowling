package bowling;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Pins;
import bowling.domain.state.State;
import bowling.domain.user.Users;
import bowling.view.InputView;
import bowling.view.ResultView;

/**
 * Created : 2020-12-16 오전 7:52
 * Developer : Seo
 */
public class Application {
    public static final int FINAL_FRAME = 10;
    public static final int INDEX_ONE = 1;
    public static final String STRIKE_SYMBOL = "X";
    public static final String SPARE_SYMBOL = "/";
    public static final int INDEX_ZERO = 0;

    public static void main(String[] args) {
        Users users = InputView.getUsers();
        Frames frames = new Frames();
        ResultView.init(frames, users);
        bowling(frames, users);
    }

    private static void bowling(Frames frames, Users users) {
        for (int frameNo = INDEX_ZERO; frameNo < FINAL_FRAME; frameNo++) {
            usersStroke(frameNo, frames, users);
            frames.next(frameNo);
        }
    }

    private static void usersStroke(int frameNo, Frames frames, Users users) {
        for (int userIndex = INDEX_ZERO; userIndex < users.getUsers().size(); userIndex++) {
            Pins pins = InputView.getPins(users.getUsers().get(userIndex).getName());
            Frame frame = frames.get(frameNo);
            frame.bowl(pins);
            ResultView.print(frames, users);
            secondStroke(frameNo, frames, users, userIndex);
            thirdStroke(frameNo, frames, users, userIndex);
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

        // 10프레임
        if (frameNo == FINAL_FRAME - INDEX_ONE) {
            Pins secondPins = InputView.getPins(users.getUsers().get(userIndex).getName());
            frame.secondBowl(userIndex, state, secondPins);
            ResultView.last(frames, users);
        }
    }

    private static void thirdStroke(int frameNo, Frames frames, Users users, int userIndex) {
        Frame frame = frames.get(frameNo);
        State state = frame.getLastState();

        if (frame instanceof FinalFrame) {
            thirdAtLastStroke(frames, users, userIndex, frame, state);
        }
    }

    private static void thirdAtLastStroke(Frames frames, Users users, int userIndex, Frame frame, State state) {
        if (state.getSymbol().contains(STRIKE_SYMBOL) || state.getSymbol().contains(SPARE_SYMBOL)) {
            Pins thirdPins = InputView.getPins(users.getUsers().get(userIndex).getName());
            frame.thirdBowl(userIndex, state, thirdPins);
            ResultView.last(frames, users);
        }
    }
}
