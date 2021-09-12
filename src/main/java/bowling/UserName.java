package bowling;

import java.util.Objects;
import java.util.regex.Pattern;

public class UserName {

    private static final String NAME_REGEX = "^[a-zA-Z]{1,3}+$";

    private String name;

    public UserName(String name) {
        validated(name);
        this.name = name;
    }

    private void validated(String name) {

        if (Pattern.matches(NAME_REGEX, name)) {
            System.out.println("match");
            return;
        }
        throw new IllegalArgumentException("이름은 영어 3글자까지 가능합니다.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserName userName = (UserName) o;
        return Objects.equals(name, userName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
