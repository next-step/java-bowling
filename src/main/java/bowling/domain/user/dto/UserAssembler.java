package bowling.domain.user.dto;

import bowling.domain.user.User;

public class UserAssembler {

    private UserAssembler() {
    }

    public static UserDTO assemble(User user) {
        return new UserDTO(user.getName());
    }

}
