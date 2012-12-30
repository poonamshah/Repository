import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class DesktopPrintPDF {
  public static void main(String[] a) {
    try {
      Desktop desktop = null;
      if (Desktop.isDesktopSupported()) {
        desktop = Desktop.getDesktop();
      }
       desktop.print(new File("pdfdoc/TestPDf.pdf"));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

  }
}