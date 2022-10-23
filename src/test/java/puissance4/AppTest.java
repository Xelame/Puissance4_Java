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
**/

public class AppTest 
{
    /**
     * Conditions de victoire dans plusieurs configurations
     * @throws IOException
    **/


    @Test
    public void ColumnWinShouldreturnCorrectResult()
    {
        Class<Grille> GridClass = Grille.class;
        Class<Colonne> Col = Colonne.class;
        try {
            Method columnWin = GridClass.getDeclaredMethod("columnWin", String.class);
            try {
                Field contenu = GridClass.getDeclaredField("contenu");
                Field body =  Col.getDeclaredField("body");
                contenu.setAccessible(true); // contenu est deja public donc pas nécessaire mais au cas où :)
                ArrayList<Colonne> GridShouldReturnTrue;
                GridShouldReturnTrue = new ArrayList<>();
                GridShouldReturnTrue.add(new Colonne("null"));
                GridShouldReturnTrue.add(new Colonne("null"));
                GridShouldReturnTrue.add(new Colonne("null"));
                GridShouldReturnTrue.add(new Colonne("withX"));  // Tableau qui doit retourner True car X gagne
                GridShouldReturnTrue.add(new Colonne("null"));
                GridShouldReturnTrue.add(new Colonne("null"));
                GridShouldReturnTrue.add(new Colonne("null"));
                GridShouldReturnTrue.add(new Colonne("null"));
                columnWin.setAccessible(true);
                body.setAccessible(true);
                Grille obj = Grille.getInstance(2);
                contenu.set(obj,GridShouldReturnTrue);
                try {assertEquals("This method should return true if there's four same symbols in a same line", true, columnWin.invoke(obj,"X"));}
                catch (InvocationTargetException e){
                    fail(e.getTargetException().toString());
                }
            }
            catch(Exception e){
                fail("error1");
            }

        }
        catch(Exception e){
            fail("Methode ColumnWin non trouvée");
        }
        
    }

    //Conditions de visctoire en cas d'égalité

}
