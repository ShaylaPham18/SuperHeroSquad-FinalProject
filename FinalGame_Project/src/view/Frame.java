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
                JTextArea jTextArea = new JTextArea("\t\t\tHelp Menu\n\n\ttype help to view this help menu\n" +
                        "\ttype map to view the map\n" +
                        "\ttype quit to end the game\n" +
                        "\ttype stat stats or st to view player stats\n" +
                        "\ttype inventory or inv to view player inventory\n" +
                        "\tNAVIGATION-type go + direction you want to go (north south east west n s e w) not case sensitive\n" +
                        "\tIN ROOM-type explore or ex to gather information about the room\n" +
                        "\tIN ROOM-type inspect or ins to view items and puzzles in the room\n" +
                        "\tITEMS-type take + itemName or itemNameShortcut to pick up an item\n" +
                        "\tTo take multiple ITEMS type take + itemName or itemNameShortcut + number you want to pick up\n" +
                        "\tITEMS-type drop + itemName or itemNameShortcut to drop a item\n" +
                        "\tTo drop multiple ITEMS type take + itemName or itemNameShortcut + number you want to drop\n" +
                        "\tTo use an ITEM type use + itemName or itemNameShortCut to use a CONSUMABLE ITEM\n");

                jTextArea.setEditable(false);
                jTextArea.setPreferredSize(new Dimension(150, 150));
                jTextArea.setFont(new Font("Georgia", Font.PLAIN, 15));
                jTextArea.setForeground(new Color(196, 70, 58));


                jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jFrame.setSize(810, 700);
                jFrame.add(jTextArea);
                jFrame.setLocationRelativeTo(null);
                //jFrame.setResizable(false);
                jFrame.setAlwaysOnTop(true);
                jFrame.setVisible(true);
        }

        public void map(){
                JFrame frame = new JFrame("Map");
                ImageIcon imageIcon = new ImageIcon("FinalGame_Project/HospitalMap.png");
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

