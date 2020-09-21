package bowling.global.utils;

public class StringParser {

    private String value;

    public StringParser(String value) {
        this.value = value;
    }

    public int toInt() {
        return Integer.parseInt(value);
    }
}
