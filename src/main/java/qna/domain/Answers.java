package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

import qna.CannotDeleteException;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> values = new ArrayList<>();

    protected Answers() {
    }

    public Answers(List<Answer> answers) {
        Objects.requireNonNull(answers);
        values.addAll(answers);
    }

    public void add(Answer answer) {
        Objects.requireNonNull(answer);
        values.add(answer);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        Objects.requireNonNull(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer value : values) {
            value.delete(loginUser);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, value.getId(), value.getWriter(),
                LocalDateTime.now()));
        }

        return deleteHistories;
    }
}
