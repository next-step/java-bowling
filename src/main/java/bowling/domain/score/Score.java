package bowling.domain.score;

public interface Score {

    Score saveNextPin(Pin pin);

    boolean isNextStorable();

}
