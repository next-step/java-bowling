package qna.domain;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import qna.CannotDeleteException;
import qna.FunctionWithCannotDeleteException;

public class Answers {

  private final List<Answer> answers;

  public Answers(List<Answer> answers) {
    this.answers = answers;
  }

  public void delete(User owner) throws CannotDeleteException {
    answers.forEach(wrapper(answer -> answer.delete(owner)));
  }

  private <T, E extends CannotDeleteException> Consumer<T> wrapper(FunctionWithCannotDeleteException<T, E> fe) {
    return arg -> {
      try {
        fe.apply(arg);
      } catch (CannotDeleteException e) {
        throw new CannotDeleteException(e.getMessage());
      }
    };
  }

  public List<Answer> getList() {
    return Collections.unmodifiableList(answers);
  }

  public Answer getAnswer(int index) {
    return answers.get(index);
  }


}
