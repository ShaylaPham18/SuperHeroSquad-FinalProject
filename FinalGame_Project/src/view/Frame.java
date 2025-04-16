package view;

import javax.swing.*;
import java.awt.*;
/**Justin
 * view.Frame
 * GUI pop ups for if user types in help (NOTHING ELSE)
 * WORK IN PROGRESS
 * */
public class Frame {
    /**Justin
     * helpMenu
     * GUI pop up frame for if user types help
     * WORK IN PROGRESS
    */
    public void helpMenu(){
        JFrame jFrame=new JFrame("Help Menu");
        JTextArea jTextArea=new JTextArea("\n\t\t\tDirections\n\n\tCan either type N S E W or north south east west(not case sensitive)\n" +
                "\n\t\t\tCommands\n\n\tex or explore to explore current room\n" +
                "\thelp to view this help menu\n" +
                "\tMust type exit to exit the game completely\n" +
                "\tpickup + itemName or pick up + itemName or pu + itemName to pick up an item\n" +
                "\tinv or inventory to view inventory\n" +
                "\tdrop + item name to drop item\n" +
                "\tins + itemName or inspect + itemName to inspect an item(Must be in inventory)\n");

        jTextArea.setEditable(false);
        jTextArea.setPreferredSize(new Dimension(150,150));
        jTextArea.setFont(new Font("Georgia",Font.PLAIN,15));
        jTextArea.setForeground(new Color(196,70,58));


        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setSize(800,700);
        jFrame.add(jTextArea);
        jFrame.setLocationRelativeTo(null);
        //jFrame.setResizable(false);
        jFrame.setAlwaysOnTop(true);
        jFrame.setVisible(true);
    }

    public void map(){
        //put map in here
    }
}
