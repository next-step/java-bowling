package bowling.domain.user;

import bowling.exception.user.NameAlphabetPatternException;
import bowling.exception.user.NameBlankException;
import bowling.exception.user.NameLengthException;
import java.util.Objects;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.Strings;

public class Name {

    private static final String ALPHABET_REGEX = "^[a-zA-Z]*$";

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        checkNameNullAndBlankException(name);
        checkNameLengthException(name);
        checkNameAlphabetException(name);

        return new Name(name);
    }

    private static void checkNameLengthException(String name) {
        if (name.length() > 3) {
            throw new NameLengthException();
        }
    }

    private static void checkNameNullAndBlankException(String name) {
        if (Strings.isBlank(name)) {
            throw new NameBlankException();
        }
    }

    private static void checkNameAlphabetException(String name) {
        if (!Pattern.matches(ALPHABET_REGEX, name)){
            throw new NameAlphabetPatternException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
