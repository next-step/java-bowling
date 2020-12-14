package bowling.dto;

public class PlayerDto {
    private final String name;
    private final BowlDto bowlDto;

    public PlayerDto(String name, BowlDto bowlDto) {
        this.name = name;
        this.bowlDto = bowlDto;
    }

    public String getName() {
        return name;
    }

    public BowlDto getBowlDto() {
        return bowlDto;
    }
}
