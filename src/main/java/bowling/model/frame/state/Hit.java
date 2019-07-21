package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;

public class Hit extends FirstState {

    private Hit(DownPin first) {
        super(first);
    }

    static State valueOf(DownPin first) {
        return new Hit(first);
    }

    @Override
    public Score getScore() {
        return Score.of(getFirst().count());
    }

    @Override
    public String printResult() {
        return getFirst().toString();
    }
}