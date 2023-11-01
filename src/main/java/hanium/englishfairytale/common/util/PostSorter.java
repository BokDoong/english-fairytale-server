package hanium.englishfairytale.common.util;

import hanium.englishfairytale.post.infra.dto.PostLikesQueryDto;
import lombok.experimental.UtilityClass;

import java.util.*;

@UtilityClass
public class PostSorter {
    public LinkedHashMap<Long, List<PostLikesQueryDto>> sortPostsByLikes(Map<Long, List<PostLikesQueryDto>> map) {
        // map 에서 좋아요 개수를 각 taleId 별로 뽑아서 HashMap 안에 저장
        Map<Long, Integer> tmpHashMap = convertToCountLikesMap(map);
        // Value 의 Value(좋아요 개수)로 정렬 -> List 뽑기
        List<Map.Entry<Long, Integer>> entryList = sortMapByLikesCount(tmpHashMap);
        // List -> LinkedMap 으로 리턴
        return postIdsSortedByLikes(map, entryList);
    }

    private static LinkedHashMap<Long, List<PostLikesQueryDto>> postIdsSortedByLikes(Map<Long, List<PostLikesQueryDto>> map, List<Map.Entry<Long, Integer>> entryList) {
        LinkedHashMap<Long, List<PostLikesQueryDto>> result = new LinkedHashMap<>();
        for (Map.Entry<Long, Integer> entry : entryList) {
            result.put(entry.getKey(), map.get(entry.getKey()));
        }
        return result;
    }

    private static List<Map.Entry<Long, Integer>> sortMapByLikesCount(Map<Long, Integer> tmpHashMap) {
        List<Map.Entry<Long, Integer>> entryList = new LinkedList<>(tmpHashMap.entrySet());
        entryList.sort((Comparator.comparingInt(o -> tmpHashMap.get(o.getKey()))));
        Collections.reverse(entryList);
        return entryList;
    }

    private static Map<Long, Integer> convertToCountLikesMap(Map<Long, List<PostLikesQueryDto>> map) {
        Map<Long, Integer> tmpHashMap = new HashMap<>();
        map.forEach((key, value) -> {
            tmpHashMap.put(key, value.size());
        });
        return tmpHashMap;
    }
}
