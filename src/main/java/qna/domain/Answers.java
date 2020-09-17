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
    private static final String ALREADY_EXIST_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> deleteAll(User loginUser) {
        validateOwner(loginUser);
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    private void validateOwner(User loginUser) {
        if (notMatchOwner(loginUser)) {
            throw new CannotDeleteException(ALREADY_EXIST_MESSAGE);
        }
    }

    private boolean notMatchOwner(User loginUser) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }
}
