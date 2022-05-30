package bowling.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlingGames {
    private final List<BowlingGame> values;
    private FrameNumber frameNumber;

    public BowlingGames(List<BowlingGame> bowlingGames, FrameNumber frameNumber) {
        validateNullAndEmpty(bowlingGames, frameNumber);
        this.values = bowlingGames;
        this.frameNumber = frameNumber;
    }

    private void validateNullAndEmpty(List<BowlingGame> bowlingGames, FrameNumber frameNumber) {
        if (Objects.isNull(bowlingGames) || bowlingGames.isEmpty()) {
            throw new IllegalArgumentException("볼링게임들이 비어있습니다.");
        }

        if (Objects.isNull(frameNumber)) {
            throw new IllegalArgumentException("프레임 번호가 비어있습니다.");
        }
    }

    public BowlingGames(List<BowlingGame> bowlingGames) {
        this(bowlingGames, FrameNumber.first());
    }

    public static BowlingGames create(List<Player> players) {
        return players.stream()
                .map(BowlingGame::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), BowlingGames::new));
    }

    public boolean isNextPitching() {
        return values.stream()
                .anyMatch(BowlingGame::isNextPitching);
    }

    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    public void increaseFrameNumber() {
        if (isCurrentFrameEnded()) {
            frameNumber = frameNumber.next();
        }
    }

    private boolean isCurrentFrameEnded() {
        return values.stream()
                .noneMatch(bowlingGame -> bowlingGame.isFrameProgress(frameNumber));
    }

    public List<BowlingGame> getValues() {
        return values;
    }
}
