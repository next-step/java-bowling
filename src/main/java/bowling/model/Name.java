package bowling.model;

import java.util.regex.Pattern;

public class Name {

    private static final String NAME_PATTERN_NOT_MATCH_MESSAGE = "이름은 영어로 3글자여야 합니다.";
    private static final String NAME_PATTERN = "^[a-zA-Z]{3}$";
    private final String name;

    public Name(String name) {
        this.name = name.trim();
        whenNamePatternNotMatchThrowException(this.name);
    }

    private void whenNamePatternNotMatchThrowException(String name) {
        if(!Pattern.matches(NAME_PATTERN,name)) {
            throw new IllegalArgumentException(NAME_PATTERN_NOT_MATCH_MESSAGE);
        }
    }

    public String get() {
        return this.name;
    }
}
