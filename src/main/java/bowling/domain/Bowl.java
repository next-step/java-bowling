package bowling.domain;

public interface Bowl {

    NormalBowlResult bowl(int numberOfPins);
    int getBowlCount();
    int getTotalNumberOfPins();

}
