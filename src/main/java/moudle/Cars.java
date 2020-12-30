package moudle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xiaogege
 * @描述：
 * @date 2020/12/16
 * @time 9:02
 */
@ToString
@Setter
@Getter
public class Cars {
    private Integer id;
    private String num; //车牌号
    private String color;//颜色
    private String type;//车型
    private String capacity;//容量
    private String message;//车辆信息
    private Integer driverId;//车主Id
}
