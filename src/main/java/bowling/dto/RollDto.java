package bowling.dto;

public class RollDto {
    private final int countOfPins;

    public RollDto(int countOfPins) {
        this.countOfPins = countOfPins;
    }

    public int getCountOfPins() {
        return countOfPins;
    }
}
