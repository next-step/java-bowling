package View;

public interface BowlingScore {
    boolean bowl(int score);

    boolean nowBowlable();

    boolean isOverPoint(int currentScore);

    int sumScore();

    String framePoint();

    int getPointScore(int position);

    int getPointExistCount();
}
