package com.shop.util.bean;

import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Table;

//for ODFtoolkit 0.9.0 & Java8
public interface OdsEl {

	public void write(Table table);

	public void setStyle(Cell cell);

}
