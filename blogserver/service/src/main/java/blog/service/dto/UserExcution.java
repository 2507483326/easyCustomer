package blog.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Epat on 2017/2/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserExcution implements Serializable{

    private Long id ;

    private Integer state ;

    private String avatar ;

    private String loginName ;

    private String nickName ;

    //用户注册城市
    private String regCity ;

    private String regIp ;

    //用户注册省份
    private String regProvince ;

    // 用户注册时间
    private Date regTime ;

    // 是否是记住我登录
    private Boolean isRemember;
}
