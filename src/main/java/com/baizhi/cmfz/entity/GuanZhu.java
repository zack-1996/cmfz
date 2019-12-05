package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GuanZhu implements Serializable {
    @Id
    private String id;
    private String user_id;
    private String guru_id;
}
