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
public class Answers extends AbstractEntity {

    @OneToMany(mappedBy = "answers", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    protected Answers() {
    }

    public Answers(final List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(final Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete(final User loginUser) {
        return answers.stream()
                .map(answer -> answer.delete(loginUser))
                .collect(Collectors.toList());
    }

}
