package bowling_step3.domain;

public class Player {
    private final String name;

    public Player(String name) {
        verify(name);
        this.name = name;
    }

    private void verify(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException("name cannot be longer than 3.");
        }
    }

    public String name() {
        return this.name;
    }
}
