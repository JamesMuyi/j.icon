package com.posridge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.xdough.image.processor.CustomColor;
import java.util.Map;

/**
 * ButtonTabImageProcessor
 *
 * @author James
 */
public class ButtonTabImageProcessor {

    private static long BASE_MASK = 0xFFFFFFFF00000000L;

    private static long TRANS_MASK = 0xFF000000L;

    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    private Map<String, Integer> fileNameMap = new HashMap<>();

    private static final String DO_PATH = "/Users/yangyunjie/Documents/github/j.icon/common/tmp/process";

    private static final String DONE_PATH = "/Users/yangyunjie/Documents/github/j.icon/common/tmp/processdone";


    public static void main(String[] args) throws Exception {
        ButtonTabImageProcessor processor = new ButtonTabImageProcessor();

        List<CustomColor> customColorList = new ArrayList<>();
        customColorList.add(CustomColor.DISNEY_TAB_OFF);
//        customColorList.add(CustomColor.DISNEY_TAB_ON);
//        customColorList.add(CustomColor.GRAY);
//        customColorList.add(CustomColor.MEGAEASE_GREEN);
//        customColorList.add(CustomColor.MEGAEASE_BLUE);
//        customColorList.add(CustomColor.MEGAEASE_BACKGROUND_GRAY);

        for (CustomColor customColor : customColorList) {
            processor.doProcess(customColor.getValue(), customColor.getName());
        }
    }

    private void doProcess(int drawColor, String colorName) throws Exception {
        File folder = new File(DO_PATH);
        if (folder.isDirectory()) {
            File[] fileLst = folder.listFiles();
            if (fileLst != null) {
                for (File file : fileLst) {
                    if (file.isFile()) {
                        String name = file.getName();
                        String fileName = name.substring(0, name.indexOf("."));

                        String newImageName = newImageName(fileName, colorName);

                        BufferedImage bi = ImageIO.read(file);
                        createPng(bi, newImageName, drawColor);
                    }
                }
            }
        }
    }

    private void createPng(BufferedImage bi, String newImageFileName, int drawColor) throws Exception {
        BufferedImage image = new BufferedImage(bi.getWidth(), bi.getHeight(), Transparency.TRANSLUCENT);

        for (int i = 0; i < bi.getHeight(); i++) {
            for (int j = 0; j < bi.getWidth(); j++) {
                int dip = bi.getRGB(j, i);

                long transpanrency = ((Long.parseLong(dip + "") & ButtonTabImageProcessor.TRANS_MASK)) >> 24;

                if (dip > 0) {
                    long tmp = (transpanrency << 24) | drawColor;

                    image.setRGB(j, i, (int) tmp);

                } else if (dip < 0) {
                    image.setRGB(j, i, (int) (ButtonTabImageProcessor.BASE_MASK | ((transpanrency << 24) | drawColor)));
                } else {
                    image.setRGB(j, i, ButtonTabImageProcessor.TRANSPARENT.getRGB());
                }
            }
        }
        ImageIO.write(image, "png", new File(DONE_PATH + File.separator + newImageFileName + ".png"));
    }

    private String newImageName(String imageName, String colorName) {
        String simpleImageName = substringImageName(imageName);
        String newImageName = simpleImageName + "_" + colorName;
        Integer fileCount = 1;
        if (fileNameMap.containsKey(newImageName)) {
            fileCount = fileNameMap.get(newImageName);
            fileCount++;
            fileNameMap.put(newImageName, fileCount);
            return simpleImageName + "_" + fileCount + "_" + colorName;
        } else {
            fileNameMap.put(newImageName, fileCount);
            return newImageName;
        }
    }

    private String substringImageName(String imageName) {
        if (imageName != null) {
            int idx = 0;

            byte[] imageNameBytes = imageName.getBytes();
            for (int i = 0; i < imageNameBytes.length; i++) {
                if (isNumber(imageNameBytes[i])) {
                    idx = i - 1;
                    break;
                }
            }
            if (idx > 0) {
                return imageName.substring(0, idx);
            }
        }
        return imageName;
    }

    private boolean isNumber(byte character) {
        return character >= 48 && character <= 57;
    }

}
