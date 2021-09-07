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
    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<Answer> value() {
        return this.answers;
    }

    public List<DeleteHistory> delete(User loginUser) {
        return this.answers.stream()
                .map(answer -> answer.delete(loginUser))
                .collect(Collectors.toList());
    }
}
