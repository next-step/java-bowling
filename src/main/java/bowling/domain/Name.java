package bowling.domain;

import bowling.exception.NameIncludeVariableLanguage;
import bowling.exception.StringNullPointerException;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public final class Name {

    private static final String REGEX = "[^a-zA-Z]";

    private final String name;

    public static Name valueOf(final String name) {
        return new Name(name);
    }

    private Name(final String name) {
        validateNull(name);
        validateAlphabet(name);
        this.name = name;
    }

    private final void validateNull(final String name) {
        if (Objects.isNull(name)) {
            throw new StringNullPointerException();
        }
    }

    private final void validateAlphabet(final String name) {
        if (!replaceSpecialCharacters(name).equals(name)) {
            throw new NameIncludeVariableLanguage();
        }
    }

    private final String replaceSpecialCharacters(final String name) {
        return name.replaceAll(REGEX, Strings.EMPTY);
    }



}
