import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

public class PrintTest {

	public static void main( String args[] ) {

		FileInputStream psStream = null;
		try {
			File file = new File( "pdfdoc/TestPDf.pdf" );
			psStream = new FileInputStream( file );
		} catch ( FileNotFoundException fnfe ) {
			fnfe.printStackTrace();
		}
		if ( psStream == null ) {
			return;
		}
		DocFlavor psInFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
		Doc myDoc = new SimpleDoc( psStream, psInFormat, null );
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		PrintService[] services = PrintServiceLookup.lookupPrintServices( psInFormat, aset );

		// Find printer
		PrintService myPrinter = null;
		for ( int i = 0; i < services.length; i++ ) {
			System.out.println( "service found: " );
			String printerName = services[i].toString();
			if ( printerName.contains( "Samsung SCX-4500W Series" ) ) {
				myPrinter = services[i];
				System.out.println( "Printer Found: " + printerName );
				break;
			}
		}

		// Print function
		if ( myPrinter != null ) {
			String printerName = myPrinter.getName();
			System.out.println( "Name" + printerName );
			DocPrintJob job = myPrinter.createPrintJob();
			try {
				aset.add(MediaSizeName.ISO_A4);
				job.print( myDoc, aset );
				

			} catch ( Exception pe ) {
				pe.printStackTrace();
			}
		} else {
			System.out.println( "no printer services found" );
		}
	}

}