package bowling.domain.bowl;

public interface Bowl {

    NormalBowlResult bowl(int numberOfPins);
    int getBowlCount();
    int getTotalNumberOfPins();

}
