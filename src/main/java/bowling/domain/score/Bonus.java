package bowling.domain.score;

public enum Bonus {
    STRIKE(2), SPARE(1);

    private final int bonus;

    Bonus(int bonus){
        this.bonus = bonus;
    }
}
