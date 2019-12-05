package com.baizhi.cmfz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class Article implements Serializable {
    @Id
    private String id;
    private String title;
    private String img;
    private String content;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date create_date;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date publish_date;
    private String status;
    private String guru_id;
    private Guru guru;
}
