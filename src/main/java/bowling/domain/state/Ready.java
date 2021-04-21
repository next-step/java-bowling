package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.apache.logging.log4j.util.Strings;

public class Ready implements State {

    private static final String INVALID_SCORE = "프레임이 종료된 후에 점수를 생성 할 수 있습니다.";

    @Override
    public State play(Pin fallenPin) {
        if (fallenPin.isGutter()) {
            return new FirstGutter();
        }

        if (fallenPin.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(fallenPin);
    }
    @Override
    public int getPitchCount() {
        return 0;
    }

    @Override
    public int getTotalCount() {
        return 0;
    }


    @Override
    public String toString() {
        return Strings.EMPTY;
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new IllegalStateException(INVALID_SCORE);
    }

}
