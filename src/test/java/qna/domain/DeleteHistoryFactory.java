package qna.domain;

import java.time.LocalDateTime;

public class DeleteHistoryFactory {

  public static DeleteHistory answer(final Answer answer) {
    return new DeleteHistory(
      ContentType.ANSWER,
      answer.getId(),
      answer.getWriter(),
      LocalDateTime.now()
    );
  }

  public static DeleteHistory question(final Question question) {
    return new DeleteHistory(
      ContentType.QUESTION,
      question.getId(),
      question.getWriter(),
      LocalDateTime.now()
    );
  }

}
