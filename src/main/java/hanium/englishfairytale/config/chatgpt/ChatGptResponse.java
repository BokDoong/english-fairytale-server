package hanium.englishfairytale.config.chatgpt;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class ChatGptResponse {
    private String id;
    private String object;
    private Long created;
    private String model;
    private List<Choices> choices;
    private ClassUsage usage;
    @Data
    @ToString
    @NoArgsConstructor
    private static class Choices{
        private Long index;
        private String role;
        private String content;
        private String finish_reason;
    }
    @Data
    @ToString
    @NoArgsConstructor
    private static class ClassUsage {
        private Long prompt_tokens;
        private Long completion_tokens;
        private Long total_tokens;
    }
}