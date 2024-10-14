package com.kabu.bif_eventdemo.pojo;



import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class User {
   @NotNull
    private Integer id;//主键ID
    private String username;//用户名
    private String password;//密码
    private String nickname;//昵称
    @NotNull
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
