package qna.domain;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    private List<DeleteHistory> deletehistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deletehistories = deleteHistories;
    }

    public DeleteHistories() {
        new ArrayList<DeleteHistory>();
    }

    public List<DeleteHistory> getDeletehistories() {
        return deletehistories;
    }

    public void add(DeleteHistory deleteHistory) {
        deletehistories.add(deleteHistory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deletehistories, that.deletehistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deletehistories);
    }
=======
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DeleteHistories {

    @OneToMany(mappedBy = "deletedBy", cascade = CascadeType.ALL)
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(){
        this(new ArrayList<>());
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

    public void addQuestionDeleteHistory(Question question) {
        deleteHistories.add(DeleteHistory.makeQuestionDeleteHistory(question));
    }

    public void addAnswerDeleteHistory(Answer answer) {
        deleteHistories.add(DeleteHistory.makeAnswerDeleteHistory(answer));
    }

>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
}
