package bowling;

public class Username {
    private final String name;

    public Username(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException("이름은 크기 3을 넘을 수 없습니다.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
