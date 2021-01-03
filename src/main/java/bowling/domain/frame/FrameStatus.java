package bowling.domain.frame;

import bowling.bowlingexception.InvalidFrameStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum FrameStatus {

    START((List<DownedPin> downedPins) -> downedPins.size() == 0, (List<DownedPin> downedPins) -> true),
    STRIKE((List<DownedPin> downedPins) -> downedPins.size() == 1, (List<DownedPin> downedPins) -> downedPins.get(0).isStrike()),
    ON_FRAME((List<DownedPin> downedPins) -> downedPins.size() == 1, (List<DownedPin> downedPins) -> !downedPins.get(0).isStrike()),
    SPARE((List<DownedPin> downedPins) -> downedPins.size() == 2, (List<DownedPin> downedPins) -> downedPins.get(0).isSpare(downedPins.get(1))),
    MISS((List<DownedPin> downedPins) -> downedPins.size() == 2, (List<DownedPin> downedPins) -> !downedPins.get(0).isSpare(downedPins.get(1)));

    private final Predicate<List<DownedPin>> currentPitches;
    private final Predicate<List<DownedPin>> isOnSpecialCondition;

    FrameStatus(Predicate<List<DownedPin>> currentPitches, Predicate<List<DownedPin>> isOnSpecialCondition) {
        this.currentPitches = currentPitches;
        this.isOnSpecialCondition = isOnSpecialCondition;
    }

    public static FrameStatus decideStatus(List<DownedPin> downedPins) {
        return Arrays.stream(FrameStatus.values())
                .filter(status -> status.currentPitches.test(downedPins))
                .filter(status -> status.isOnSpecialCondition.test(downedPins))
                .findAny()
                .orElseThrow(InvalidFrameStatusException::new);
    }

    public static boolean isEnd(List<DownedPin> downedPins) {
        FrameStatus status = decideStatus(downedPins);

        return status == STRIKE || status == SPARE || status == MISS;
    }

    public static void record(List<DownedPin> downedPins, int record) {
        FrameStatus status = decideStatus(downedPins);

        if (isEnd(downedPins)) {
            throw new InvalidFrameStatusException();
        }

        if (status == ON_FRAME) {
            downedPins.add(downedPins.get(0).fromPreviousPitch(record));
            return;
        }

        downedPins.add(DownedPin.fromNumber(record));
    }
}
