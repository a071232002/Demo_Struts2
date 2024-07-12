package com.shop.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
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

//	訂單查詢
	private int ordNo;
	private List<OrdDTO> ordDTOList;

	private List<Integer> ordNos;

//	範本下載
	private InputStream inputStream;
	private String fileName;

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
		List<Pro> proList = new ArrayList<Pro>();

		try {
			proList = manageSvc.excelToProList(proData);
			if (proList.size() > 0) {
				this.insertCount = manageSvc.importProItem(proList);
				this.newProList = manageSvc.getAddItems(insertCount);
			} else {
				return "error";
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String getOrderInfo() {
		System.out.println(ordNo);

		this.ordDTOList = manageSvc.getAllOrd();
		return "success";
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

	public int getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(int ordNo) {
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
