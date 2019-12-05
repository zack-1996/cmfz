package com.baizhi.cmfz;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baizhi.DemoData;
import com.baizhi.cmfz.util.MessageUtil;
import com.baizhi.cmfz.util.SecurityCode;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelTest1 {
    String fileName="C:\\Users\\zhangte\\Desktop\\img\\DemoData4.xlsx";
    @Test
    public void testWrite(){
        //方法一
        //EasyExcel.write(fileName,DemoData.class).sheet("模板").doWrite(data());
        //方法2
        ExcelWriter build = EasyExcel.write(fileName, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        build.write(data(),writeSheet);
        build.finish();
    }
    @Test
    public void testRead(){
        //第一种方法
        //EasyExcel.read(fileName,DemoData.class,new DemoDataListener()).sheet().doRead();
        //第二种方法
        ExcelReader excelRead = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelRead.read(readSheet);
        excelRead.finish();
    }
    @Test
    public void testOrderWrite(){
        //根据用户传入字段，假设我们要忽略 date
        Set<String> excludeColumnFiledNames = new HashSet<String>();
        excludeColumnFiledNames.add("date");
        //这里需要指定写用哪个class去写，然后写到第一个sheet，名字为模板，然后文件流会自动关闭
       EasyExcel.write(fileName,DemoData.class).includeColumnFiledNames(excludeColumnFiledNames).sheet("模板").doWrite(data());

    }
    @Test
    public void testImage() throws IOException {
        ImageData imageData=new ImageData();
        imageData.setFile(new File("C:\\Users\\zhangte\\Desktop\\img\\2019-08-26\\4173078.jpg"));
        imageData.setByteArray(FileUtils.readFileToByteArray(new File("C:\\Users\\zhangte\\Desktop\\img\\2019-08-26\\4173078.jpg")));
        imageData.setInputStream(new FileInputStream(new File("C:\\Users\\zhangte\\Desktop\\img\\2019-08-26\\4173078.jpg")));
        imageData.setString("C:\\Users\\zhangte\\Desktop\\img\\2019-08-26\\4173078.jpg");
        List<ImageData> imageData1 = Arrays.asList(imageData);
        EasyExcel.write(fileName,ImageData.class).sheet().doWrite(imageData1);
    }
    @Test
    public void testIndex(){

    }
    private List<DemoData> data(){
        DemoData demoData1 = new DemoData("1","张特",23, new Date(), 13.5, "Rxx");
        DemoData demoData2 = new DemoData("2","张特",23, new Date(), 14.5, "Rxx");
        DemoData demoData3 = new DemoData("3","张特",23, new Date(), 17.5, "Rxx");
        DemoData demoData4 = new DemoData("4","张特",23, new Date(), 20.5, "Rxx");
        List<DemoData> demoData = Arrays.asList(demoData1, demoData2, demoData3, demoData4);
        return demoData;
    }
    @Test
    public void messageTest(){
        String code = SecurityCode.getSecurityCode();
        MessageUtil.sendMessage("15515242561",code);
        System.out.println(code);
    }
}
