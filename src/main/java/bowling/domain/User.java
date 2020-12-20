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

    private final String name;
    private State state;

    public User(String name) {
        validate(name);
        this.name = name;
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

    public void bowl(ResultView resultView) {
        Bowl firstBowl = new FirstBowl();
        this.state = firstBowl.stroke(InputView.getPins(this.name));
        if (this.state instanceof Miss) {
            Score firstScore = this.state.getScore();
            Bowl secondBowl = new SecondBowl(firstScore);
            this.state = secondBowl.stroke(InputView.getPins(this.name));
        }
        if (this.state instanceof Gutter) {
            Score firstScore = this.state.getScore();
            Bowl secondBowl = new SecondBowl(firstScore);
            this.state = secondBowl.stroke(InputView.getPins(this.name));
        }
        resultView.print();
    }

    public State getState() {
        return state;
    }
}
