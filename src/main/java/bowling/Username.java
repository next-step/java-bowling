package bowling;

public class Username {

    private final String name;

    public Username(String name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("사용자 이름은 필수값입니다.");
        }
        this.name = name;
    }
}
