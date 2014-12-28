
package hangman;

import hangman.Engine.*;
import hangman.GUI.GameMainWindow;

/**
 *
 * @author P
 */
public class Hangman /*extends JFrame */ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
//        GameScreen f = new GameScreen();
//        GameWindow w = new GameWindow(f);
//        
//        w.run(1024,768);
//
//
//       GameElements elem = new GameElements();
//       
//       SwingUtilities.invokeLater(new Runnable() {
//            public void run(){
//                f.add(elem);
//                f.setVisible(true);
//            }
//        });

//        Word aWord = new Word("aaa");
//        Word bWord = new Word("eee");
//        
//        WordList wList = new WordList();
//        wList.addWords(aWord,bWord);
//        
//        for(int i = 0; i < wList.getAmount(); i++){
//            Word wordCheck;
//            if( (wordCheck = wList.getWord(i)) != null){
//                wordCheck.checkLetter('a');
//                wordCheck.checkLetter('t');
//            }
//        }
//        
//        System.out.println("Guessed: " + wList.checkGuessed() + " from " + wList.getAmount());
        
        GameMainWindow game = new GameMainWindow();
        
           /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameMainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameMainWindow().setVisible(true);
                System.out.println("Hangman opened.");
                
            }
        });

       

    }
    
}
