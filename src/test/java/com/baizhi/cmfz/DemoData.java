package com.baizhi;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by HIAPAD on 2019/12/2.
 * @ExcelProperty("字符串标题") value = "" 对应生成excel的表头 index="" 指定该属性出现在哪一列
 * @ExcelIgnore 忽略该字段
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoData {
    @ExcelProperty(value = "id",index = 0)
    private String string;
    @ExcelProperty(value = "姓名",index = 1)
    private String name;
    @ExcelProperty(value = "年龄",index = 2)
    private Integer age;
    @ExcelProperty(value = "出生日期",index = 3)
    private Date date;
    @ExcelProperty(value = "工资",index = 4)
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}