package bowling.domain;

public class Frame {

    private int round;
    private Bowlings values;

    public Frame(int round, Bowlings values) {
        this.round = round;
        this.values = values;
    }

    public static Frame initNormal(int count) {
        Bowlings bowlings = Bowlings.initNormal();
        bowlings.bowling(count);
        return new Frame(0, bowlings);
    }

    public static Frame initFinal(int count) {
        Bowlings bowlings = Bowlings.initFianl();
        bowlings.bowling(count);
        return new Frame(10, bowlings);
    }

    public Bowling get(int index) {
        return values.get(index);
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

    public int size() {
        return values.size();
    }

    public void bowling(int count) {
        values.bowling(count);
    }

    public Frame createNext(int count) {
        if (isNextFinal()) {
            return Frame.initFinal(count);
        }
        return Frame.initNormal(count);
    }

    private boolean isNextFinal() {
        return round == 9;
    }
}
