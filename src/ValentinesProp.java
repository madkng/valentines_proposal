import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.net.URL;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;


class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}

class ValentinesProp {
    
    public JFrame frame;
    public JButton yesButton;
    public JButton noButton;
    public JLabel label;
    public URL iconURL;
    public BufferedImage img;
    public int fontButtonSize;



    ValentinesProp() {
        // Creating frame
        this.frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the app closes properly
        
        //Try to read image from path
        try{
            File imFile = new File("/home/madking/Desktop/study/valentines_prj/heart.png");
            this.img = ImageIO.read(imFile);
        }catch(IOException ie){
            ie.printStackTrace();
            
        }
        
        //Set image as background
        frame.setContentPane(new ImagePanel(img));

        // Creating buttons and text
        this.fontButtonSize = 20;

        this.yesButton = new JButton("YES <3");
        yesButton.setFont(new Font("Times New Roman", Font.ITALIC, fontButtonSize));

        this.noButton = new JButton("NO :(");
        noButton.setFont(new Font("Times New Roman", Font.ITALIC, fontButtonSize));


        this.label = new JLabel("Would you be my valentine?");
        label.setFont(new Font("Times New Roman", Font.ITALIC, 40));

        
        // Set bounds and format
        label.setBounds(200, -75, 500, 200);

        yesButton.setBounds(800, 550, 100, 100);
        yesButton.setBackground(Color.GREEN);
        yesButton.setActionCommand("pressedYes");
        
        noButton.setBounds(50, 550, 100, 100);
        noButton.setBackground(Color.RED);
        noButton.setActionCommand("pressedNo");

        // Add ActionListener to yesButton
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedYes(e);
            }
        });

        //Catch pressedButton
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                pressedNo(e);
            }
        });

        // Add components to frame
        frame.add(yesButton);
        frame.add(noButton);
        frame.add(label);
        
        //Create a frame of size of Image
        frame.setSize(img.getWidth(), img.getHeight());
        frame.setLayout(null);
        frame.setVisible(true);
    }


    //Executes when Yes button was pressed
    public void pressedYes(ActionEvent e) {
        if ("pressedYes".equals(e.getActionCommand())) {
            //Get button size
            int w = yesButton.getWidth();
            int h = yesButton.getHeight();

            //Increase size by 100 and change text
            yesButton.setSize(w+100, h+100);
            yesButton.setText("I LOVE YOU!! <3");
            fontButtonSize += 4;
            yesButton.setFont(new Font("Times New Roman", Font.ITALIC, fontButtonSize));

            //Change text in noButton also, but disabling it
            noButton.setBounds(noButton.getX(), noButton.getY(), 250, noButton.getHeight());
            noButton.setText("I Love you in red <3");
            noButton.setEnabled(false);
        }
    }


    //Executes when No button was pressed
    public void pressedNo(ActionEvent e){
        if("pressedNo".equals(e.getActionCommand())){
            //Randomly change the position of the button
            //And change the text
            Random random = new Random();
            int x = random.nextInt(50, 500);
            int y = random.nextInt(100, 400);



            noButton.setText("Say yes haha");
            noButton.setBounds(x, y, 150, noButton.getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ValentinesProp()); // Ensure thread safety
    }
}