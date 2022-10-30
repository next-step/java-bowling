package bowling.domain;

public class Player {
    private static final String WRONG_INPUT_STRING = "Input name is wrong";

    private final String name;

    public Player(String name) {
        checkValid(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void checkValid(String name){
        if(name.isEmpty() || name.length() != RuleConfig.NAME_LENGTH){
            throw new IllegalArgumentException(WRONG_INPUT_STRING);
        }
    }
}
