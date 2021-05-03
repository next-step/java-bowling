package bowling.domain.frame.state;

import bowling.domain.Score;

public class FirstPitch implements State {
    private int remainPins;

    public FirstPitch() {
        remainPins = 10;
    }

    @Override
    public State nextState() {
        if (remainPins == 0) {
            return new Strike();
        }
        return new SecondPitch(remainPins);
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public void bowl(int killedPins) {
        if (remainPins < killedPins) {
            throw new IllegalArgumentException("쓰러뜨릴려는 핀 개수가 남아 있는 핀 개수보다 큽니다.");
        }
        remainPins = remainPins - killedPins;
    }

    @Override
    public Score calculateScore() {
        return new Score(10 - remainPins, 0);
    }

    @Override
    public boolean isCalculable() {
        return true;
    }
}
