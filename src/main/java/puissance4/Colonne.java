package puissance4;
import java.util.ArrayList;
import java.util.Arrays;

public final class Colonne {

    private ArrayList<String> body;

    public ArrayList<String> colonneEmpty; //For AppTest
    public ArrayList<String> colonneWith4jetons;//For AppTest

    public Colonne(int height) {
        body = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            body.add(" ");
        }
    }

    public Colonne(){ //test
        colonneEmpty = new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " "));//For AppTEST
        colonneWith4jetons = new ArrayList<>(Arrays.asList(" ", " ", "X", "X", "X", "X", " ", " "));//For AppTEST
    }

    public Boolean isFull() {
        return body.indexOf(" ") == -1;
    }

    public void fill(String character) {
        if (!isFull()) {
            int peek = body.indexOf(" ");
            body.set(peek, character);
        }
    }

    public Object[] getArray() {
        return body.toArray();
    }
}