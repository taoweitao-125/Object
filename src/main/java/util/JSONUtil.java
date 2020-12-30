package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaogege
 * @描述：
 * @date 2020/12/22
 * @time 22:18
 */
public class JSONUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String serialize(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("java对象序列化为JSON出错"+o,e);
        }
    }

    public static <T> T deserialize(InputStream is, Class<T> clazz) {
        try{
            return MAPPER.readValue(is,clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("反序列化失败");
        }
    }
}
