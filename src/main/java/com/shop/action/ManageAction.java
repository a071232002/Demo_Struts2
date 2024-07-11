package com.shop.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.shop.model.dto.OrdDTO;
import com.shop.model.entity.Pro;
import com.shop.service.ManageService;

@Component
public class ManageAction extends ActionSupport implements SessionAware {

//	商品匯入
	private File proData;
	private int insertCount;
	private List<Pro> newProList;

	private List<OrdDTO> ordDTOList;
	Map<String, Object> session;

	@Autowired
	ManageService manageSvc;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String importProByFileUpload() {
		List<Pro> proList = new ArrayList<Pro>();
		
		try {
			FileInputStream fileInputStream = new FileInputStream(proData);
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheetAt(0);

			boolean isFirstRow = true;
			for (Row row : sheet) {
				if (isFirstRow) {
					isFirstRow = false;
					continue;
				}
				String proName = row.getCell(0).getStringCellValue();
				int proPrice = (int) row.getCell(1).getNumericCellValue();
				int proQty = (int) row.getCell(2).getNumericCellValue();
				
				Pro pro = new Pro();
				pro.setProName(proName);
				pro.setProPrice(proPrice);
				pro.setProQty(proQty);

				proList.add(pro);
			}
			

			if (proList.size() > 0) {
				this.insertCount = manageSvc.importProItem(proList);
				this.newProList = manageSvc.getAddItems(insertCount);
				System.out.println(newProList);
			}

			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
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
