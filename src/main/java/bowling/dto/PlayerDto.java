package bowling.dto;

public class PlayerDto {
    private final String name;
    private final PlayerStatusDto status;

    public PlayerDto(String name, PlayerStatusDto status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public PlayerStatusDto getStatus() {
        return status;
    }
}
