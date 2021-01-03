package bowling.domain.bowl;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.*;

/**
 * Created : 2020-12-22 오후 2:29
 * Developer : Seo
 */
public class FinalFirstBowl implements Bowl {
    @Override
    public State stroke(Pins pins) {
        Score score = new Score(pins);

        if (score.isStrike()) {
            return new FinalFirstState(new Strike());
        }
        if (score.isGutter()) {
            return new FinalFirstState(new Gutter(pins));
        }
        return new FinalFirstState(new Miss(pins));
    }
}
