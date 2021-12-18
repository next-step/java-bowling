package bowling.model.state;

public class Strike extends Finished {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

    @Override
    public String getDesc() {
        return "X";
    }
}
