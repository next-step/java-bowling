package qna.domain.answer;

import qna.domain.ContentType;
import qna.domain.DeleteHistory;
import qna.domain.user.User;
import qna.error.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Answers {
  private final List<Answer> answers;

  private Answers(List<Answer> answers){
    this.answers = answers;
  }

  public static Answers of(List<Answer> answers){
    return new Answers(answers);
  }

  public List<DeleteHistory> deleteAll(){
    List<DeleteHistory> deleteHistories = new ArrayList<>();

    for(Answer answer : answers){
      answer.setDeleted(true);
      deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }

    return deleteHistories;
  }

  public void checkAnswers(User loginUser) throws CannotDeleteException {
    for (Answer answer : answers) {
      answer.checkDeletable(loginUser);
    }
  }

  public int size(){
    return answers.size();
  }

  public Optional<Answer> head(){
    return answers.stream().findFirst();
  }

}
