package qna;

public class AnswerOtherWrittenException extends RuntimeException {

    public AnswerOtherWrittenException() {
        super("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
