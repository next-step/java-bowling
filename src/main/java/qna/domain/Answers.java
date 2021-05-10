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
    private List<Answer> list = new ArrayList<>();

    public Answers(){

    }

    public Answers(List<Answer> list){
        this.list = list;
    }

    public void add(Answer answer) {
        list.add(answer);
    }

    public List<DeleteHistory> delete() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : list) {
            deleteHistories.add(answer.delete());
        }
        return deleteHistories;
    }

    public List<Answer> value() {
        return this.list;
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }
}
