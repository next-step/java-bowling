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
    private final List<Answer> answers;

    public Answers() {
        answers = new ArrayList<>();
    }

    public void deleteAllBy(User loginUser) {
        answers.forEach(answer -> answer.deleteBy(loginUser));
    }

    public List<DeleteHistory> toDeleteHistories() {
        return answers.stream()
                .map(Answer::toDeleteHistory)
                .collect(Collectors.toList());
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
