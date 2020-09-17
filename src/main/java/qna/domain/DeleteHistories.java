package qna.domain;

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

}
