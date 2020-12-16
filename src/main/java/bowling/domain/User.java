package bowling.domain;

import java.util.regex.Pattern;

/**
 * Created : 2020-12-16 오전 7:55
 * Developer : Seo
 */
public class User {
    public static final String NAME_PATTERN = "^([A-z]{3})$";

    private final String name;

    public User(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!Pattern.matches(NAME_PATTERN, name)) {
            throw new IllegalArgumentException("3자 영문으로 입력해주십시요.");
        }
    }

    public String getName() {
        return name;
    }
}
