package bowling.view;

public enum FrameFormat {
    BOWLING_FRAME("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |"),
    DEFAULT_SCORE_FRAME("|  %s |      |      |      |      |      |      |      |      |      |      |"),
    EMPTY_FRAME("|      |      |      |      |      |      |      |      |      |      |      |"),
    BLANK_FRAME_FORMAT("      |"),
    BLANK_FRAME("|      |"),
    FRAME_LINE("|"),
    NAME_FORMAT("|  %s |"),
    MULTI_SCORE_FORMAT("  %s |"),
    SINGLE_SCORE_FORMAT("  %s   |"),
    POINT_SCORE_FORMAT("  %4s|");

    private String foramt;

    FrameFormat(String foramt) {
        this.foramt = foramt;
    }

    public String getForamt() {
        return foramt;
    }
}
