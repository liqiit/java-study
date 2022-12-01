package com.liqi.merge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * @Author :liqi
 * @CreateTime: 2022-11-30 11:10
 * @Description: 原生java图片合成
 */
public class NativeJavaMerge {
    /***
     * 合成指定目录图片
     * @param combinedPath 合成图片存放地址
     * @param bgImagePath 背景图片目录
     * @param imagePathList 元素图片目录
     */
    public void mergeImage(String combinedPath, String bgImagePath, List<String> imagePathList) {
        try {
            List<List<BufferedImage>> combineImageList = new ArrayList();
            File bgDir = new File(bgImagePath);
            File[] bgImageList = bgDir.listFiles(file -> file.isFile() && file.getName().endsWith(".png"));
            for (File bgFile : bgImageList) {
                List<BufferedImage> list = new ArrayList<>();
                BufferedImage bgImage = ImageIO.read(bgFile);
                list.add(bgImage);
                for (String path : imagePathList) {
                    File imageDir = new File(path);
                    File[] imageFileList = imageDir.listFiles();

                    for (File imageFile : imageFileList) {
                        BufferedImage bufferedImage = ImageIO.read(imageFile);
                        list.add(bufferedImage);
                    }
                }
                combineImageList.add(list);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 合成单张图片
     * @param combinedPath
     * @param bgImage
     * @param imageList
     * @throws IOException
     */
    private void combine(String combinedPath, BufferedImage bgImage, List<BufferedImage> imageList) throws IOException {
        int width = bgImage.getWidth();
        int height = bgImage.getHeight();
        for (BufferedImage image : imageList) {
            width = Math.max(bgImage.getWidth(), image.getWidth());
            height = Math.max(bgImage.getHeight(), image.getHeight());
        }

        BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // paint both images, preserving the alpha channels
        Graphics g = combined.getGraphics();
        g.drawImage(bgImage, 0, 0, null);
        for (BufferedImage image : imageList) {
            g.drawImage(image, 0, 0, null);
        }
        g.dispose();
        //保存合成图片
        ImageIO.write(combined, "PNG", new File(combinedPath, "combined.png"));
    }

    public static void main(String[] args) {

    }

    /**
     * 组合选择
     *
     * @param dataList    待选列表
     * @param dataIndex   待选开始索引
     * @param resultList  前面（resultIndex-1）个的组合结果
     * @param resultIndex 选择索引，从0开始
     */
    private static void combinationSelect(String[] dataList, int dataIndex, String[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            System.out.println(Arrays.asList(resultList));
            return;
        }

        // 递归选择下一个
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
        }
    }
}
