package cn.sccl.ilife.android.core.httpclient;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import static cn.sccl.ilife.android.core.httpclient.ILifeConstants.CHARSET_UTF8;
import static com.google.gson.stream.JsonToken.BEGIN_ARRAY;

public class HttpClientUtils {
    private static  final int BUFFER_SIZE = 8192;

    /**
     * 查看请求是否真的有错误
     * @param error
     * @return
     */
    public static boolean isRequestError(ILifeRequestError error) {
        return error.getMessage() != null;
    }

    /**
     * 将响应获得的byte转换成为json，在onSuccess方法当中打印出来
     *
     * @param type
     *            转换POJO
     * @param listType
     * @param responseBody
     * @param <T>
     * @return
     */
    public static <T> T parseByte2JsonPojo(Type type, Type listType,
                                     byte[] responseBody) {
        ByteArrayInputStream bis = new ByteArrayInputStream(responseBody);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    bis, CHARSET_UTF8), BUFFER_SIZE);
            Gson gson = new Gson();
            if (listType == null) {
                return gson.fromJson(reader, type);
            } else {
                JsonReader jsonReader = new JsonReader(reader);
                if (jsonReader.peek() == BEGIN_ARRAY) {
                    return gson.fromJson(reader, listType);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
