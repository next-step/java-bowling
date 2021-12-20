package bowling;

public class Player {

    private final String name;
    private final ScoreBoard scoreBoard;

    public Player(String name) {
        this(name, new ScoreBoard());
    }

    public Player(String name, ScoreBoard scoreBoard) {
        valid(name);

        this.name = name;
        this.scoreBoard = scoreBoard;
    }


    private void valid(String name) {
        if (name.length() != 3) {
            throw new IllegalArgumentException("플레이어의 이름은 3글자를 입력해주세요.");
        }
    }
}
