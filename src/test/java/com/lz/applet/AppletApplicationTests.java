package com.lz.applet;

import com.lz.applet.entity.FileUploadRunnable;
import com.lz.applet.entity.HisDataEntity;
import com.lz.applet.entity.KMeansEntity;
import com.lz.applet.entity.WatermelonEntity;
import com.lz.applet.entity.template.AskForLeaveFlow;
import com.lz.applet.entity.template.CompanyB;
import com.lz.applet.service.HisDataService;
import com.lz.applet.util.DateUtils;
import com.lz.applet.util.RedExcelUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppletApplicationTests<aprioriDislodge> {

    final static Base64.Encoder encoder = Base64.getEncoder();
    final static Base64.Decoder decoder = Base64.getDecoder();

   @Autowired
   private HisDataService hisDataService;

   String nameSrvAddr = "8.137.18.41:9876";

   @Test
   public void test145() throws Exception {

       DefaultMQProducer producer = new DefaultMQProducer("async-producer-group");
       producer.setNamesrvAddr(nameSrvAddr);
       producer.start();
       Message message = new Message("asyncTopic", "我是一个异步消息".getBytes());
       producer.send(message, new SendCallback() {
           @Override
           public void onSuccess(SendResult sendResult) {
               System.out.println("发送成功");
           }

           @Override
           public void onException(Throwable e) {
               System.out.println("发送失败");
           }
       });

       System.out.println("我先执行");
       System.in.read();

   }

   @Test
   public void test144(){

      new Thread(()->{
          System.out.println(Thread.currentThread().getName());
      },"111").start();

      new Thread(){
          @Override
          public void run() {
              System.out.println(Thread.currentThread().getName()+"222");
          }
      }.start();

      new Thread(()-> new Runnable(){

          @Override
          public void run() {
              System.out.println(Thread.currentThread().getName());
          }
      },"dududu").start();

       int[] result = new int[]{};



   }

   @Test
   public void test143() throws InterruptedException {
       Object o = new Object();
       Thread thread = new Thread(() -> {
           synchronized (o) {
               System.out.println("11111111");
           }
       });
       synchronized (o){
           thread.start();
//           Thread.sleep(500);
           System.out.println(thread.getState());
       }
   }

   @Test
   public void test142(){

       String path = "C:\\Users\\lenono\\Desktop\\consumption.xlsx";
       int i = path.lastIndexOf(".");
       System.out.println(i);
       List<KMeansEntity> kMeansEntities = RedExcelUtils.redExcel(path);

       ArrayList<KMeansEntity> List1 = new ArrayList<>();
       ArrayList<KMeansEntity> List2 = new ArrayList<>();
       ArrayList<KMeansEntity> List3 = new ArrayList<>();
       //设置前三个元素为中心点
       KMeansEntity kMeansEntity1 = kMeansEntities.get(0);
       KMeansEntity kMeansEntity2 = kMeansEntities.get(1);
       KMeansEntity kMeansEntity3 = kMeansEntities.get(2);

       Boolean isFlag = true;
       while (isFlag){
           ArrayList<KMeansEntity> m1List = new ArrayList<>();
           ArrayList<KMeansEntity> m2List = new ArrayList<>();
           ArrayList<KMeansEntity> m3List = new ArrayList<>();
           for (KMeansEntity kMeansEntity : kMeansEntities) {
               String s = kMeans(kMeansEntity, kMeansEntity1, kMeansEntity2, kMeansEntity3);
               if (s.equals("m1")){
                   m1List.add(kMeansEntity);
               }else if (s.equals("m2")){
                   m2List.add(kMeansEntity);
               }else {
                   m3List.add(kMeansEntity);
               }
           }
           //判断两次的聚集是否相同，如果相同就退出循环
           if (kMeansIsFlag(List1,List2,List3,m1List,m2List,m3List)){
               System.out.println("最后的三个中心点");
               System.out.println(kMeansEntity1+"聚集个数"+m1List.size());
               System.out.println(kMeansEntity2+"聚集个数"+m2List.size());
               System.out.println(kMeansEntity3+"聚集个数"+m3List.size());
               isFlag = false;
           }else {
               List1.clear();
               List2.clear();
               List3.clear();
               List1.addAll(m1List);
               List2.addAll(m2List);
               List3.addAll(m3List);
               kMeansEntity1 = kMeansMath(m1List);
               kMeansEntity2 = kMeansMath(m2List);
               kMeansEntity3 = kMeansMath(m3List);
           }
       }

   }

   //判断是否还要继续向下执行
   public boolean kMeansIsFlag(List<KMeansEntity> list1,List<KMeansEntity> list2,List<KMeansEntity> list3,List<KMeansEntity> m1List,List<KMeansEntity> m2List,List<KMeansEntity> m3List){
       HashSet<KMeansEntity> m1 = new HashSet<>(list1);
       m1.addAll(m1List);
       HashSet<KMeansEntity> m2 = new HashSet<>(list2);
       m2.addAll(m2List);
       HashSet<KMeansEntity> m3 = new HashSet<>(list3);
       m3.addAll(m3List);
       if (m1List.size()==list1.size() && m1.size()==((list1.size()+m1List.size())/2) && m2List.size()==list2.size() && m2.size()==((list2.size()+m2List.size())/2) && m3List.size()==list3.size() && m3.size()==((list3.size()+m3List.size())/2)){
           return true;
       }

       return false;
   }

    /**
     * 重新计算点
     * @return
     */
   public KMeansEntity kMeansMath(List<KMeansEntity> list){
       KMeansEntity kMeansEntity1 = new KMeansEntity();
       double t = 0.0;
       double f = 0.0;
       double m = 0.0;
       for (KMeansEntity kMeansEntity : list) {
           t+=kMeansEntity.getTime();
           f+=kMeansEntity.getFrequency();
           m+=kMeansEntity.getMoney();
       }
       kMeansEntity1.setTime(t/list.size());
       kMeansEntity1.setFrequency(f/list.size());
       kMeansEntity1.setMoney(m/list.size());
       return kMeansEntity1;
   }
    /**
     *
     * @param kMeansEntity 比较的数据
     * @param kMeansEntity1 中心点1
     * @param kMeansEntity2 中心点2
     * @param kMeansEntity3 中心点3
     * @return
     */
   public String kMeans(KMeansEntity kMeansEntity,KMeansEntity kMeansEntity1,KMeansEntity kMeansEntity2,KMeansEntity kMeansEntity3){
       Double MT = kMeansEntity.getTime();
       Double MF = kMeansEntity.getFrequency();
       Double MM = kMeansEntity.getMoney();

       Double M1T = kMeansEntity1.getTime();
       Double M1F = kMeansEntity1.getFrequency();
       Double M1M = kMeansEntity1.getMoney();
       Double M2T = kMeansEntity2.getTime();
       Double M2F = kMeansEntity2.getFrequency();
       Double M2M = kMeansEntity2.getMoney();
       Double M3T = kMeansEntity3.getTime();
       Double M3F = kMeansEntity3.getFrequency();
       Double M3M = kMeansEntity3.getMoney();

//       double m1 = Math.sqrt(Math.pow(Math.abs(MT-M1T),2)+Math.pow(Math.abs(MF-M1F),2)+Math.pow(Math.abs(MM-M1M),2));
       //距离M1点的距离
       Double m1 = kMeansResult(MT, M1T, MF, M1F, MM, M1M);
       //距离M2点的距离
       Double m2 = kMeansResult(MT, M2T, MF, M2F, MM, M2M);
       //距离M3点的距离
       Double m3 = kMeansResult(MT, M3T, MF, M3F, MM, M3M);


       String s = kMeansSelectM(m1, m2, m3);

       System.out.println("数据id:"+kMeansEntity.getId()+"  "+"间隔时间:"+kMeansEntity.getTime()+"   "+"消费频率:"+kMeansEntity.getFrequency()+"  "+"消费金额:"+kMeansEntity.getMoney()+"距离M1："+m1+"   "+"距离M2："+m2+"   "+"距离M3："+m3+"   "+"归属于M1/M2/M3:"+s);


       return s;
   }

   public String kMeansSelectM(Double m1,Double m2,Double m3){
       if (m1 < m2){
           if (m3<m1){
               return "m3";
           }else {
               return "m1";
           }
       }else {
           if (m3<m2){
               return "m3";
           }else {
               return "m2";
           }
       }
   }

   public Double kMeansResult(Double t,Double t1,Double f,Double f1,double m,double m1){
       return Math.sqrt(Math.pow(Math.abs(t-t1),2)+Math.pow(Math.abs(f-f1),2)+Math.pow(Math.abs(m-m1),2));
   }

   @Test
   public void test140() throws IOException {
       //设置最小支持计数
       int min = 2;
       int num = 0;
       String lines;
       int count=0;
       //文件目录
       String fileName = "C:\\Users\\lenono\\Desktop\\dataset.txt";
       BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
       List<String[]> dataList = new ArrayList<>();
       while ((lines=bufferedReader.readLine())!=null){
           count++;
           if (num!=0){
               String[] split = lines.split(",");
               dataList.add(split);
               System.out.println(split.toString());
           }
           num++;
       }
       DecimalFormat decimalFormat = new DecimalFormat("0.00");
       //第一次全部项
       HashMap<String, Integer> hashMap = aprioriCandidates(dataList);
//       HashMap<String, Integer> hashMap1;
       //第一次扫描
       System.out.println("第1次扫描");
       System.out.println("项集**********计数*********支持度");
       for (String s : hashMap.keySet()) {
           System.out.println(s+"**********"+hashMap.get(s)+"***********"+decimalFormat.format(Float.valueOf(Float.valueOf(hashMap.get(s))/Float.valueOf(count))));
       }
       Boolean isFlag = true;
       HashMap<String, Float> confidenceMap = new HashMap<>();
       int i= 2;
       int count1 = 0;
       //对项目进行排列
       while (isFlag){
           hashMap = aprioriDelete(hashMap, min);
           System.out.println("满足最小候选支持计数与最小支持度计数");
           System.out.println("项集**********计数**********支持度");
           for (String s : hashMap.keySet()) {
               System.out.println(s+"**********"+hashMap.get(s)+"**********"+decimalFormat.format(Float.valueOf(Float.valueOf(hashMap.get(s))/Float.valueOf(count))));
               if (count1!=0){
                   confidenceMap.put(s,0.0f);
               }
           }
           if (aprioriContinue(hashMap,min)){
               HashMap<String, Integer> hashMap2 = aprioriMakeUp(hashMap);
               hashMap = aprioriCompare(hashMap2,dataList);
               System.out.println("第"+i+"次扫描");
               System.out.println("扫描所有项集");
               System.out.println("项集**********计数");
               for (String s : hashMap2.keySet()) {
                   System.out.println(s+"**********"+hashMap.get(s));
               }
               i++;
               count1++;
           }else {
               isFlag = false;
           }
       }
       System.out.println("************置信度*************");
       HashMap<String, Float> stringFloatHashMap = aprioriConfidence(confidenceMap, dataList);
       for (String s : confidenceMap.keySet()) {
           StringBuffer stringBuffer = new StringBuffer();
           String y = null;
           String[] split = s.split(",");
           for (int j = 0; j < split.length; j++) {
               if (j<(split.length-1)){
                   stringBuffer.append(split[j]+" ");
               }else {
                   y = split[j];
               }
           }
           System.out.println("出现"+stringBuffer+"同时出现"+y+"的置信度是"+stringFloatHashMap.get(s));
       }
   }

   //计算置信度 计算方式 Y在X中出现的次数 用Y/X
   public HashMap<String,Float> aprioriConfidence(HashMap<String,Float> confidenceMap,List<String[]> dataList){
       //因为每次增加一项都是key的最后一个值拆分后
       for (String s : confidenceMap.keySet()) {
           Float X = 0.0f;
           Float Y = 0.0f;
           String[] split = s.split(",");
           String[] split2 = new String[split.length-1];
           for (int i = 0; i < split.length - 1; i++) {
               split2[i] = split[i];
           }
           for (String[] strings : dataList) {
               if (aprioriCheckDnKeyword(strings, split2)){
                   X++;
               }
               if (aprioriCheckDnKeyword(strings,split)){
                   Y++;
               }
           }
           confidenceMap.put(s,(Y/X));
       }
       return confidenceMap;
   }

   //判断组合的数据是否在事务列表中存在并且大于最小支持计数的集合
    public HashMap<String, Integer> aprioriCompare(HashMap<String, Integer> hashMap2,List<String[]> dataList){
        for (String[] strings : dataList) {
            for (String s : hashMap2.keySet()) {
                if (aprioriCheckDnKeyword(strings,s.split(","))){
                    hashMap2.put(s,hashMap2.get(s)+1);
                }
            }
        }
        return hashMap2;
    }

    //比较候选项的数据是否全部在事务中出现
    public boolean aprioriCheckDnKeyword(String[] data,String[] source){
        Set<String> set = new HashSet<>();
        for (String s : source) {
            set.add(s);
        }
        for (String datum : data) {
            for (String s : source) {
                if (datum.equals(s)){
                    set.remove(s);
                }
            }
        }
        if (set.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

   //扫描组合  组合成下一轮的项集
   public HashMap<String, Integer> aprioriMakeUp(HashMap<String, Integer> hashMap){
       ArrayList<String> list = new ArrayList<>();
       Iterator<String> iterator = hashMap.keySet().iterator();
       ArrayList<String> list1 = new ArrayList<>();
       while (iterator.hasNext()){
           String key = iterator.next();
           list.add(key);
           String[] split = key.split(",");
           for (String s : split) {
               list1.add(s);
           }
       }
       List<String> collect = list1.stream().distinct().collect(Collectors.toList());
       HashMap<String, Integer> resultHashMap = new HashMap<>();
       for (int i = 0; i < list.size() - 1; i++) {
           for (String s : collect) {
               String key = list.get(i)+","+s;
               if (aprioriDislodge(resultHashMap,key)){
                   resultHashMap.put(key,0);
               }
           }
       }
       return resultHashMap;
   }

    //去除多余项集
    public Boolean aprioriDislodge(HashMap<String, Integer> resultHashMap,String key){
        //如果集合存在相同的key不加入项集
        for (String s : resultHashMap.keySet()) {
            if (aprioriCheckDnKeyword(s.split(","),key.split(","))){
                return false;
            }
        }
        //如果key中已经存在重复的直接去除key
        String[] split = key.split(",");
        ArrayList<String> list = new ArrayList(Arrays.asList(split));
        HashSet objects = new HashSet<>(list);
        return list.size() == objects.size() ? true : false;
    }

   //判断是否继续进行扫描
   public Boolean aprioriContinue(HashMap<String, Integer> hashMap,Integer min){
       int aa = 0;
       for (String s : hashMap.keySet()) {
           if (hashMap.get(s)>=min){
               aa++;
           }
       }
       if (aa>=2){
           return true;
       }else {
           return false;
       }
   }

   //删除不满足最小计数的项集
   public HashMap<String, Integer> aprioriDelete(HashMap<String, Integer> hashMap,Integer min){
       ArrayList<String> list = new ArrayList<>();
       for (String s : hashMap.keySet()) {
           if (hashMap.get(s)<min){
               list.add(s);
           }
       }
       for (String s : list) {
           hashMap.remove(s);
       }
       return hashMap;
   }

   //计算各个候选项的个数
   public HashMap<String,Integer> aprioriCandidates(List<String[]> dataList){
       HashMap<String, Integer> map = new HashMap<>();
       for (String[] strings : dataList) {
           for (String string : strings) {
               if (map.containsKey(string)){
                   map.put(string,map.get(string)+1);
               }else {
                   map.put(string,1);
               }
           }
       }
       return map;
   }

   @Test
   public void test139(){
       //文件目录
       String fileName = "C:\\Users\\lenono\\Desktop\\dataset.txt";
       try {
           //文件读取
           BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
           String lines;

           int num = 0;
           //存储是好瓜的集合
           ArrayList<WatermelonEntity> listTrue = new ArrayList<>();
           //存储是坏瓜的集合
           ArrayList<WatermelonEntity> listFalse = new ArrayList<>();
           while ((lines=bufferedReader.readLine()) != null){
               if (num!=0){
                   //把各个元素存入对应的对象字段中
                   WatermelonEntity watermelonEntity = new WatermelonEntity();
                   String[] str = lines.split(",");
                   watermelonEntity.setColour(str[1]);
                   watermelonEntity.setRoot(str[2]);
                   watermelonEntity.setSound(str[3]);
                   watermelonEntity.setVein(str[4]);
                   watermelonEntity.setUmbilicalRegion(str[5]);
                   watermelonEntity.setTouch(str[6]);
                   watermelonEntity.setTrueOrFalse(trueOrFalse(str[7]));
                   //判断是否是好瓜
                   if (watermelonEntity.getTrueOrFalse()){
                       listTrue.add(watermelonEntity);
                   }else{
                       listFalse.add(watermelonEntity);
                   }
               }
               num++;

           }
           //接收用户输入
           System.out.println("依次输入西瓜信息: 色泽,根蒂,敲声,纹理,脐部,触感 使用符号‘,’分割");
           Scanner scanner = new Scanner(System.in);
           String s1 = scanner.nextLine();
           System.out.println("用户输入信息"+s1);
           //分割用户输入的信息
           String[] data = s1.split(",");

           //好瓜和坏瓜的总数
           Double isTrue = Double.valueOf(listTrue.size());
           Double isFalse = Double.valueOf(listFalse.size());

           //记录信息 各个要素的数量信息
           Double colourTrue = 0.0,colourFalse = 0.0,
                   rootTrue = 0.0,rootFalse = 0.0,
                   soundTrue = 0.0,soundFalse = 0.0,
                   veinTrue = 0.0,veinFalse = 0.0,
                   umbilicalRegionTrue = 0.0,umbilicalRegionFalse = 0.0,
                   touchTrue = 0.0,touchFalse = 0.0;

           for (int i = 0; i < data.length; i++) {
               switch (i){
                   //根据色泽判断好瓜坏瓜数量
                   case 0:
                       for (WatermelonEntity watermelonEntity : listTrue) {
                           if (data[i].equals(watermelonEntity.getColour())){
                               colourTrue++;
                           }
                       }
                       for (WatermelonEntity watermelonEntity : listFalse) {
                           if (data[i].equals(watermelonEntity.getColour())){
                               colourFalse++;
                           }
                       }
                       //根据根蒂判断好瓜坏瓜数量
                   case 1:
                       for (WatermelonEntity watermelonEntity : listTrue) {
                           if (data[i].equals(watermelonEntity.getRoot())){
                               rootTrue++;
                           }
                       }
                       for (WatermelonEntity watermelonEntity : listFalse) {
                           if (data[i].equals(watermelonEntity.getRoot())){
                               rootFalse++;
                           }
                       }
                       //根据敲声判断好瓜坏瓜数量
                   case 2:
                       for (WatermelonEntity watermelonEntity : listTrue) {
                           if (data[i].equals(watermelonEntity.getSound())){
                               soundTrue++;
                           }
                       }
                       for (WatermelonEntity watermelonEntity : listFalse) {
                           if (data[i].equals(watermelonEntity.getSound())){
                               soundFalse++;
                           }
                       }
                       //根据纹理判断好瓜坏瓜数量
                   case 3:
                       for (WatermelonEntity watermelonEntity : listTrue) {
                           if (data[i].equals(watermelonEntity.getVein())){
                               veinTrue++;
                           }
                       }
                       for (WatermelonEntity watermelonEntity : listFalse) {
                           if (data[i].equals(watermelonEntity.getVein())){
                               veinFalse++;
                           }
                       }
                       //根据脐部判断好瓜坏瓜数量
                   case 4:
                       for (WatermelonEntity watermelonEntity : listTrue) {
                           if (data[i].equals(watermelonEntity.getUmbilicalRegion())){
                               umbilicalRegionTrue++;
                           }
                       }
                       for (WatermelonEntity watermelonEntity : listFalse) {
                           if (data[i].equals(watermelonEntity.getUmbilicalRegion())){
                               umbilicalRegionFalse++;
                           }
                       }
                       //根据触感判断好瓜坏瓜数量
                   case 5:
                       for (WatermelonEntity watermelonEntity : listTrue) {
                           if (data[i].equals(watermelonEntity.getTouch())){
                               touchTrue++;
                           }
                       }
                       for (WatermelonEntity watermelonEntity : listFalse) {
                           if (data[i].equals(watermelonEntity.getTouch())){
                               touchFalse++;
                           }
                       }
               }
           }
           System.out.println("颜色判断是好瓜的概率为:"+(colourTrue/isTrue));
           System.out.println("颜色判断是坏瓜的概率为:"+(colourFalse/isFalse));
           System.out.println("根蒂判断是好瓜的概率为:"+(rootTrue/isTrue));
           System.out.println("根蒂判断是坏瓜的概率为:"+(rootFalse/isFalse));
           System.out.println("敲声判断是好瓜的概率为:"+(soundTrue/isTrue));
           System.out.println("敲声判断是坏瓜的概率为:"+(soundFalse/isFalse));
           System.out.println("纹理判断是好瓜的概率为:"+(veinTrue/isTrue));
           System.out.println("纹理判断是坏瓜的概率为:"+(veinFalse/isFalse));
           System.out.println("脐部判断是好瓜的概率为:"+(umbilicalRegionTrue/isTrue));
           System.out.println("脐部判断是坏瓜的概率为:"+(umbilicalRegionFalse/isFalse));
           System.out.println("触感判断是好瓜的概率为:"+(touchTrue/isTrue));
           System.out.println("触感判断是坏瓜的概率为:"+(touchFalse/isFalse));
           double allPropertiesTrue = ((colourTrue/isTrue)*(rootTrue/isTrue)*(soundTrue/isTrue)*(veinTrue/isTrue)*(umbilicalRegionTrue/isTrue)*(touchTrue/isTrue));
           double allPropertiesFalse = ((colourFalse/isFalse)*(rootFalse/isFalse)*(soundFalse/isFalse)*(veinFalse/isFalse)*(umbilicalRegionFalse/isFalse)*(touchFalse/isFalse));
           System.out.println("各属性总的概率："+allPropertiesTrue);
           System.out.println("各属性总的概率："+allPropertiesFalse);
           //西瓜是好瓜的概率
           Double isGood = ((isTrue/(listTrue.size()+listFalse.size()))*allPropertiesTrue);
           //西瓜不是好瓜的概率
           Double isBad = ((isFalse/(listTrue.size()+listFalse.size()))*allPropertiesFalse);
           badOrGood(isGood,isBad);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   //输出信息 贝叶斯算法最终判断
   public void badOrGood(Double isGood,Double isBad){
       System.out.println("是一个好瓜的概率:"+isGood);
       System.out.println("是一个坏瓜的概率:"+isBad);
       if (isGood>isBad){
           System.out.println("根据贝叶斯分类算法预测是一个好瓜");
       }else {
           System.out.println("根据贝叶斯分类算法预测是一个坏瓜");
       }
   }

    //数据是否是好瓜 数据最后多个符号 需要做数据处理
    public Boolean trueOrFalse(String isTrue){
        String replace = isTrue.replace(" ", "");
        if (replace.equals("是")){
            return true;
        }
        return false;
    }


    @Test
   public void test138(){
         String fileName = "C:\\Users\\lenono\\Desktop\\dataset.txt";
       try {
           BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
           String lines;
           int num = 0;
           ArrayList<WatermelonEntity> list = new ArrayList<>();
           while ((lines=bufferedReader.readLine()) != null){
               if (num!=0){
                   WatermelonEntity watermelonEntity = new WatermelonEntity();
                   String[] str = lines.split(",");
                   watermelonEntity.setColour(str[1]);
                   watermelonEntity.setRoot(str[2]);
                   watermelonEntity.setSound(str[3]);
                   watermelonEntity.setVein(str[4]);
                   watermelonEntity.setUmbilicalRegion(str[5]);
                   watermelonEntity.setTouch(str[6]);
                   watermelonEntity.setTrueOrFalse(trueOrFalse(str[7]));
                   list.add(watermelonEntity);
               }
               num++;

           }
           //接收用户输入
           String s = "青绿,稍蜷,浊响,清晰,凹陷,硬滑";
           String[] data = s.split(",");

           Double isTrue = 0.0;
           Double isFalse = 0.0;
           for (WatermelonEntity entity : list) {
               //true总数
               if (entity.getTrueOrFalse()){
                   isTrue++;
               }else {
                   isFalse++;
               }
           }
           Double colourTrue = 0.0,colourFalse = 0.0,
               rootTrue = 0.0,rootFalse = 0.0,
               soundTrue = 0.0,soundFalse = 0.0,
               veinTrue = 0.0,veinFalse = 0.0,
           umbilicalRegionTrue = 0.0,umbilicalRegionFalse = 0.0,
           touchTrue = 0.0,touchFalse = 0.0;

           for (int i = 0; i < data.length; i++) {
               switch (i){
                   case 0:
                       for (WatermelonEntity entity : list) {
                           if (entity.getTrueOrFalse()){
                               if (data[i].equals(entity.getColour())){
                                   colourTrue++;
                               }
                           }else {
                               if (data[i].equals(entity.getColour())){
                                   colourFalse++;
                               }
                           }
                       }
                   case 1:
                       for (WatermelonEntity entity : list) {
                           if (entity.getTrueOrFalse()){
                               if (data[i].equals(entity.getRoot())){
                                   rootTrue++;
                               }
                           }else {
                               if (data[i].equals(entity.getRoot())){
                                   rootFalse++;
                               }
                           }
                       }
                   case 2:
                       for (WatermelonEntity entity : list) {
                           if (entity.getTrueOrFalse()){
                               if (data[i].equals(entity.getSound())){
                                   soundTrue++;
                               }
                           }else {
                               if (data[i].equals(entity.getSound())){
                                   soundFalse++;
                               }
                           }
                       }
                   case 3:
                       for (WatermelonEntity entity : list) {
                           if (entity.getTrueOrFalse()){
                               if (data[i].equals(entity.getVein())){
                                   veinTrue++;
                               }
                           }else {
                               if (data[i].equals(entity.getVein())){
                                   veinFalse++;
                               }
                           }
                       }
                   case 4:
                       for (WatermelonEntity entity : list) {
                           if (entity.getTrueOrFalse()){
                               if (data[i].equals(entity.getUmbilicalRegion())){
                                   umbilicalRegionTrue++;
                               }
                           }else {
                               if (data[i].equals(entity.getUmbilicalRegion())){
                                   umbilicalRegionFalse++;
                               }
                           }
                       }
                   case 5:
                       for (WatermelonEntity entity : list) {
                           if (entity.getTrueOrFalse()){
                               if (data[i].equals(entity.getTouch())){
                                   touchTrue++;
                               }
                           }else {
                               if (data[i].equals(entity.getTouch())){
                                   touchFalse++;
                               }
                           }
                       }
               }
           }
           double allPropertiesTrue = ((colourTrue/isTrue)*(rootTrue/isTrue)*(soundTrue/isTrue)*(veinTrue/isTrue)*(umbilicalRegionTrue/isTrue)*(touchTrue/isTrue));
           double allPropertiesFalse = ((colourFalse/isFalse)*(rootFalse/isFalse)*(soundFalse/isFalse)*(veinFalse/isFalse)*(umbilicalRegionFalse/isFalse)*(touchFalse/isFalse));
           //西瓜是好瓜的概率
           Double isGood = ((isTrue/list.size())*allPropertiesTrue);
           System.out.println("是一个好瓜的概率:"+isGood);
           //西瓜不是好瓜的概率
           Double isBad = ((isFalse/list.size())*allPropertiesFalse);
           System.out.println("是一个坏瓜的概率:"+isBad);
           if (isGood>isBad){
               System.out.println("根据贝叶斯分类算法预测是一个好瓜");
           }else {
               System.out.println("根据贝叶斯分类算法预测是一个坏瓜");

           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }


    //比较结果相同后属性是否相同
    public Boolean attribute(String parameterData,String basicData){
        if (parameterData.equals(basicData)){
            return true;
        }else {
            return false;
        }
    }

   @Test
   public void test137(){
       HashSet<Integer> nums = new HashSet<>();
       for (int i = 0; i < 4; i++) {
           for (int j = 0; j < 4; j++) {
               for (int k = 0; k < 4; k++) {
                   if (i != j && j != k && i != k){
                       int num = i*100+j*10+k;
                       if (num>99){
                           nums.add(num);
                       }
                   }
               }
           }
       }
       for (Integer num : nums) {
           System.out.println(num);
       }
       System.out.println(nums.size());
   }


    @Test
    public void test136(){

        double d = 114.145;
        double dff = 0.123131312;
        String format1 = String.format("%.2f", d);
        System.out.println(format1);
        String format = String.format("%.5f", dff);
        System.out.println(format);

        double x = 3.33147531591749E-4;
        double y = 4.3040291853913E-4;
        System.out.println(x+y);


        double f = -4.465896586236529E-5;
        double h = -1.1212121233;
        DecimalFormat df = new DecimalFormat("#.00000");
        String s = String.valueOf(f);
        int i = s.lastIndexOf(".");
        String substring = s.substring(0, i + 6);
        System.out.println(substring);
        System.out.println(df.format(f));
        System.out.println(df.format(h));
    }

    @Test
    public void test135(){
        ArrayList<String> tripartiteList = new ArrayList<>();
        tripartiteList.add("1232312");
        StringBuilder stringBuilder = new StringBuilder();
        for (String tripartiteId : tripartiteList) {
            if (stringBuilder.length() == 0) {
                stringBuilder.append(tripartiteId);
            } else {
                stringBuilder.append("," + tripartiteId);
            }
        }
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void test134(){
        String str = "ZDK230(ZDM15-ZJL3)";
        System.out.println(str.contains("+"));
        String str1 = "ZDK230+110(ZDM15-ZJL3)";
        System.out.println(str1.contains("+"));
    }



    @Test
    public void test133(){
        int i = 999;
        i--;
        ++i;
        System.out.println(i++);

    }


    @Test
    public void test132(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        String dateString = formatter.format(date);
        System.out.println(dateString);
        try {
            Date date1 = (Date) formatter.parse(dateString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date1);
            cal.add(Calendar.HOUR,1);
            Date time = cal.getTime();
            System.out.println(formatter.format(date1));
            System.out.println(formatter.format(time));
            Date date2 = StringToDate("2022-08-05 11:16:00");
            boolean b = timeCalendar(date2, date1, time);
            System.out.println(b);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Date StringToDate(String str) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = (Date) formatter.parse(str);
        return date;
    }

    public static boolean timeCalendar(Date nowTime, Date beginTime, Date endTime) {
        //设置当前时间
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        //设置开始时间
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);//开始时间
        //设置结束时间
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);//上午结束时间
        //处于开始时间之后，和结束时间之前的判断
        if((date.after(begin) && date.before(end))) {
            return true;
        } else {
            return false;
        }
    }


   @Test
   public void test131(){
       ArrayList<Integer> list = new ArrayList<>();
       list.add(89);
       list.add(79);
       list.add(100);
       list.add(50);
       list.add(60);
       list.sort(Integer::compareTo);
       for (Integer integer : list) {
           System.out.println(integer);
       }

   }


    @Test
    public void test130(){
        String str = "ABCasdD1231+890(adc";
        int i = str.lastIndexOf("(");
        System.out.println(str.substring(0,i));
        Pattern compile = Pattern.compile("[a-z]+");
        Matcher matcher = compile.matcher(str);
        String s = matcher.replaceAll("");
        Pattern compile1 = Pattern.compile("[A-Z]+");
        Matcher matcher1 = compile1.matcher(s);
        String s1 = matcher1.replaceAll("");
        System.out.println(s1);
        String s2 = s1.replace("+", "");
        System.out.println(s2);
    }

    @Test
    public void test129(){

        String  ss = "[a12,da,das]";

        String replaceAll = ss.replaceAll("[\\[\\]]","");

        System.out.println(replaceAll);// a12,da,das
    }

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for(int j = 1; j < nums.length; j++){
            if(nums[i] != nums[j]){
                i = i + 1;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

   @Test
   public void test128(){
       String array[]={"123","1234","12345","1","45"};
       for (int i = 0; i < 10; i++) {
           int num = (int)(Math.random()*5);
           System.out.println(array[num]);
       }
   }

    @Test
    public void test127(){
        AEntity aEntity1 = new AEntity();
        AEntity aEntity2 = new AEntity();
        AEntity aEntity3 = new AEntity();
        AEntity aEntity4 = new AEntity();
        AEntity aEntity5 = new AEntity();
        AEntity aEntity6 = new AEntity();
        aEntity1.setStar(10);
        aEntity1.setEnd(15);
        aEntity2.setStar(13);
        aEntity2.setEnd(18);
        aEntity3.setStar(28);
        aEntity3.setEnd(25);
        aEntity4.setStar(24);
        aEntity4.setEnd(28);
        aEntity5.setStar(28);
        aEntity5.setEnd(32);
        aEntity6.setStar(28);
        aEntity6.setEnd(32);
        ArrayList<AEntity> list = new ArrayList<>();
        list.add(aEntity1);
//        list.add(aEntity2);
//        list.add(aEntity3);
//        list.add(aEntity4);
//        list.add(aEntity5);
//        list.add(aEntity6);

//        HashSet<AEntity> aEntities = new HashSet<>(list);
//        ArrayList<AEntity> aEntities1 = new ArrayList<>(aEntities);
//        for (AEntity aEntity : aEntities1) {
//            System.out.println(aEntity);
//        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i!=j){
                    System.out.println("计算");
                }else {
                    System.out.println("不计算");
                }
            }
        }

    }

    @Test
    public void test126(){
        ArrayList<AEntity> list = new ArrayList<>();

        AEntity aEntity1 = new AEntity();
        AEntity aEntity2 = new AEntity();
        AEntity aEntity3 = new AEntity();
        AEntity aEntity4 = new AEntity();
        aEntity1.setStar(1);
        aEntity2.setStar(2);
        aEntity3.setStar(3);
        aEntity4.setStar(4);
        list.add(aEntity1);
        list.add(aEntity2);
        list.add(aEntity3);
        list.add(aEntity4);
        Collections.sort(list, Comparator.comparing(AEntity::getStar));
        for (AEntity aEntity : list) {
            System.out.println(aEntity.getStar());
        }

        Collections.sort(list, Comparator.comparing(AEntity::getStar).reversed());
        for (AEntity aEntity : list) {
            System.out.println(aEntity.getStar());
        }
    }

    @Test
    public void test124(){
        Date frontDay7 = DateUtils.getFrontDay(new Date(), 7);
        Date frontDay15 = DateUtils.getFrontDay(new Date(), 15);
        Date frontDay30 = DateUtils.getFrontDay(new Date(), 30);
        Date frontDay90 = DateUtils.getFrontDay(new Date(), 90);
        String s7 = DateUtils.dateFormat(frontDay7);
        String s15 = DateUtils.dateFormat(frontDay15);
        String s30 = DateUtils.dateFormat(frontDay30);
        String s90 = DateUtils.dateFormat(frontDay90);
        System.out.println(s7);
        System.out.println(s15);
        System.out.println(s30);
        System.out.println(s90);
    }

    @Test
    public void test125(){
        ArrayList<Float> list = new ArrayList<>();
        list.add(2.3f);
        list.add(2.6f);
        list.add(2.1f);
        list.add(2.9f);
        Collections.sort(list);
        System.out.println(list.get(0));
        System.out.println(list.get(list.size()-1));
    }

    @Test
    public void test123(){
        int a = 1;
        ArrayList<HisDataEntity> list = new ArrayList<>();
        HisDataEntity hisDataEntity = new HisDataEntity();
        hisDataEntity.setTimeValue("!23");
        switch (a){
            case 1:
                if (1==1){
                    hisDataEntity.setDataId("123");
                    System.out.println(hisDataEntity);
                    list.add(hisDataEntity);
                }
                if(2==2){
                    hisDataEntity.setDataId("111");
                    System.out.println(hisDataEntity);
                    list.add(hisDataEntity);
                }
        }
        System.out.println(list.size());
    }

    @Test
    public void test122(){
        System.out.println(fib(6));
    }

    int fib(int n)
    {

        if(n>2) {
            System.out.println("*****"+n);
            return (fib(n - 1) + fib(n - 2));
        }else {
            System.out.println(n);
            return 2;
        }
    }



   @Test
   public void test121(){
       int m =3;
       double a = 0.0;
       for (int i = 0; i < m; i++) {
           a+=f(i);
       }
       System.out.println(a);
   }
   public double f(int n){
       double s=1;
       for (int i = 1; i <=n; i++) {
           s+=1.0/i;
       }
       return s;
   }

    @Test
    public void test120(){
        int a[] = {78,89,12,32,12,32,45,12,56,98};
        int ka,ki,i;
        ka=ki=0;
        for (int j = 1; j < 10; j++) {
//            if (a[])
        }
    }

    @Test
    public void test119(){
       int a=20;
       int b = 5;
       double d = b/a;
        System.out.println(d);
    }

    @Test
    public void test118(){
        int s  = 7;
        System.out.println(s/2+(s+1)%2);
    }

    @Test
    public void test117(){
        int arr[] = {9,3,1,4,2};
        int k,temp;
        for (int i = 0; i <5; i++) {
            k = i;
            for (int j = i+1; j <5; j++) {
                if (arr[j]<arr[k]){
                  k=j;
                }
            }
            temp = arr[k];
            arr[k] = arr[i];
            arr[i] = temp;
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void test116(){
        hanoi1(3,"A","B","C");
    }

    void hanoi1(int n,String one,String two,String three){
        if (n==1){
            move(one,three);
        }else {
            hanoi1(n-1,one,three,two);
            move(one,three);
            hanoi1(n-1,two,one,three);
        }
    }

    void move(String x,String y){
        System.out.println(x+"->"+y);
    }




    @Test
    public void test115(){
        int[] nums = {1,1,2,2,3};
        if(nums != null && nums.length>0){
            int left = 0;
            for(int right = 1;right < nums.length; right++){
                if(nums[left]!=nums[right]){
                    nums[++left] = nums[right];
                }
            }
            System.out.println(++left);
        }
        System.out.println(0);
    }

    @Test
    public void test114(){
        ArrayList<String> list = new ArrayList<>();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("4444");
        for (String s : list) {
            if (s.equals("3333")){
                continue;
            }
            System.out.println(s);
        }
    }

   @Test
   public void test113(){



       String str = "1.29999999999927";
       Float aFloat = Float.valueOf(str);
       System.out.println(aFloat);
       System.out.println(aFloat+"");
   }

   @Test
   public void test112(){
       Float aFloat = 123456.78f;
       String result;
       if (aFloat<1000){
           result = "ZK0"+aFloat;
       }else if (aFloat<10000){
           String data1 = aFloat+"";
           String substring = data1.substring(0, 1);
           String substring1 = data1.substring(1, data1.length());
           result = "K"+substring+"+"+substring1;
       }else if (aFloat<100000){
           String data1 = aFloat+"";
           String substring = data1.substring(0, 2);
           String substring1 = data1.substring(2, data1.length());
           result = "K"+substring+"+"+substring1;
       } else if (aFloat<1000000) {
           String data1 = aFloat+"";
           String substring = data1.substring(0, 3);
           String substring1 = data1.substring(3, data1.length());
           result = "K"+substring+"+"+substring1;
       }
   }


   @Test
   public void test111(){
       String str = "/images/points/011超前地质预报（宗宝山出口左幅ZK13%2B870~ZK13%2B840）.pdf";
       int i = str.lastIndexOf("/");
       int length = str.length();
       String substring = str.substring(i+1);
       System.out.println(substring);
       System.out.println(str.substring(i+1,length));
   }

   @Test
   public void test110(){
       for (int x = 0; x < 10; x++) {
           for (int y = 0; y < 10; y++) {
               for (int z = 0; z < 10; z++) {
                   if (x+y == 5 && y+y == 6 && z+z == 2 ){
                       System.out.println(x);
                       System.out.println(y);
                       System.out.println(z);
                   }
               }
           }
       }
   }


    @Test
    public void test109() throws IOException {
        byte[] buffer = null;
        String dirPath ="C:\\Users\\lenono\\Desktop\\aa\\";
        String fileName="123.pdf";
        //分段大小  1M为一段
        long partSize = 1024*1024;

//        File file = new File(dirPath+fileName);
        try
        {
            FileInputStream fis = new FileInputStream(dirPath+fileName);
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024*1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bos.write(b, 0, n);
                byte[] bytes = bos.toByteArray();
//                byte[] decode = java.util.Base64.getDecoder().decode(bytes);
                String string = Base64.getEncoder().encodeToString(bytes);
                System.out.println(bytes.length);
                System.out.println(string);
                bos.close();
            }
            fis.close();

////            buffer = bos.toByteArray();
//            int length = buffer.length;
//            System.out.println(length);
//            System.out.println(buffer);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
//        return buffer;
    }

   @Test
   public void test108() throws IOException {
       String dirPath ="C:\\Users\\lenono\\Desktop\\aa\\";
       String fileName="123.pdf";
       //分段大小  1M为一段
       long partSize = 1024*1024;

       File file = new File(dirPath+fileName);

       FileInputStream fis = new FileInputStream(file);

       //获取总文件大小
       long fileSize = fis.getChannel().size();

       //分多少段
       int  partCount = (int) (fileSize/partSize);
       if(partSize*partCount<fileSize){
           partCount++;
       }
       byte[] bytes = new byte[1024*1024];
       System.out.println("文件一共分为"+partCount+"块");
       FileOutputStream outputStream = new FileOutputStream(file);
       FileInputStream inputStream = new FileInputStream(file);

       int ii = 100;
       int j = 10000;
       fis.read(bytes,100,10000);
       ByteArrayOutputStream bos = new ByteArrayOutputStream();
       bos.write(bytes,0,j-ii);
       byte[] ss = bos.toByteArray();
       String string = Base64.getEncoder().encodeToString(ss);
       System.out.println(ss);
       System.out.println(ss.length);

       for (int i = 0; i <partCount ; i++) {

           int num = i+1;
           //当前分段起始位置
           long partStart=i*partSize;
           //当前分段大小  如果为最后一个大小为fileSize-partStart  其他为partSize
           long curPartSize=(i+1==partCount)?(fileSize-partStart):partSize;

           long len = curPartSize-partStart;






//           Long len = curPartSize-partStart;
//           outputStream.write(bytes,Integer.parseInt(partStart + ""),Integer.parseInt(len + ""));
//           System.out.println(outputStream);
//           long size = outputStream.getChannel().size();
//           System.out.println(size);
//
//           String str1 = new String(bytes,Integer.parseInt(partStart + ""),Integer.parseInt(curPartSize + ""));
//           System.out.println(str1);


           System.out.println(num);
           System.out.println(file);
           System.out.println(curPartSize);
           System.out.println(partStart);
       }
   }


    @Test
    public void test106() throws IOException {
        String dirPath ="C:\\Users\\lenono\\Desktop\\aa\\";
        String fileName="123.pdf";
        //分段上传请求地址
        String url1="xxxxxxxxxxxxxxxxx";
        //分段文件全部上传完毕后请求后台拼接 请求地址
        String url2="xxxxxxxxxxxxxxxxx";
        PartFileUpload(dirPath,dirPath,dirPath,fileName);

        File file = new File(dirPath+fileName);

        FileInputStream fis = new FileInputStream(file);


        //分段大小  1M为一段
        long partSize = 1024*1024;

        //获取总文件大小
        long fileSize = fis.getChannel().size();

        //分多少段
        int  partCount = (int) (fileSize/partSize);
        if(partSize*partCount<fileSize){
            partCount++;
        }
        System.out.println("文件一共分为"+partCount+"块");

        String fileId = UUID.randomUUID().toString().replace("-","");

        for (int i = 0; i <partCount ; i++) {
            int num = i+1;
            //当前分段起始位置
            long partStart=i*partSize;
            //当前分段大小  如果为最后一个大小为fileSize-partStart  其他为partSize
            long curPartSize=(i+1==partCount)?(fileSize-partStart):partSize;
//            OutputStream outputStream = new DataOutputStream();
//            FileUploadRunnable fileUploadRunnable = new FileUploadRunnable(url1,fileId,num,countDownLatch,file,curPartSize,partStart);
//            service.submit(fileUploadRunnable);
        }

    }

    @Test
    public void test107() {

        String dirPath ="C:\\Users\\lenono\\Desktop\\aa";
        String fileName="123.pdf";
        //分段上传请求地址
        String url1="";
        //分段文件全部上传完毕后请求后台拼接 请求地址
        String url2="";

        PartFileUpload(dirPath,url1,url2,fileName);

    }

    public static void PartFileUpload(String dirPath,String url1,String url2,String fileName){

        try {
            File file = new File(dirPath+fileName);

            FileInputStream fis = new FileInputStream(file);


            //分段大小  1M为一段
            long partSize = 1024*1024;

            //获取总文件大小
            long fileSize = fis.getChannel().size();

            //分多少段
            int  partCount = (int) (fileSize/partSize);
            if(partSize*partCount<fileSize){
                partCount++;
            }
            System.out.println("文件一共分为"+partCount+"块");

            //线程大小为分段数
            ExecutorService service = Executors.newFixedThreadPool(partCount);

            //partCount个线程全部处理完后  countDownLatch.await()阻塞通过
            CountDownLatch countDownLatch = new CountDownLatch(partCount);
            //获取文件ID
            String fileId = UUID.randomUUID().toString().replace("-","");

            for (int i = 0; i <partCount ; i++) {
                int num = i+1;
                //当前分段起始位置
                long partStart=i*partSize;
                //当前分段大小  如果为最后一个大小为fileSize-partStart  其他为partSize
                long curPartSize=(i+1==partCount)?(fileSize-partStart):partSize;
                FileUploadRunnable fileUploadRunnable = new FileUploadRunnable(url1,fileId,num,countDownLatch,file,curPartSize,partStart);
                service.submit(fileUploadRunnable);
            }

            //阻塞直至countDownLatch.countDown()被调用partCount次  即所有线程执行任务完毕
            countDownLatch.await();
            service.shutdown();
            System.out.println("分块文件全部上传完毕");
            System.out.println("开始请求后台拼装");
            CloseableHttpClient ht = HttpClientBuilder.create().build();
            String param =fileId+"/"+partCount+"/"+fileName;
            HttpPost post = new HttpPost(url2+param);
            HttpResponse response=ht.execute(post);
            if(response.getStatusLine().getStatusCode()==200){
                String ret = EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.println(ret);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test105() {

//        String pwd1 = phone.trim().substring(phone.length() - 6);

        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(stringBuffer);

        String pwd = "18881919795";
        String substring = pwd.trim().substring(pwd.length() - 6);
        System.out.println(substring);


    }

    public static String mDg(String input) {
        byte[] byteInput = input.getBytes();

        String password = null;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            System.out.println(sha.toString());
            sha.update(byteInput);
            byte[] output = sha.digest();
//            password = Base64.encodeToString(output, Base64.DEFAULT);
//            Base64
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return password.substring(0,"SMpYeGaVRe".length());
    }

    @Test
    public void test104(){
        double aa =3/2;
        System.out.println(aa);

     int a=8,b=7,c=6,x=1;
     if (a>6){
         if (a>7) {
             if (c>8) {
                 x=2;
             }else {
                 x=3;
             }
         }
     }
        System.out.println(x);

    }

    @Test
   public void test103(){
       Date date = new Date();
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
       String format = simpleDateFormat.format(date);
       System.out.println(format);
       ParsePosition parsePosition = new ParsePosition(8);
   }



   @Test
   public void test102(){
       int x = 1, y = 0, a = 0, b = 0;
       switch(x)
       {
           case 1:switch (y)
           {
               case 0: a++; break;
               case 1: b++; break;
           }
           case 2:
               a++;
               System.out.println("??????????");
               b++;
               break;
       }
       System.out.println(a+"##########"+b);
   }

   @Test
   public void test101(){
       int[] arr = new int[]{0,2,1,0};
       int max = 0;
       int index = 0;
       for(int i=0;i<arr.length;i++){
           System.out.println(arr[i]+"----"+max);
           if(arr[i]>max){
               max = arr[i];
               index = i;
           }
       }
       System.out.println(index);
   }

   @Test
   public void account(){
       Date date = new Date();
       String pattern = "yyyy-MM-dd HH:mm:ss";
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
       System.out.println(simpleDateFormat.format(date));
   }

   @Test
   public void Base64(){

       String account = "18881919795";
       int length = account.length();
       System.out.println(length);
       String substring = account.substring(5, 11);

       System.out.println(substring);


       String text = "123456";
       byte[] textByte = new byte[0];
       try {
           textByte = text.getBytes("UTF-8");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
       String encodedText = encoder.encodeToString(textByte);
       System.out.println(encodedText);
   }


    @Test
    public void test100(){

        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String mon;
        int month = cal.get(Calendar.MONTH )+1;
        if (month<10){
            mon = "0"+month;
        }else {
            mon = String.valueOf(month);
        }

        int i = Integer.parseInt(year+mon);
        System.out.println(i);

        System.out.println(year);
        System.out.println(month);
    }

    @Test
    public int searchInsert(int[] nums, int target) {
        nums = new int[]{-1, 0, 3, 5, 9, 12};
        int len = nums.length;
        // 特殊判断
        if (nums[len - 1] < target) {
            return len;
        }

        // 程序走到这里一定有 nums[len - 1] >= target
        int left = 0;
        int right = len - 1;
        // 在区间 nums[left..right] 里查找第 1 个大于等于 target 的元素的下标
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                // 下一轮搜索的区间是 [mid + 1..right]
                left = mid + 1;
            } else {
                // 下一轮搜索的区间是 [left..mid]
                right = mid;
            }
        }
        return left;
    }








   @Test
   public void testTime() throws ParseException {

       int[] nums = {-1,0,3,5,9,12};
       for (int i = 0; i < nums.length; i++) {
           if (9==nums[i]){
               System.out.println(i);
           }
       }


       String fileName = "明子山1%23隧道进口左幅ZK131%2B973～ZK132%2B003段超前地质预报-013期.pdf";

       String replace = fileName.replace("%25", "%");
       String replace1 = replace.replace("%20", " ");
       String replace2 = replace1.replace("%3F", "?");
       String replace3 = replace2.replace("%2F", "/");
       String replace4 = replace3.replace("%2B", "+");
       String replace5 = replace4.replace("%23", "#");
       String replace6 = replace5.replace("%26", "&");
       String replace7 = replace6.replace("%3D", "=");
       System.out.println(replace7.length());
       System.out.println(replace7);

       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
       Date date3=new Date();
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(date3);
       calendar.add(Calendar.DAY_OF_MONTH, 1);
       date3 = calendar.getTime();
       System.out.println(sdf.format(date3));




       //获取当前时间
       Date date = new Date();
       Date dBefore;
       Calendar instance = Calendar.getInstance();
       instance.setTime(date);
       //时间设置为前一天
       instance.add(Calendar.DAY_OF_MONTH,-7);
       dBefore = instance.getTime();

       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

       Date parse1 = dateFormat.parse("2021-08-10");
       Date parse2 = dateFormat.parse("2021-08-05");
       int day = (int)((parse1.getTime()/(1000*3600*24))- (parse2.getTime()/(1000*3600*24)));
       System.out.println(day);


       String newDate = dateFormat.format(date);
       String newDate2 = dateFormat.format(dBefore);


       System.out.println("今天的时间"+newDate);
       System.out.println("-7的时间"+newDate2);

       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

       Date date1 = new Date();


//       Date parse3 = format.parse(newDate2);
//       System.out.println("转换为时分秒的数据"+format.format(parse3));


       Date startTime = format.parse(format.format(dBefore));
       Date startTime2 = format.parse(format.format(new Date()));
       String format1 = format.format(startTime);
       System.out.println(format1);
       Date parse = format.parse(format1);
       System.out.println(parse);

   }

   @Test
   public void test11(){
       String str = "D:\\WorkAndStudy\\  idea\\IdeaProjects\\  dianzhong\\demo\\网  片自检.pdf";
       String trim = str.trim();
       System.out.println(trim);
   }

   @Test
   public void test10(){
       UUID uuid = UUID.randomUUID();
       String str = uuid.toString();
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String format = dateFormat.format(new Date());
       String string = format+str;
       String replace = string.replace("-", "");
       String replace1 = replace.replace(" ", "");
       String replace2 = replace1.replace(":", "");
       System.out.println(replace2);
   }

   @Test
   public void test9(){
       File file = new File("D:\\WorkAndStudy\\idea\\IdeaProjects\\dianzhong\\demo\\网片自检.pdf");
       if (file!=null){
           System.out.println(file.delete());
       }
   }


   @Test
   public void test8(){
       List<HisDataEntity> hisDataEntities = hisDataService.selectByTime("2021-03-11 00:29:17", "2021-07-13 17:11:32");
       System.out.println(hisDataEntities.size());
       ArrayList<HisDataEntity> hisDataEntities2 = new ArrayList<>();
       for (HisDataEntity hisDataEntity : hisDataEntities) {
           HisDataEntity hisDataEntity1 = new HisDataEntity();
           hisDataEntity1.setDataId(hisDataEntity.getDataId());
           hisDataEntity1.setTempValue(hisDataEntity.getHumiValue());
           hisDataEntity1.setHumiValue(hisDataEntity.getLngValue());
           hisDataEntity1.setLngValue(hisDataEntity.getLatValue());
           hisDataEntity1.setLatValue(hisDataEntity.getTimeValue());
           hisDataEntity1.setTimeValue(hisDataEntity.getDevKey());
           hisDataEntity1.setDevKey(hisDataEntity.getTempValue());
           hisDataEntities2.add(hisDataEntity1);

       }
       int i = 0;
       ArrayList<HisDataEntity> hisDataEntities3 = new ArrayList<>();
       for (HisDataEntity hisDataEntity : hisDataEntities2) {
           hisDataEntities3.add(hisDataEntity);
           i++;
           if (i == 2000){
               int il = hisDataService.updateAll(hisDataEntities3);
               System.out.println(il);
               hisDataEntities3.clear();
               System.out.println("清除集合");
               System.out.println(hisDataEntities3.size());
               i=0;
           }
       }
       if (hisDataEntities3 != null){
           int il = hisDataService.updateAll(hisDataEntities3);
       }
   }

    @Test
    public void test7(){
        String fileName = "/images/points/龙庆隧洞(1#支洞）KM37＋640.697~KM37＋670.697段（J21检滇中引水－LQI－LD－04）2021－04－09.pdf";
        String replace = fileName.replace("%", "%25");
        String replace1 = replace.replace(" ", "%20");
        String replace2 = replace1.replace("?", "%3F");
        String replace3 = replace2.replace("/", "%2F");
        String replace4 = replace3.replace("+", "%2B");
        String replace5 = replace4.replace("#", "%23");
        String replace6 = replace5.replace("&", "%26");
        String replace7 = replace6.replace("=", "%3D");
        System.out.println(replace7);
    }

    @Test
    public void test6() throws ParseException {



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = "2021-07-03 00:00:00 ";
        Date format = sdf2.parse(dateString);

        System.out.println(sdf2.format(getStartOfDay(format)));
        System.out.println(sdf2.format(getEndOfDay(format)));
        System.out.println(sdf.format(getStartOfDay(format)));
        System.out.println(sdf.format(getEndOfDay(format)));
        System.out.println(getStartOfDay(format));
        System.out.println(getEndOfDay(format));

    }

    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }



    @Test
    public void test5(){
        String path = "D:\\opt\\server\\files\\points\\test";
        File file = new File(path);
        System.out.println("isDirectory---"+file.isDirectory());
        System.out.println("exists---"+file.exists());
        if (!file.exists()){
            System.out.println("新建文件夹");
            file.mkdirs();
        }
    }

    @Test
    public void test4() throws IOException {

        String strPath = "E:\\a\\aa\\aaa.txt";
        File file2 = new File(strPath);
        if (!file2.exists()){
            file2.createNewFile();
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        System.out.println(format);


        System.out.println(date.toString());

        String fileName = "/images/points/巴尼洛俄隧道进口左幅超前地质预报003雷达法.pdf";
        //设置允许上传文件类型
        String suffixList = "pdf";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")
                + 1, fileName.length());
        if (suffixList.contains(suffix.trim().toLowerCase())) {
            System.out.println("1111111111111");
        }


        String path = "/images/points/巴尼洛俄隧道进口左幅超前地质预报003雷达法.pdf";
        File file1 = new File(path.trim());
        String name = file1.getName();
        System.out.println(name);

        File file = new File("u=1485012388,2380514454&fm=26&fmt=auto&gp=0.jpg");
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void test3() throws Exception {
        String path = "http://www.tgmis.cn:8088/points/拱架自检2.jpg";
        File tempFile =new File( path.trim());

        String fileName = tempFile.getName();

        System.out.println("方法一：fileName = " + fileName);

        String s = path.replaceAll(fileName, "");
        System.out.println(path);
        System.out.println(s);

    }

    @Test
    public void test333() throws Exception {
        URL url = new URL("https://img1.baidu.com/it/u=1485012388,2380514454&fm=26&fmt=auto&gp=0.jpg");
        File file1 = new File("u=1485012388,2380514454&fm=26&fmt=auto&gp=0.jpg");
        System.out.println(file1);
        FileUtils.copyURLToFile(url, file1);
        String absolutePath = file1.getAbsolutePath();
        System.out.println(absolutePath);
    }

    @Test
    void testLog() throws IOException {
        String urlStr = "https://img2018.cnblogs.com/i-beta/1278703/201911/1278703-20191128121650595-812419505.png";
        URL url = new URL(urlStr);
        String tempFileName = System.getProperty("user.dir");
        String tempFileName2 = tempFileName+"/1278703-20191128121650595-812419505.png";
        System.out.println(tempFileName2);
        File temp = new File(tempFileName2);
        FileUtils.copyURLToFile(url, temp);

    }

    @Test
    void testListRemoveAll() {
        int n = 3;
        String a = "a";
        String b = "b";
        String c = "c";
        hanoi(n, a, b, c);
    }

    public void hanoi(int n, String a, String b, String c) {
        if (n == 1) {
            System.out.println(a + b + c);
            System.out.println(a + "移动到" + c);
        } else {
            hanoi(n - 1, a, c, b);
            System.out.println(a + b + c);
            System.out.println(a + "移动到" + c);
            hanoi(n - 1, b, a, c);
        }
    }

    @Test
    public void timeTest() {
        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        String dateNowStr = sdf.format(d);
        System.out.println("格式化后的日期：" + dateNowStr);

    }

    @Test
    public void testTemplate() {
        //公司A创建流程
        AskForLeaveFlow companyA = new CompanyB();
        companyA.askForLeave("吴正雄");
    }

}
