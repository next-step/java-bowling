package bowling.domain;

public class Player {
    private String name = "";
    public Player(String name) {
        checkLetter(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    public void checkLetter(String name) {
        if (name.length() > 3){
            throw new IllegalArgumentException("3글자까지만 입력가능합니다.");
        }
    }
}
