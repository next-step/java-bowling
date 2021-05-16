package bowling.domain.frame;

public abstract class Frame {

    public static final int INIT_SCORE = 0;

    public static final int STRIKE_BONUS = 2;
    public static final int SPARE_BONUS = 1;
    public static final int NO_BONUS = 0;

    public abstract void addPoint(int bonusPoint);

    public abstract void bowl(int pin);

    public abstract boolean isEnd();

    public abstract int bonusAmount();
}
