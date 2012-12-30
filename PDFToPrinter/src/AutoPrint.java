import java.awt.Graphics;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

public class AutoPrint implements Printable {

	public static void main( String args[] ) throws PrinterException, IOException {

		File f = new File( "pdfdoc/TestPDf.pdf" );
		PrinterJob pjob = PrinterJob.getPrinterJob();
		PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
		pf = pjob.validatePage( pf );
		pjob.setJobName( f.getName() );
		Book book = new Book();
		book.append( new AutoPrint(), new PageFormat() );
		pjob.setPageable( book );
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add( MediaSizeName.ISO_A4 );
		pjob.print( aset );
	}

	@Override
	public int print( Graphics graphics, PageFormat pageFormat, int pageIndex ) throws PrinterException {
		return 0;
	}
}
