package bowling.dto;

public class PinDto {
    private final int countOfPins;

    public PinDto(int countOfPins) {
        this.countOfPins = countOfPins;
    }

    public int getCountOfPins() {
        return countOfPins;
    }
}
