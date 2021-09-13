package bowling.model.game;

import bowling.model.frame.Frame;
import bowling.model.player.Player;
import bowling.model.player.PlayerNumber;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class BowlingGames {
    private static final int FIRST_INDEX = 0;
    private static final int GAP_BETWEEN_SIZE_AND_LAST_INDEX = 1;
    private static final int INDEX_GAP_BETWEEN_CURRENT_AND_NEXT_GAME = 1;

    private PlayerNumber turnNumber;
    private final List<BowlingGame> games;

    public BowlingGames(List<Player> players) {
        validateNotEmpty(players);
        validateNotDuplicatedPlayerNumber(players);

        Player firstPlayer = players.get(FIRST_INDEX);
        turnNumber = firstPlayer.number();
        games = players.stream()
                .map(BowlingGame::new)
                .collect(toList());
    }

    public BowlingGames(List<Player> players, List<Frame> frames) {
        this(players);

        games.clear();
        players.forEach(player -> games.add(new BowlingGame(player, frames)));
    }

    private void validateNotEmpty(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("볼링 게임 진행을 위해서는 적어도 1명의 플레이어가 필요합니다.");
        }
    }

    private void validateNotDuplicatedPlayerNumber(List<Player> players) {
        List<PlayerNumber> playerNumbers = players.stream()
                .map(Player::number)
                .collect(toList());

        Set<PlayerNumber> notDuplicatedPlayerNumbers = new HashSet<>(playerNumbers);

        if (playerNumbers.size() != notDuplicatedPlayerNumbers.size()) {
            throw new IllegalArgumentException("중복되는 플레이어 번호가 존재합니다.");
        }
    }

    public String currentPlayerName() {
        return currentBowlingGame().playerName();
    }

    public boolean canPlayNext() {
        BowlingGame nextBowlingGame = nextBowlingGame();
        return nextBowlingGame.canPlayNextFrame() || !isLastTurnGame(nextBowlingGame);
    }

    private boolean isLastTurnGame(BowlingGame bowlingGame) {
        int lastGameIndex = games.size() - GAP_BETWEEN_SIZE_AND_LAST_INDEX;
        return games.indexOf(bowlingGame) == lastGameIndex;
    }

    public void play(int fallenPinCount) {
        currentBowlingGame().play(fallenPinCount);
        turnNumber = nextBowlingGame().playerNumber();
    }

    private BowlingGame nextBowlingGame() {
        BowlingGame currentBowlingGame = currentBowlingGame();
        if (!currentBowlingGame.isLastFramePitchOver()) {
            return currentBowlingGame;
        }

        int nextIndex = games.indexOf(currentBowlingGame()) + INDEX_GAP_BETWEEN_CURRENT_AND_NEXT_GAME;
        int nextBowlingGameIndex = nextIndex % games.size();
        return games.get(nextBowlingGameIndex);
    }

    private BowlingGame currentBowlingGame() {
        return games.stream()
                .filter(game -> game.isEqualPlayerNumber(turnNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("진행 중인 볼링 게임을 찾을 수 없습니다."));
    }

    public List<BowlingGame> games() {
        return games;
    }
}
