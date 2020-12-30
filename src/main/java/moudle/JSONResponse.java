package moudle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xiaogege
 * @描述： 序列化信息
 * @date 2020/12/22
 * @time 21:31
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class JSONResponse {
    //    业务操作是否成功
    private boolean success;
    //    错误码：开发人员看的，用来定位错误
    private String code;
    //    错误信息：给用户看
    private String message;
    //    业务数据
    private Object data;
}
