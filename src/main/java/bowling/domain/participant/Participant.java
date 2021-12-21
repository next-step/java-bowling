package bowling.domain.participant;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
