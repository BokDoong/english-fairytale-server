package hanium.englishfairytale.tale.domain.factory;

import hanium.englishfairytale.tale.domain.factory.dto.CreateTaleDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class TaleUtility {

    public static List<CreateTaleDto> createEngTaleMessages(List<String> keywords) {
        String question = makeQuestion(keywords);

        List<CreateTaleDto> messages = new ArrayList<>();
        messages.add(new CreateTaleDto("system", "You are a fairy tale writer."));
        messages.add(new CreateTaleDto("user", question));

        return messages;
    }

    public static List<CreateTaleDto> createKorTaleMessages(String engTale) {
        List<CreateTaleDto> messages = new ArrayList<>();
        messages.add(new CreateTaleDto("assistant", engTale));
        messages.add(new CreateTaleDto("user", "Please change the above fairy tale to Korean."));

        return messages;
    }

    public static List<CreateTaleDto> createTaleTitleMessages(String engTale) {
        List<CreateTaleDto> messages = new ArrayList<>();
        messages.add(new CreateTaleDto("assistant", engTale));
        messages.add(new CreateTaleDto("user", "Please give me a title for the fairy tale above."));

        return messages;
    }

    private static String makeQuestion(List<String> keywords) {
        return "Make a fairy tale with keywords" + String.join(",", keywords) + ".";
    }

}
