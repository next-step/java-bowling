package qna.domain.answers;

import org.hibernate.annotations.Where;
import qna.domain.deleteHistory.DeleteHistories;
import qna.domain.users.User;
import qna.exception.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers;

    public Answers() {
        answers = new ArrayList<>();
    }

    public Answers(final List<Answer> answers) {
        this.answers = answers;
    }

    public void add(final Answer answer) {
        answers.add(answer);
    }

    public DeleteHistories deleteAll(final User loginUser) throws CannotDeleteException {
        checkAnswerWrittenBySomeoneElse(loginUser);
        for (Answer answer : answers) {
            answer.changeDeleted(true);
        }
        return DeleteHistories.of(this);
    }

    public List<Answer> elements() {
        return Collections.unmodifiableList(answers);
    }

    private void checkAnswerWrittenBySomeoneElse(User loginUser) throws CannotDeleteException {
        boolean isExistWrittenAnswerBySomeoneElse = answers.stream().anyMatch(answer -> !answer.isOwner(loginUser));
        if (isExistWrittenAnswerBySomeoneElse) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
