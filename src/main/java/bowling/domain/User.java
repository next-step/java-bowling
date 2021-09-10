package bowling.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private static final Pattern NAME_VALID_PATTERN = Pattern.compile("^[a-zA-Z]{1,3}$");
    private String name;

    public User(String name) {
        Matcher nameMatcher = NAME_VALID_PATTERN.matcher(name);
        if (!nameMatcher.matches()) {
            throw new IllegalArgumentException("잘못된 유형의 이름이 들어왔습니다.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
