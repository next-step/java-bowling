package qna.exception;

public class LoginUserNotMatchWriterException extends CannotDeleteException{

    public static final String LOGIN_USER_NOT_MATCH_WRITER = "질문자와 답변자가 달라서 삭제할수 없습니다.";

    public LoginUserNotMatchWriterException() {
        super(LOGIN_USER_NOT_MATCH_WRITER);
    }
}
