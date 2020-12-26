package bowling.domain;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static bowling.util.StringUtils.isBlank;

public class Player {

    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 3;
    public static final Pattern NAME_REGEX_PATTERN = Pattern.compile("[A-Za-z]+");
    public static final String MIN_NAME_LENGTH_ERROR = String.format("플레이어의 이름은 최소 %s자이상 입력 해야합니다.", MIN_NAME_LENGTH);
    public static final String MAX_NAME_LENGTH_ERROR = String.format("플레이어의 이름은 최대 %s자까지 입력 가능합니다.", MIN_NAME_LENGTH);
    public static final String NAME_REGEX_PATTERN_ERROR = "플레이어 이름은 영문만 가능합니다.";

    private final String name;

    private Player(String name) {
        this.name = name;
        validation();
    }

    private void validation() {
        isNameBlank();
        checkOverNameLength();
        checkEnglishName();
    }

    private void isNameBlank() {
        if (isBlank(name)) {
            throw new IllegalArgumentException(MIN_NAME_LENGTH_ERROR);
        }
    }

    private void checkOverNameLength() {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(MAX_NAME_LENGTH_ERROR);
        }
    }

    private void checkEnglishName() {
        Matcher matcher = NAME_REGEX_PATTERN.matcher(name);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(NAME_REGEX_PATTERN_ERROR);
        }
    }

    public static Player from(String name) {
        return new Player(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
