package bowling.domain;

public interface HitHistory {

    void add(int hit);

    boolean isStrike();

    boolean isSpare();

    boolean isMiss();

}
