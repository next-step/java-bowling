package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {

    private static final String CANNOT_DELETE_OTHERS_ANSWER_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers (Answer... answers) {
        this(Arrays.stream(answers)
                .collect(Collectors.toList()));
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(User loginUser) {
        checkDeletable(loginUser);

        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    private void checkDeletable(User loginUser) {
        answers.stream()
                .filter(answer -> !answer.isOwner(loginUser))
                .findAny()
                .ifPresent(answer -> {
                    throw new CannotDeleteException(CANNOT_DELETE_OTHERS_ANSWER_MESSAGE);
                });
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
