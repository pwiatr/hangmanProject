/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman.GUI;

import hangman.Engine.GameLogic;
import hangman.Engine.Word;
import hangman.Engine.WordList;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


/**
 *
 * @author P
 */
public class GameAreaPanel extends javax.swing.JPanel implements KeyListener {

    GameLogic gLogic = new GameLogic();
    

    private ArrayList<javax.swing.JLabel> charLabels;

    class CharLabel extends javax.swing.JLabel {

        public CharLabel() {
            this.setFont(new java.awt.Font("Microsoft YaHei", 0, 24)); // NOI18N
            this.setMaximumSize(new java.awt.Dimension(30, 35));
            this.setMinimumSize(new java.awt.Dimension(30, 35));
            this.setPreferredSize(new java.awt.Dimension(30, 35));
            this.setSize(new java.awt.Dimension(30, 35));
            this.setHorizontalAlignment(javax.swing.JLabel.CENTER);
            this.setText("_");
        }

    }

    private char wChar[];

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String pK = String.valueOf(e.getKeyChar());

        char pKS = pK.toLowerCase().charAt(0);
        char pKU = pK.toUpperCase().charAt(0);

        String buttonText;

        Component comp[] = buttonPanel.getComponents();
        for (Component c : comp) {
            javax.swing.JButton cBut = (javax.swing.JButton) c;
            if (cBut.isEnabled() == true) {
                buttonText = cBut.getText();

                if (buttonText.compareTo(pK.toUpperCase()) == 0) {
                    gLogic.checkLetter(pKS, pKU, cBut);
                    gameRenderLogic();
                    break;
                }
            }
        }

    }

  

    /**
     * Creates new form GameAreaPanel
     */
    public GameAreaPanel() {
        initComponents();
        setBackground(Color.WHITE);

        // Essential for KeyListener
        setFocusable(true);
        addKeyListener(this);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameAreaPanel.this.requestFocus();
            }
        });
        
        gLogic.NewGame(GameLogic.GameType.SINGLE);
        gameRenderLogic();

    }

    private boolean isFieldShown = false;
    /**
     * Shows all letters as a JLabel objects on screen.
     * @param wordList WordList objects with words used in game.
     * @param wordIndex Index of particular word in WordList
     * @return True if everything went well.
     */
    public boolean showWordField(WordList wordList, int wordIndex) {
        int wordLength = gLogic.wordChar.length;

        wordAreaPanel.removeAll();
        charLabels = new ArrayList<javax.swing.JLabel>();

        for (int i = 0; i < wordLength; i++) {
            charLabels.add(new CharLabel());
            wordAreaPanel.add(charLabels.get(i));
        }

        // Painting
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(GameAreaPanel.this);
                topFrame.validate();
                topFrame.repaint();

            }
        });

        gLogic.changeHangman(GameLogic.HangmanChange.RESET);
        changeKeyboardMode(true);
        this.requestFocus();

        
        return true;
    }

    /**
     * Shows a popup with text.
     * @param text Given text.
     */
    public void showPopup(String text) {
        GamePopup gamePopup = new GamePopup(text);
        gamePopup.setVisible(true);
    }




    public void gameRenderLogic(){
        if(!isFieldShown) {
            showWordField(gLogic.wordList,gLogic.wordIndex);
            isFieldShown = true;
        }
        
        for(Integer e : gLogic.indexes){
            int i = (int) e;
            charLabels.get(i).setText(String.valueOf(gLogic.wordChar[i]));
        }
        
      
        GameLogic.HangmanChange result = gLogic.checkLetterResult();
        if(result != GameLogic.HangmanChange.FAIL)
            changeHangman(gLogic.actualHangmanChange,gLogic.hangingTime);
        else
            loseGame();
    }
    
    /**
     * Changes the hangman picture and the level of hanging .
     * @param hC Enumeration HangmanChange - INCREASE or RESET or FAIL.
     */
    private void changeHangman(int changeNumber, int hangingTime){

        switch(changeNumber){
            case 0:
            {
                hangingMan.setIcon(new ImageIcon(getClass().getResource("/Resources/" + hangingTime + ".jpg")));
                break;
            }
            case 1:
            {
                 hangingMan.setIcon(new ImageIcon(getClass().getResource("/Resources/0.jpg"))); 
                  break;
            }
            case 2: 
            {
               hangingMan.setIcon(new ImageIcon(getClass().getResource("/Resources/12.jpg"))); 
                break;
            }
        }
    }
    
    /**
     * Changes the keyboard to enabled/disabled.
     * @param enable True - the keyboard gets enabled, false otherwise.
     */
    private void changeKeyboardMode(boolean enable) {

        Component comp[] = buttonPanel.getComponents();
        for (Component d : comp) {
            ((javax.swing.JButton) (d)).setEnabled(enable);
        }

    }
    
    private void loseGame(){
        changeKeyboardMode(false);
        showPopup("Przegrałeś!");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        scoreLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        hangingMan = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        ABut = new javax.swing.JButton();
        AAltBut = new javax.swing.JButton();
        BBut = new javax.swing.JButton();
        CBut = new javax.swing.JButton();
        CAltBut = new javax.swing.JButton();
        DBut = new javax.swing.JButton();
        EBut = new javax.swing.JButton();
        EAltBut = new javax.swing.JButton();
        FBut = new javax.swing.JButton();
        GBut = new javax.swing.JButton();
        HBut = new javax.swing.JButton();
        IBut = new javax.swing.JButton();
        JBut = new javax.swing.JButton();
        KBut = new javax.swing.JButton();
        LBut = new javax.swing.JButton();
        LAltBut = new javax.swing.JButton();
        MBut = new javax.swing.JButton();
        NBut = new javax.swing.JButton();
        NAltBut = new javax.swing.JButton();
        OBut = new javax.swing.JButton();
        OAltBut = new javax.swing.JButton();
        PBut = new javax.swing.JButton();
        QBut = new javax.swing.JButton();
        RBut = new javax.swing.JButton();
        SBut = new javax.swing.JButton();
        SAltBut = new javax.swing.JButton();
        TBut = new javax.swing.JButton();
        UBut = new javax.swing.JButton();
        WBut = new javax.swing.JButton();
        VBut = new javax.swing.JButton();
        YBut = new javax.swing.JButton();
        XBut = new javax.swing.JButton();
        ZBut = new javax.swing.JButton();
        ZAltABut = new javax.swing.JButton();
        ZAltBBut = new javax.swing.JButton();
        wordAreaPanel = new javax.swing.JPanel();
        statusBar = new javax.swing.JPanel();
        statusBarText = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Back to menu");
        jButton1.setToolTipText("");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setDoubleBuffered(true);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        scoreLabel.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        scoreLabel.setText("Score (0)");
        scoreLabel.setToolTipText("");
        scoreLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                scoreLabelMouseEntered(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scoreLabel)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(scoreLabel)
                            .addComponent(jButton2))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        hangingMan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/0.jpg"))); // NOI18N
        hangingMan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hangingMan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hangingMan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        buttonPanel.setBackground(new java.awt.Color(255, 255, 255));
        buttonPanel.setToolTipText("");
        buttonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonPanelMouseEntered(evt);
            }
        });

        ABut.setBackground(new java.awt.Color(51, 51, 51));
        ABut.setForeground(new java.awt.Color(255, 255, 255));
        ABut.setText("A");
        ABut.setToolTipText("");
        ABut.setPreferredSize(new java.awt.Dimension(50, 23));
        ABut.setRequestFocusEnabled(false);
        ABut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AButActionPerformed(evt);
            }
        });
        buttonPanel.add(ABut);

        AAltBut.setBackground(new java.awt.Color(51, 51, 51));
        AAltBut.setForeground(new java.awt.Color(255, 255, 255));
        AAltBut.setText("Ą");
        AAltBut.setToolTipText("");
        AAltBut.setPreferredSize(new java.awt.Dimension(50, 23));
        AAltBut.setRequestFocusEnabled(false);
        AAltBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AAltButActionPerformed(evt);
            }
        });
        buttonPanel.add(AAltBut);

        BBut.setBackground(new java.awt.Color(51, 51, 51));
        BBut.setForeground(new java.awt.Color(255, 255, 255));
        BBut.setText("B");
        BBut.setToolTipText("");
        BBut.setPreferredSize(new java.awt.Dimension(50, 23));
        BBut.setRequestFocusEnabled(false);
        BBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BButActionPerformed(evt);
            }
        });
        buttonPanel.add(BBut);

        CBut.setBackground(new java.awt.Color(51, 51, 51));
        CBut.setForeground(new java.awt.Color(255, 255, 255));
        CBut.setText("C");
        CBut.setToolTipText("");
        CBut.setPreferredSize(new java.awt.Dimension(50, 23));
        CBut.setRequestFocusEnabled(false);
        CBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CButActionPerformed(evt);
            }
        });
        buttonPanel.add(CBut);

        CAltBut.setBackground(new java.awt.Color(51, 51, 51));
        CAltBut.setForeground(new java.awt.Color(255, 255, 255));
        CAltBut.setText("Ć");
        CAltBut.setToolTipText("");
        CAltBut.setPreferredSize(new java.awt.Dimension(50, 23));
        CAltBut.setRequestFocusEnabled(false);
        CAltBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CAltButActionPerformed(evt);
            }
        });
        buttonPanel.add(CAltBut);

        DBut.setBackground(new java.awt.Color(51, 51, 51));
        DBut.setForeground(new java.awt.Color(255, 255, 255));
        DBut.setText("D");
        DBut.setToolTipText("");
        DBut.setPreferredSize(new java.awt.Dimension(50, 23));
        DBut.setRequestFocusEnabled(false);
        DBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DButActionPerformed(evt);
            }
        });
        buttonPanel.add(DBut);

        EBut.setBackground(new java.awt.Color(51, 51, 51));
        EBut.setForeground(new java.awt.Color(255, 255, 255));
        EBut.setText("E");
        EBut.setToolTipText("");
        EBut.setPreferredSize(new java.awt.Dimension(50, 23));
        EBut.setRequestFocusEnabled(false);
        EBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EButActionPerformed(evt);
            }
        });
        buttonPanel.add(EBut);

        EAltBut.setBackground(new java.awt.Color(51, 51, 51));
        EAltBut.setForeground(new java.awt.Color(255, 255, 255));
        EAltBut.setText("Ę");
        EAltBut.setToolTipText("");
        EAltBut.setPreferredSize(new java.awt.Dimension(50, 23));
        EAltBut.setRequestFocusEnabled(false);
        EAltBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EAltButActionPerformed(evt);
            }
        });
        buttonPanel.add(EAltBut);

        FBut.setBackground(new java.awt.Color(51, 51, 51));
        FBut.setForeground(new java.awt.Color(255, 255, 255));
        FBut.setText("F");
        FBut.setToolTipText("");
        FBut.setPreferredSize(new java.awt.Dimension(50, 23));
        FBut.setRequestFocusEnabled(false);
        FBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FButActionPerformed(evt);
            }
        });
        buttonPanel.add(FBut);

        GBut.setBackground(new java.awt.Color(51, 51, 51));
        GBut.setForeground(new java.awt.Color(255, 255, 255));
        GBut.setText("G");
        GBut.setToolTipText("");
        GBut.setPreferredSize(new java.awt.Dimension(50, 23));
        GBut.setRequestFocusEnabled(false);
        GBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GButActionPerformed(evt);
            }
        });
        buttonPanel.add(GBut);

        HBut.setBackground(new java.awt.Color(51, 51, 51));
        HBut.setForeground(new java.awt.Color(255, 255, 255));
        HBut.setText("H");
        HBut.setToolTipText("");
        HBut.setPreferredSize(new java.awt.Dimension(50, 23));
        HBut.setRequestFocusEnabled(false);
        HBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HButActionPerformed(evt);
            }
        });
        buttonPanel.add(HBut);

        IBut.setBackground(new java.awt.Color(51, 51, 51));
        IBut.setForeground(new java.awt.Color(255, 255, 255));
        IBut.setText("I");
        IBut.setToolTipText("");
        IBut.setPreferredSize(new java.awt.Dimension(50, 23));
        IBut.setRequestFocusEnabled(false);
        IBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IButActionPerformed(evt);
            }
        });
        buttonPanel.add(IBut);

        JBut.setBackground(new java.awt.Color(51, 51, 51));
        JBut.setForeground(new java.awt.Color(255, 255, 255));
        JBut.setText("J");
        JBut.setToolTipText("");
        JBut.setPreferredSize(new java.awt.Dimension(50, 23));
        JBut.setRequestFocusEnabled(false);
        JBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButActionPerformed(evt);
            }
        });
        buttonPanel.add(JBut);

        KBut.setBackground(new java.awt.Color(51, 51, 51));
        KBut.setForeground(new java.awt.Color(255, 255, 255));
        KBut.setText("K");
        KBut.setToolTipText("");
        KBut.setPreferredSize(new java.awt.Dimension(50, 23));
        KBut.setRequestFocusEnabled(false);
        KBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KButActionPerformed(evt);
            }
        });
        buttonPanel.add(KBut);

        LBut.setBackground(new java.awt.Color(51, 51, 51));
        LBut.setForeground(new java.awt.Color(255, 255, 255));
        LBut.setText("L");
        LBut.setToolTipText("");
        LBut.setPreferredSize(new java.awt.Dimension(50, 23));
        LBut.setRequestFocusEnabled(false);
        LBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LButActionPerformed(evt);
            }
        });
        buttonPanel.add(LBut);

        LAltBut.setBackground(new java.awt.Color(51, 51, 51));
        LAltBut.setForeground(new java.awt.Color(255, 255, 255));
        LAltBut.setText("Ł");
        LAltBut.setToolTipText("");
        LAltBut.setPreferredSize(new java.awt.Dimension(50, 23));
        LAltBut.setRequestFocusEnabled(false);
        LAltBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LAltButActionPerformed(evt);
            }
        });
        buttonPanel.add(LAltBut);

        MBut.setBackground(new java.awt.Color(51, 51, 51));
        MBut.setForeground(new java.awt.Color(255, 255, 255));
        MBut.setText("M");
        MBut.setToolTipText("");
        MBut.setMaximumSize(new java.awt.Dimension(39, 23));
        MBut.setMinimumSize(new java.awt.Dimension(39, 23));
        MBut.setPreferredSize(new java.awt.Dimension(50, 23));
        MBut.setRequestFocusEnabled(false);
        MBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MButActionPerformed(evt);
            }
        });
        buttonPanel.add(MBut);

        NBut.setBackground(new java.awt.Color(51, 51, 51));
        NBut.setForeground(new java.awt.Color(255, 255, 255));
        NBut.setText("N");
        NBut.setToolTipText("");
        NBut.setPreferredSize(new java.awt.Dimension(50, 23));
        NBut.setRequestFocusEnabled(false);
        NBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NButActionPerformed(evt);
            }
        });
        buttonPanel.add(NBut);

        NAltBut.setBackground(new java.awt.Color(51, 51, 51));
        NAltBut.setForeground(new java.awt.Color(255, 255, 255));
        NAltBut.setText("Ń");
        NAltBut.setToolTipText("");
        NAltBut.setPreferredSize(new java.awt.Dimension(50, 23));
        NAltBut.setRequestFocusEnabled(false);
        NAltBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAltButActionPerformed(evt);
            }
        });
        buttonPanel.add(NAltBut);

        OBut.setBackground(new java.awt.Color(51, 51, 51));
        OBut.setForeground(new java.awt.Color(255, 255, 255));
        OBut.setText("O");
        OBut.setToolTipText("");
        OBut.setPreferredSize(new java.awt.Dimension(50, 23));
        OBut.setRequestFocusEnabled(false);
        OBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OButActionPerformed(evt);
            }
        });
        buttonPanel.add(OBut);

        OAltBut.setBackground(new java.awt.Color(51, 51, 51));
        OAltBut.setForeground(new java.awt.Color(255, 255, 255));
        OAltBut.setText("Ó");
        OAltBut.setToolTipText("");
        OAltBut.setPreferredSize(new java.awt.Dimension(50, 23));
        OAltBut.setRequestFocusEnabled(false);
        OAltBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OAltButActionPerformed(evt);
            }
        });
        buttonPanel.add(OAltBut);

        PBut.setBackground(new java.awt.Color(51, 51, 51));
        PBut.setForeground(new java.awt.Color(255, 255, 255));
        PBut.setText("P");
        PBut.setToolTipText("");
        PBut.setPreferredSize(new java.awt.Dimension(50, 23));
        PBut.setRequestFocusEnabled(false);
        PBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PButActionPerformed(evt);
            }
        });
        buttonPanel.add(PBut);

        QBut.setBackground(new java.awt.Color(51, 51, 51));
        QBut.setForeground(new java.awt.Color(255, 255, 255));
        QBut.setText("Q");
        QBut.setToolTipText("");
        QBut.setPreferredSize(new java.awt.Dimension(50, 23));
        QBut.setRequestFocusEnabled(false);
        QBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QButActionPerformed(evt);
            }
        });
        buttonPanel.add(QBut);

        RBut.setBackground(new java.awt.Color(51, 51, 51));
        RBut.setForeground(new java.awt.Color(255, 255, 255));
        RBut.setText("R");
        RBut.setToolTipText("");
        RBut.setPreferredSize(new java.awt.Dimension(50, 23));
        RBut.setRequestFocusEnabled(false);
        RBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RButActionPerformed(evt);
            }
        });
        buttonPanel.add(RBut);

        SBut.setBackground(new java.awt.Color(51, 51, 51));
        SBut.setForeground(new java.awt.Color(255, 255, 255));
        SBut.setText("S");
        SBut.setToolTipText("");
        SBut.setPreferredSize(new java.awt.Dimension(50, 23));
        SBut.setRequestFocusEnabled(false);
        SBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SButActionPerformed(evt);
            }
        });
        buttonPanel.add(SBut);

        SAltBut.setBackground(new java.awt.Color(51, 51, 51));
        SAltBut.setForeground(new java.awt.Color(255, 255, 255));
        SAltBut.setText("Ś");
        SAltBut.setToolTipText("");
        SAltBut.setPreferredSize(new java.awt.Dimension(50, 23));
        SAltBut.setRequestFocusEnabled(false);
        SAltBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAltButActionPerformed(evt);
            }
        });
        buttonPanel.add(SAltBut);

        TBut.setBackground(new java.awt.Color(51, 51, 51));
        TBut.setForeground(new java.awt.Color(255, 255, 255));
        TBut.setText("T");
        TBut.setToolTipText("");
        TBut.setPreferredSize(new java.awt.Dimension(50, 23));
        TBut.setRequestFocusEnabled(false);
        TBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TButActionPerformed(evt);
            }
        });
        buttonPanel.add(TBut);

        UBut.setBackground(new java.awt.Color(51, 51, 51));
        UBut.setForeground(new java.awt.Color(255, 255, 255));
        UBut.setText("U");
        UBut.setToolTipText("");
        UBut.setPreferredSize(new java.awt.Dimension(50, 23));
        UBut.setRequestFocusEnabled(false);
        UBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UButActionPerformed(evt);
            }
        });
        buttonPanel.add(UBut);

        WBut.setBackground(new java.awt.Color(51, 51, 51));
        WBut.setForeground(new java.awt.Color(255, 255, 255));
        WBut.setText("W");
        WBut.setToolTipText("");
        WBut.setPreferredSize(new java.awt.Dimension(50, 23));
        WBut.setRequestFocusEnabled(false);
        WBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WButActionPerformed(evt);
            }
        });
        buttonPanel.add(WBut);

        VBut.setBackground(new java.awt.Color(51, 51, 51));
        VBut.setForeground(new java.awt.Color(255, 255, 255));
        VBut.setText("V");
        VBut.setToolTipText("");
        VBut.setPreferredSize(new java.awt.Dimension(50, 23));
        VBut.setRequestFocusEnabled(false);
        VBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VButActionPerformed(evt);
            }
        });
        buttonPanel.add(VBut);

        YBut.setBackground(new java.awt.Color(51, 51, 51));
        YBut.setForeground(new java.awt.Color(255, 255, 255));
        YBut.setText("Y");
        YBut.setToolTipText("");
        YBut.setPreferredSize(new java.awt.Dimension(50, 23));
        YBut.setRequestFocusEnabled(false);
        YBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YButActionPerformed(evt);
            }
        });
        buttonPanel.add(YBut);

        XBut.setBackground(new java.awt.Color(51, 51, 51));
        XBut.setForeground(new java.awt.Color(255, 255, 255));
        XBut.setText("X");
        XBut.setToolTipText("");
        XBut.setPreferredSize(new java.awt.Dimension(50, 23));
        XBut.setRequestFocusEnabled(false);
        XBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XButActionPerformed(evt);
            }
        });
        buttonPanel.add(XBut);

        ZBut.setBackground(new java.awt.Color(51, 51, 51));
        ZBut.setForeground(new java.awt.Color(255, 255, 255));
        ZBut.setText("Z");
        ZBut.setToolTipText("");
        ZBut.setPreferredSize(new java.awt.Dimension(50, 23));
        ZBut.setRequestFocusEnabled(false);
        ZBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZButActionPerformed(evt);
            }
        });
        buttonPanel.add(ZBut);

        ZAltABut.setBackground(new java.awt.Color(51, 51, 51));
        ZAltABut.setForeground(new java.awt.Color(255, 255, 255));
        ZAltABut.setText("Ź");
        ZAltABut.setToolTipText("");
        ZAltABut.setPreferredSize(new java.awt.Dimension(50, 23));
        ZAltABut.setRequestFocusEnabled(false);
        ZAltABut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZAltAButActionPerformed(evt);
            }
        });
        buttonPanel.add(ZAltABut);

        ZAltBBut.setBackground(new java.awt.Color(51, 51, 51));
        ZAltBBut.setForeground(new java.awt.Color(255, 255, 255));
        ZAltBBut.setText("Ż");
        ZAltBBut.setToolTipText("");
        ZAltBBut.setPreferredSize(new java.awt.Dimension(50, 23));
        ZAltBBut.setRequestFocusEnabled(false);
        ZAltBBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZAltBButActionPerformed(evt);
            }
        });
        buttonPanel.add(ZAltBBut);

        wordAreaPanel.setBackground(new java.awt.Color(255, 255, 255));
        wordAreaPanel.setForeground(new java.awt.Color(255, 255, 255));
        wordAreaPanel.setMaximumSize(new java.awt.Dimension(464, 323));
        wordAreaPanel.setMinimumSize(new java.awt.Dimension(464, 323));
        wordAreaPanel.setPreferredSize(new java.awt.Dimension(464, 323));
        wordAreaPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                wordAreaPanelMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(wordAreaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(wordAreaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );

        statusBar.setBackground(new java.awt.Color(51, 51, 51));

        statusBarText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        statusBarText.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout statusBarLayout = new javax.swing.GroupLayout(statusBar);
        statusBar.setLayout(statusBarLayout);
        statusBarLayout.setHorizontalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusBarText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        statusBarLayout.setVerticalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusBarText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        statusBarText.setText("Stop the game and return to menu.");
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameMainMenuPanel GMMP = new GameMainMenuPanel();

                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(GameAreaPanel.this);
                topFrame.getContentPane().removeAll();
                topFrame.add(GMMP);
                topFrame.validate();
                topFrame.repaint();

            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    private void scoreLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scoreLabelMouseEntered
        statusBarText.setText("The score you gained throughout the game.");      // TODO add your handling code here:
    }//GEN-LAST:event_scoreLabelMouseEntered

    // <editor-fold defaultstate="collapsed" desc="Letter buttons Code">                          
    private void AButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AButActionPerformed
        gLogic.checkLetter('A', 'a', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_AButActionPerformed

    private void AAltButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AAltButActionPerformed
        gLogic.checkLetter('Ą', 'ą', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_AAltButActionPerformed

    private void BButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BButActionPerformed
        gLogic.checkLetter('B', 'b', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_BButActionPerformed

    private void CButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CButActionPerformed
        gLogic.checkLetter('C', 'c', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_CButActionPerformed

    private void CAltButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CAltButActionPerformed
        gLogic.checkLetter('Ć', 'ć', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_CAltButActionPerformed

    private void DButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DButActionPerformed
        gLogic.checkLetter('D', 'd', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_DButActionPerformed

    private void EButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EButActionPerformed
        gLogic.checkLetter('E', 'e', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_EButActionPerformed

    private void EAltButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EAltButActionPerformed
        gLogic.checkLetter('Ę', 'ę', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_EAltButActionPerformed

    private void FButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FButActionPerformed
        gLogic.checkLetter('F', 'f', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_FButActionPerformed

    private void GButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GButActionPerformed
        gLogic.checkLetter('G', 'g', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_GButActionPerformed

    private void HButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HButActionPerformed
        gLogic.checkLetter('H', 'h', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_HButActionPerformed

    private void IButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IButActionPerformed
        gLogic.checkLetter('I', 'i', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_IButActionPerformed

    private void JButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButActionPerformed
        gLogic.checkLetter('J', 'j', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_JButActionPerformed

    private void KButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KButActionPerformed
        gLogic.checkLetter('K', 'k', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_KButActionPerformed

    private void LButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LButActionPerformed
        gLogic.checkLetter('L', 'l', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_LButActionPerformed

    private void LAltButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LAltButActionPerformed
        gLogic.checkLetter('Ł', 'ł', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_LAltButActionPerformed

    private void MButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MButActionPerformed
        gLogic.checkLetter('M', 'm', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_MButActionPerformed

    private void NButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NButActionPerformed
        gLogic.checkLetter('Ń', 'ń', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_NButActionPerformed

    private void NAltButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAltButActionPerformed
        gLogic.checkLetter('N', 'n', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_NAltButActionPerformed

    private void OButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OButActionPerformed
        gLogic.checkLetter('O', 'o', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_OButActionPerformed

    private void OAltButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OAltButActionPerformed
        gLogic.checkLetter('Ó', 'ó', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_OAltButActionPerformed

    private void PButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PButActionPerformed
        gLogic.checkLetter('P', 'p', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_PButActionPerformed

    private void QButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QButActionPerformed
        gLogic.checkLetter('Q', 'q', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_QButActionPerformed

    private void RButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RButActionPerformed
        gLogic.checkLetter('R', 'r', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_RButActionPerformed

    private void SButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SButActionPerformed
        gLogic.checkLetter('S', 's', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_SButActionPerformed

    private void SAltButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAltButActionPerformed
        gLogic.checkLetter('Ś', 'ś', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_SAltButActionPerformed

    private void TButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TButActionPerformed
        gLogic.checkLetter('T', 't', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_TButActionPerformed

    private void UButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UButActionPerformed
        gLogic.checkLetter('U', 'u', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_UButActionPerformed

    private void WButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WButActionPerformed
        gLogic.checkLetter('W', 'w', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_WButActionPerformed

    private void VButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VButActionPerformed
        gLogic.checkLetter('V', 'v', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_VButActionPerformed

    private void YButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YButActionPerformed
        gLogic.checkLetter('Y', 'y', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_YButActionPerformed

    private void XButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XButActionPerformed
        gLogic.checkLetter('X', 'x', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_XButActionPerformed

    private void ZButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZButActionPerformed
        gLogic.checkLetter('Z', 'z', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_ZButActionPerformed

    private void ZAltAButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZAltAButActionPerformed
        gLogic.checkLetter('Ź', 'ź', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_ZAltAButActionPerformed

    private void ZAltBButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZAltBButActionPerformed
        gLogic.checkLetter('Ż', 'ż', (javax.swing.JButton) evt.getSource()); gameRenderLogic();
    }//GEN-LAST:event_ZAltBButActionPerformed
    // </editor-fold>   

    private void buttonPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPanelMouseEntered
        statusBarText.setText("Choose a letter (you can also use your keyboard).");
    }//GEN-LAST:event_buttonPanelMouseEntered

    private void wordAreaPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wordAreaPanelMouseEntered
        statusBarText.setText("Here you can see the word you are guessing.");
    }//GEN-LAST:event_wordAreaPanelMouseEntered

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Word w[] = {new Word("AUTOBUS"), new Word("Słowob")};
        WordList words = new WordList();
        words.addWords(w);

        showWordField(words, 1);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AAltBut;
    private javax.swing.JButton ABut;
    private javax.swing.JButton BBut;
    private javax.swing.JButton CAltBut;
    private javax.swing.JButton CBut;
    private javax.swing.JButton DBut;
    private javax.swing.JButton EAltBut;
    private javax.swing.JButton EBut;
    private javax.swing.JButton FBut;
    private javax.swing.JButton GBut;
    private javax.swing.JButton HBut;
    private javax.swing.JButton IBut;
    private javax.swing.JButton JBut;
    private javax.swing.JButton KBut;
    private javax.swing.JButton LAltBut;
    private javax.swing.JButton LBut;
    private javax.swing.JButton MBut;
    private javax.swing.JButton NAltBut;
    private javax.swing.JButton NBut;
    private javax.swing.JButton OAltBut;
    private javax.swing.JButton OBut;
    private javax.swing.JButton PBut;
    private javax.swing.JButton QBut;
    private javax.swing.JButton RBut;
    private javax.swing.JButton SAltBut;
    private javax.swing.JButton SBut;
    private javax.swing.JButton TBut;
    private javax.swing.JButton UBut;
    private javax.swing.JButton VBut;
    private javax.swing.JButton WBut;
    private javax.swing.JButton XBut;
    private javax.swing.JButton YBut;
    private javax.swing.JButton ZAltABut;
    private javax.swing.JButton ZAltBBut;
    private javax.swing.JButton ZBut;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JLabel hangingMan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JPanel statusBar;
    private javax.swing.JLabel statusBarText;
    private javax.swing.JPanel wordAreaPanel;
    // End of variables declaration//GEN-END:variables
}
