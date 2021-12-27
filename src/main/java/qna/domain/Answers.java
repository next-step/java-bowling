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
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public DeleteHistories delete(User loginUser) throws CannotDeleteException {
        validationWriter(loginUser);
        return answers.stream()
                .map(Answer::toDeleteHistory)
                .collect(Collectors.collectingAndThen(Collectors.toList(), DeleteHistories::new));
    }

    private void validationWriter(User loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            answer.validationWriterIsOwner(loginUser);
        }
    }

    public void toQuestion(Question question) {
        this.answers.forEach(answer -> answer.toQuestion(question));
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
