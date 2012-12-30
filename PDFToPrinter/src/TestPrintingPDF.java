import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;

public class TestPrintingPDF implements Printable, ActionListener {

	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {

		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		g.drawString("Print PDF!", 100, 100);

		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}

	public void actionPerformed(ActionEvent e) {
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}
		}
	}

	public static void main(String args[]) {

		UIManager.put("swing.boldMetal", Boolean.FALSE);
		JFrame f = new JFrame("Hello World Printer");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		JButton printButton = new JButton("Print Hello World");
		printButton.addActionListener(new TestPrintingPDF());
		f.add("Center", printButton);
		f.pack();
		f.setVisible(true);
	}
}