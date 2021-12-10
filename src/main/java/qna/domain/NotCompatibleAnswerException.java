package qna.domain;

public class NotCompatibleAnswerException extends RuntimeException {

    public NotCompatibleAnswerException(Question question) {
        super(String.format("%s 에 대한 답변은 추가할 수 없습니다.", question.getTitle()));
    }
}
