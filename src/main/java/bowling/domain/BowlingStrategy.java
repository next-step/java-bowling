package bowling.domain;

public interface BowlingStrategy {

    Pin nextPin(int remain);
}
