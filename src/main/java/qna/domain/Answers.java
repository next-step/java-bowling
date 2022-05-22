package qna.domain;

import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {

    private final List<Answer> answers = new ArrayList<>();


    protected Answers() {
    }

    public void add(Answer answer) {
        validate(answer);
        answers.add(answer);
    }

    void validate(Answer answer){
        if(answer == null){
            throw new IllegalArgumentException("Answers에 null값을 추가할 수 없습니다.");
        }

        answers.stream()
                .filter(a -> a.equalsQuestion(answer))
                .findAny()
                .ifPresent(a -> {
                    throw new IllegalArgumentException("Answers에 다른 Question 대한 Answer을 추가할 수 없습니다. "+a);
                });
    }

    public List<DeleteHistory> delete(User loginUser) {
        return answers.stream()
                .map(answer -> answer.delete(loginUser))
                .collect(Collectors.toList());
    }
}
