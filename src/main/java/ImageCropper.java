import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCropper {
    public static void main(String[] args) {
        try {
            BufferedImage originalImage = ImageIO.read(new File("img/original.jpg"));

            System.out.println("Original Image Dimension: "+originalImage.getWidth()+"x"+originalImage.getHeight());

            BufferedImage subImage = originalImage.getSubimage(300, 150, 200, 200);
            System.out.println(String.format("Resized Image Dimension: %sx%s",subImage.getWidth(),subImage.getHeight()));

            File outputFile = new File("img/croppedImage.jpg");
            ImageIO.write(subImage, "jpg", outputFile);


            System.out.println("Image cropped successfully: " + outputFile.getPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
