package bowling.dto;

public class PlayerDto {
    private String name;

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
