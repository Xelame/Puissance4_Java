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
      
   

   

    @Test
    public void LineWinShouldreturnCorrectResult()
    {
        
        Class<Grille> GridClass = Grille.class;
        Class<App> Game = App.class;
        //Class<Colonne> colonne = Colonne.class;
        try {
            //récupération des variables et méthodes
            int numberOfPlayer = 2;
              //mise en place du constructeur grille
              Constructor<Grille> constructor = GridClass.getDeclaredConstructor(int.class);
      
              constructor.setAccessible(true);
               //création de l'instance grille
               constructor.newInstance(numberOfPlayer);
  

        
            Method columnWin = GridClass.getDeclaredMethod("columnWin",String.class);
            Field contenu = GridClass.getDeclaredField("contenu");
            Field instance = GridClass.getDeclaredField("instance");
            Method fillColumn =  GridClass.getDeclaredMethod("fillColumn", int.class,String.class);     
            Method isFull = GridClass.getDeclaredMethod("isFull");     
            Method getInstance = GridClass.getDeclaredMethod("getInstance", int.class);
            

            getInstance.invoke(constructor, numberOfPlayer);
              
          
             //création d'un array similaire un contenu qui va être rempli en fonction de nos choix
             ArrayList<String> Line = new ArrayList<>(Arrays.asList("X", "X", "X", "X", "X", "X"));
             ArrayList<String> LineVoid = new ArrayList<>(Arrays.asList(" ", " ", " ", " ", " ", " "));
             ArrayList<ArrayList<String>> GridShouldReturnTrue = new ArrayList<>();

             GridShouldReturnTrue.add(Line);
             GridShouldReturnTrue.add(Line);
             GridShouldReturnTrue.add(Line);
             GridShouldReturnTrue.add(Line);
             GridShouldReturnTrue.add(Line);
             GridShouldReturnTrue.add(Line);
             GridShouldReturnTrue.add(Line);
             GridShouldReturnTrue.add(Line);               

            try {                  
               
            //ajout de notre array a contenu
            contenu.setAccessible(true);
            contenu.set(getInstance.invoke(null, numberOfPlayer),GridShouldReturnTrue);
            
                      
            
            }
            catch (IllegalArgumentException e){
                fail("error lors de l'ajout de l'array" +e);
            }   

                

            try {
                Boolean value = (Boolean) isFull.invoke(constructor);
                
                try {
                    assertEquals("This method should return true if there's four same symbols in a same line ", true,value );
                } catch (Exception e) {
                    fail("error assert equals " );
                }
            
            }
            catch (Exception e){
                fail("error bool ");
            }
         

        }
        catch(Exception e){
            fail("error");
        }
        

    }

    //Conditions de visctoire en cas d'égalité

}