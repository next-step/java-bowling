package qna.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DeleteHistories {

  private final Collection<DeleteHistory> deleteHistories;

  DeleteHistories() {
    this.deleteHistories = new LinkedList<>();
  }

  DeleteHistories(Collection<DeleteHistory> deleteHistories) {
    this.deleteHistories = deleteHistories;
  }

  public DeleteHistories add(DeleteHistory deleteHistory) {
    this.deleteHistories.add(deleteHistory);
    return this;
  }

  public DeleteHistories addAll(DeleteHistories other) {
    this.deleteHistories.addAll(other.deleteHistories);
    return this;
  }

  public List<DeleteHistory> toList() {
    return new ArrayList<>(deleteHistories);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeleteHistories that = (DeleteHistories) o;
    return deleteHistories.containsAll(that.deleteHistories);
  }

  public static DeleteHistories from(DeleteHistory deleteHistory) {
    return new DeleteHistories().add(deleteHistory);
  }
}
