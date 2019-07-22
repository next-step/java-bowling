package domain.score;

import domain.Pins;

public class Score {

    public final static Score EMPTY = new Score(-1, BonusType.normal());

    private BonusType type;
    private int score;

    public Score(int score, BonusType type) {
        this.score = score;
        this.type = type;
    }

    public static Score of(int score) {
        return new Score(score, BonusType.normal());
    }

    public static Score of(Pins first, Pins second, BonusType type) {
        return new Score(sum(first, second), type);
    }

    private static int sum(Pins first, Pins second) {
        return first.value() + second.value();
    }

    private int addScore(int added) {
        return score + added;
    }

    public boolean hasBonus() {
        return type.hasBonus();
    }

    public int getValue() {
        return score;
    }

    public Score calculate(Score target) {
        return new Score(addScore(target.score),type.next());
    }
}
