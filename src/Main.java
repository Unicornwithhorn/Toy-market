import UIConsole.UIConsole;

public class Main {
    public static void main(String[] args) {
        UIConsole uiConsole = new UIConsole();
        try {
            uiConsole.start();
        } catch (IllegalArgumentException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
