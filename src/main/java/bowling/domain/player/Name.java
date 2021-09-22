package bowling.domain.player;

import bowling.exception.NameException;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z]*$");

    private final String name;

    public Name(String name) {
        validateNameLength(name);
        validateNameEng(name);
        this.name = name.toUpperCase(Locale.ROOT);
    }

    private void validateNameLength(String name) {
        if (name.length() != 3) {
            throw new NameException(name);
        }
    }

    private void validateNameEng(String name) {
        if (!PATTERN.matcher(name).find()) {
            throw new NameException(name);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
