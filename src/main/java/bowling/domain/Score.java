package bowling.domain;

public interface Score {

    boolean isNextScore();

    Score firstScore(Pins pins);

    Score nextScore(Pins pins);

}
