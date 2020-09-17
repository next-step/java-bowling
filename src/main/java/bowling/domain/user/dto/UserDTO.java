package bowling.domain.user.dto;

public class UserDTO {

    private final String name;

    public UserDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
