package hanium.englishfairytale.tale.application.impl;

import hanium.englishfairytale.config.ChatGptMessage;
//import hanium.englishfairytale.exception.CustomException;
//import hanium.englishfairytale.exception.CustomException;
//import hanium.englishfairytale.exception.ErrorCode;
import hanium.englishfairytale.tale.application.ChatGptService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ChatGptServiceImpl implements ChatGptService {
    @Value("${gpt.key}")
    String key;

    /**
     * GPT에게 동화 만들어 달라고 하기
     * @param model ChatGpt 모델 설정
     * @param keyword 동화내용에 들어갈 모델 설정
     * @return 영어 동화 내용, 한글 동화 내용, 영어 제목
     */
    public List<String> post(String model, List<String> keyword) {
        // Null 검사
//        verifyKeywordIncluded(keyword);

        String question = makeQuestion(keyword);
        // 질의응답 작성
        List<ChatGptMessage> messages = new ArrayList<>();

        // 리턴 작성
        List<String> tale_content = new ArrayList<>();

        // ChatGpt 역할 부여
        messages.add(
                ChatGptMessage.builder()
                        .role("system")
                        .content("You are a fairy tale writer.")
                        .build()
        );

        // ChatGpt 동화 제작 질문
        messages.add(
                ChatGptMessage.builder()
                        .role("user")
                        .content(question)
                        .build()
        );

        // Map으로 GPT에게 날릴 Request 작성
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("model", model);
        bodyMap.put("stream", false);
        bodyMap.put("messages", messages);

        // ChatGPT에게 질문 전달
        String content = getGptReponse(bodyMap);

        // 리턴할 리스트에 영어 동화 내용 추가
        tale_content.add(content);


        // 동화 내용 추가
        messages.add(
                ChatGptMessage.builder()
                        .role("assistant")
                        .content(content)
                        .build()
        );

        // 영어 동화의 번역본 생성
        messages.add(
                ChatGptMessage.builder()
                        .role("user")
                        .content("Please change the above fairy tale to Korean.")
                        .build()
        );

        tale_content.add(getGptReponse(bodyMap));

        // 번역본을 얻기위한 질문을 제거
        messages.remove(messages.size()-1);

        // 제목을 얻기위한 내용 추가
        messages.add(
                ChatGptMessage.builder()
                        .role("user")
                        .content("Please give me a title for the fairy tale above.")
                        .build()
        );

        // 영어로된 제목 생성
        String content_kor = getGptReponse(bodyMap);
        // 영어로된 제목 리턴 값에 추가
        tale_content.add(content_kor);

//        // 영어 제목 답변 저장
//        messages.add(
//                ChatGptMessage.builder()
//                        .role("assistant")
//                        .content(content_kor)
//                        .build()
//        );
//
//        // 한글 제목 얻기위한 답변
//        messages.add(
//                ChatGptMessage.builder()
//                        .role("user")
//                        .content("Please change the above title to Korean.")
//                        .build()
//        );
//
//        tale_content.add(getGptReponse(bodyMap));
//        System.out.println("tale_content = " + tale_content);
        return tale_content;
    }

    /**
     * ChatGPT에게 API 보내서 응답 받음
     * @param bodyMap Request문
     * @return  String형 응답
     */
    String getGptReponse(Map<String, Object> bodyMap){
        WebClient webClient =
                WebClient
                        .builder()
                        .baseUrl("https://api.openai.com")
                        .build();

        String response =
                webClient
                        .post()
                        .uri("/v1/chat/completions")
                        .header("Authorization", "Bearer " + key)
                        .header("Content-Type","application/json;charset=utf-8")
                        .bodyValue(bodyMap)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

        JSONObject rjson = new JSONObject(response);
        JSONArray choices = rjson.getJSONArray("choices");
        JSONObject itemJson = choices.getJSONObject(0);
        JSONObject msg = itemJson.getJSONObject("message");
        return msg.getString("content");
    }

//    private static void verifyKeywordIncluded(List<String> keywords) {
//        if (keywords.isEmpty()) {
//            throw new CustomException(ErrorCode.INVALID_PARAMETER);
//        }
//    }
}