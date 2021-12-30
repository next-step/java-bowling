package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlingGames {
    private final List<BowlingGame> bowlingGames;
    private FrameIndex currentFrameIndex;

    private BowlingGames(List<BowlingGame> bowlingGames) {
        this(bowlingGames, FrameIndex.first());
    }

    private BowlingGames(List<BowlingGame> bowlingGames, FrameIndex currentFrameIndex) {
        validate(bowlingGames, currentFrameIndex);
        this.bowlingGames = new ArrayList<>(bowlingGames);
        this.currentFrameIndex = currentFrameIndex;
    }

    public static BowlingGames create(Players players) {
        return players.getPlayers()
                .stream()
                .map(BowlingGame::create)
                .collect(Collectors.collectingAndThen(Collectors.toList(), BowlingGames::new));
    }

    private void validate(List<BowlingGame> bowlingGames, FrameIndex currentFrameIndex) {
        validateBowlingGames(bowlingGames);
        validateCurrentFrameIndex(currentFrameIndex);
    }

    private void validateBowlingGames(List<BowlingGame> bowlingGames) {
        if (Objects.isNull(bowlingGames)) {
            throw new IllegalArgumentException("전달된 볼링게임들이 null 입니다.");
        }
        if (bowlingGames.isEmpty()) {
            throw new IllegalArgumentException("전달된 볼링게임들이 비어있습니다.");
        }
    }

    private void validateCurrentFrameIndex(FrameIndex frameIndex) {
        if (Objects.isNull(frameIndex)) {
            throw new IllegalArgumentException("전달된 현재 프레임 인덱스가 null 입니다.");
        }
    }

    public List<BowlingGame> getBowlingGames() {
        return Collections.unmodifiableList(bowlingGames);
    }

    public boolean hasNextPitching() {
        return bowlingGames.stream()
                .anyMatch(BowlingGame::hasNextPitching);
    }

    public FrameIndex getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    public void increaseFrameIndex() {
        if (isCurrentFrameEnded()) {
            currentFrameIndex = currentFrameIndex.next();
        }
    }

    private boolean isCurrentFrameEnded() {
        return bowlingGames.stream()
                .noneMatch(bowlingGame -> bowlingGame.hasFrameInProgress(currentFrameIndex));
    }
}
