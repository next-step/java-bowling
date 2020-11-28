package bowling.dto;

public class CountOfPinsDto {
    private final int countOfPins;

    public CountOfPinsDto(int countOfPins) {
        this.countOfPins = countOfPins;
    }

    public int getCountOfPins() {
        return countOfPins;
    }
}
