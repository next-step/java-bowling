package bowling.user.domain;

import bowling.user.dto.UserDTO;

public class UserAssembler {

    private UserAssembler() {
    }

    public static UserDTO assemble(User user) {
        return new UserDTO(user.getName());
    }

}
