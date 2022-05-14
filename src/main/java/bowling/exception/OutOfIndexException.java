package bowling.exception;

public class OutOfIndexException extends RuntimeException {

    private static final String OUT_OF_INDEX_MESSAGE_FORMAT = "다음 인덱스는 최대 인덱스를 초과하였습니다. : %d";

    public OutOfIndexException(int nextIndex) {
        super(String.format(OUT_OF_INDEX_MESSAGE_FORMAT, nextIndex));
    }

}
