package bowling.domain;

public enum Bonus {
    STRIKE(2), SPARE(1);

    private int bonus;

    Bonus(int bonus){
        this.bonus = bonus;
    }
}
