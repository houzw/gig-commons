package cn.gig.rs.commons.utils;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * description:
 * 图片处理工具类
 * https://blog.csdn.net/listeningsea/article/details/120868525?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-120868525-blog-55098709.pc_relevant_blogantidownloadv1&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1-120868525-blog-55098709.pc_relevant_blogantidownloadv1&utm_relevant_index=2
 *
 * @author houzhiwei
 * @date 2022/6/14/0014 17:24
 */
@Slf4j
public class ImageUtils {

    /**
     * 按指定比例和质量压缩图片<br/>
     * compress picture without change the scale
     *
     * @param srcPath source file path
     * @param dstPath destination file path
     * @param scale   scale of size, e.g., 0.5f
     * @param quality picture quality, e.g., 0.1f
     * @return true for success
     */
    public static boolean compressPicture(String srcPath, String dstPath, float scale, float quality) {
        try {
            Thumbnails.of(srcPath).scale(scale).outputQuality(quality).toFile(dstPath);
            return true;
        } catch (IOException e) {
            log.error("Compress picture failed! {}", e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * 压缩图片为指定图片大小和质量<br/>
     * compress picture to certain size
     *
     * @param srcPath
     * @param dstPath
     * @param width   图像宽
     * @param height  图像高
     * @param quality 图像质量，如 0.1f
     * @return
     */
    public static boolean compressPicture2Size(String srcPath, String dstPath, int width, int height, float quality) {
        try {
            Thumbnails.of(srcPath).size(width, height).outputQuality(quality).toFile(dstPath);
            return true;
        } catch (IOException e) {
            log.error("Compress picture failed! {}", e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * 压缩图片，不修改图片大小。质量为 0.25<br/>
     * compress picture without change the scale
     *
     * @param srcPath source file path
     * @param dstPath destination file path
     * @return true for success
     */
    public static boolean compressPicWithSameScale(String srcPath, String dstPath) {
        return compressPicture(srcPath, dstPath, 1f, 0.25f);
    }

    /**
     * 根据指定大小压缩图片
     *
     * @param imageBytes  源图片字节数组
     * @param desFileSize 指定图片大小，单位kb
     * @return 压缩质量后的图片字节数组
     */
    public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize) {
        if (imageBytes == null || imageBytes.length <= 0 || imageBytes.length < desFileSize * 1024) {
            return imageBytes;
        }
        long srcSize = imageBytes.length;
        double accuracy = getAccuracy(srcSize / 1024);
        try {
            while (imageBytes.length > desFileSize * 1024) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
                Thumbnails.of(inputStream)
                        .scale(accuracy)
                        .outputQuality(accuracy)
                        .toOutputStream(outputStream);
                imageBytes = outputStream.toByteArray();
            }
            log.info("图片原大小={}kb | 压缩后大小={}kb", srcSize / 1024, imageBytes.length / 1024);
        } catch (Exception e) {
            log.error("图片压缩失败!", e);
        }
        return imageBytes;
    }

    /**
     * 自动调节精度(经验数值)
     *
     * @param srcSize 源图片大小
     * @return 图片压缩质量比
     */
    private static double getAccuracy(long srcSize) {
        double accuracy;
        if (srcSize < 900) {
            accuracy = 0.85;
        } else if (srcSize < 2047) {
            accuracy = 0.6;
        } else if (srcSize < 3275) {
            accuracy = 0.44;
        } else {
            accuracy = 0.4;
        }
        return accuracy;
    }

}
