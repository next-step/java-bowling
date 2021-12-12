package bowling.domain.participant;

import java.util.regex.Pattern;

public class Participant {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]{3}$");

    private final String name;

    public Participant(String name) {
        checkPattern(name);
        this.name = name;
    }

    private void checkPattern(String name) {
        if (name == null || !matches(name)) {
            throw new IllegalNameException();
        }
    }

    private boolean matches(String name) {
        return NAME_PATTERN.matcher(name)
                .matches();
    }

    public String getName() {
        return name;
    }
}
