package qna.domain;

import org.hibernate.annotations.Where;

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
    private final List<Answer> answers = new ArrayList<>();

    public Answers() {

    }

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public boolean deletableBy(User loginUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void deleteAll() {
        answers.forEach(Answer::delete);
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }
}
