package qna;

public class ExistTheOthersAnswerException extends CannotDeleteException {

    public static final String EXIST_OTHERS_ANSWER = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    public ExistTheOthersAnswerException() {
        super(EXIST_OTHERS_ANSWER);
    }
}
