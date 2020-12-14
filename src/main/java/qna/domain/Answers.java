package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By mand2 on 2020-12-14.
 */
@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {}

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }


    // question writer과 일치하지 않는 사람 찾기
    public boolean isDifferentWriter(User questionWriter) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(questionWriter));
    }

    public List<DeleteHistory> delete() {
        return answers.stream()
                .map(answer -> answer.delete())
                .collect(Collectors.toList());
    }


}
