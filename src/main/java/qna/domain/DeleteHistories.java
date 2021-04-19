package qna.domain;

import java.util.Collections;
import java.util.List;

public class DeleteHistories {

  private final List<DeleteHistory> deleteHistories;

  private DeleteHistories(List<DeleteHistory> deleteHistories) {
    this.deleteHistories = deleteHistories;
  }

  public static DeleteHistories from(List<DeleteHistory> deleteHistories) {
    return new DeleteHistories(deleteHistories);
  }

  public List<DeleteHistory> getList() {
    return Collections.unmodifiableList(deleteHistories);
  }
}
