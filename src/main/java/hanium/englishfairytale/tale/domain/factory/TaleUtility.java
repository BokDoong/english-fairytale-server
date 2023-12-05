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
        messages.add(new CreateTaleFactoryDto("user", "Please translate this English fairytale into Korean." +
                " When you translate, please translate one English sentence into one Korean sentence." +
                " For your information, the division unit of the sentence here is divided based on symbol \".\""));

        return messages;
    }

    public static List<CreateTaleFactoryDto> createTaleTitleMessages(String engTale) {
        List<CreateTaleFactoryDto> messages = new ArrayList<>();
        messages.add(new CreateTaleFactoryDto("assistant", engTale));
        messages.add(new CreateTaleFactoryDto("user", "Please give me a title for this fairy tale."));

        return messages;
    }

    private static String makeQuestion(List<String> keywords) {
        return "Make a fairy tale with keywords" + String.join(",", keywords) + ".";
    }

}
