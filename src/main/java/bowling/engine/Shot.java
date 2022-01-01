package bowling.engine;

public interface Shot extends IntWrapper {
    boolean isClear();
    boolean isSpare();
    boolean isSpareWith(Shot previous);
    Shot add(Shot other);
    Score score();
    BonusScores bonusScore();

    default boolean notEquals(Object other) {
        return !equals(other);
    }

}
