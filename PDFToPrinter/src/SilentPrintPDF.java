import com.lowagie.tools.Executable;

public class SilentPrintPDF {

	public static void main( String args[] ) {

		try {
			Executable.printDocumentSilent( "pdfdoc/TestPDf.pdf", true );
		} catch ( Exception e ) {
			System.out.println( e );
		}

	}
}
