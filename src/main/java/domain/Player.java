package domain;

public class Player {
    private final int MAX_LENGTH = 3;
    private String name;

    public Player(String name) {
        if(name.getBytes().length > MAX_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 3bytes 를 초과할 수 없습니다.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
