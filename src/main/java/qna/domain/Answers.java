package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "softDelete = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }
    public List<DeleteHistory> softDelete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> list = new ArrayList<>();
        for (Answer answer : answers) {
            DeleteHistory deleteHistory = answer.softDelete(loginUser);
            list.add(deleteHistory);
        }
        return list;
    }
}
