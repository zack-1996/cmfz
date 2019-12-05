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
public class Album implements Serializable {
    @Id
    private String id;
    private String title;
    private String score;
    private String author;
    private String broadcast;
    private String status;
    private Integer count;
    private String description;
    private String img;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date publish_date;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date create_date;
}
