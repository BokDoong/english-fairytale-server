package hanium.englishfairytale.tale.domain.factory;

import hanium.englishfairytale.tale.domain.factory.dto.CreateTaleFactoryDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class TaleUtility {

    public static List<CreateTaleFactoryDto> createEngTaleMessages(List<String> keywords) {
        String question = makeQuestion(keywords);

        List<CreateTaleFactoryDto> messages = new ArrayList<>();
        messages.add(new CreateTaleFactoryDto("system", "You are a fairy tale writer."));
        messages.add(new CreateTaleFactoryDto("user", question));

        return messages;
    }

    public static List<CreateTaleFactoryDto> createKorTaleMessages(String engTale) {
        List<CreateTaleFactoryDto> messages = new ArrayList<>();
        messages.add(new CreateTaleFactoryDto("assistant", engTale));
        messages.add(new CreateTaleFactoryDto("user", "Please change the above fairy tale to Korean."));

        return messages;
    }

    public static List<CreateTaleFactoryDto> createTaleTitleMessages(String engTale) {
        List<CreateTaleFactoryDto> messages = new ArrayList<>();
        messages.add(new CreateTaleFactoryDto("assistant", engTale));
        messages.add(new CreateTaleFactoryDto("user", "Please give me a title for the fairy tale above."));

        return messages;
    }

    private static String makeQuestion(List<String> keywords) {
        return "Make a fairy tale with keywords" + String.join(",", keywords) + ".";
    }

}
