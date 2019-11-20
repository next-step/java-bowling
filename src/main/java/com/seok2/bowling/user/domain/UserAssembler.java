package com.seok2.bowling.user.domain;

import com.seok2.bowling.user.dto.UserDTO;

public class UserAssembler {

    private UserAssembler() {
    }

    public static UserDTO assemble(User user) {
        return new UserDTO(user.getName());
    }

}
