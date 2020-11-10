package cn.lh.life.picrecongnition;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

public class PICGetShotUtils {

    /**
     * 指定屏幕区域截图，返回截图的BufferedImage对象
     * @param robot
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage getScreenShot(Robot robot, int x, int y, int width, int height) {
        BufferedImage bfImage = null;
            bfImage = robot.createScreenCapture(new Rectangle(x, y, width, height));
        return bfImage;
    }

    /**
     * 指定屏幕区域截图，保存到指定目录
     * @param robot
     * @param x
     * @param y
     * @param width
     * @param height
     * @param savePath - 文件保存路径
     * @param fileName - 文件保存名称
     * @param format - 文件格式
     */
    public static BufferedImage screenShotAsFile(Robot robot, int x, int y, int width, int height, String savePath, String fileName, String format) throws IOException {
            BufferedImage bfImage = robot.createScreenCapture(new Rectangle(x, y, width, height));
            File path = new File(savePath);
            File file = new File(path, fileName+ "." + format);
            ImageIO.write(bfImage, format, file);
            return bfImage;
    }
    /**
     * BufferedImage图片剪裁
     * @param srcBfImg - 被剪裁的BufferedImage
     * @param x - 左上角剪裁点X坐标
     * @param y - 左上角剪裁点Y坐标
     * @param width - 剪裁出的图片的宽度
     * @param height - 剪裁出的图片的高度
     * @return 剪裁得到的BufferedImage
     */
    public static BufferedImage cutBufferedImage(BufferedImage srcBfImg, int x, int y, int width, int height) {
        BufferedImage cutedImage = null;
        CropImageFilter cropFilter = new CropImageFilter(x, y, width, height);
        Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(srcBfImg.getSource(), cropFilter));
        cutedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = cutedImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return cutedImage;
    }

    /**
     * BufferedImage流转String
     * @param cutedImage
     * @return
     */
    public static String BufImgToByteArray(BufferedImage cutedImage){
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过再URLEncode的字节数组字符串

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( cutedImage, "jpg", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return URLEncoder.encode(encoder.encode(imageInByte));
        } catch (IOException e) {
            e.printStackTrace();
        }
       return "";
    }

}
