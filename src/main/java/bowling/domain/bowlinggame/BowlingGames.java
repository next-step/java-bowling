package bowling.domain.bowlinggame;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 볼링 게임의 일급 컬렉션
 * 전체 게임 진행을 제어한다.
 */
public class BowlingGames {
    private final List<BowlingGame> bowlingGames;

    private BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public static BowlingGames of(List<Player> players) {
        return players.stream()
                .map(player -> new BowlingGame(Frames.of(), player))
                .collect(Collectors.collectingAndThen(Collectors.toList(), BowlingGames::new));
    }

    public boolean isOverAllGames() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::isOver);
    }

    public int size() {
        return bowlingGames.size();
    }

    public void addNextFrame(int index) {
        bowlingGames.get(index).addNextFrame();
    }

    public Frame getLastFrame(int index) {
        return bowlingGames.get(index).getLastFrame();
    }

    public Player getPlayer(int index) {
        return bowlingGames.get(index).getPlayer();
    }

    public List<BowlingGame> getBowlingGames() {
        return bowlingGames;
    }
}
