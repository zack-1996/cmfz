package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Log {
    @Id
    private String id;
    private String name;
    private String action;
    private Date time;
    private String status;
}
