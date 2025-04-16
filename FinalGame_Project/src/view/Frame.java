package view;

import javax.swing.*;
import java.awt.*;
/**Justin
 * view.Frame
 * GUI pop ups for if user types in help (NOTHING ELSE)
 * WORK IN PROGRESS
 * */
public class Frame {
    public void helpMenu(){
            JFrame jFrame = new JFrame("Help Menu");

            // Create a panel with null layout (for absolute positioning)
            JPanel panel = new JPanel(null);

            // Load the image
            ImageIcon backgroundImage = new ImageIcon("FinalGame_Project/src/HospitalMap.png"); // <-- Update path to your .png
            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setBounds(0, 0, 800, 700); // match JFrame size

            // Create your text area
            JTextArea jTextArea = new JTextArea(
                    "\n\t\t\tDirections\n\n" +
                            "\tCan either type N S E W or north south east west (not case sensitive)\n" +
                            "\n\t\t\tCommands\n\n" +
                            "\tex or explore to explore current room\n" +
                            "\thelp to view this help menu\n" +
                            "\tMust type exit to exit the game completely\n" +
                            "\tpickup + itemName or pick up + itemName or pu + itemName to pick up an item\n" +
                            "\tinv or inventory to view inventory\n" +
                            "\tdrop + item name to drop item\n" +
                            "\tins + itemName or inspect + itemName to inspect an item (Must be in inventory)\n"
            );

            jTextArea.setBounds(30, 30, 740, 600); // Set size and position
            jTextArea.setOpaque(false); // Make background transparent
            jTextArea.setFont(new Font("Georgia", Font.PLAIN, 15));
            jTextArea.setForeground(new Color(196, 70, 58));
            jTextArea.setEditable(false);

            // Add components in the correct order
            panel.add(jTextArea);       // on top
            panel.add(backgroundLabel); // at the bottom

            jFrame.setContentPane(panel);
            jFrame.setSize(800, 700);
            jFrame.setLocationRelativeTo(null);
            jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jFrame.setAlwaysOnTop(true);
            jFrame.setVisible(true);
        }
    }
        /*JFrame jFrame=new JFrame("Help Menu");
        JPanel panel = new JPanel(null);

        // Load the image
        ImageIcon backgroundImage = new ImageIcon("FinalGame_Project/src/HospitalMap.png"); // <-- Update path to your .png
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 300, 200);
        JTextArea jTextArea=new JTextArea("\n\t\t\tDirections\n\n\tCan either type N S E W or north south east west(not case sensitive)\n" +
                "\n\t\t\tCommands\n\n\tex or explore to explore current room\n" +
                "\thelp to view this help menu\n" +
                "\tMust type exit to exit the game completely\n" +
                "\tpickup + itemName or pick up + itemName or pu + itemName to pick up an item\n" +
                "\tinv or inventory to view inventory\n" +
                "\tdrop + item name to drop item\n" +
                "\tins + itemName or inspect + itemName to inspect an item(Must be in inventory)\n");

        jTextArea.setEditable(false);
        jTextArea.setBounds(30,30,300,200);
        jTextArea.setPreferredSize(new Dimension(150,150));
        jTextArea.setFont(new Font("Georgia",Font.PLAIN,15));
        jTextArea.setForeground(new Color(196,70,58));

        panel.add(jTextArea);
        panel.add(backgroundLabel);

        jFrame.setContentPane(panel);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setSize(800,700);
        jFrame.add(jTextArea);
        jFrame.setLocationRelativeTo(null);
        //jFrame.setResizable(false);
        jFrame.setAlwaysOnTop(true);
        jFrame.setVisible(true);
    }*/
//}

