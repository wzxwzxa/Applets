package com.lz.applet.entity;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.CountDownLatch;

public class FileUploadRunnable implements Runnable {
    private String url ;

    //文件id
    private String fileId;

    //分块编号
    private int num;

    private CountDownLatch countDownLatch;

    //当前分段大小
    private long partSize;

    //当前分段在输入流中的起始位置
    private long partStart;

    //总文件
    private File file;

    public FileUploadRunnable(String url, String fileId, int num, CountDownLatch countDownLatch, File file, long partSize, long partStart){
        this.url =url;
        this.fileId=fileId;
        this.num=num;
        this.countDownLatch=countDownLatch;
        this.partSize=partSize;
        this.partStart =partStart;
        this.file =file;
    }

    //临时文件名 temp_+filId+_partId.temp
    @Override
    public void run() {
        try{
            FileInputStream fis = new FileInputStream(file);
            CloseableHttpClient ht = HttpClients.createDefault();
            String tempFileName ="temp_"+fileId+"_"+num+".temp";
            HttpPost post = new HttpPost(url+partSize+"/"+tempFileName+"/"+partStart);
            HttpResponse response;
            //跳过起始位置
            fis.skip(partStart);


            System.out.println("开始上传分块:"+num);
            //请求接收分段上传的地址
            post.setEntity(new InputStreamEntity(fis,fis.getChannel().size()));
            response=ht.execute(post);
            if(response.getStatusLine().getStatusCode()==200){
                String ret = EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.println(ret);
                System.out.println("分块"+num+"上传完毕");
                countDownLatch.countDown();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
