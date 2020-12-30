package exception;

import lombok.Getter;

/**
 * @author xiaogege
 * @描述：
 * @date 2020/12/22
 * @time 21:45
 */

@Getter
public class AppException extends RuntimeException{
    private String code;
    public AppException(String code,String message) {
        this(code,message,null);
    }
    public AppException(String code,String message,Throwable cause) {
        super(message,cause);
        this.code = code;
    }
}
