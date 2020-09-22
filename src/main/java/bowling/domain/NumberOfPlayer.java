package bowling.domain;

public class NumberOfPlayer {

    public static final int MIN_NUMBER_OF_PLAYER = 1;

    private final int numberOfPlayer;

    private NumberOfPlayer(int numberOfPlayer) {
        if (numberOfPlayer < MIN_NUMBER_OF_PLAYER) {
            throw new IllegalArgumentException("한명 이상 입력해야 합니다.");
        }
        this.numberOfPlayer = numberOfPlayer;
    }

    public static NumberOfPlayer of(int numberOfPlayer) {
        return new NumberOfPlayer(numberOfPlayer);
    }

    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

}
