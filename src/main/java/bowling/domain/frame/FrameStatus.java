package bowling.domain.frame;

import bowling.domain.statusdetail.Miss;
import bowling.domain.statusdetail.Spare;
import bowling.domain.statusdetail.Status;
import bowling.domain.statusdetail.Strike;

import java.util.List;

public enum FrameStatus {
    STRIKE(new Strike()),
    SPARE(new Spare()),
    MISS(new Miss());

    private final Status statusDetail;

    FrameStatus(Status statusDetail) {
        this.statusDetail = statusDetail;
    }

    public static FrameStatus getStatus(List<DownedPin> downedPins) {
        if (NormalFrameProgress.getProgress(downedPins) == NormalFrameProgress.ON_FIRST_PITCH) {
            return MISS;
        }

        if (NormalFrameProgress.getProgress(downedPins) == NormalFrameProgress.ON_SECOND_PITCH
                && downedPins.get(0).isStrike()) {
            return STRIKE;
        }

        if (NormalFrameProgress.getProgress(downedPins) == NormalFrameProgress.END
                && downedPins.get(0).isSpare(downedPins.get(1))) {
            return SPARE;
        }

        return MISS;
    }

    public String interpretFrame(List<DownedPin> downedPins) {
        return statusDetail.interpretFrame(downedPins);
    }
}
