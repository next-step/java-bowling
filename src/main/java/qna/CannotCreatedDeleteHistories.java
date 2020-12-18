package qna;

public class CannotCreatedDeleteHistories extends RuntimeException {

    private static final String MESSAGE = "삭제 히스토리를 생성할 수 없습니다.";

    public CannotCreatedDeleteHistories(){
        super(MESSAGE);
    }
}
