package common;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片压缩工具类
 */
public class ImageResizeUtils {

    private static InputStream BaseToInputStream(String base64string) throws IOException {
        ByteArrayInputStream stream = null;
        byte[] bytes1 = Base64Utils.decodeFromString(base64string);
        stream = new ByteArrayInputStream(bytes1);
        return stream;
    }

    private static BufferedImage base64String2BufferedImage(String base64string) throws IOException {
        BufferedImage image = null;
        InputStream stream = BaseToInputStream(base64string);
        image = ImageIO.read(stream);
        return image;
    }

    private static String imageToBase64(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        return Base64Utils.encodeToString((baos.toByteArray()));
    }

    /**
     * 图片压缩
     * @param base64Img 原图片的base64
     * @param newSize 新的最大尺寸（KB）
     * @return
     * @throws IOException
     */
    public static String resizeImage(String base64Img,long newSize) throws IOException {
        newSize = newSize * 1024; //转为B
        BufferedImage src = base64String2BufferedImage(base64Img);
        //第一次压缩三分之一
        BufferedImage output = Thumbnails.of(src).size(src.getWidth() / 3, src.getHeight() / 3).asBufferedImage();
        String base64 = imageToBase64(output);
        //第二次按比例压缩
        if (base64.length() - base64.length() / 8 * 2 > newSize) {
            double ratio = 1.0/(Double.valueOf(base64.length())/Double.valueOf(newSize));
            output = Thumbnails.of(output).scale(ratio).asBufferedImage();
            base64 = imageToBase64(output);
        }
        return base64;
    }
}
