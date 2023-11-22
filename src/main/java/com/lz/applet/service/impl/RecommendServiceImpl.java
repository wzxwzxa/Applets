package com.lz.applet.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lz.applet.entity.Article;
import com.lz.applet.mapper.ArticleMapper;
import com.lz.applet.service.RecommendService;
import com.lz.applet.util.FastDfsUtils;
import com.lz.applet.util.ProcessRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dell
 * @date 2020-06-08 15:38
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private ArticleMapper articleMapper;

    @Value("${fast.path}")
    private String fastPath;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /**
     * 根据栏目ID查询推荐信息
     *
     * @param columnId 栏目ID
     * @return
     */
    @Override
    public List<Article> getRecommendList(int columnId) {
        List<Article> articleList = articleMapper.backstageSelectRecommendList(columnId);
        //这里有视频和文章的内容需要对图片部分内容进行处理
        List<Article> articleList1 = ProcessRequestUtils.processImg(articleList);
        return articleList1;
    }

    /**
     * 删除推荐类的文章或视频信息
     *
     * @param recommendId 文章或视频的ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRecommendById(int recommendId) {
        //首先需要查询出对应id的文章信息，先删除图片存储地址
        Article article = articleMapper.selectByPrimaryKey(recommendId);
        //先判断是否有图片
        if ("".equals(article.getImgs()) || null == article.getImgs()) {
            int i = articleMapper.deleteByPrimaryKey(recommendId);
            return i;
        }
        //如果图片类型等于1那么就是视频类的封面图片不需要进行拆分直接删除
        boolean isOk = false;
        //article.getImgType()==1表示这个是视频类的信息需要删除封面的图片之间删除即可
        if (article.getImgType() == 1) {
            isOk = FastDfsUtils.deleteFastFile(article.getImgs(), fastFileStorageClient);
        } else if (null != article.getImgs() && article.getImgs().length() > 0) {
            isOk = FastDfsUtils.deleteFile(article.getImgs(), fastPath, fastFileStorageClient);
        } else {
            isOk = true;
        }
        if (isOk) {
            int i = articleMapper.deleteByPrimaryKey(recommendId);
            return i;
        }
        System.out.println(article.getImgs().length());

        return 0;
    }

    /**
     * 查询推荐 类的文章/视频信息
     *
     * @param columnId 推荐类id
     * @param pageNum  从那页开始查询数据
     * @param pageSize 每页展示多少条数据
     * @return
     */
    @Override
    public List<Article> getRecommendListPaging(int columnId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = articleMapper.backstageSelectAll(columnId);
        return articleList;
    }

    /**
     * 查询推荐类数据的总数
     *
     * @param columnId
     * @return
     */
    @Override
    public int selectCount(int columnId) {
        int count = articleMapper.selectCount(columnId);
        return count;
    }
}
