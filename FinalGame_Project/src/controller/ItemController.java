package controller;

import model.Items;
import view.GameView;

import java.util.Scanner;

public class ItemController {

    private final Items item;
    private final GameView view;
    private final Scanner myScanner;
    private boolean pickedUp;

    public ItemController(Scanner myScanner, GameView view, Items item, boolean pickedUp) {
        this.myScanner = myScanner;
        this.view = view;
        this.item = item;

    }
}
