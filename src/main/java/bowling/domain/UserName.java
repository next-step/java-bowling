package bowling.domain;

public class UserName {

    private final String userName;

    public UserName(String userName) {
        validCheck(userName);
        this.userName = userName;
    }

    private static void validCheck(String userName) {
        if (userName == null || userName.length() != 3) {
            throw new RuntimeException("플레이어 이름 세글자로 해주세요.");
        }
    }

    public String getUserName() {
        return userName;
    }
}
