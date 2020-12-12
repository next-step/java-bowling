package bowling.domain;

public enum Stage {

    STAGE1("01"),
    STAGE2("02"),
    STAGE3("03"),
    STAGE4("04"),
    STAGE5("05"),
    STAGE6("06"),
    STAGE7("07"),
    STAGE8("08"),
    STAGE9("09"),
    STAGE10("10");

    private String stageName;

    Stage(String stageName) {
        this.stageName = stageName;
    }
}
