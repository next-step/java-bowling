package bowling.view;

enum ScoreResultType {

    GUTTER("-"),
    SPARE("/"),
    STRIKE("X");

    private String type;

    ScoreResultType(String type) {
        this.type = type;
    }

    public String value() {
        return this.type;
    }
}
