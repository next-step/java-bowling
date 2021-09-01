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
    List<Answer> values = new ArrayList<>();

    public void addAnswer(Answer answer, Question parentQuestion) {
        answer.toQuestion(parentQuestion);
        values.add(answer);
    }

    public boolean containsOnlyBy(User writer) {
        return values.stream()
                .allMatch(answer -> answer.isOwner(writer));
    }

    public List<DeleteHistory> delete() {
        return values.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }
}
