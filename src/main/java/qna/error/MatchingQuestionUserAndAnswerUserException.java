package qna.error;

public class MatchingQuestionUserAndAnswerUserException extends CannotDeleteException {
  private static final String UNMATCHED_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

  public MatchingQuestionUserAndAnswerUserException(){
    this(UNMATCHED_MESSAGE);
  }

  public MatchingQuestionUserAndAnswerUserException(String message){
    super(message);
  }
}
