package bowling.domian.player;

import java.util.List;

public class Player {
    private final Name name;

    private Player(Name name) {
        this.name = name;
    }

    public static Player get(String name) {
        return new Player(Name.get(name));
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getDescs() {
        return null;
    }

    public List<Integer> getTotalScores() {
        return null;
    }

    public boolean isFrameNumber(int frameNumber) {
        return false;
    }

    public void bowl(int falledPinsCount) {
    }
}
