package bowling.domain;

public class PlayerName {

    private static final String PLAYER_NAMING_RULE_REGEX = "[a-zA-Z]{3}";

    private final String name;

    public PlayerName(String name) {
        validatePlayerNamingRule(name);
        this.name = name;
    }

    private void validatePlayerNamingRule(String name) {
        if (name == null || !name.matches(PLAYER_NAMING_RULE_REGEX)) {
            throw new IllegalArgumentException(String.format("플레이어명은 영문 3자이어야 합니다. name: %s", name));
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
