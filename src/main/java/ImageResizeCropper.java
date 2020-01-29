import net.coobird.thumbnailator.makers.FixedSizeThumbnailMaker;
import net.coobird.thumbnailator.resizers.DefaultResizerFactory;
import net.coobird.thumbnailator.resizers.Resizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResizeCropper {
    public static void main(String[] args) throws IOException {
        // we use here all!

        final int WIDTH = 200;
        final int HEIGHT = 200;
        BufferedImage originalImage = ImageIO.read(new File("img/original.jpg"));

        // let's crop first, because we need to save image properties before scaling

        // So, we need to make x == y
        BufferedImage subImage;
        int delta = originalImage.getHeight() - originalImage.getWidth();

        if (delta > 0) {
            subImage = originalImage.getSubimage(0, delta/2 , originalImage.getWidth(), originalImage.getHeight() - delta);
        } else if (delta < 0){
            subImage = originalImage.getSubimage(delta/2, 0 , originalImage.getWidth() + delta, originalImage.getHeight());
        } else {
            subImage = originalImage;
        }

        // Now we can resize our image to our dimensions

        Resizer resizer = DefaultResizerFactory.getInstance().getResizer(
                new Dimension(subImage.getWidth(), subImage.getHeight()),
                new Dimension(WIDTH, HEIGHT));

        BufferedImage scaledImage =
                new FixedSizeThumbnailMaker(WIDTH, HEIGHT, false, true)
                        .imageType(BufferedImage.TYPE_INT_RGB)
                        .resizer(resizer)
                        .make(subImage);

        ImageIO.write(scaledImage, "jpg", new File("img/resizedAndCropped.jpg"));
        System.out.println("Successful!");


    }
}
