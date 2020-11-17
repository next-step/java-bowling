package bowling.domain.player;

public class Player {

    private EnglishName englishName;

    public Player(String name) {
        this.englishName = new EnglishName(name);
    }

    public String getName() {
        return englishName.getName();
    }

    @Override
    public String toString() {
        return englishName.getName();
    }
}
