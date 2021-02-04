package bowling.domain.frame.status;

import bowling.domain.frame.DownedPin;
import bowling.domain.score.Score;

public class Start extends OnPitching {

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public Status record(int downedPin) {
        if (DownedPin.fromNumber(downedPin).isStrike()) {
            return new Strike();
        }

        return new OnSecondPitch(downedPin);
    }

    @Override
    public Score addBonus(Score originalScore) {
        return originalScore;
    }
}
