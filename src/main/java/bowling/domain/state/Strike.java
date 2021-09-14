package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import bowling.exception.BowlingStateException;

public class Strike extends State {
    public Strike() {
        firstPin = new Pin(10);
        score = Score.ofStrike();
    }

    @Override
    public Score getScore(){
        return Score.ofStrike();
    }

    @Override
    public State bowl(int pin) {
        throw new BowlingStateException("다음 프레임에서 투구해주세요.");
    }

    @Override
    public boolean finish() {
        return true;
    }
}
