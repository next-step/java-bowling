package bowling.dto;

public class PlayerDto {
    private final String name;

    private PlayerDto(final String name) {
        this.name = name;
    }

    public static PlayerDto from(final String name) {
        return new PlayerDto(name);
    }

    public String getName() {
        return name;
    }
}
