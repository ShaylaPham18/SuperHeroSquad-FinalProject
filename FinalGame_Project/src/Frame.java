import javax.swing.*;
import java.awt.*;

public class Frame {
    void helpmenu(){
        JFrame jFrame=new JFrame("Help Menu");
        JTextArea jTextArea=new JTextArea("\n\t\t\tDirections\n\n\tCan either type N S E W or north south east west(not case sensitive)\n" +
                "\n\t\t\tCommands\n\n\tex or explore to explore current room\n" +
                "\thelp to view this help menu\n" +
                "\tMust type exit to exit the game completely\n" +
                "\tpickup + itemName or pick up + itemName or pu + itemName to pick up an item\n" +
                "\tinv or inventory to view inventory\n" +
                "\tdrop + item name to drop item\n" +
                "\tins + itemName or inspect + itemName to inspect an item(Must be in inventory)\n" +
                "\n\n\n\n\t\t\tMap\n\n\n" +
                "\t 3(Puzzle)\t 5(Item)\t\t6\n\tWashroom\tChemicals room                Thesis room" +
                "\n\n\t 1(Item)\t 4(Item)\n\tLaboratory\tSamples room" +
                "\n\n\t 2(Item)\n\tTools room");

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
}
