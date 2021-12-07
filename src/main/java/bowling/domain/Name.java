package bowling.domain;

import static bowling.utils.StringUtils.isAlphabet;

public class Name {
    private final String name;

    public Name(String name) {
        this.name = validateName(name);
    }

    public String getName() {
        return name;
    }

    private String validateName(String name) {
        if (name.length() > 3 || !isAlphabet(name)) {
            throw new IllegalArgumentException("Name must be under 3 characters and composed of alphabets.");
        }

        return name;
    }
}
