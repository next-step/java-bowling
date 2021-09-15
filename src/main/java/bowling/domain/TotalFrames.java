package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TotalFrames {
    private final List<Frames> totalFrames;

    public TotalFrames(int size) {
        this.totalFrames = new ArrayList<>();
        IntStream.range(0, size)
                 .forEach(i -> this.totalFrames.add(new Frames()));
    }

    public boolean isGameFinish() {
        long finishedCount = totalFrames.stream()
                                        .filter(Frames::isFinish)
                                        .count();
        return finishedCount == totalFrames.size();
    }

    public Frames of(int playerIndex) {
        return totalFrames.get(playerIndex);
    }

    public void throwBall(int playerIndex, int pitchingCount) {
        of(playerIndex).throwBalls(pitchingCount);
    }

    public boolean isNextPlayer(int playerIndex) {
        return of(playerIndex).isNext();
    }
}
