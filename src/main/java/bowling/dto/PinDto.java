package bowling.dto;

public class PinDto {
    private final int countOfDownPins;

    public PinDto(int countOfDownPins) {
        this.countOfDownPins = countOfDownPins;
    }

    public int getCountOfDownPins() {
        return countOfDownPins;
    }
}
