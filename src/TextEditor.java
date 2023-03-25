import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.DefaultMenuLayout;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class TextEditor extends Thread implements ActionListener {

   int set = 0;
   StringBuffer sbufer,str;
    String findString;
    int ind=0;
   JFrame frame = new JFrame("TextEditor");
   JMenuItem Cut, Copy, Paste, F1, Background_Colour, New, Open, Save, Print, Exit, Td,help,normal;
   JTextArea area;
   JMenuItem FINDEDIT,FINDNEXTEDIT,REPLACEDIT ,GOTOEDIT,encr,dypr;
fontSelector fontS;

   TextEditor() {
fontS=new fontSelector();
      JMenuBar menu = new JMenuBar();
      area = new JTextArea();
      //---------------------------------------
      JMenu File = new JMenu("File");
      JMenu Edit = new JMenu("Edit");
      JMenu Search = new JMenu("Search");
       JMenu Encrdyp = new JMenu("Data Security");
      JMenu Help = new JMenu("Help");

      //---------------------------------------
      Open = new JMenuItem("Open");
      New = new JMenuItem("New");
      Save = new JMenuItem("Save");
      Print = new JMenuItem("Print");
      Exit = new JMenuItem("Exit");


      New.addActionListener(this::actionPerformed);
      Exit.addActionListener(this::actionPerformed);
      Open.addActionListener(this::actionPerformed);
      Save.addActionListener(this::actionPerformed);
      Print.addActionListener(this::actionPerformed);

      File.add(Open);
      File.add(New);
      File.add(Save);
      File.add(Print);
      File.add(Exit);

       encr = new JMenuItem("Encrypt Data");
       dypr = new JMenuItem("Decrypt Data");
       Encrdyp.add(encr);
       Encrdyp.add(dypr);
       encr.addActionListener(this::actionPerformed);
       dypr.addActionListener(this::actionPerformed);
      //--------------------------

      Cut = new JMenuItem("Cut");
      Copy = new JMenuItem("Copy");
      Paste = new JMenuItem("Paste");
      F1 = new JMenuItem("Font");
      Background_Colour = new JMenuItem("Dark Mode");
       normal = new JMenuItem("Normal");
      Td = new JMenuItem("Time and Date");

      FINDEDIT = new JMenuItem("Find");
      FINDNEXTEDIT = new JMenuItem("Find Next");
      REPLACEDIT = new JMenuItem("Replace");
      GOTOEDIT = new JMenuItem("Go To");

      Search.add(FINDEDIT);
      Search.add(FINDNEXTEDIT);
      Search.add( REPLACEDIT);
      Search.add(GOTOEDIT);

      FINDEDIT .addActionListener(this::actionPerformed);
      FINDNEXTEDIT.addActionListener(this::actionPerformed);
      REPLACEDIT .addActionListener(this::actionPerformed);
      GOTOEDIT.addActionListener(this::actionPerformed);


      Edit.add(Cut);
      Edit.add(Copy);
      Edit.add(Paste);
      Edit.add(F1);
      Edit.add(Background_Colour);
      Edit.add(normal);
      Edit.add(Td);

      Cut.addActionListener(this::actionPerformed);
      Copy.addActionListener(this::actionPerformed);
      Paste.addActionListener(this::actionPerformed);
      F1.addActionListener(this::actionPerformed);
      Background_Colour.addActionListener(this::actionPerformed);
      normal.addActionListener(this);
      Td.addActionListener(this::actionPerformed);

       help = new JMenuItem("Show About Developer");
       help.addActionListener(this);

        Help.add(help);
      //----------------------------------------------------------------------


      //----------------------------------------------------------------------

      menu.add(File);
      menu.add(Edit);
      menu.add(Search);
      menu.add(Encrdyp);
      menu.add(Help);


      //-----------------------------------------
      area.setBounds(15, 10, 750, 700);
//       area.setSize(750,750);
      frame.setSize(800,750);
      frame.setJMenuBar(menu);
      Image i = Toolkit.getDefaultToolkit().getImage("C:\\Users\\wakur\\Documents\\darshan\\darshanAwt\\TextEditorProject\\animation.gif");
      //-----------------------------------

      JScrollPane scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scroll.setBorder(BorderFactory.createEmptyBorder());

      //---------------------------------
      frame.add(scroll);
      BorderLayout b=new BorderLayout();
      b.addLayoutComponent(scroll,BorderLayout.EAST);
       b.addLayoutComponent(area,BorderLayout.CENTER);
       b.addLayoutComponent(menu,BorderLayout.NORTH);
      frame.add(area);
      area.getAccessibleContext();
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setLayout(b);
      frame.setVisible(true);
   }

    public void run() {
        String s = "";
        while (set == 0) {
            s = s + area.getText();
            int i = area.getCaretPosition();
            if (s.endsWith("{")) {
                area.append("}");
            }
            if (s.endsWith("[")) {
                area.append("]");
            }
            if (s.endsWith("(")) {
                area.append(")");
            }
        }
    }


   public void actionPerformed(ActionEvent e) {
       if (e.getSource() == Cut) {
           area.cut();
       } else if (e.getSource() == New) {
           area.setText(" ");
       } else if (e.getSource() == Exit) {
           System.exit(1);
           set = 1;
       } else if (e.getSource() == Save) {
           JFileChooser j = new JFileChooser("f:");

           // Invoke the showsSaveDialog function to show the save dialog
           int r = j.showSaveDialog(null);

           if (r == JFileChooser.APPROVE_OPTION) {

               // Set the label to the path of the selected directory
               File fi = new File(j.getSelectedFile().getAbsolutePath());

               try {
                   // Create a file writer
                   FileWriter wr = new FileWriter(fi, false);

                   // Create buffered writer to write
                   BufferedWriter w = new BufferedWriter(wr);

                   // Write
                   w.write(area.getText());

                   w.flush();
                   w.close();
               } catch (Exception evt) {
                   JOptionPane.showMessageDialog(frame, evt.getMessage());
               }
           }
       } else if (e.getSource() == Open) {

           JFileChooser j = new JFileChooser("f:");

           // Invoke the showsOpenDialog function to show the save dialog
           int r = j.showOpenDialog(null);

           // If the user selects a file
           if (r == JFileChooser.APPROVE_OPTION) {
               // Set the label to the path of the selected directory
               File fi = new File(j.getSelectedFile().getAbsolutePath());

               try {
                   // String
                   String s1 = "", sl = "";

                   // File reader
                   FileReader fr = new FileReader(fi);

                   // Buffered reader
                   BufferedReader br = new BufferedReader(fr);

                   // Initialize sl
                   sl = br.readLine();

                   // Take the input from the file
                   while ((s1 = br.readLine()) != null) {
                       sl = sl + "\n" + s1;
                   }

                   // Set the text
                   area.setText(sl);
               } catch (Exception evt) {
                   JOptionPane.showMessageDialog(frame, evt.getMessage());
               }
           }
       } else if (e.getSource() == Td) {
           String s = area.getText();
           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy      HH:mm:ss Z");
           Date date = new Date();
           area.setText(s + sdf.format(date));
       } else if (e.getSource() == Print) {
           try {
               // print the file
               area.print();
           } catch (Exception evt) {
               JOptionPane.showMessageDialog(frame, evt.getMessage());
           }
       } else if (e.getSource() == Copy) {
           area.copy();
       } else if (e.getSource() == Paste) {
           area.paste();
       } else if (e.getSource() == F1) {

           Font selectedFont = fontS.returnFont();
           fontS.setVisible(true);
           fontS.okButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent ae) {
                   Font selectedFont = fontS.returnFont();
                   area.setFont(selectedFont);
                   fontS.setVisible(false);
               }
           });


           fontS.cancelButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent ae) {
                   fontS.setVisible(false);

               }
           });

       } else if (e.getSource() == Background_Colour) {
           area.setBackground(Color.darkGray);
           area.setForeground(Color.white);
       } else if (e.getSource() == FINDEDIT) {
           {
               try {
                   sbufer = new StringBuffer(area.getText());
                   findString = JOptionPane.showInputDialog(null, "Find");
                   ind = sbufer.indexOf(findString);
                   area.setCaretPosition(ind);
                   area.setSelectionStart(ind);
                   area.setSelectionEnd(ind + findString.length());
               } catch (IllegalArgumentException npe) {
                   JOptionPane.showMessageDialog(null, "String not found");
               } catch (NullPointerException nfe) {
               }
           }
       } else if (e.getSource() == FINDNEXTEDIT) {
           area.copy();
       } else if (e.getSource() == GOTOEDIT) {
           try {
               Element root = area.getDocument().getDefaultRootElement();
               Element paragraph = root.getElement(Integer.parseInt(JOptionPane.showInputDialog(null, "Go to line")));
               area.setCaretPosition(paragraph.getStartOffset() - 1);
           } catch (NullPointerException npe) {
               JOptionPane.showMessageDialog(null, "Invalid line number");
           } catch (NumberFormatException nfe) {

           }
       } else if (e.getSource() == REPLACEDIT) {
           try {
               String replace = JOptionPane.showInputDialog(null, "Replace");
               area.replaceSelection(replace);
           } catch (NumberFormatException nfe) {
           }
       } else if (e.getSource() == normal) {
           area.setBackground(Color.white);
           area.setForeground(Color.black);
       } else if (e.getSource() == help) {

           area.setText("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n" +
                   "▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒\n" +
                   "▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒\n" +
                   "▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒\n" +
                   "▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒\n" +
                   "▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▄░░▒▒▒▒▒\n" +
                   "▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██▌░░▒▒▒▒\n" +
                   "▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░▄▄███▀░░░░▒▒▒\n" +
                   "▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░█████░▄█░░░░▒▒\n" +
                   "▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░▄████████▀░░░░▒▒\n" +
                   "▒▒░░░░░░░░░░░░░░░░░░░░░░░░▄█████████░░░░░░░▒\n" +
                   "▒░░░░░░░░░░░░░░░░░░░░░░░░░░▄███████▌░░░░░░░▒\n" +
                   "▒░░░░░░░░░░░░░░░░░░░░░░░░▄█████████░░░░░░░░▒\n" +
                   "▒░░░░░░░░░░░░░░░░░░░░░▄███████████▌░░░░░░░░▒\n" +
                   "▒░░░░░░░░░░░░░░░▄▄▄▄██████████████▌░░░░░░░░▒\n" +
                   "▒░░░░░░░░░░░▄▄███████████████████▌░░░░░░░░░▒\n" +
                   "▒░░░░░░░░░▄██████████████████████▌░░░░░░░░░▒\n" +
                   "▒░░░░░░░░████████████████████████░░░░░░░░░░▒\n" +
                   "▒█░░░░░▐██████████▌░▀▀███████████░░░░░░░░░░▒\n" +
                   "▐██░░░▄██████████▌░░░░░░░░░▀██▐█▌░░░░░░░░░▒▒\n" +
                   "▒██████░█████████░░░░░░░░░░░▐█▐█▌░░░░░░░░░▒▒\n" +
                   "▒▒▀▀▀▀░░░██████▀░░░░░░░░░░░░▐█▐█▌░░░░░░░░▒▒▒\n" +
                   "▒▒▒▒▒░░░░▐█████▌░░░░░░░░░░░░▐█▐█▌░░░░░░░▒▒▒▒\n" +
                   "▒▒▒▒▒▒░░░░███▀██░░░░░░░░░░░░░█░█▌░░░░░░▒▒▒▒▒\n" +
                   "▒▒▒▒▒▒▒▒░▐██░░░██░░░░░░░░▄▄████████▄▒▒▒▒▒▒▒▒\n" +
                   "▒▒▒▒▒▒▒▒▒██▌░░░░█▄░░░░░░▄███████████████████\n" +
                   "▒▒▒▒▒▒▒▒▒▐██▒▒░░░██▄▄███████████████████████\n" +
                   "▒▒▒▒▒▒▒▒▒▒▐██▒▒▄████████████████████████████\n" +
                   "▒▒▒▒▒▒▒▒▒▒▄▄████████████████████████████████\n" +
                   "████████████████████████████████████████████\n" +
                   "       TEXT EDITOR IN JAVA         \n" +
                   "                                   \n" +
                   "           S I M M C              \n" +
                   "          Bajarang More            \n" +
                   "         Darshan Wakurde           \n" +
                   "         Chaitanya Badhe           \n" +
                   "                                     ");

       }

       if (e.getSource() == encr) {

           JFileChooser j = new JFileChooser("f:");

           // Invoke the showsOpenDialog function to show the save dialog
           int r = j.showOpenDialog(null);
           String s = String.valueOf(j.getSelectedFile());
           // If the user selects a file
           if (r == JFileChooser.APPROVE_OPTION) {
               // Set the label to the path of the selected directory
               File fi = new File(j.getSelectedFile().getAbsolutePath());
               File inputFile = new File(s);
               var key = JOptionPane.showInputDialog(null, "Enter 16 digits password");
               //encryptedFile
               try {
                   encryptedFile(key, s, s);
               } catch (NoSuchAlgorithmException ex) {
                   throw new RuntimeException(ex);
               } catch (NoSuchPaddingException ex) {
                   throw new RuntimeException(ex);
               } catch (InvalidKeyException ex) {
                   throw new RuntimeException(ex);
               } catch (IOException ex) {
                   throw new RuntimeException(ex);
               } catch (IllegalBlockSizeException ex) {
                   throw new RuntimeException(ex);
               } catch (BadPaddingException ex) {
                   throw new RuntimeException(ex);
               }

           }
       }

       if (e.getSource() == dypr) {

           JFileChooser j = new JFileChooser("f:");

           // Invoke the showsOpenDialog function to show the save dialog
           int r = j.showOpenDialog(null);
           String s = String.valueOf(j.getSelectedFile());
           File inputFile = new File(s);
           // If the user selects a file
           if (r == JFileChooser.APPROVE_OPTION) {
               // Set the label to the path of the selected directory
               File fi = new File(j.getSelectedFile().getAbsolutePath());

               var key = JOptionPane.showInputDialog(null, "Enter 16 digits password");
               try {
                   decryptedFile(key, s, inputFile.getParent()+"\\decrypted"+ inputFile.getName());
               } catch (NoSuchAlgorithmException ex) {
                   throw new RuntimeException(ex);
               } catch (NoSuchPaddingException ex) {
                   throw new RuntimeException(ex);
               } catch (InvalidKeyException ex) {
                   throw new RuntimeException(ex);
               } catch (IOException ex) {
                   throw new RuntimeException(ex);
               } catch (IllegalBlockSizeException ex) {
                   throw new RuntimeException(ex);
               } catch (BadPaddingException ex) {
                   throw new RuntimeException(ex);
               }
           }

           File fi = new File(inputFile.getParent()+"\\decrypted"+ inputFile.getName());

           try {
               // String
               String s1 = "", sl = "";

               // File reader
               FileReader fr = new FileReader(fi);

               // Buffered reader
               BufferedReader br = new BufferedReader(fr);

               // Initialize sl
               sl = br.readLine();

               // Take the input from the file
               while ((s1 = br.readLine()) != null) {
                   sl = sl + "\n" + s1;
               }

               // Set the text
               area.setText(sl);
           } catch (Exception evt) {
               JOptionPane.showMessageDialog(frame, evt.getMessage());
           }
       }
       }

      public static void main(String[]args){
         TextEditor proj = new TextEditor();
         proj.start();
//            proj.area.replaceSelection("{}");
      }
    public static void encryptedFile(String secretKey, String fileInputPath, String fileOutPath)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
            IllegalBlockSizeException, BadPaddingException {
        var key = new SecretKeySpec(secretKey.getBytes(), "AES");
        var cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        var fileInput = new File(fileInputPath);
        var inputStream = new FileInputStream(fileInput);
        var inputBytes = new byte[(int) fileInput.length()];
        inputStream.read(inputBytes);

        var outputBytes = cipher.doFinal(inputBytes);

        var fileEncryptOut = new File(fileOutPath);
        var outputStream = new FileOutputStream(fileEncryptOut);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        System.out.println("File successfully encrypted!");
        System.out.println("New File: " + fileOutPath);
    }

    public static void decryptedFile(String secretKey, String fileInputPath, String fileOutPath)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
            IllegalBlockSizeException, BadPaddingException {
        var key = new SecretKeySpec(secretKey.getBytes(), "AES");
        var cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        var fileInput = new File(fileInputPath);
        var inputStream = new FileInputStream(fileInput);
        var inputBytes = new byte[(int) fileInput.length()];
        inputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        var fileEncryptOut = new File(fileOutPath);
        var outputStream = new FileOutputStream(fileEncryptOut);
        outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        System.out.println("File successfully decrypted!");
        System.out.println("New File: " + fileOutPath);
    }
}






