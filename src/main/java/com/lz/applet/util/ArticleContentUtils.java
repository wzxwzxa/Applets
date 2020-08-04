package com.lz.applet.util;

import com.lz.applet.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文章正文内容的图片拼接
 *
 * @author dell
 * @date 2020-06-03 14:14
 */
public class ArticleContentUtils {

    public static Article articleContentUtil(MultipartFile[] files, Article article,String phoneWidth, HashMap<String,String> hashMap){
        StringBuffer stringBuffer = new StringBuffer();
        for (MultipartFile multipartFile : files) {
            if (null!=multipartFile && multipartFile.getSize()!=0){
                //获取上传文件的名称
                String originalFilename = multipartFile.getOriginalFilename();
                //获取存入fastDfs的路径
                String fastDfsPath = hashMap.get(originalFilename);
                //要替换的文件名
                Pattern compile = Pattern.compile(originalFilename);
                //要查找的字符串
                Matcher matcher = compile.matcher(article.getContent());
                System.out.println(matcher);
                //对返回的字符串再做修改要修改为src路径
                String replaceString = replaceString(fastDfsPath, phoneWidth);

                //要替换的值,返回最后的替换完的字符串
                String temp = matcher.replaceAll(replaceString);

                article.setContent(temp);
                stringBuffer.append(fastDfsPath);
                stringBuffer.append(",");
            }
        }
        //将拼接的字符串转换为string
        String img = new String(stringBuffer);
        //将图片路径到文章对象中
        article.setImgs(img);
        return article;
    }


    /**
     * 拼装存入数据库的路径
     * @param  path        fast文件的访问路径
     * @param phoneWidth   图片的尺寸
     * @return
     */
    private static String replaceString(String path,String phoneWidth){
        String front = "<img src='";
        String style = "' "+phoneWidth;
        String last = ">";
        String temp = front+path+style+last;
        System.out.println("拼装后的路径*********"+temp);
        return temp;
    }

}
