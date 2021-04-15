package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteHistories {

  private final List<DeleteHistory> deleteHistories;

  private DeleteHistories(List<DeleteHistory> deleteHistories) {
    this.deleteHistories = deleteHistories;
  }

  public static DeleteHistories from(Question question) {
    List<DeleteHistory> histories = new ArrayList<>();
    histories.add(DeleteHistory.from(question));

    Answers answers = question.getAnswers();
    List<DeleteHistory> answerHistoryList = answers.getList().stream()
        .map(DeleteHistory::from)
        .collect(Collectors.toList());
    histories.addAll(answerHistoryList);

    return new DeleteHistories(histories);
  }

  public List<DeleteHistory> getList() {
    return Collections.unmodifiableList(deleteHistories);
  }
}
