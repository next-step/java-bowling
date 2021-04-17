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

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {

    }

    public static Answers of(Answer... answers) {
        return new Answers(Arrays.asList(answers));
    }

    public List<DeleteHistory> createDeleteHistories() {
        checkIfAllAnswersDeleted();
        return answers.stream().map(Answer::createAnswerDeleteHistory).collect(Collectors.toList());
    }

    private void checkIfAllAnswersDeleted() {
        answers.stream().filter(answer -> !answer.isDeleted()).findAny().ifPresent(ignored -> {
            throw new IllegalStateException("삭제되지 않은 답변이 존재합니다.");
        });
    }

    public Answers beDeletedBy(User user) {
        return answers.stream()
                      .map(answer -> answer.beDeletedBy(user))
                      .collect(Collectors.collectingAndThen(Collectors.toList(), Answers::new));
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

}
