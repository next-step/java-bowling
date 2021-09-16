package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class AllFrames {
    private static final String ALL_FRAMES_IS_NULL_EXCEPTION_STATEMENT = "모든 플레이어의 프레임 데이터의 변수가 널입니다";
    private final List<Frames> allFrames;

    private AllFrames(List<Frames> allFrames, int size) {
        validate(allFrames);
        this.allFrames = new ArrayList<>();
        if (allFrames.isEmpty()) {
            IntStream.range(0, size)
                .forEach(i -> this.allFrames.add(Frames.from(new ArrayList<>())));
            return;
        }

        IntStream.range(0, size)
            .forEach(i -> this.allFrames.add(allFrames.get(i)));
    }

    public static AllFrames from(List<Frames> allFrames, int size) {
        return new AllFrames(allFrames, size);
    }

    private void validate(List<Frames> allFrames) {
        if (Objects.isNull(allFrames)) {
            throw new IllegalArgumentException(ALL_FRAMES_IS_NULL_EXCEPTION_STATEMENT);
        }
    }

    public boolean isGameFinish() {
        long finishedCount = allFrames.stream()
            .filter(Frames::isFinish)
            .count();

        return finishedCount == allFrames.size();
    }

    public Frames nthFramesOf(int nth) {
        return allFrames.get(nth);
    }

    public void throwBallOfNthPlayer(int playerNo, int downPinNumber) {
        nthFramesOf(playerNo).throwBalls(downPinNumber);
    }

    public boolean isNextOfNthPlayer(int playerNo) {
        return nthFramesOf(playerNo).isNext();
    }
}
