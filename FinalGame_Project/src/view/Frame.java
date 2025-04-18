package view;

import javax.swing.*;
import java.awt.*;
/**Justin
 * view.Frame
 * GUI pop ups for if user types in help (NOTHING ELSE)
 * WORK IN PROGRESS
 * */
public class Frame {
        public void helpMenu() {
                JFrame jFrame = new JFrame("Help Menu");
                JTextArea jTextArea = new JTextArea("\n\t\t\tDirections\n\n\tCan either type N S E W or north south east west(not case sensitive)\n" +
                        "\n\t\t\tCommands\n\n\tex or explore to explore current room\n" +
                        "\thelp to view this help menu\n" +
                        "\tMust type exit to exit the game completely\n" +
                        "\tpickup + itemName or pick up + itemName or pu + itemName to pick up an item\n" +
                        "\tinv or inventory to view inventory\n" +
                        "\tdrop + item name to drop item\n" +
                        "\tins + itemName or inspect + itemName to inspect an item(Must be in inventory)\n" +
                        "\tequip or eq + itemName to equip an item(Must be a weapon)\n" +
                        "\tunequip or un to unequip an item\n" +
                        "\theal + itemName to restore health(Must be a consumable)\n" +
                        "\tstats or st to view player stats(player health and player damage deal)\n" +
                        "\n\n\t\twhen encountering the monster room:\n" +
                        "\t\battack or att to initiate combat\t\bignore or ig to ignore combat\n" +
                        "\tNOTE: THERE'S A SEPARATE HELP MENU DURING COMBAT");

                jTextArea.setEditable(false);
                jTextArea.setPreferredSize(new Dimension(150, 150));
                jTextArea.setFont(new Font("Georgia", Font.PLAIN, 15));
                jTextArea.setForeground(new Color(196, 70, 58));


                jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jFrame.setSize(800, 700);
                jFrame.add(jTextArea);
                jFrame.setLocationRelativeTo(null);
                //jFrame.setResizable(false);
                jFrame.setAlwaysOnTop(true);
                jFrame.setVisible(true);
        }

        public void map(){
                JFrame frame = new JFrame("Map");
                ImageIcon imageIcon = new ImageIcon("FinalGame_Project/src/HospitalMap.png");
                //ImageIcon imageIcon = new ImageIcon("HospitalMap.png");
                JLabel imageLabel = new JLabel(imageIcon);
                Image image = imageIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(image));
                frame.add(imageLabel);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setAlwaysOnTop(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
        }
}

