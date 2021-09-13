package com.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HelloSwing {

   public static void main(String[] args) {
       BufferedImage off_Image =
               new BufferedImage(100, 50,
                       BufferedImage.TYPE_INT_ARGB);

       Graphics2D g2 = off_Image.createGraphics();

       g2.drawLine( 0,0, 100,50);
       g2.setBackground(Color.WHITE);

       File outputfile = new File("saved.png");
       try{
       ImageIO.write(off_Image, "png", outputfile);
       }
       catch(IOException e) {

       }
   }
}
