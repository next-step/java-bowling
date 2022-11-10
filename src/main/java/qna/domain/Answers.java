package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seungwoo.song on 2022-11-07
 */
@Embeddable
public class Answers extends AbstractList<Answer> {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> values = new ArrayList<>();

    public Answers(List<Answer> values) {
        this.values = values;
    }

    public Answers() {
    }

    public DeleteHistories deleteAll(User user) throws CannotDeleteException {
        List<DeleteHistory> list = new ArrayList<>();
        for (Answer answer : values) {
            list.add(answer.delete(user));
        }
        return new DeleteHistories(list);
    }

    @Override
    public Answer get(int index) {
        return values.get(index);
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public boolean add(Answer answer) {
        return values.add(answer);
    }

    public List<Answer> getValues() {
        return values;
    }

    public boolean isDeletedAll() {
        return !values.stream()
                .anyMatch(Answer::isNotDeleted);
    }
}
