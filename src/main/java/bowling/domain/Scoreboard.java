package bowling.domain;

import java.util.Objects;

public class Scoreboard {

    private final Name name;

    public Scoreboard(Name name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Scoreboard that = (Scoreboard) o;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}
