package qna.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

@Embeddable
public class Answers implements Iterable<Answer> {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers;

    public Answers() {
        answers = new ArrayList<>();
    }

    public static Answers of() {
        return new Answers();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete(User questionWriter) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(questionWriter);
        }
    }

    public List<DeleteHistory> deleteHistorys() {
        List<DeleteHistory> deleteHistorys = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistorys.add(answer.deleteHistory());
        }
        return deleteHistorys;
    }

    @Override
    public Iterator<Answer> iterator() {
        return answers.iterator();
    }
}
