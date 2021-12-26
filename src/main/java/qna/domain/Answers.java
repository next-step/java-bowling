package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Answers {

    public static final String MESSAGE_EXIST_ANSWER_BY_OTHER_USER = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers;

    public Answers() {
        answers = Collections.emptyList();
    }

    public Answers(Answer answer) {
        this.answers = Collections.singletonList(answer);
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(User loginUser) throws CannotDeleteException {
        validationWriter(loginUser);
        answers.forEach(answer -> answer.setDeleted(true));
    }

    private void validationWriter(User loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException(MESSAGE_EXIST_ANSWER_BY_OTHER_USER);
            }
        }
    }

    public void toQuestion(Question question) {
        this.answers.forEach(answer -> answer.toQuestion(question));
    }

}
