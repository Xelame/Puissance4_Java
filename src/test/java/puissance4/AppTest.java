package test.java.puissance4;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;

import main.java.puissance4.Grille;
import main.java.puissance4.App;
import main.java.puissance4.Colonne;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
   
    //on test si une colonne de 4 pions est faite par un joueur
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

    //on test si une ligne de 4 pions est faite par un joueur
    @Test
    public void LineWinShouldreturnCorrectResult()
    {
        Class<Grille> GridClass = Grille.class;
        Class<Colonne> Col = Colonne.class;
        try {
            Method lineWin = GridClass.getDeclaredMethod("lineWin", String.class);
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
                GridShouldReturnTrue.add(new Colonne("withX"));
                GridShouldReturnTrue.add(new Colonne("withX"));
                GridShouldReturnTrue.add(new Colonne("withX"));
                GridShouldReturnTrue.add(new Colonne("null"));
                lineWin.setAccessible(true);
                body.setAccessible(true);
                Grille obj = Grille.getInstance(2);
                contenu.set(obj,GridShouldReturnTrue);
                try {assertEquals("This method should return true if there's four same symbols in a same line", true, lineWin.invoke(obj,"X"));}
                catch (InvocationTargetException e){
                    fail(e.getTargetException().toString());
                }
            }
            catch(Exception e){
                fail("error1");
            }

        }
        catch(Exception e){
            fail("Methode lineWin non trouvée");
        }
        
    }

    //on test si la grille est complètement rempli (cas d'égalité)
    @Test
    public void isFullShouldreturnCorrectResult()
    {
        Class<Grille> GridClass = Grille.class;
        Class<Colonne> Col = Colonne.class;
        try {
            Method isFull = GridClass.getDeclaredMethod("isFull");
            try {
                Field contenu = GridClass.getDeclaredField("contenu");
                Field body =  Col.getDeclaredField("body");
                contenu.setAccessible(true); // contenu est deja public donc pas nécessaire mais au cas où :)
                ArrayList<Colonne> GridShouldReturnTrue;
                GridShouldReturnTrue = new ArrayList<>();
                GridShouldReturnTrue.add(new Colonne("withX"));
                GridShouldReturnTrue.add(new Colonne("withX"));
                GridShouldReturnTrue.add(new Colonne("withX"));
                GridShouldReturnTrue.add(new Colonne("withX"));  // Tableau qui doit retourner True car X gagne
                GridShouldReturnTrue.add(new Colonne("withX"));
                GridShouldReturnTrue.add(new Colonne("withX"));
                GridShouldReturnTrue.add(new Colonne("withX"));
                GridShouldReturnTrue.add(new Colonne("withX"));
                isFull.setAccessible(true);
                body.setAccessible(true);
                Grille obj = Grille.getInstance(2);
                contenu.set(obj,GridShouldReturnTrue);
                try {assertEquals("This method should return true if there's four same symbols in a same line", true, isFull.invoke(obj));}
                catch (InvocationTargetException e){
                    fail(e.getTargetException().toString());
                }
            }
            catch(Exception e){
                fail("error1");
            }

        }
        catch(Exception e){
            fail("Methode isFull non trouvée");
        }
        
    }

}