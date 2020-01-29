import net.coobird.thumbnailator.makers.FixedSizeThumbnailMaker;
import net.coobird.thumbnailator.resizers.DefaultResizerFactory;
import net.coobird.thumbnailator.resizers.Resizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Thumbnailator {
    public static void main(String[] args) throws IOException {

        BufferedImage imageToScale = ImageIO.read(new File("img/original.jpg"));
        int dWidth = 200;
        int dHeight = 200;
        Resizer resizer = DefaultResizerFactory.getInstance().getResizer(
                new Dimension(imageToScale.getWidth(), imageToScale.getHeight()),
                new Dimension(dWidth, dHeight));

        BufferedImage scaledImage = new FixedSizeThumbnailMaker(
                dWidth, dHeight, false, true).imageType(BufferedImage.TYPE_INT_RGB).resizer(resizer).make(imageToScale);

        ImageIO.write(scaledImage, "jpg", new File("img/thumbnailatorImage.jpg"));
        System.out.println("Successful!");
    }
}
