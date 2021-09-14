package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lane {

    private final List<Frames> allFrames;
    private int turn;

    public Lane(final PlayerCount playerCount) {
        allFrames = Stream.generate(Frames::new)
                .limit(playerCount.count())
                .collect(Collectors.toList());
        turn = 0;
    }

    public Lane(final List<Frames> allFrames) {
        this.allFrames = allFrames;
        turn = 0;
    }

    public void addPitch(final Pitch pitch) {
        currentFrames().addPitch(pitch);
        nextTurnIfCurrentFrameEnd();
    }

    public List<Frames> allFrames() {
        return Collections.unmodifiableList(allFrames);
    }

    public String currentPlayerName(final Players players) {
        return players.getName(turn);
    }

    public boolean isAllEnd() {
        return allFrames.stream()
                .allMatch(Frames::isEnd);
    }

    private Frames currentFrames() {
        return allFrames.get(turn);
    }

    private void nextTurnIfCurrentFrameEnd() {
        if (currentFrames().isCurrentFrameEnd()) {
            turn = (turn + 1) % allFrames.size();
        }
    }

}
