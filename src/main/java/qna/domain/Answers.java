package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers;

    public Answers() {
        answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAndGenerateHistories(User loginUser) {
        return answers.stream()
                .map(answer -> answer.deleteAndGenerateHistory(loginUser))
                .collect(toList());
    }
}
