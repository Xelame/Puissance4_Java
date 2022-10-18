package puissance4;


import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import puissance4.Grille;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Conditions de victoire dans plusieurs configurations
     * @throws IOException
     */


    @Test
    public void LineWinShouldreturnCorrectResult()
    {
        Class<Grille> GridClass = Grille.class;
        Class<GameManager> Game = GameManager.class;
        try {
            Method columnWin = GridClass.getDeclaredMethod("columnWin", String.class);
            try {
                Field contenu = GridClass.getDeclaredField("contenu");
                Field numberOfPlayer = Game.getDeclaredField("numberOfPlayer");
                numberOfPlayer.set(null, 2);
                contenu.setAccessible(true); // contenu est deja public donc pas nécessaire mais au cas où :)
                ArrayList<String> Line1 = new ArrayList<>(Arrays.asList("X", " ", " ", " ", " ", " "));
                ArrayList<String> Line2 = new ArrayList<>(Arrays.asList("X", " ", " ", " ", " ", " "));
                ArrayList<String> Line3 = new ArrayList<>(Arrays.asList("X", " ", " ", " ", " ", " "));
                ArrayList<String> Line4 = new ArrayList<>(Arrays.asList("X", " ", " ", " ", " ", " "));
                ArrayList<String> Line5 = new ArrayList<>(Arrays.asList("X", " ", " ", " ", " ", " "));
                ArrayList<String> Line6 = new ArrayList<>(Arrays.asList("X", "X", "X", "X", " ", " "));
                ArrayList<ArrayList<String>> GridShouldReturnTrue = new ArrayList<>(Arrays.asList(Line1, Line2, Line3, Line4, Line5, Line6));
                columnWin.setAccessible(true);
                Grille obj = Grille.getInstance();
                contenu.set(obj,GridShouldReturnTrue);
                System.out.println(columnWin.invoke("obj", null));
                try {assertEquals("This method should return true if there's four same symbols in a same line", true, columnWin.invoke(obj,"X"));}
                catch (Exception e){
                    fail("erorr assert equals");
                }
            }
            catch(Exception e){
                fail("error");
            }

        }
        catch(Exception e){
            fail("error");
        }
        
    }

    //Conditions de visctoire en cas d'égalité

}