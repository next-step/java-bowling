package qna.domain;

import org.hibernate.annotations.Where;

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
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public List<DeleteHistory> deleteAll() {
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isOwnerOfAll(User user) {
        for (Answer answer : answers) {
            if (!answer.isOwner(user)) {
                return false;
            }
        }
        return true;
    }
}
