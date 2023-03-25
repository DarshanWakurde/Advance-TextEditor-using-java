import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;


public class FunctionSave {
 TextEditor txt;

    public FunctionSave(TextEditor txt){

        final JFileChooser SaveAs = new JFileChooser();
        SaveAs.setApproveButtonText("Save");
        int actionDialog = SaveAs.showOpenDialog(txt.frame);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File fileName = new File(SaveAs.getSelectedFile() + ".txt");
        BufferedWriter outFile = null;
        try {
            outFile = new BufferedWriter(new FileWriter(fileName));
            txt.area.write(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
  public void open() {
      JFileChooser chooser = new JFileChooser("C:/");
      chooser.setAcceptAllFileFilterUsed(false);
      //Allowing only text (.txt) files extension to open
      FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
      chooser.addChoosableFileFilter(restrict);
      int result = chooser.showOpenDialog(txt.frame);
      if (result == JFileChooser.APPROVE_OPTION) {
          File file = chooser.getSelectedFile();
          try {
              //Reading the file
              FileReader reader = new FileReader(file);
              BufferedReader br = new BufferedReader(reader);
              txt.area.read(br, null);
              //Closing the file after reading
              //Clearing the memory
              br.close();
              txt.area.requestFocus();
          } catch (Exception e) {
              System.out.print(e);
          }
      }
  }}
