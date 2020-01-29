import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResize {

    public static void main(String[] args)  {
        try {
            BufferedImage originalImage = ImageIO.read(new File("img/original.jpg"));
            System.out.println("Original Image Dimension: " + originalImage.getWidth() + "x" + originalImage.getHeight());

            BufferedImage resizedImage = createResizedCopy(originalImage, 200, 200, true);
            System.out.println(String.format("Resized Image Dimension: %sx%s", resizedImage.getWidth(), resizedImage.getHeight()));

            File outputFile = new File("img/resizedImage.jpg");
            ImageIO.write(resizedImage, "jpg", outputFile);

            System.out.println("Image resized successfully: " + outputFile.getPath());
            System.out.println("Congratulations! You have a image with worst quality");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha)
    {
        System.out.println("resizing...");
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}
