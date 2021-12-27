package qna.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.transaction.Transactional;
import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

@Embeddable
public class Answers {

    private static final String MESSAGE_ALL_ANSWERS_MUST_WRITTEN_BY_WRITER_OF_QUESTION = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    @Transactional
    public List<DeleteHistory> deleteAllAfterDeletingQuestion(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        try {
            for (Answer answer : answers) {
                deleteHistories.add(answer.delete(loginUser));
            }
        } catch (CannotDeleteException exception) {
            throw new CannotDeleteException(MESSAGE_ALL_ANSWERS_MUST_WRITTEN_BY_WRITER_OF_QUESTION, exception);
        }

        return deleteHistories;
    }

    public int size() {
        return answers.size();
    }
}
