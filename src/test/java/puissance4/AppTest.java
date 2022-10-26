package puissance4;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;



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
                contenu.setAccessible(true); // contenu est deja public donc pas necessaire mais au cas où :)
                ArrayList<Colonne> GridShouldReturnTrue;
                GridShouldReturnTrue = new ArrayList<>();
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("X", "X", "X", "X", " ", " "))));  // Tableau qui doit retourner True car X gagne
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
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
            fail("Methode ColumnWin non trouvee");
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
                contenu.setAccessible(true); // contenu est deja public donc pas necessaire mais au cas où :)
                ArrayList<Colonne> GridShouldReturnTrue;
                GridShouldReturnTrue = new ArrayList<>();
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("X", " ", " ", " ", " ", " "))));;  // Tableau qui doit retourner True car X gagne
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("X", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("X", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("X", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
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
            fail("Methode lineWin non trouvee");
        }
        
    }

    //on test si la grille est complètement rempli (cas d'egalite)
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
                contenu.setAccessible(true); // contenu est deja public donc pas necessaire mais au cas où :)
                ArrayList<Colonne> GridShouldReturnTrue;
                GridShouldReturnTrue = new ArrayList<>();
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("X", "O", "X", "O", "X", "O"))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("O", "X", "O", "X", "O", "X"))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("O", "X", "O", "X", "O", "X"))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("O", "X", "O", "X", "O", "X"))));  
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("X", "O", "X", "O", "X", "O"))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("O", "X", "O", "X", "O", "X"))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("O", "X", "O", "X", "O", "X"))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("O", "X", "O", "X", "O", "X"))));
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
            fail("Methode isFull non trouvee");
        }

    }


        @Test
    public void DiagonalWinShouldreturnCorrectResult()
    {
        Class<Grille> GridClass = Grille.class;
        Class<Colonne> Col = Colonne.class;
        try {
            Method diagonalWin = GridClass.getDeclaredMethod("diagonalWin", String.class , Boolean.class);
            try {
                Field contenu = GridClass.getDeclaredField("contenu");
                Field body =  Col.getDeclaredField("body");
                contenu.setAccessible(true); // contenu est deja public donc pas necessaire mais au cas où :)
                ArrayList<Colonne> GridShouldReturnTrue;
                GridShouldReturnTrue = new ArrayList<>();
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList("X", " ", " ", " ", " ", " "))));  // Tableau qui doit retourner True car X gagne
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", "X", " ", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", "X", " ", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", "X", " ", " "))));
                GridShouldReturnTrue.add(new Colonne(6, new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "))));
                diagonalWin.setAccessible(true);
                body.setAccessible(true);
                Grille obj = Grille.getInstance(2);
                contenu.set(obj,GridShouldReturnTrue);
                try {assertEquals("This method should return true if there's four same symbols in a same line", true, diagonalWin.invoke(obj,"X",false));}
                catch (InvocationTargetException e){
                    fail(e.getTargetException().toString());
                }
            }
            catch(Exception e){
                fail("error1");
            }

        }
        catch(Exception e){
            fail("Methode diagonalWin non trouvee");
        }
        
    
        
    }

}
