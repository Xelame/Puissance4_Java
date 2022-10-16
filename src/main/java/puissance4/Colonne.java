package puissance4;
import java.util.ArrayList;

public final class Colonne {

    private ArrayList<String> body;

    public Colonne(int height) {
        body = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            body.add(" ");
        }
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