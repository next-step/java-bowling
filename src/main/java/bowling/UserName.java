package bowling;

import java.util.regex.Pattern;

public class UserName {

    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z]{3}");

    private final String name;

    public UserName(String name) {
        valid(name);
        this.name = name;
    }

    private void valid(String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("플레이어의 이름은 3글자 영문자만 가능합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
