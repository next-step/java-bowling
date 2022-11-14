package bowling.domain;

public class Player {

    private final Username username;
    private final Bowling bowling;


    public Player(Username username, Bowling bowling) {
        if (username == null || bowling == null){
            throw new IllegalArgumentException("플레이어는 이름과 자신이 플레이할 볼링 게임을 가지고 있어야 합니다.");
        }
        this.username = username;
        this.bowling = bowling;
    }

    public boolean isFinish() {
        return bowling.isFinish();
    }

    public ScoreResult bowl(int knownDownPins) {
        return bowling.play(knownDownPins, username);
    }

    public BowlingRound currentRound() {
        return bowling.currentRound();
    }

    public boolean isSameUsername(Username username) {
        return this.username.equals(username);
    }

    public Username getUsername() {
        return username;
    }

    public Bowling getBowling() {
        return bowling;
    }
}
