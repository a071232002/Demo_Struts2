package com.shop.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.Counta;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.odftoolkit.odfdom.type.Color;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.style.Font;
import org.odftoolkit.simple.style.StyleTypeDefinitions.FontStyle;
import org.odftoolkit.simple.style.StyleTypeDefinitions.HorizontalAlignmentType;
import org.odftoolkit.simple.table.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.model.dto.OdsTestDTO;
import com.shop.model.dto.OrdDTO;
import com.shop.model.entity.Pro;
import com.shop.service.ManageService;
import com.shop.util.bean.DataCell;
import com.shop.util.bean.MergeCell;
import com.shop.util.bean.OdsEl;
import com.shop.util.bean.OdsStyle;
import com.shop.util.bean.TittleCell;


@Component("Manage")
public class ManageAction extends ActionSupport implements SessionAware {

//	範本下載
	private InputStream inputStream;
	private String fileName;
	
//	商品匯入
	private File proData;
	private int insertCount;
	private List<Pro> newProList;

//	訂單查詢
	private String ordNo;
	private List<OrdDTO> ordDTOList;
	
//	checkbox操作
	private List<Integer> ordNos;

	
	
	Map<String, Object> session;
	
	
	
	
	@Autowired
	ManageService manageSvc;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String downloadSpec() throws FileNotFoundException {
		String filePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/proSpec.xlsx");
        File file = new File(filePath);
        
        if (file.exists()) {
            inputStream = new FileInputStream(file);
            fileName = "example.xlsx";
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String importProByFileUpload() {
		long startTime = System.currentTimeMillis();
		List<Pro> proList = new ArrayList<Pro>();

		try {
			proList = manageSvc.excelToProList(proData);
		
			if (proList.size() > 0) {
				this.insertCount = manageSvc.importProItem(proList);
				this.newProList = manageSvc.getAddItems(insertCount);
			} else {
				return "error";
			}
			long endTime = System.currentTimeMillis();

			long executionTime = endTime - startTime;
			System.out.println("執行時間: " + executionTime + " milliseconds");

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String getOrderInfo() {
		if (!"".equals(this.ordNo) && this.ordNo != null) {
			this.ordDTOList = manageSvc.getOrdWithCondition(Integer.valueOf(ordNo));
			if (ordDTOList.size() == 0) {
				return "error";
			}
			return "success";
		}
		this.ordDTOList = manageSvc.getAllOrd();
		return "success";
	}
	
	public String deleteOrder() {
		try {
			manageSvc.deleteOrd(ordNos);			
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String printData() {
		
		List<OdsTestDTO> resultList = new ArrayList<OdsTestDTO>();
		
		resultList.add(new OdsTestDTO("20240822", "MT956", 1, 2, 3, 6));
		resultList.add(new OdsTestDTO("20240822", "MT888", 3, 1, 2, 6));
		resultList.add(new OdsTestDTO("20240823", "MT956", 10, 15, 0, 25));
		resultList.add(new OdsTestDTO("20240824", "MT956", 3, 17, 1, 21));
		resultList.add(new OdsTestDTO("20240824", "MT888", 2, 11, 1, 14));
		resultList.add(new OdsTestDTO("20240824", "MT975", 5, 30, 1, 36));
		resultList.add(new OdsTestDTO("20240824", "MT777", 0, 0, 1, 1));
		resultList.add(new OdsTestDTO("20240825", "MT777", 11, 10, 11, 32));
			
        try {
			new InnerOdsUtil().exportOds(resultList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
	
	class InnerOdsUtil{
		
		OdsStyle centerFormat, rightFormat;
		
		public InnerOdsUtil() {
			Font font = new Font("Times New Roman", FontStyle.REGULAR, 12.0d, Color.BLACK); 
			centerFormat = new OdsStyle(font, HorizontalAlignmentType.CENTER);
			rightFormat = new OdsStyle(font, HorizontalAlignmentType.RIGHT);
		}
		
		public void exportOds(List<OdsTestDTO> resultList) throws Exception {
			HttpServletResponse response = (HttpServletResponse) ServletActionContext.getResponse();
	        response.setContentType("application/vnd.oasis.opendocument.spreadsheet");
	        response.setHeader("Content-Disposition", "attachment; filename=\"data.ods\"");
	        
	        SpreadsheetDocument ods = SpreadsheetDocument.newSpreadsheetDocument();
			if (ods != null) {
				// 建立工作表(SpreadsheetDocument 創建時已有預設的sheet1)
				Table table = ods.getSheetByName("Sheet1");
		        List<OdsEl> cells = new ArrayList<OdsEl>();
		        int rowNum = 0;
		        cells.add(new MergeCell(0, rowNum, 5, rowNum, "Test ODF.ods export", centerFormat));
		        rowNum++;
		        cells.add(new MergeCell(0, rowNum, 5, rowNum, "列印日期：" + LocalDateTime.now().toString(), centerFormat));
		        rowNum++;
		        cells.add(new TittleCell(0, rowNum, "日期", 30, centerFormat));
		        cells.add(new TittleCell(1, rowNum, "flightNo", 30, centerFormat));
		        cells.add(new TittleCell(2, rowNum, "typeA", 15, centerFormat));
		        cells.add(new TittleCell(3, rowNum, "typeB", 15, centerFormat));
		        cells.add(new TittleCell(4, rowNum, "typeC", 15, centerFormat));
		        cells.add(new TittleCell(5, rowNum, "總計", 15, centerFormat));
		        
		        int countA = 0;
		        int countB = 0;
		        int countC = 0;
		        int sum = 0;
		        String temp = "";
		        
		        for ( int i = 0; i < resultList.size(); i++ ) {
		        	if (i == 0 || temp.equals(resultList.get(i).getDate())) {
		        		temp = resultList.get(i).getDate();
		        		rowNum++;
		        		cells.add(new DataCell(0, rowNum, resultList.get(i).getDate(), centerFormat));		        	
		        		cells.add(new DataCell(1, rowNum, resultList.get(i).getFlightNo(), centerFormat));		        	
		        		cells.add(new DataCell(2, rowNum, resultList.get(i).getTypeA(), rightFormat));		        	
		        		cells.add(new DataCell(3, rowNum, resultList.get(i).getTypeB(), rightFormat));		        	
		        		cells.add(new DataCell(4, rowNum, resultList.get(i).getTypeC(), rightFormat));		        	
		        		cells.add(new DataCell(5, rowNum, resultList.get(i).getTotal(), rightFormat));
		        		
		        		countA += resultList.get(i).getTypeA();
		        		countB += resultList.get(i).getTypeB();
		        		countC += resultList.get(i).getTypeC();
		        		sum += resultList.get(i).getTotal();
		        		
		        	} else {
	        			temp = resultList.get(i).getDate();		        			

		        		rowNum++;
		        		cells.add(new MergeCell(0, rowNum, 1, rowNum, "總計", centerFormat));
		        		cells.add(new DataCell(2, rowNum, countA, rightFormat));		        	
		        		cells.add(new DataCell(3, rowNum, countB, rightFormat));		        	
		        		cells.add(new DataCell(4, rowNum, countC, rightFormat));		        	
		        		cells.add(new DataCell(5, rowNum, sum, rightFormat));
		        		
		        		countA = 0;
		        		countB = 0;
		        		countC = 0;
		        		sum = 0;
		        		
		        		rowNum++;
		        		cells.add(new DataCell(0, rowNum, resultList.get(i).getDate(), centerFormat));		        	
		        		cells.add(new DataCell(1, rowNum, resultList.get(i).getFlightNo(), centerFormat));		        	
		        		cells.add(new DataCell(2, rowNum, resultList.get(i).getTypeA(), rightFormat));		        	
		        		cells.add(new DataCell(3, rowNum, resultList.get(i).getTypeB(), rightFormat));		        	
		        		cells.add(new DataCell(4, rowNum, resultList.get(i).getTypeC(), rightFormat));		        	
		        		cells.add(new DataCell(5, rowNum, resultList.get(i).getTotal(), rightFormat));
		        		
		        		countA += resultList.get(i).getTypeA();
		        		countB += resultList.get(i).getTypeB();
		        		countC += resultList.get(i).getTypeC();
		        		sum += resultList.get(i).getTotal();
		        	}
		        }
		        
		        rowNum++;
        		cells.add(new MergeCell(0, rowNum, 1, rowNum, "總計", centerFormat));
        		cells.add(new DataCell(2, rowNum, countA, rightFormat));		        	
        		cells.add(new DataCell(3, rowNum, countB, rightFormat));		        	
        		cells.add(new DataCell(4, rowNum, countC, rightFormat));		        	
        		cells.add(new DataCell(5, rowNum, sum, rightFormat));
		        
		        for (OdsEl c : cells) {
		        	c.write(table);
		        }
			}
	        OutputStream out = response.getOutputStream();
	        ods.save(out);
	        ods.close();
		}
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}

	public List<Integer> getOrdNos() {
		return ordNos;
	}

	public void setOrdNos(List<Integer> ordNos) {
		this.ordNos = ordNos;
	}

	public File getProData() {
		return proData;
	}

	public void setProData(File proData) {
		this.proData = proData;
	}

	public int getInsertCount() {
		return insertCount;
	}

	public void setInsertCount(int insertCount) {
		this.insertCount = insertCount;
	}

	public List<Pro> getNewProList() {
		return newProList;
	}

	public void setNewProList(List<Pro> newProList) {
		this.newProList = newProList;
	}

	public List<OrdDTO> getOrdDTOList() {
		return ordDTOList;
	}

	public void setOrdDTOList(List<OrdDTO> ordDTOList) {
		this.ordDTOList = ordDTOList;
	}
	
}
