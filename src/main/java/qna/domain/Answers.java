package qna.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void deleteAnswers(User loginUser) {
        answers.forEach(answer -> answer.delete(loginUser));
    }

    public List<DeleteHistory> createDeleteHistoryies() {
        return answers.stream()
                .map(Answer::createDeleteHistory)
                .collect(Collectors.toList());
    }

    public int size() {
        return answers.size();
    }
}
