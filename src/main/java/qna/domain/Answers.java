package qna.domain;

import java.util.ArrayList;
import java.util.List;
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

    public Answers(final List<Answer> values) {
        this.values = values;
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        final List<DeleteHistory> result = new ArrayList<>();
        for (Answer value : values) {
            result.add(value.delete(loginUser));
        }
        return result;
    }

}
