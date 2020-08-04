package com.lz.applet.util;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * 文件上传FastDFS工具类
 *
 * @author dell
 * @date 2020-06-03 11:06
 */
public class FastDfsUtils {
    /**
     * 上传文件
     * @return
     */
    public static HashMap<String,String> upload(MultipartFile[] multipartFiles,FastFileStorageClient fastFileStorageClient,String fastPath){
        HashMap<String, String> hashMap = new HashMap<>(16);
        //遍历文件数组
        for (MultipartFile multipartFile : multipartFiles) {
            if (null!=multipartFile && multipartFile.getSize()!=0){
                //获取到这个文件的长度，在上传的时候需要使用
                long size = multipartFile.getSize();
                //获取这个文件名称
                String originalFilename = multipartFile.getOriginalFilename();
                //获取文件的后缀名，通过截取的方式
                String exName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                //将multipartFile转换为一个byte数组再放入ByteArray用InputStream接收获取到输入流
                InputStream inputStream = null;
                try {
                    inputStream = new ByteArrayInputStream(multipartFile.getBytes());
                    //使用fastDfs的客户端，参数一：文件的输入流，参数二：文件的长度，参数三：文件的后缀名
                    StorePath storePath = fastFileStorageClient.uploadFile(inputStream,size,exName,null);
                    //打印fastDfs存储的文件路径

                    hashMap.put(originalFilename,fastPath+storePath.getFullPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return hashMap;
    }

    /**
     * 删除fastDfs文件
     * @param articleImg 数据库文件路径
     * @param fastPath 文件ip
     * @return
     */
    public static boolean deleteFile(String articleImg,String fastPath,FastFileStorageClient fastFileStorageClient){
        StringBuffer buffer = new StringBuffer();
        buffer.append(articleImg);
        while (null!=buffer && buffer.length()>0){
            //取出第一部分图片路径
            String imgPath = buffer.substring(0, buffer.indexOf(","));
            System.out.println("第一部分==="+imgPath);
            //替换第一部分的ip,替换为空"",返回的路径就是fastDfs存储的文件路径
            String fastDfsPath = imgPath.replace(fastPath, "");
            //调用删除fast文件的client客户端进行删除
            boolean isOk = deleteFastFile(fastDfsPath, fastFileStorageClient);
            if (isOk){
                //把 buffer转换为String，后续进行字符串替换
                String temp = new String(buffer);
                //将已经删除后的文件替换为空""。
                String newImg = temp.replace(imgPath + ",", "");
                //将原有的buffer置为空
                buffer.delete(0,buffer.length());
                //为空的buffer赋值附上最新的img路径信息
                buffer.append(newImg);
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * 删除fast文件
     * @param fastDfsPath 要删除的文件路径
     */
    public static boolean deleteFastFile(String fastDfsPath,FastFileStorageClient fastFileStorageClient){
        try {
            //调用fastDfs客户端删除文件
            fastFileStorageClient.deleteFile(fastDfsPath);
            //删除文件5
            System.out.println("删除文件："+fastDfsPath);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除文件失败"+e.getMessage());
            return false;
        }
    }

    public static String uploadLecturer(MultipartFile multipartFile, FastFileStorageClient fastFileStorageClient, String fastPath) {
        //获取文件的长度，上传图片时需要这个参数
        long size = multipartFile.getSize();
        //获取文件的名称，通过名称获取后缀
        String filename = multipartFile.getOriginalFilename();
        //截取到后缀名称
        String exName = filename.substring(filename.lastIndexOf(".") + 1);
        try {
            //将multipartFile转换为byte数组再放入byteArray,用InputStream接收获取到输入流
            InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
            //使用fastDfs的客户端，参数一：文件的输入流，参数二：文件的长度，参数三：文件的后缀名
            StorePath storePath = fastFileStorageClient.uploadFile(inputStream, size, exName, null);
            return fastPath+storePath.getFullPath();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
