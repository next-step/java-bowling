package step3.domain;

import step3.exceptions.UserNameLengthError;

public class UserName {
    private String userName;

    public UserName(String userName) {
        validUserName(userName);
        this.userName = userName;
    }

    private void validUserName(String userName) {
        if (userName.length() != 3) {
            throw new UserNameLengthError();
        }
    }

    public String name() {
        return userName;
    }
}
