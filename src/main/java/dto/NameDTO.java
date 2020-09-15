package dto;

import java.util.regex.Pattern;

public class NameDTO implements DTO {

    private static final String NAMING_REGEX = "^[a-zA-Z]{3,}$";
    private final String name;

    private NameDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static void validate(String name) throws IllegalArgumentException {
        if (!Pattern.matches(NAMING_REGEX, name)) {
            throw new IllegalArgumentException("이름은 영문 3 글자만 허용 됩니다.");
        }
    }

    public static NameDTO of(String name) {
        validate(name);
        return new NameDTO(name);
    }
}
