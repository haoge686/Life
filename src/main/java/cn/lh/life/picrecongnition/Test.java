package cn.lh.life.picrecongnition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

public class Test {

    public static void main(String[] args) {

        try {
            Robot robot = new Robot();
            //
            BufferedImage screenShot = PICGetShotUtils.getScreenShot(robot, 0, 0, 1080, 1080);
            //截屏，并保存文件到路径，并返回BufferedImage流
            BufferedImage bufferedImage = PICGetShotUtils.screenShotAsFile(robot, 0, 0, 1080, 1080, "E:\\Study", "test", "png");
            String image = PICGetShotUtils.BufImgToByteArray(bufferedImage);
            String param = "image=" + image;
            String retJson = Check.post(param);
            JSONObject jsonObject = JSON.parseObject(retJson);
            System.out.println(jsonObject.get("words_result_num"));
            System.out.println(jsonObject.get("log_id"));
            System.out.println(jsonObject.get("words_result"));
            System.out.println(retJson);
            System.out.println(111);

        } catch (AWTException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }



    }

}
