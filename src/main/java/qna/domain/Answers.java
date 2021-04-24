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

    @Where(clause = "deleted = false")
    @OrderBy(value = "id ASC")
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    protected Answers() {}

    protected Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteBy(User loginUser) {
        return answers.stream()
                .map(answer->answer.delete(loginUser))
                .collect(Collectors.toList());
    }
}
