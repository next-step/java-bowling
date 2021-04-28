package qna.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;

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

    public List<DeleteHistory> delete(List<DeleteHistory> deleteHistories) {
        list.forEach(answer -> {
            answer.delete();
            answer.writeHistory(deleteHistories);
        });
        return deleteHistories;
    }

    public List<Answer> value() {
        return this.list;
    }
}
