package bowling.model;

import java.util.regex.Pattern;

public class Name {

    private static final String NAME_PATTERN = "^[a-zA-Z]{3}$";
    private final String name;

    public Name(String name) {
        this.name = name.trim();
        checkPatternMatch(this.name);
    }

    private void checkPatternMatch(String name) {
        if(!Pattern.matches(NAME_PATTERN,name)) {
            throw new IllegalArgumentException("이름은 영어로 3글자여야 합니다.");
        }
    }

    public String get() {
        return this.name;
    }
}