class fontSelector extends JDialog
{
   String fontString[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
   JComboBox fontSelector = new JComboBox(fontString);
   JLabel fontLabel = new JLabel("Select Font");

   String fontSizeString[] = {"10","12","14","16","18","20","22","24","28"};
   JComboBox fontSize = new JComboBox(fontSizeString);
   JLabel sizeLabel = new JLabel("Select Size");

   String fontStyleString[] = {"Normal","Bold","Italic","Bold Italic"};
   JComboBox fontStyle = new JComboBox(fontStyleString);
   JLabel styleLabel = new JLabel("Select Style");

   JButton okButton = new JButton("OK");
   JButton cancelButton = new JButton("Cancel");

   JLabel previewLabel = new JLabel("Preview:");
   JLabel preview = new JLabel("   AaBbCc");

   Font selectedFont;

   public fontSelector()
   {
      this.setSize(300,200);
      this.setBackground(Color.blue);
      this.setTitle("Font Selector");

      getContentPane().setLayout(null);

      fontLabel.setBounds(10,10,100,20);
      fontSelector.setBounds(110,10,150,20);
      sizeLabel.setBounds(10,35,100,20);
      fontSize.setBounds(110,35,100,20);
      styleLabel.setBounds(10,60,100,20);
      fontStyle.setBounds(110,60,100,20);

      okButton.setBounds(10,100,100,20);
      cancelButton.setBounds(110,100,100,20);

      previewLabel.setBounds(50,130,100,30);
      preview.setBorder(BorderFactory.createLineBorder(Color.black));
      preview.setBounds(120,130,70,30);

      getContentPane().add(fontLabel);
      getContentPane().add(fontSelector);
      getContentPane().add(sizeLabel);
      getContentPane().add(fontSize);
      getContentPane().add(styleLabel);
      getContentPane().add(fontStyle);
      getContentPane().add(okButton);
      getContentPane().add(cancelButton);
      getContentPane().add(previewLabel);
      getContentPane().add(preview);

      //SETS THE PREVIEW
      fontSelector.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            preview.setFont(new Font(String.valueOf(fontSelector.getSelectedItem()),fontStyle.getSelectedIndex(),14));
         }
      });
      fontStyle.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            preview.setFont(new Font(String.valueOf(fontSelector.getSelectedItem()),fontStyle.getSelectedIndex(),14));
         }
      });
   }

   public Font returnFont()
   {
      String fontSS = String.valueOf(fontSelector.getSelectedItem());
      int fontSZ = Integer.parseInt(String.valueOf(fontSize.getSelectedItem()));
      int fontST = fontStyle.getSelectedIndex();

      selectedFont = new Font(fontSS,fontST,fontSZ);

      return selectedFont;
   }

}

