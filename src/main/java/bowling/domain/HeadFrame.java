package bowling.domain;

public class HeadFrame implements Frame{
    private static final String HEAD_TITLE = "NAME";

    private String value;

    public static HeadFrame from(String value) {
        return new HeadFrame(value);
    }

    private HeadFrame(String value) {
        this.value = value;
    }

    @Override
    public String printableTitle() {
        return String.format(COLUMN_WITH_FORMAT, HEAD_TITLE, BLANK);
    }

    @Override
    public String printableValue() {
        return String.format(COLUMN_WITH_FORMAT, value, BLANK);
    }

    @Override
    public void record() {

    }

    @Override
    public Frame makeNextFrame() {
        return NormalFrame.of("01", this);
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }
}
