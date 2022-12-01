package com.liqi.merge;

import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.enums.OutputFormat;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * @Author :liqi
 * @CreateTime: 2022-11-30 10:34
 * @Description: 测试图片合成
 */
public class Junit {

    @Test
    public void testNative3DivMerge() {
        long start = System.currentTimeMillis();
        System.out.println("开始时间：" + start);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        //执行线程
        for (int i = 0; i < 1; i++) {
            executorService.submit(() -> {
                try {
                    nativeMerge();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }

        //等线程全部执行完后关闭线程池
        executorService.shutdown();
        try {
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + start);

        System.out.println("共消耗：" + (end - start) / 1000 + "秒");

    }

    private void nativeMerge() throws Exception {
        File path = new File("/Users/liqi/Documents/image/source");
        // load source images
        BufferedImage face = ImageIO.read(new File(path, "/01Face/Face=Angry, Face Color=Blue.png"));
        BufferedImage image = ImageIO.read(new File(path, "/02Accessories/Accessories=Beanie.png"));
        BufferedImage overlay = ImageIO.read(new File(path, "/00Background/Tier=Super Voyager, City=Bangkok, Background Color=Blue.png"));

        // create the new image, canvas size is the max. of both image sizes
        int w = Math.max(image.getWidth(), overlay.getWidth());
        int h = Math.max(image.getHeight(), overlay.getHeight());
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        // paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.drawImage(overlay, 0, 0, null);
        g.drawImage(image, 0, 0, null);
        g.drawImage(face, 0, 0, null);

        g.dispose();

        // Save as new image
        ImageIO.write(combined, "PNG", new File("/Users/liqi/Documents/image/target/", "combined" + UUID.randomUUID() + ".png"));
    }

    @Test
    public void testImageCombineMerge() {
        long start = System.currentTimeMillis();
        System.out.println("开始时间：" + start);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //执行线程
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() -> {
                try {
                    imageCombineMerge();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        //等线程全部执行完后关闭线程池
        executorService.shutdown();
        try {
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + start);

        System.out.println("共消耗：" + (end - start) / 1000 + "秒");

    }

    private void imageCombineMerge() throws Exception {
        String basePath = "/Users/liqi/Documents/image/source";
        //合成器（指定背景图和输出格式，整个图片的宽高和相关计算依赖于背景图，所以背景图的大小是个基准）
        BufferedImage overlay = ImageIO.read(new File(basePath, "/00Background/Tier=Super Voyager, City=Bangkok, Background Color=Blue.png"));
        ImageCombiner combiner = new ImageCombiner(overlay, OutputFormat.JPG);
        BufferedImage face = ImageIO.read(new File(basePath, "/01Face/Face=Angry, Face Color=Blue.png"));
        BufferedImage image = ImageIO.read(new File(basePath, "/02Accessories/Accessories=Beanie.png"));
        //加图片元素
        combiner.addImageElement(face, 0, 0);
        combiner.addImageElement(image, 0, 0);
        //执行图片合并
        combiner.combine();

        //可以获取流（并上传oss等）
        InputStream is = combiner.getCombinedImageStream();

        //也可以保存到本地
        combiner.save("/Users/liqi/Documents/image/target/" + "combined" + UUID.randomUUID() + ".png");
    }

    @Test
    public void testThumbNailatorMerge() {
        long start = System.currentTimeMillis();
        System.out.println("开始时间：" + start);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //执行线程
        for (int i = 0; i < 1; i++) {
            executorService.submit(() -> {
                try {
                    thumbNailatorMerge();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        //等线程全部执行完后关闭线程池
        executorService.shutdown();
        try {
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end);

        System.out.println("共消耗：" + (end - start) / 1000 + "秒");

    }

    public void thumbNailatorMerge() throws Exception {
        String basePath = "/Users/liqi/Documents/image/source";
        Thumbnails.of(new File(basePath + "/00Background/Tier=Super Voyager, City=Bangkok, Background Color=Blue.png"))
                .size(512, 512)
                .rotate(0)
                .watermark(Positions.CENTER, ImageIO.read(new File(basePath + "/01Face/Face=Angry, Face Color=Blue.png")), 0.5f)
                .watermark(Positions.CENTER, ImageIO.read(new File(basePath + "/02Accessories/Accessories=Beanie.png")), 0.5f)
                .outputQuality(0.8)
                .toFile(new File("/Users/liqi/Documents/image/target/" + "combined" + UUID.randomUUID() + ".png"));
    }


}
