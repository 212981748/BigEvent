package com.kabu.bif_eventdemo.pojo;

import com.sun.istack.NotNull;
import lombok.*;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    private Integer id;//主键ID
    @NotNull
    private String categoryName;//分类名称
    @NotNull
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
