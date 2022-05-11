package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() { }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void validateAnswerOwner(User loginUser) throws CannotDeleteException {
        if (isAnswersOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isAnswersOwner(User loginUser) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }

    public List<Answer> getAnswers() {
        return unmodifiableList(answers);
    }
}
