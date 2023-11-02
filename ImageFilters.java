import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;

import photoshopJr.Picture;
import photoshopJr.Pixel;

/**
 * This class contains a series of static method that manipulate an image.
 * 
 * @Mondol, Sajib Joseph  
 * @version 
 */
public class ImageFilters
{
    /*
     * Change the names of your custom buttons here
     * 
     * These are static constants
     */
    public static final String YOUR_BUTTON_1 = "[OPTIONAL] CustomFilter #1";
    public static final String YOUR_BUTTON_2 = "[OPTIONAL] CustomFilter #2";
    public static final String YOUR_BUTTON_3 = "[OPTIONAL] CustomFilter #3";

    /**
     * flipHorizontal: Flip the image horizontally along the y-axis in the
     * middle of the image
     * @param picture
     */
    public static Picture flipHorizontal(Picture picture)
    {
        System.err.println("Flip the image horizontally along the y-axis in the middle of the image");
        int w = picture.getWidth();
        int h = picture.getHeight();

        for (int c = 0; c < w/2; c++) {   // Iterate over all pixels in the image, up to the middle of the image
            for (int r = 0; r < h; r++) { // Iterate over all pixels in the row
                Pixel pix1 = picture.getPixel(r, c); // Get the pixel at the current row and column
                Pixel pix2 = picture.getPixel(r, w-c-1); // Get the pixel at the current row and the corresponding column on the other side of the image

                // Swap the two pixels.
                picture.setPixel(r, c, pix2);
                picture.setPixel(r, w-c-1, pix1);
            }
        }
        return picture;
    }

    /**
     * flipVertical: Flip the image vertically along its X-axis
     * Only use flipHorizontal and rotateRight for this one. Do not alter pixels directly in this function.
     * @param picture
     */
    public static Picture flipVertical(Picture picture)
    {
        System.err.println("Flip the image vertically along its X-axis");

        picture = rotateRight(picture);
        picture = rotateRight(picture);
        picture = flipHorizontal(picture);

        return picture;
    }

