package bowling.domain.bowl;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.Gutter;
import bowling.domain.state.Miss;
import bowling.domain.state.State;
import bowling.domain.state.Strike;

/**
 * Created : 2020-12-22 오후 2:29
 * Developer : Seo
 */
public class ThirdBowl implements Bowl {
    @Override
    public State stroke(Pins pins) {
        Score score = new Score(pins);
        if (score.isStrike()) {
            return new Strike();
        }
        if (score.isGutter()) {
            return new Gutter(pins);
        }
        return new Miss(pins);
    }
}
