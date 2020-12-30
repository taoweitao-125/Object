package moudle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xiaogege
 * @描述：
 * @date 2020/12/16
 * @time 8:58
 */
@Getter
@Setter
@ToString
public class Driver {
    private Integer id;
    private String username;
    private String password;
    private String name;//姓名
    private Integer age;//年龄
    private String sex;//性别
    private String idCard;//身份证号
    private String carNum;//驾驶证编号
}
