package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class JNumberTextField extends JTextField implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1592348797907332103L;
	private static final char DOT = '.';
	private static final char NEGATIVE = '-';
	private static final String BLANK = "";
	private static final int DEF_PRECISION = 2;

	public static final int NUMERIC = 2;
	public static final int DECIMAL = 3;

	public static final String FM_NUMERIC = "123456789";
	public static final String FM_DECIMAL = FM_NUMERIC + DOT;

	private int maxLength = 0;
	private int format = NUMERIC;
	private String negativeChars = BLANK;
	private String allowedChars = null;
	private boolean allowNegative = false;
	private int precision = 0;

	private int x = -1;
	private int y = -1;
	JNumberTextField jf[][];

	protected PlainDocument numberFieldFilter;

	public JNumberTextField(int i, int j, JNumberTextField[][] jf) {
		this(10, NUMERIC, i, j, jf);
	}

	public JNumberTextField(int maxLen, int i, int j, JNumberTextField[][] jf) {
		this(maxLen, NUMERIC, i, j, jf);
	}

	public JNumberTextField(int maxLen, int format, int i, int j,
			JNumberTextField[][] jf) {
		setAllowNegative(false);
		setMaxLength(maxLen);
		setFormat(format);
		this.addMouseListener(this);
		numberFieldFilter = new JNumberFieldFilter();
		super.setDocument(numberFieldFilter);
		this.x = i;
		this.y = j;
		this.jf = jf;

	}

	public void setMaxLength(int maxLen) {
		if (maxLen > 0)
			maxLength = maxLen;
		else
			maxLength = 0;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setPrecision(int precision) {
		if (format == NUMERIC)
			return;

		if (precision >= 0)
			this.precision = precision;
		else
			this.precision = DEF_PRECISION;
	}

	public int getPrecision() {
		return precision;
	}

	public Number getNumber() {
		Number number = null;

		if (format == NUMERIC)
			number = new Integer(getText());
		else
			number = new Double(getText());

		return number;
	}

	public void setNumber(Number value) {
		setText(String.valueOf(value));
	}

	public int getInt() {
		return Integer.parseInt(getText());
	}

	public void setInt(int value) {
		setText(String.valueOf(value));
	}

	public float getFloat() {
		return (new Float(getText())).floatValue();
	}

	public void setFloat(float value) {
		setText(String.valueOf(value));
	}

	public double getDouble() {
		return (new Double(getText())).doubleValue();
	}

	public void setDouble(double value) {
		setText(String.valueOf(value));
	}

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		switch (format) {
		case NUMERIC:
		default:
			this.format = NUMERIC;
			this.precision = 0;
			this.allowedChars = FM_NUMERIC;
			break;

		case DECIMAL:
			this.format = DECIMAL;
			this.precision = DEF_PRECISION;
			this.allowedChars = FM_DECIMAL;
			break;
		}
	}

	public void setAllowNegative(boolean value) {
		allowNegative = value;

		if (value)
			negativeChars = "" + NEGATIVE;
		else
			negativeChars = BLANK;
	}

	public boolean isAllowNegative() {
		return allowNegative;
	}

	public void setDocument(Document document) {
	}

	class JNumberFieldFilter extends PlainDocument {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4235161939309358477L;

		public JNumberFieldFilter() {
			super();
		}

		public void insertString(int offset, String str, AttributeSet attr)
				throws BadLocationException {
			String text = getText(0, offset) + str
					+ getText(offset, (getLength() - offset));

			if (str == null || text == null)
				return;

			for (int i = 0; i < str.length(); i++) {
				if ((allowedChars + negativeChars).indexOf(str.charAt(i)) == -1)
					return;
			}

			int precisionLength = 0, dotLength = 0, minusLength = 0;
			int textLength = text.length();

			try {
				if (format == NUMERIC) {
					if (!((text.equals(negativeChars)) && (text.length() == 1)))
						new Long(text);
				} else if (format == DECIMAL) {
					if (!((text.equals(negativeChars)) && (text.length() == 1)))
						new Double(text);

					int dotIndex = text.indexOf(DOT);
					if (dotIndex != -1) {
						dotLength = 1;
						precisionLength = textLength - dotIndex - dotLength;

						if (precisionLength > precision)
							return;
					}
				}
			} catch (Exception ex) {
				return;
			}

			if (text.startsWith("" + NEGATIVE)) {
				if (!allowNegative)
					return;
				else
					minusLength = 1;
			}

			if (maxLength < (textLength - dotLength - precisionLength - minusLength))
				return;

			super.insertString(offset, str, attr);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

		int LigneDeZone = (x / (9 / 3)) * 9 / 3;

		int LigneDeZone2 = LigneDeZone + 9 / 3;

		int ColoneDeZone = (y / (9 / 3)) * 9 / 3;

		int ColoneDeZone2 = ColoneDeZone + 9 / 3;
		// TODO Auto-generated method stub
		for (int k = LigneDeZone; k < LigneDeZone2; k++) {
			for (int j = ColoneDeZone; j < ColoneDeZone2; j++) {
				jf[k][j].setBackground(Color.cyan);
			}
		}
		for (int g = 0; g < 9; g++) {
			for (int h = 0; h < 9; h++) {
				if (g == x) {
					jf[g][h].setBackground(Color.red);

				}
			}
		}
		for (int b = 0; b < 9; b++) {
			for (int c = 0; c < 9; c++) {
				if (c == y) {
					jf[b][c].setBackground(Color.yellow);

				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		int LigneDeZone = (x / (9 / 3)) * 9 / 3;

		int LigneDeZone2 = LigneDeZone + 9 / 3;

		int ColoneDeZone = (y / (9 / 3)) * 9 / 3;

		int ColoneDeZone2 = ColoneDeZone + 9 / 3;
		// TODO Auto-generated method stub
		for (int k = LigneDeZone; k < LigneDeZone2; k++) {
			for (int j = ColoneDeZone; j < ColoneDeZone2; j++) {
				jf[k][j].setBackground(Color.white);
			}
		}
		for (int g = 0; g < 9; g++) {
			for (int h = 0; h < 9; h++) {
				if (g == x) {
					jf[g][h].setBackground(Color.white);

				}
			}
		}
		for (int b = 0; b < 9; b++) {
			for (int c = 0; c < 9; c++) {
				if (c == y) {
					jf[b][c].setBackground(Color.white);

				}
			}
		}

	}
}
