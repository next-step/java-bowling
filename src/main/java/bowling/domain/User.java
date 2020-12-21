package bowling.domain;

import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created : 2020-12-16 오전 7:55
 * Developer : Seo
 */
public class User {
    public static final String NAME_PATTERN = "^([A-z]{3})$";
    public static final int PREVIOUS_INDEX = 1;

    private final String name;
    private final States states;

    public User(String name) {
        validate(name);
        this.name = name;
        this.states = new States();
    }

    private void validate(String name) {
        Pattern p = Pattern.compile(NAME_PATTERN);
        Matcher m = p.matcher(name);
        if (!m.matches()) {
            throw new IllegalArgumentException("3자 영문으로 입력해주십시요.");
        }
    }

    public String getName() {
        return name;
    }

    public void bowl(int frameNo) {
        Bowl firstBowl = new FirstBowl();
        State state = firstBowl.stroke(InputView.getPins(this.name));
        this.states.add(state);
        ResultView.print(frames, users);

        secondStroke(frameNo, state);
    }

    private void secondStroke(int frameNo, State state) {
        if (state instanceof Miss || state instanceof Gutter) {
            Score firstScore = state.getScore();
            Bowl secondBowl = new SecondBowl(firstScore);
            state = secondBowl.stroke(InputView.getPins(this.name));
            states.set(frameNo - PREVIOUS_INDEX, state);
            ResultView.print(frames, users);
        }
    }

    public State getState(int frameNo) {
        return this.states.getState(frameNo);
    }

    public int getScore(int frameNo) {
        State state = this.states.getState(frameNo);
        return state.getScore().getFrameScore();
    }

    public int getFirstScore(int frameNo) {
        State state = this.states.getState(frameNo);
        return state.getScore().getFirst().get();
    }
}
