import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;



public class java_mini_new extends JFrame implements ItemListener 
{

    BufferedImage wheelImage;
    BufferedImage ballImage;
    int i = 0;

    // Create a JLabel for the spinning animation
    JLabel wheelAnimationLabel = new JLabel();

    // making an object for each of the classes used: RouletteWheel.java and ProbabilityCalculations.java
    ProbabilityCalculations pc = new ProbabilityCalculations();
    RouletteWheel rw = new RouletteWheel();

    static Image img;  // Images
    static Image img1;

    // Fixed variables
    double credits = 5000.0;
    double newCredits = credits;

    int winOrLoss;
    int number;

    // Checkbox variables
    static boolean oddChecked = false;
    static boolean evenChecked = false;
    static boolean lowChecked = false;
    static boolean highChecked = false;
    static boolean redChecked = false;
    static boolean blackChecked = false;
    static boolean doubleZeroChecked = false;
    static boolean streetChecked = false;

    // Results display panel and label
    Panel resultsPanel;
    TextArea resultsTextArea;

    public java_mini_new() 
    {

        // Load the wheel and ball images
        try 
        {
            wheelImage = ImageIO.read(new File("roulettewheel.png"));
            ballImage = ImageIO.read(new File("Ball.png"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }


        // Background color
        setBackground(new Color(240, 240, 240));


        // TITLE OF ROULETTE
        Font titleFont = new Font("Arial", Font.BOLD, 28);
        Label titleLabel = new Label("The Great American Roulette", Label.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setBounds(0, 30, 600, 40);
        titleLabel.setForeground(Color.BLACK); // Title color
        add(titleLabel);

        // Setting size, layout, and visibility of frame
        setTitle("The Great American Roulette");
        setSize(600, 700); // Adjusted size to accommodate additional components
        setLayout(null);
        setVisible(true);

        // Adding window listener to handle close event
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                dispose(); // close the frame
            }
        });

        // IMAGES
        // Using ToolKit image getImage to get the Image
        img = Toolkit.getDefaultToolkit().getImage("wheel.jpg");
        img1 = Toolkit.getDefaultToolkit().getImage("table.jpg");

        // Mention the path or save the image in the same folder
        MediaTracker track = new MediaTracker(this);

        this.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                System.exit(0);
            }
        });

        // Use a unique id for the image object
        track.addImage(img, 0);
        try 
        {
            track.waitForID(0);
        } 
        catch (InterruptedException ae) 
        {
        }

        // New Image - Wheel
        // Image below the title, fit to size
        //int imageWidth = img.getWidth(this) / 2; // Adjusting width
        //int imageHeight = img.getHeight(this) / 2; // Adjusting height
        //int x = 725; // Center horizontally
        //int y = 150; // Below the title
    


        // RULES PANEL
        // Rules of Roulette
        String rulesText = "Rules of Roulette:\n\n" +
                "1. Each spin of the wheel offers various betting options.\n" +
                "2. Players can bet on single numbers, rows, colors, odd/even, etc.\n" +
                "3. Different bets have different payouts, e.g., single number (straight) bets pay 35 to 1.\n" +
                "4. Players use colored chips to distinguish their bets.\n" +
                "5. Redeem chips at the same table before leaving.\n" +
                "6. The American variant of Roulette is offered in this minigame.\n" +
                "7. The dealer can explain any betting options.";

        // Creating a panel for the rules text
        Panel rulesPanel = new Panel();
        rulesPanel.setLayout(new BorderLayout());
        rulesPanel.setBounds(615, 500, 475, 200);
        rulesPanel.setBackground(Color.WHITE); // Background color

        // Adding rules text to the panel
        TextArea rulesTextArea = new TextArea(rulesText);
        rulesTextArea.setEditable(false);
        rulesPanel.add(rulesTextArea, BorderLayout.CENTER);

        // Adding the rules panel with a red border to the frame
        add(rulesPanel);


        // AVAILABLE BETS
        // Bets of Roulette
        String betsText = "Offered Bets:\n\n" +
                "1. Straight: Single number (lowest probability, 1/38) (payout x35)\n" +  
                "2. Street: Three numbers in a line (3/38) (payout x11)\n" + 
                "4. Red: All red numbers (18/38) (payout x1)\n" + 
                "5. Black: All black numbers (18/38) (payout x1)\n" + 
                "6. 1-18 (Low): Numbers from 1 to 18 (18/38) (payout x1)\n" + 
                "7. 19-36 (High): Numbers from 19 to 36 (18/38) (payout x1)\n";

        // Creating a panel for the rules text
        Panel betsPanel = new Panel();
        betsPanel.setLayout(new BorderLayout());
        betsPanel.setBounds(1115, 500, 400, 200);
        betsPanel.setBackground(Color.WHITE); // Background color

        // Adding rules text to the panel
        TextArea betsTextArea = new TextArea(betsText);
        betsTextArea.setEditable(false);
        betsPanel.add(betsTextArea, BorderLayout.CENTER);

        // Adding the rules panel with a red border to the frame
        add(betsPanel);

        
        // New image - Table
        // Image below the title, fit to size
        int imageWidth1 = img.getWidth(this) / 2; // Adjusting width
        int imageHeight1 = img.getHeight(this) / 2; // Adjusting height
        int x1 = (getWidth() - imageWidth1) / 2; // Center horizontally
        int y1 = 80; // Below the title
        add(new ImagePanel(img1, x1, y1, imageWidth1, imageHeight1));

        // Text boxes
        Label creditsLabel = new Label("Credits:");
        TextField creditsField = new TextField(Double.toString(newCredits), 15);
        creditsField.setEditable(false);

        Label betAmountLabel = new Label("Bet Amount:");
        TextField betAmountField = new TextField("", 15);
        Label singleNumberLabel = new Label("Single Number:");
        TextField singleNumberField = new TextField("", 15);



        // Setting positions for text fields and label
        int textFieldX = 50;
        int textFieldY = 150;

        creditsLabel.setBounds(textFieldX, textFieldY, 100, 20);
        creditsField.setBounds(textFieldX + 150, textFieldY, 100, 20);

        singleNumberLabel.setBounds(textFieldX, textFieldY + 50, 100, 20);
        singleNumberField.setBounds(textFieldX + 150, textFieldY + 50, 100, 20);

        betAmountLabel.setBounds(textFieldX + 300, textFieldY, 100, 20);
        betAmountField.setBounds(textFieldX + 400, textFieldY, 100, 20);

        add(creditsLabel);
        add(creditsField);
        add(betAmountLabel);
        add(betAmountField);
        add(singleNumberLabel);
        add(singleNumberField);



        // RADIO BUTTONS
        // Creating the radio buttons
        CheckboxGroup checkboxGroup = new CheckboxGroup();
        Checkbox checkbox1 = new Checkbox("Odd", checkboxGroup, false);
        Checkbox checkbox2 = new Checkbox("Even", checkboxGroup, false);
        Checkbox checkbox3 = new Checkbox("1-18", checkboxGroup, false);
        Checkbox checkbox4 = new Checkbox("19-36", checkboxGroup, false);
        Checkbox checkbox5 = new Checkbox("RED", checkboxGroup, false);
        Checkbox checkbox6 = new Checkbox("BLACK", checkboxGroup, false);
        Checkbox checkbox7 = new Checkbox("00", checkboxGroup, false);
        Checkbox checkbox9 = new Checkbox("Street", checkboxGroup, false);

        // Setting positions for checkboxes
        int checkboxX = 50;
        int checkboxY = 260;
        int checkboxGap = 30;
        checkbox1.setBounds(checkboxX, checkboxY, 80, 20);
        checkbox2.setBounds(checkboxX, checkboxY + checkboxGap, 80, 20);
        checkbox3.setBounds(checkboxX, checkboxY + 2 * checkboxGap, 80, 20);
        checkbox4.setBounds(checkboxX, checkboxY + 3 * checkboxGap, 80, 20);
        checkbox5.setBounds(checkboxX + 200, checkboxY, 80, 20);
        checkbox6.setBounds(checkboxX + 200, checkboxY + checkboxGap, 80, 20);
        checkbox7.setBounds(checkboxX + 200, checkboxY + 2 * checkboxGap, 80, 20);
        checkbox9.setBounds(checkboxX + 400, checkboxY + checkboxGap, 80, 20);

        // Adding item listeners to checkboxes
        checkbox1.addItemListener(this);
        checkbox2.addItemListener(this);
        checkbox3.addItemListener(this);
        checkbox4.addItemListener(this);
        checkbox5.addItemListener(this);
        checkbox6.addItemListener(this);
        checkbox7.addItemListener(this);
        checkbox9.addItemListener(this);

        // Adding checkboxes to frame
        add(checkbox1);
        add(checkbox2);
        add(checkbox3);
        add(checkbox4);
        add(checkbox5);
        add(checkbox6);
        add(checkbox7);
        add(checkbox9);



        // Creating the action buttons
        Button spinButton = new Button("Deal");
        Button cashoutButton = new Button("Cashout");
        Button deselectButton = new Button("Deselect");

        deselectButton.setBounds(checkboxX + 200, checkboxY + 4 * checkboxGap, 100, 40);
        spinButton.setBounds(checkboxX + 200, checkboxY + 6 * checkboxGap, 100, 40);
        cashoutButton.setBounds(checkboxX + 200, checkboxY + 8 * checkboxGap, 100, 40);


        // Button styling
        spinButton.setBackground(new Color(0, 128, 0)); // Green background
        spinButton.setForeground(Color.WHITE); // White text
        spinButton.setFont(new Font("Courier", Font.BOLD, 16)); // Bold font

        cashoutButton.setBackground(new Color(178, 34, 34)); // Dark red background
        cashoutButton.setForeground(Color.WHITE); // White text
        cashoutButton.setFont(new Font("Courier", Font.BOLD, 16)); // Bold font

        deselectButton.setBackground(new Color(28, 47, 109)); // Dark red background
        deselectButton.setForeground(Color.WHITE); // White text
        deselectButton.setFont(new Font("Courier", Font.BOLD, 16)); // Bold font


        // Adding action buttons to frame
        add(spinButton);
        add(cashoutButton);
        add(deselectButton);




        // Results display panel and label
        resultsPanel = new Panel();
        resultsPanel.setLayout(new BorderLayout());
        resultsPanel.setBounds(50, 600, 500, 100); // Adjusted height
        resultsPanel.setBackground(Color.WHITE); // Background color
        add(resultsPanel);

        resultsTextArea = new TextArea("Results will be displayed here.");
        resultsTextArea.setEditable(false);
        resultsPanel.add(resultsTextArea, BorderLayout.CENTER);
        resultsPanel.setVisible(false);


        // Adding action listener to spin button
        spinButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {

                Random random = new Random();
                number = random.nextInt(37);

                // Display results
                updateResultsTextArea();
                resultsPanel.setVisible(true);


                // Load the GIF
                ImageIcon wheelAnimationIcon = new ImageIcon("wheelAnimation.gif");

                // Resize the image to a smaller size
                Image scaledImage = wheelAnimationIcon.getImage().getScaledInstance(100 * wheelImage.getWidth() / 195, 100 * wheelImage.getHeight() / 195, Image.SCALE_DEFAULT);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                
                // Set the scaled image to the spinning label
                wheelAnimationLabel.setIcon(scaledIcon);
                
                // Add the spinning label to the frame
                add(wheelAnimationLabel);
                
                // Set the size and position of the label
                wheelAnimationLabel.setBounds(718, 120, scaledIcon.getIconWidth(), scaledIcon.getIconHeight());
                
                // Repaint the frame to show the label
                repaint();
                
                // Start a timer to remove the spinning label after a delay (e.g., 3 seconds)
                Timer timer = new Timer(2000, new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e) 
                    {

                        // Remove the spinning label from the frame
                        remove(wheelAnimationLabel);
                        // Repaint the frame to reflect the changes
                        repaint();

                        // Getting the bet amount number entered by the user
                        String betAmountString = betAmountField.getText();
                        double betAmount = Double.parseDouble(betAmountString);

                        // Use checkbox statuses in ProbabilityCalculations
                        newCredits = pc.calculateWinnings(number, newCredits, betAmount, oddChecked, evenChecked, lowChecked, highChecked, redChecked, blackChecked, doubleZeroChecked);
                        winOrLoss = pc.winStatus();

                        resultsTextArea.append("Lucky Number: " + number + "\n");

                        String status = (winOrLoss == 1 ? "You Win!" : "You Lose.");

                        resultsTextArea.append("\n" + status);

                        // Update credits        
                        creditsField.setText(Double.toString(newCredits));
                    }
                });
                timer.setRepeats(false); // Set the timer to run only once
                timer.start(); // Start the timer

            }
        });
    

        // Adding action listener to cashout button
        cashoutButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {    // Display remaining credits
                resultsTextArea.setText("Remaining Credits: " + newCredits);
                resultsPanel.setVisible(true);

                // Close the application
                dispose();
            }
        });


        deselectButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                // Reset all checkboxes to unchecked
                checkbox1.setState(false);
                checkbox2.setState(false);
                checkbox3.setState(false);
                checkbox4.setState(false);
                checkbox5.setState(false);
                checkbox6.setState(false);
                checkbox7.setState(false);
                checkbox9.setState(false);

                // Update results display text area
                updateResultsTextArea();
            }
        });

    }    


    public void paint(Graphics g) 
    {
        // Create an offscreen image
        Image offscreenImage = createImage(getWidth(), getHeight());
        Graphics offscreenGraphics = offscreenImage.getGraphics();

        // Paint to the offscreen image
        offscreenGraphics.clearRect(0, 0, getWidth(), getHeight());
        super.paint(offscreenGraphics);

        // Drawing the image
        int xOffset = 725;
        int yOffset = 150;
        double scaleFactor = 0.5; // Increase size by a factor of 2

        // Apply transformations to the wheel image
        AffineTransform wheelTransform = AffineTransform.getTranslateInstance(xOffset, yOffset);
        wheelTransform.rotate(Math.toRadians(i+=360), wheelImage.getWidth() / 4, wheelImage.getHeight() / 4);
        wheelTransform.scale(scaleFactor, scaleFactor);

        // Apply transformations to the ball image
        AffineTransform ballTransform = AffineTransform.getTranslateInstance(xOffset, yOffset);
        ballTransform.rotate(Math.toRadians(-(i++)), wheelImage.getWidth() / 4, wheelImage.getHeight() / 4);
        ballTransform.scale(scaleFactor * 0.36, scaleFactor * 0.36); // Scale ball size proportionally

        Graphics2D g2d = (Graphics2D) offscreenGraphics;

        // Draw the wheel image
        g2d.drawImage(wheelImage, wheelTransform, null);

        // Draw the ball image
        g2d.drawImage(ballImage, ballTransform, null);

        // Drawing the image
        int imageWidth1 = 2 * img.getWidth(this) / 6; // Adjusting width
        int imageHeight1 = 2 * img.getHeight(this) / 3; // Adjusting height
        int x1 = 1200; // Center horizontally
        int y1 = 90; // Below the title
        offscreenGraphics.drawImage(img1, x1, y1, imageWidth1, imageHeight1, this);

        // Copy the offscreen image to the screen
        g.drawImage(offscreenImage, 0, 0, this);

        // Repaint to continue animation
        //repaint();

    }

    public static void main(String args[]) 
    {
        JFrame frame = new JFrame();
        frame.add(new java_mini_new());
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ItemListener method implementation for checkboxes
    public void itemStateChanged(ItemEvent e) 
    {
        Checkbox checkbox = (Checkbox) e.getItemSelectable();
        String label = checkbox.getLabel();
        boolean checked = e.getStateChange() == ItemEvent.SELECTED;

        // Deselect all other checkboxes when one is selected
        oddChecked = false;
        evenChecked = false;
        lowChecked = false;
        highChecked = false;
        redChecked = false;
        blackChecked = false;
        doubleZeroChecked = false;
        streetChecked = false;

        switch (label) 
        {
            case "Odd":
                oddChecked = checked;
                break;
            case "Even":
                evenChecked = checked;
                break;
            case "1-18":
                lowChecked = checked;
                break;
            case "19-36":
                highChecked = checked;
                break;
            case "RED":
                redChecked = checked;
                break;
            case "BLACK":
                blackChecked = checked;
                break;
            case "00":
                doubleZeroChecked = checked;
                break;
            case "Street":
                streetChecked = checked;
                break;
        }
    }

    // Update the results display text area based on checkbox states
    public void updateResultsTextArea() 
    {
        StringBuilder sb = new StringBuilder();

        // Check if any bet option is selected
        boolean anyBetSelected = oddChecked || evenChecked || lowChecked || highChecked || redChecked || blackChecked || doubleZeroChecked || streetChecked;

        if (anyBetSelected) 
        {
            if (oddChecked) 
            {
                sb.append("Bet chosen: Odd\n\n");
            }

            if (evenChecked) 
            {
                sb.append("Bet chosen: Even\n\n");
            }

            if (lowChecked)
            {
                sb.append("Bet chosen: 1-18\n\n");
            }

            if (highChecked) 
            {
                sb.append("Bet chosen: 19-36\n\n");
            }

            if (redChecked) 
            {
                sb.append("Bet chosen: RED\n\n");
            }

            if (blackChecked) 
            {
                sb.append("Bet chosen: BLACK\n\n");
            }

            if (doubleZeroChecked) 
            {
                sb.append("Bet chosen: 00\n\n");
            }

            if (streetChecked) 
            {
                sb.append("Bet chosen: Street\n\n");
            }

            resultsTextArea.setText(sb.toString());
        } 
        else 
        {
            // If no bet is selected
            sb.append("Bet chosen: None / Straight (Single Number)\n\n");

            resultsTextArea.setText(sb.toString());
        }
    }
}

class ImagePanel extends Panel {
    private Image image;
    private int x, y, width, height;

    public ImagePanel(Image image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, width, height, this);
    }
}