    /**
     * Convert an image to black-and-white
     * @param picture
     */
    public static Picture toGrayscale(Picture picture)
    {
        System.err.println("Convert the image to black-and-white");
        int w = picture.getWidth();
        int h = picture.getHeight();

        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                Pixel p = picture.getPixel(r,c);

                // Calculates the average of the red, green, and blue values of the pixel
                int total = p.getRed() + p.getGreen() + p.getBlue();
                int avg = total / 3;

                p.setRed (avg) ;
                p.setGreen(avg) ;
                p.setBlue(avg) ;

                picture.setPixel(r, c, p);
            }
        }

        return picture;
    }

    /**
     * Convert an image to "Zombie vision" by subtracting each color intensity from 255 to 
     * "invert" the image.
     * 
     * This is like "mirroring" the color!
     * 
     * @param picture
     */
    public static Picture zombieVision(Picture picture)
    {
        System.err.println("Convert the image to 'Zombie vision' by subtracting each color intensity from 255");
        int w = picture.getWidth();
        int h = picture.getHeight();

        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                Pixel p = picture.getPixel(r,c);

                // Subtract each color intensity from 255
                p.setRed(255 - p.getRed()) ;
                p.setGreen(255 - p.getGreen()) ;
                p.setBlue(255 - p.getBlue()) ;

                // Set the pixel back in the image
                picture.setPixel(r, c, p);
            }
        }

        return picture;
    }

    /**
     * scaleUp: scale up the image by a factor of 2.  This turns every pixel into 4 pixels
     * @param picture
     */
    public static Picture scaleUp(Picture picture)
    {
        System.err.println("scaleUp: scale up the image by a factor of 2 by expanding each pixel into 4 pixels");
        int w = picture.getWidth();
        int h = picture.getHeight();

        // Create a new image that is twice the width and height of the original image
        Picture pic2 = new Picture(2 * picture.getWidth(), 2 * picture.getHeight());

        for (int c = 0; c < w; c++) {
            for (int r = 0; r < h; r++) {
                Pixel pix1 = picture.getPixel(r, c); 

                // Set the corresponding pixel in the new image to the pixel from the original image
                pic2.setPixel(2*r, 2*c, pix1);
                pic2.setPixel(2*r+1, 2*c, pix1);
                pic2.setPixel(2*r, 2*c+1, pix1);
                pic2.setPixel(2*r+1, 2*c+1, pix1);
            }
        }

        return pic2;
    }

    /**
     * scaleDown: scale down the image by a factor of 2 by merging every 4 pixels into 1 pixel
     * (or by randomly picking one of the 4 pixels from the old image to be in the new image)
     *
     * @param picture
     */
    public static Picture scaleDown(Picture picture)
    {
        System.err.println("scaleDown: scale down the image by a factor of two by merging every 4 pixels into 1 pixel");
        int w = picture.getWidth();
        int h = picture.getHeight();

        // Create a new image that is half the size of the original image
        Picture pic2 = new Picture(w / 2, h / 2);

        for (int r = 0; r < h/2; r++) {
            for (int c = 0; c < w/2; c++) {

                // Get the four pixels that will be merged into one pixel in the new image
                Pixel pix1 = picture.getPixel(2 * r, 2 * c);
                Pixel pix2 = picture.getPixel(2 * r + 1, 2 * c);
                Pixel pix3 = picture.getPixel(2 * r, 2 * c + 1);
                Pixel pix4 = picture.getPixel(2 * r + 1, 2 * c + 1);

                // Create a new pixel with the average red, green, and blue values.
                Pixel avg = new Pixel((pix1.getRed() + pix2.getRed() + pix3.getRed() + pix4.getRed()) / 4,
                        (pix1.getGreen() + pix2.getGreen() + pix3.getGreen() + pix4.getGreen()) / 4,
                        (pix1.getBlue() + pix2.getBlue() + pix3.getBlue() + pix4.getBlue()) / 4);

                pic2.setPixel(r, c, avg);
            }
        }

        return pic2;

    }

    /**
     * Mirror the image vertically.  Thus if it's a person's head, the mirrored
     * image should have two heads, one facing up and once facing down.
     * @param picture
     */
    public static Picture mirrorVertical(Picture picture)
    {
        System.err.println("Mirror the image vertically");
        int w = picture.getWidth();
        int h = picture.getHeight();

        for (int c = 0; c < w; c++)  {
            for (int r = 0; r < h; r++) {
                Pixel pix1 = picture.getPixel(r, c); 
                picture.setPixel(h-r-1,c, pix1);
            }
        }
        return picture;
    }

    /**
     * Mirror the image horizontally.  Thus if it's a picture of a person's hands, they should have two left hands.
     * Use only mirrorVertical(), rotateLeft(), and rotateRight() for this one. Do not alter pixels directly in this function.
     * @param picture
     */
    public static Picture mirrorHorizontal(Picture picture)
    {
        System.err.println("Mirror the image horizontally");

        picture = rotateRight(picture);        
        picture = mirrorVertical(picture);
        picture = rotateLeft(picture);

        return picture;
    }

    /**
     * rotateRight: Rotate the image 90 degrees to the right
     * @param picture
     */
    public static Picture rotateRight(Picture picture)
    {
        System.err.println("rotateRight: Rotate the image 90 degrees to the right");
        int w = picture.getWidth();
        int h = picture.getHeight();

        Picture pic1 = new Picture(h , w );

        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                Pixel pix1 = picture.getPixel(r, c); 
                pic1.setPixel(c, r, pix1);
            }
        } 
        Picture pic2 = flipHorizontal(pic1);
        return pic2;
    }

    /**
     * rotateLeft: Rotate the image 90 degrees to the left
     * Use only rotateRight() for this one. Do not alter pixels directly in this function.
     * @param picture
     */
    public static Picture rotateLeft(Picture picture)
    {
        System.err.println("rotateLeft: Rotate the image 90 degrees to the left");

        picture = rotateRight(picture);
        picture = rotateRight(picture);
        picture = rotateRight(picture);

        return picture;
    }

    /**
     * Shift the image to the right by distance pixels.
     *
     * HINT: A really simple way to do this is to create a new image to copy pixels into,
     * use the mod operation to "wrap around", and then copy the new image over the old image at the end
     *
     * @param picture
     */
    public static Picture shiftRight(Picture picture, int distance)
    {
        System.err.println("Shift the image to the right by distance pixels.");
        int w = picture.getWidth();
        int h = picture.getHeight();

        Picture pic1 = new Picture(w , h );

        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w ; c++) {
                int newC = (distance + c)%w;
                Pixel pix1 = picture.getPixel(r, c); 
                pic1.setPixel(r,newC , pix1);
            }
        } 

        return pic1;
    }

    /**
     * Shift the image to the left by distance pixels.
     *
     * Use only shiftRight() for this one. Think about how far you would need to
     * shift something to the right to make it look like it was actually
     * shifted to the left...
     *
     * Do not alter pixels directly in this function.
     *
     * @param picture
     */
    public static Picture shiftLeft(Picture picture, int distance)
    {
        System.err.println("Shift the image to the left by distance pixels.");
        int w = picture.getWidth();
        int h = picture.getHeight();

        int d = w - distance;
        picture = shiftRight(picture, d);

        return picture;
    }

    /**
     * Turn each pixel black, unless it has a high contrast with nearby pixels.
     *
     * @param picture
     */
    public static Picture detectEdges(Picture picture)
    {
        System.err.println("detectEdges: turn each pixel black unless it has a high contrast of the pixels around it");
        int w = picture.getWidth();
        int h = picture.getHeight();
        Picture edge = new Picture(w, h);

        // Detect edges horizontally.
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w - 1; c++) {
                // Get the two pixels that we are comparing
                Pixel pixel1 = picture.getPixel(r, c);
                Pixel pixel2 = picture.getPixel(r, c + 1);

                // Calculate the difference between the two pixels
                int diff = Math.abs(pixel1.getRed() - pixel2.getRed())
                    + Math.abs(pixel1.getGreen() - pixel2.getGreen())
                    + Math.abs(pixel1.getBlue() - pixel2.getBlue());

                if (diff > 100) {
                    // Set both pixels to white
                    edge.setPixel(r, c, new Pixel(255, 255, 255));
                    edge.setPixel(r, c + 1, new Pixel(255, 255, 255));
                } else {
                    // Set both pixels to black
                    edge.setPixel(r, c, new Pixel(0, 0, 0));
                    edge.setPixel(r, c + 1, new Pixel(0, 0, 0));
                }
            }
        }

        // Detect edges vertically.
        for (int r = 0; r < h - 1; r++) {
            for (int c = 0; c < w; c++) {

                // Get the two pixels that we are comparing
                Pixel pixel1 = picture.getPixel(r, c);
                Pixel pixel2 = picture.getPixel(r + 1, c);

                // Calculate the difference between the two pixels
                int diff = Math.abs(pixel1.getRed() - pixel2.getRed())
                    + Math.abs(pixel1.getGreen() - pixel2.getGreen())
                    + Math.abs(pixel1.getBlue() - pixel2.getBlue());

                if (diff > 100) {
                    // Set both pixels to white
                    edge.setPixel(r, c, new Pixel(255, 255, 255));
                    edge.setPixel(r + 1, c, new Pixel(255, 255, 255));
                }
            }
        }

        return edge;
    }

    /**
     * Turn each pixel black, unless it has a high contrast with nearby pixels.
     * 
     * @param picture
     */
    public static Picture blackAndWhiteComic(Picture picture)
    {
        System.err.println("blackAndWhiteComic: turn each pixel completely black or completely white depending on how much total color the pixel has");
        int w = picture.getWidth();
        int h = picture.getHeight();

        Picture comicBook = new Picture(w, h);

        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                Pixel pixel = picture.getPixel(r, c);

                int totalColor = pixel.getRed() + pixel.getGreen() + pixel.getBlue();

                if (totalColor >= 382) {
                    comicBook.setPixel(r, c, new Pixel(255, 255, 255));
                } else {
                    comicBook.setPixel(r, c, new Pixel(0, 0, 0));
                }
            }
        }

        return comicBook;
    }

    /**
     * Pixelate image by scaling down 4 times, then scaling back up 4 times.
     * Do not alter pixels directly in this function.
     * @param picture
     */
    public static Picture pixelate(Picture picture)
    {
        System.err.println("Pixelate image by scaling down 4 times, then scaling back up 4 times");

        for (int i = 0;i < 4; i++){
            picture = scaleDown(picture);
        }

        for (int j = 0; j < 4; j++){
            picture = scaleUp(picture);
        }
        return picture;
    }

    /**
     * Implement Blur
     *
     * @param picture
     */
    public static Picture blur(Picture picture)
    {
        System.err.println("Blur the image");

        return picture;
    }

    /**
     * Implement some other filter
     * @param picture
     */
    public static Picture button1(Picture picture)
    {
        System.err.println("Implement some other filter");
        picture = mirrorVertical(picture);
        picture = rotateRight(picture);
        picture = mirrorVertical(picture);
        picture = rotateLeft(picture);

        return picture;
    }

    /**
     * Implement some other filter
     * @param picture
     */
    public static Picture button2(Picture picture)
    {
        System.err.println("Implement some other filter");

        picture = pixelate(picture);
        picture = blackAndWhiteComic(picture);

        return picture;
    }

    /**
     * Implement some other filter
     * @param picture
     */
    public static Picture button3(Picture picture)
    {
        System.err.println("Implement some other filter");

        return picture;
    }

    /**
     * Save to a file. This has been implemented for you! Don't change it.
     * 
     * @param picture
     */
    public static void saveFile(Picture picture, File file)
    throws IOException
    {
        System.err.println("Save to a file");

        int width = picture.getWidth(); 
        int height = picture.getHeight();
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = bi.getGraphics(); 
        g.drawImage(picture.getBufferedImage(), 0, 0, null);
        ImageIO.write(bi,"jpg",new File(file.getAbsolutePath())); 
        g.dispose();
    }

    /**
     * Restore the original image by copying the original picture over the
     * current picture. This has been implemented for you! Don't change it.
     * @param picture
     * @param originalPicture
     */
    public static void restore(Picture picture, Picture originalPicture)
    {
        picture.copy(originalPicture);
    }

    /**
     * Quit: Just calls System.exit(0) to shutdown the program.
     * @param picture
     */
    public static void quit(Picture picture)
    {
        System.exit(0);
    }

}
