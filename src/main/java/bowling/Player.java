package bowling;

public class Player {

    private final String name;

    public Player(String name) {
        valid(name);
        this.name = name;
    }

    private void valid(String name) {
        if (name.length() != 3) {
            throw new IllegalArgumentException("플레이어의 이름은 3글자를 입력해주세요.");
        }
    }
}
