package bowling.domain;

/**
 * Created By mand2 on 2020-12-18.
 */
public class FinalFrame extends Frame {

    public static final int INDEX = 10;
    public static final String ERROR_MESSAGE_FINAL_FRAME_INDEX = "마지막 프레임은 10번입니다.";

    private FinalFrame(int index) {
        super(index);
    }

    public static FinalFrame of(int index) {
        validate(index);
        return new FinalFrame(index);
    }

    private static void validate(int index) {
        if (INDEX != index) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FINAL_FRAME_INDEX);
        }
    }

    @Override
    public void pitch() {

    }
}
