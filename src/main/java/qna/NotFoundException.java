package qna;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        this("답변을 할 질문이 없습니다.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
