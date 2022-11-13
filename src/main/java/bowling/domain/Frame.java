package bowling.domain;

public class Frame {

    private Bowlings values;

    private Frame(Bowlings values) {
        this.values = values;
    }

    public static Frame createNormal() {
        return new Frame(Bowlings.initNormal());
    }

    public static Frame createFinal() {
        return new Frame(Bowlings.initFianl());
    }

    public Bowling get(int index) {
        return values.get(index);
    }

    public int size() {
        return values.size();
    }

    public void bowling(int count) {
        values.bowling(count);
    }

    public boolean isEnd() {
        return values.isEnd();
    }

    //=======================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frame that = (Frame) o;

        return values != null ? values.equals(that.values) : that.values == null;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "values=" + values +
                '}';
    }

    // ======================================================================
}
