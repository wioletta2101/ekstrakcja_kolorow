package lab6;

import java.awt.*;
import static java.awt.Color.gray;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import static java.lang.Math.round;
import java.net.*;
import javax.imageio.*;

public class Lab6 extends JFrame {

    BufferedImage image;
    JLabel promptLabel;
    JTextField prompt;
    JButton promptButton;
    JFileChooser fileChooser;
    JButton loadButton;
    JButton processingButton;
    JScrollPane scrollPane;
    JLabel imgLabel;

    public Lab6() {
        super("Image processing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        JPanel inputPanel = new JPanel();
        promptLabel = new JLabel("Filename:");
        inputPanel.add(promptLabel);
        prompt = new JTextField(20);
        inputPanel.add(prompt);
        promptButton = new JButton("Browse");
        inputPanel.add(promptButton);
        contentPane.add(inputPanel, BorderLayout.NORTH);
        fileChooser = new JFileChooser();
        promptButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int returnValue
                        = fileChooser.showOpenDialog(null);
                        if (returnValue
                        == JFileChooser.APPROVE_OPTION) {
                            File selectedFile
                            = fileChooser.getSelectedFile();
                            if (selectedFile != null) {
                                prompt.setText(selectedFile.getAbsolutePath());
                            }
                        }
                    }
                }
        );

        imgLabel = new JLabel();
        scrollPane = new JScrollPane(imgLabel);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel outputPanel = new JPanel();
        loadButton = new JButton("Load");
        outputPanel.add(loadButton);
        loadButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String name = prompt.getText();
                            File file = new File(name);
                            if (file.exists()) {
                                image = ImageIO.read(file.toURL());
                                if (image == null) {
                                    System.err.println("Invalid input file format");
                                } else {
                                    imgLabel.setIcon(new
                                    ImageIcon(image));
                                }
                            } else {
                                System.err.println("Bad filename");
                            }
                        } catch (MalformedURLException mur) {
                            System.err.println("Bad filename");
                        } catch (IOException ioe) {
                            System.err.println("Error reading file");
                        }
                    }
                }
        );

        processingButton = new JButton("Processing");
        outputPanel.add(processingButton);
        processingButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Processing(image);
                        imgLabel.setIcon(new ImageIcon(image));
                    }
                });

        contentPane.add(outputPanel, BorderLayout.SOUTH);
    }

    private static void Processing(BufferedImage img) {
        BufferedImage temp = copyImage(img);
        int[][] maska = new int[][]{
         {-1, -1, -1},
         {-1, 8, -1},
         {-1, -1, -1},
         };
                
        double sc = 0.5;
        double roz = 1.5;
        double stala = 1.5;
        double gray;
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        for (int x = 0; x < w -1 ; x++) {
            for (int y = 0; y < h-1; y++) {
                int rgb = img.getRGB(x, y);
                int a = (rgb & 0xff000000) >>> 24;
                int r = (rgb & 0x00ff0000) >>> 16;
                int g = (rgb & 0x0000ff00) >>> 8;
                int b = rgb & 0x000000ff;

        //tu można modyfikować wartość kanałów
       ////zad2
                //    gray=0.299*r + 0.587*g + 0.114*b;
                //    r=(int)gray;
                //    g=(int)gray;
                //    b=(int)gray;
                //    
                ////zad3
                //      if (gray > 50) r = 255; else r = 0;
                //      if (gray > 50) g = 255; else g = 0;
                //      if (gray > 50) b = 255; else b = 0;
       //zad4
                //    a = 255 - a;
                //    r = 255 - r;
                //    g = 255 - g;
                //    b = 255 - b;
       //zad5
                //    a = (int)((a - 128) * stala + 128);
                //    r = (int)((r - 128) * stala + 128);
                //    g = (int)((g - 128) * stala + 128);
                //    b = (int)((b - 128) * stala + 128);
       //zad6
                //sciemnianie
                //    a = (int)(a * sc);
                //    r = (int)(r * sc);
                //    g = (int)(g * sc);
                //    b = (int)(b * sc);
                //rozjasnienie
//                a = (int) (a * roz);
//                r = (int) (r * roz);
//                g = (int) (g * roz);
//                b = (int) (b * roz);
//
//                if (a < 0) {
//                    a = 0;
//                } else if (a > 255) {
//                    a = 255;
//                }
//                if (r < 0) {
//                    r = 0;
//                } else if (r > 255) {
//                    r = 255;
//                }
//                if (g < 0) {
//                    g = 0;
//                } else if (g > 255) {
//                    g = 255;
//                }
//                if (b < 0) {
//                    b = 0;
//                } else if (b > 255) {
//                    b = 255;
//                }
//
                //zapis kanałów
//                int RGB = b | (g << 8) | (r << 16) | (a << 24);
                
                int rr = 121;
                int gg = 67;
                int bb = 116;
                
                int RGB = 0;
                
                if(yy(y(r, g, b),0.0001) == yy(y(rr,gg,bb),0.0001) &&
                        uu(u(r, g, b),0.0001) == uu(u(rr, gg, bb), 0.0001) &&
                        vv(v(r, g, b),0.0001) == vv(v(rr, gg, bb), 0.0001)
                        )
                
//                int a = filterPixel(temp, x, y, maska);
                RGB = a | (a << 8) | (a << 16) | (255 << 24);
                img.setRGB(x, y, RGB);
                
            }
        }
    }
    
    private static int getPixel(BufferedImage img, int x, int y)
    {
         double gray;
         if(x>= img.getWidth()|| y>=img.getHeight()) return 0;
         
         int rgb = img.getRGB(x, y);
         int a = (rgb & 0xff000000) >>> 24;
         int r = (rgb & 0x00ff0000) >>> 16;
         int g = (rgb & 0x0000ff00) >>> 8;
         int b = rgb & 0x000000ff;
         gray=0.299*r + 0.587*g + 0.114*b;
        //img.setRGB(x, y, RGB);
        return (int)gray;
    }
    
    private static int y(int r, int g,int b)
    {
        double y;
        y = 0.299*r + 0.587*g + 0.114*b;
        return (int)y;
    }
    
     private static int u(int r, int g,int b)
    {
        double u;
        u = -0.147*r - 0.289*g + 0.114*b;
        return (int)u;
    }
      private static int v(int r, int g,int b)
    {
        double v;
        v = 0.615*r - 0.515*g - 0.100*b;
        return (int)v;
    }
      
    private static int yy(int y, double alfa)
    {
        double yy;
        yy = round (y/25.5 + alfa);
        return (int)yy;
    }
    
    private static int uu(int u, double alfa)
    {
        double uu;
        uu = round((u + 111.18) / (22.236 + alfa));
        return (int)uu;
    }
    private static int vv(int v, double alfa)
    {
        double vv;
        vv = round((v + 156.825) / (31.365 + alfa));
        return (int)vv;
    }
    
    private static BufferedImage copyImage(BufferedImage source) 
    {
        BufferedImage b = new BufferedImage(source.getWidth(),
        source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
  }
    
    private static int filterPixel(BufferedImage img, int x, int y,int[][]
    maska)
    {
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        if((x>w || y>h || x<0 || y<0)) return 0;
            
        
        int f=0;
        for (int i = 0; i < maska.length; i++) 
        {
            for (int j = 0; j < maska[0].length; j++)
            {
                f = f + maska[i][j]*getPixel(img, x - maska.length/2+i,y -
                maska[0].length/2+j);
            }
       }
        
        if(f<=0) return 0;
        if(f>=255) return 255;
        
        return f;
        
    }

    public static void main(String args[]) {
        JFrame frame = new Lab6();
        frame.pack();
        frame.show();
    }
}
