package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

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
    private final List<Answer> answers;

    public Answers() {
        answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void checkLoginUserEqualWithAnswersOwners(User loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            throwIfOwnerAndLoginUserNotEqual(loginUser, answer);
        }
    }

    private void throwIfOwnerAndLoginUserNotEqual(User loginUser, Answer answer) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public List<Answer> value() {
        return Collections.unmodifiableList(this.answers);
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
