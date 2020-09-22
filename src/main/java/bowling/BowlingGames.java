package bowling;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGames {

    private static final int START_ENTRY = 0;
    private final List<BowlingGame> bowlingGames;
    private BowlingGame currentBowlingGame;

    private BowlingGames(int totalFrames, List<String> names) {
        this.bowlingGames = IntStream.range(0, names.size())
                .mapToObj(entry -> BowlingGame.of(totalFrames, names.get(entry), entry))
                .collect(Collectors.toList());
        this.currentBowlingGame = this.bowlingGames.get(START_ENTRY);
    }

    public static BowlingGames of(int totalFrames, List<String> names) {
        return new BowlingGames(totalFrames, names);
    }

    public String getCurrentPlayerName() {
        return currentBowlingGame.getPlayerName();
    }

    public void roundBowlingGames(Consumer<BowlingGame> consumer) {
        bowlingGames.forEach(consumer);
    }

    public void bowlCurrentEntry(Pin pin) {
        if (isAllFinished()) {
            throw new IllegalStateException("모든 프레임이 완료된 상태 입니다.");
        }

        currentBowlingGame.bowl(pin);
        if (currentBowlingGame.isFinished()) {
            shiftCurrentBowlingGame();
            return;
        }

        if (currentBowlingGame.hasNextFrameAndIsCurrentFrameFinished()) {
            currentBowlingGame.shiftCurrentFrame();
            shiftCurrentBowlingGame();
        }
    }

    public boolean isAllFinished() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::isFinished);
    }

    private void shiftCurrentBowlingGame() {
        if (isAllFinished()) {
            return;
        }

        int entry = currentBowlingGame.getEntry();
        BowlingGame nextBowlingGame = getNextBowlingGame(entry);
        while (nextBowlingGame.isFinished()) {
            nextBowlingGame = getNextBowlingGame(nextBowlingGame.getEntry());
        }

        currentBowlingGame = nextBowlingGame;
    }

    private BowlingGame getNextBowlingGame(int currentEntry) {
        int nextEntry = (currentEntry + 1) % bowlingGames.size();
        return bowlingGames.get(nextEntry);
    }
}
