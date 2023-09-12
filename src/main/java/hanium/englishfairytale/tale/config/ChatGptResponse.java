package hanium.englishfairytale.tale.config;

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

/*
{
        "id": "chatcmpl-7xUMZHZBeysILEKTnffrXEtGOlEfJ",
        "object": "chat.completion",
        "created": 1694412423,
        "model": "gpt-3.5-turbo-0613",
        "choices": [
        {
        "index": 0,
        "message": {
        "role": "assistant",
        "content": "The Los Angeles Dodgers won the World Series in 2020."
        },
        "finish_reason": "stop"
        }
        ],
        "usage": {
        "prompt_tokens": 27,
        "completion_tokens": 13,
        "total_tokens": 40
        }
        }
*/
/*
{
    id=chatcmpl-7xUHNNFkIfvwjvH8VZ4Csm71smnd1, object=chat.completion,
    created=1694412101,
    model=gpt-3.5-turbo-0613,
    choices=[
        {
            index=0,
            message=
                {
                    role=assistant,
                    content=Once upon a time in the enchanted land of Mysteria, there lived a young girl named Lily. She had always been fascinated with mystical creatures and magical worlds, but she had never seen a fairy.

Every night before going to bed, Lily would close her eyes and wish upon the brightest star in the sky, hoping to catch a glimpse of a fairy. One night, as she made her wish, a shooting star streaked across the heavens and landed in her backyard.

Curiosity filled Lily’s heart as she followed the gentle glow of the fallen star. To her amazement, she discovered a tiny, sparkling fairy with delicate wings and shimmering blue attire. The fairy introduced herself as Aurora, the Guardian of Dreams.

Aurora explained that she had been sent by the fairy queen to grant Lily one wish. Overjoyed, Lily shared her heartfelt desire to explore the magical world of the fairies and all its wonders. Aurora smiled, waved her wand, and in an instant, they shrunk down to fairy size.

Hand in hand, Lily and Aurora ventured into the magnificent Fairy Forest. Towering trees sparkled with glowing leaves, and the air was filled with the sweet melody of hummingbirds. Lily's eyes twinkled with astonishment as they journeyed deeper into the forest.

They met fairies of all kinds, each with their own unique magic. There were pixies who could paint beautiful rainbows, water fairies who could manipulate rivers and lakes, and fire fairies with flames dancing upon their fingertips.

Lily learned that fairies were not only enchanting but also wise, offering guidance and support to those in need. The fairies shared their ancient tales of bravery, love, and perseverance, which inspired Lily to become the hero of her own story.

But all adventures must come to an end, and it was time for Lily to return home. Aurora promised that the memories they had created would live forever in Lily's heart. With a magical twinkle in her eyes, she used her wand one last time to transform Lily back into her human form.

As Lily opened her eyes, she found herself lying on the grass in her own backyard. The sun was rising, leaving a trail of vibrant colors across the sky. She knew that her encounter with the fairy world had been real.

From that day on, Lily kept the spirit of the fairies alive in her heart. She believed that magic existed both in the world around her and within herself. Through her stories and imagination, she spread that magic to others, just as the fairies had done for her.

And so, the tale of Lily and Aurora became a legend passed down through generations, a reminder of the enchantment that can be found in the most unexpected places and the importance of never letting go of your dreams.
            },
            finish_reason=stop
        }
    ],
    usage={
        prompt_tokens=26,
        completion_tokens=564,
        total_tokens=590
    }
}
 */