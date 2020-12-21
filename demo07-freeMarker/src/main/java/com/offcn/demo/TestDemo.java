package com.offcn.demo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class TestDemo {

    //    第一步：创建一个 Configuration 对象，直接 new 一个对象。构造方法的参数就是 freemarker的版本号。
    //    第二步：设置模板文件所在的路径。
    //    第三步：设置模板文件使用的字符集。一般就是 utf-8.
    //    第四步：加载一个模板，创建一个模板对象。
    //    第五步：创建一个模板使用的数据集，可以是 pojo 也可以是 map。一般是 Map。
    //    第六步：创建一个 Writer 对象，一般创建一 FileWriter 对象，指定生成的文件名。
    //    第七步：调用模板对象的 process 方法输出文件。
    //    第八步：关闭流
    @Test
    public void test(){
        Writer writer = null;
        try {
            //    第一步：创建一个 Configuration 对象，直接 new 一个对象。构造方法的参数就是 freemarker的版本号。
            Configuration configuration = new Configuration(Configuration.getVersion());
            //    第二步：设置模板文件所在的路径。
            configuration.setDirectoryForTemplateLoading(new File("src/main/resources/"));
            //    第三步：设置模板文件使用的字符集。一般就是 utf-8.
            configuration.setDefaultEncoding("utf-8");
            //    第四步：加载一个模板，创建一个模板对象。
            Template template = configuration.getTemplate("template.ftl");
            //    第五步：创建一个模板使用的数据集，可以是 pojo 也可以是 map。一般是 Map。
            Map map = new HashMap();
            map.put("name","hanhan");
            map.put("message","template");
            map.put("success",true);

            List goodsList=new ArrayList();
            Map goods1=new HashMap();
            goods1.put("name", "苹果");
            goods1.put("price", 5.8);
            Map goods2=new HashMap();
            goods2.put("name", "香蕉");
            goods2.put("price", 2.5);
            Map goods3=new HashMap();
            goods3.put("name", "橘子");
            goods3.put("price", 3.2);
            goodsList.add(goods1);
            goodsList.add(goods2);
            goodsList.add(goods3);
            map.put("goodsList", goodsList);

            map.put("today", new Date());

            map.put("point", 102920122);
            //    第六步：创建一个 Writer 对象，一般创建一 FileWriter 对象，指定生成的文件名。
            writer = new FileWriter(new File("src/main/resources/","test.html"));
            //    第七步：调用模板对象的 process 方法输出文件。
            template.process(map,writer);
            //    第八步：关闭流
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer!=null)
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
