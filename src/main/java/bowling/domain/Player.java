package bowling.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created : 2020-12-16 오전 7:55
 * Developer : Seo
 */
public class Player {
    public static final String NAME_PATTERN = "^([A-z]{3})$";

    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        Pattern p = Pattern.compile(NAME_PATTERN);
        Matcher m = p.matcher(name);
        if (!m.matches()) {
            throw new IllegalArgumentException("3자 영문으로 입력해주십시요.");
        }
    }

    public String getName() {
        return name;
    }
}
