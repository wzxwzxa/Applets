package com.lz.applet.util;

import com.lz.applet.entity.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * 请求参数处理工具类
 *
 * @author dell
 * @date 2020-06-05 09:48
 */
public class ProcessRequestUtils {

    /**
     * 处理图片的信息
     * 这里存储了所有的图片信息，请求需求只需要一张图片就处理后返回一张
     */
    public static List<Article> processImg(List<Article> articleList) {
        ArrayList<Article> articleArrayList = new ArrayList<>();
        if (null != articleList && articleList.size() != 0) {
            for (Article article : articleList) {
                System.out.println(article);
                String imgString = article.getImgs();

                if (1 == article.getImgType()) {
                    articleArrayList.add(article);
                } else if (null != imgString && !imgString.equals("")) {
                    String substring = imgString.substring(0, imgString.indexOf(","));
                    article.setImgs(substring);
                    articleArrayList.add(article);
                    //如果没有图片就直接返回
                } else {
                    articleArrayList.add(article);
                }
            }
        }
        return articleArrayList;
    }

}
