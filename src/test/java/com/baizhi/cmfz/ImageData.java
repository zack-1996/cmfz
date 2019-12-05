package com.baizhi.cmfz;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.converters.string.StringImageConverter;
import lombok.Data;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

@Data
@HeadRowHeight(50)
@ContentRowHeight(100)
@ColumnWidth(100 / 8)
public class ImageData {
    private File file;
    private InputStream inputStream;
    /**
     * 如果String类型，必须制定转换器，String默认转换成string
     * 绝对路径 继承StringImageConverter 重写方法
     * */
    @ExcelProperty(converter = StringImageConverter.class)
    private String string;
    private byte[] byteArray;
    private URL url;
}
