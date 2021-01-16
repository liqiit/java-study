package com.liqi.file;

/**
 * Title: AccessRandomFileDemo
 * Description: 随机访问文件 可以指定访问起点
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/12/24
 */
public class AccessRandomFileDemo {
    public static void main(String[] args) {
        BigFileReader.Builder builder = new BigFileReader.Builder("E:\\话单模板\\CDR.GS.IF.2020111823300000000.66.txt", line -> {
            System.out.println(line);
            //increat();
        });
        builder.withTreahdSize(100)
                .withCharset("gbk")
                .withBufferSize(1024*1024);
        BigFileReader bigFileReader = builder.build();
        bigFileReader.start();
        //bigFileReader.shutdown();
    }
}
