package com.baizhi.cmfz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    @Id
    private String id;
    private String name;
    private String password;
    private String phone;
    private String salt;
    private String status;
    private String photo;
    private String nick_name;
    private String sex;
    private String sign;
    private String location;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date register_date;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date last_login;
}
