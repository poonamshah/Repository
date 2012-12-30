 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
 
/**
 * Using JAVA to print simple <span class="IL_AD" id="IL_AD1">text file</span> to a printer
 */
 
public class PrintFileToPrinter implements Printable {
 
    static AttributedString myStyledText = null;
 
	public static void main(String args[]) {
		PdfReader reader;
		try {
			reader = new PdfReader("pdfdoc/TestPDf.pdf");
			int n = reader.getNumberOfPages();
			System.out.println(n);
			// Extracting the content from a particular page.
			String str = PdfTextExtractor.getTextFromPage(reader, n); 
			System.out.println(str);
			myStyledText = new AttributedString(str);
			printToPrinter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
 
 
	public static void printToPrinter() {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		Book book = new Book();
		book.append(new PrintFileToPrinter(), new PageFormat());
		printerJob.setPageable(book);

		boolean doPrint = printerJob.printDialog();

		if (doPrint) {
			try {
				printerJob.print();
			} catch (PrinterException ex) {
				System.err.println("Error occurred while trying to Print: "
						+ ex);
			}
		}
	}
 
    public int print(Graphics g, PageFormat format, int pageIndex) {
 
    	Graphics2D g2d = ( Graphics2D )g;

  		g2d.translate( format.getImageableX(), format.getImageableY() );

  		g2d.setPaint( Color.black );

  		Point2D.Float pen = new Point2D.Float();
  		AttributedCharacterIterator charIterator = myStyledText.getIterator();
  		LineBreakMeasurer measurer = new LineBreakMeasurer( charIterator, g2d.getFontRenderContext() );
  		float wrappingWidth = ( float )format.getImageableWidth();
  		while ( measurer.getPosition() < charIterator.getEndIndex() ) {
  			TextLayout layout = measurer.nextLayout( wrappingWidth );
  			pen.y += layout.getAscent();
  			float dx = layout.isLeftToRight() ? 0 : ( wrappingWidth - layout.getAdvance() );
  			layout.draw( g2d, pen.x + dx, pen.y );
  			pen.y += layout.getDescent() + layout.getLeading();
  		}
  		return Printable.PAGE_EXISTS;
  	}
 
}