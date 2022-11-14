package bowling.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Bowlings {

    private final Players players;

    private Position position;

    public Bowlings(List<Username> usernames) {
        if (isDuplicateUsernames(usernames)) {
            throw new IllegalArgumentException("사용자 이름은 중복이 존재해서는 안됩니다.");
        }
        this.players = new Players(usernames.stream()
                .map((username) -> new Player(username, new Bowling()))
                .collect(Collectors.toList()));
        this.position = new Position(0);
    }

    private boolean isDuplicateUsernames(List<Username> usernames) {
        return new HashSet<>(usernames).size() != usernames.size();
    }

    public boolean isFinish() {
        return players.isFinish();
    }

    private Player findCurrentPlayer() {
        return players.findPlayerByPosition(position);
    }

    public ScoreResult play(Integer knockDownPinNumber) {
        Player player = findCurrentPlayer();
        BowlingRound before = player.currentRound();
        ScoreResult scoreResult = player.bowl(knockDownPinNumber);
        BowlingRound after = player.currentRound();
        if (isNextUserTurn(before, after)) {
            position = position.next(players.size());
        }
        scoreResult.addUsername(player.getUsername());
        return scoreResult;
    }

    private boolean isNextUserTurn(BowlingRound before, BowlingRound after) {
        return !after.isSameRound(before) || after.isLastRoundEnd();
    }

    public Username currentPlayerName() {
        return findCurrentPlayer().getUsername();
    }

    public Bowling findBowlingByUsername(Username username) {
        return players.findBowlingByUsername(username);
    }

    public Players getPlayers() {
        return players;
    }

}
