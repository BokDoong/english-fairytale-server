package hanium.englishfairytale.tale.application;

import java.util.List;

public interface ChatGptService {
    List<String> post(String model, List<String> keyword);
    default String makeQuestion(List<String> keyword){
        return "Make a fairy tale with keywords " + String.join(",", keyword)+".";
    }
}
