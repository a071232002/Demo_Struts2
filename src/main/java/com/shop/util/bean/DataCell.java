package com.shop.util.bean;

import java.util.Locale;

import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Table;

//for ODFtoolkit 0.9.0 & Java8
public class DataCell implements OdsEl {

	private int col;
	private int row;
	private String cont;
	private Integer number;
	private OdsStyle odsStyle;

	public DataCell(int col, int row, String cont, OdsStyle odsStyle) {
		this.col = col;
		this.row = row;
		this.cont = cont;
		this.odsStyle = odsStyle;
	}
	
	public DataCell(int col, int row, Integer number, OdsStyle odsStyle) {
		this.col = col;
		this.row = row;
		this.number = number;
		this.odsStyle = odsStyle;
	}
	
	@Override
	public void write(Table table) {
		Cell cell = table.getCellByPosition(col, row);
		if (cont == null) {
			cell.setDoubleValue(number.doubleValue());			
		} else {
			cell.setStringValue(cont);			
		}
		setStyle(cell);
	}

	@Override
	public void setStyle(Cell cell) {
		cell.getStyleHandler().setBackgroundColor(odsStyle.getBackgroundColor());
		cell.getStyleHandler().setFont(odsStyle.getFont());
		cell.getStyleHandler().setFont(odsStyle.getFont(), Locale.getDefault());
		
		cell.getStyleHandler().setHorizontalAlignment(odsStyle.getHorizontalAlignmentType());
		if (odsStyle.getVerticalAlignmentType() != null) {
			cell.getStyleHandler().setVerticalAlignment(odsStyle.getVerticalAlignmentType());
		}

		cell.getStyleHandler().setBorders(odsStyle.getBorder(), odsStyle.getCellBordersType());
		cell.getStyleHandler().setTextWrapped(odsStyle.getTextWrapped());
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public OdsStyle getOdsStyle() {
		return odsStyle;
	}

	public void setOdsStyle(OdsStyle odsStyle) {
		this.odsStyle = odsStyle;
	}
	
}
