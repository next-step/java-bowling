package domain;

public class BowlingResult {

    private String first;
    private String second;

    public BowlingResult() {
        this.first = "";
        this.second = "";
    }

    public void appendFirst(String first) {
        this.first = first;
    }

    public void appendSecond(String second) {
        this.second = second;
    }

    public String getFirstResult() {
        return this.first;
    }

    public String getSecondResult() {
        return this.second;
    }
}
