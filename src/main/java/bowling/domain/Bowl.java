package bowling.domain;

public interface Bowl {

    BowlResult bowl(int numberOfPins);
    int getBowlCount();
    int getTotalNumberOfPins();

}
