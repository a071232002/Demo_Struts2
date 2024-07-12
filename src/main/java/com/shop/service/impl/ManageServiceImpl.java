package com.shop.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.model.dao.DtlDAO;
import com.shop.model.dao.OrdDAO;
import com.shop.model.dao.ProDAO;
import com.shop.model.dao.impl.DtlDAOImpl;
import com.shop.model.dto.DtlDTO;
import com.shop.model.dto.OrdDTO;
import com.shop.model.entity.Dtl;
import com.shop.model.entity.Ord;
import com.shop.model.entity.Pro;
import com.shop.service.ManageService;

@Component
public class ManageServiceImpl implements ManageService {

	@Autowired
	ProDAO proDAO;

	@Autowired
	OrdDAO ordDAO;

	@Autowired
	DtlDAO dtlDAO;
	
	
	@Override
	public List<OrdDTO> getOrdWithCondition(int ordNo) {
		List<OrdDTO> list = new ArrayList<OrdDTO>();
		Ord ord = ordDAO.findByOrdNo(ordNo);
		OrdDTO ordDTO = new OrdDTO ( ord.getOrdNo(),
							 		 ord.getUser().getUserNo(),
							 		 ord.getOrdPrice(),
							 		 ord.getOrdSt(),
							 		 dtlFormatToDTO(dtlDAO.findByOrdNo(ordNo)));
		list.add(ordDTO);
		System.out.println(list);
		return list;
	}
	
	
	@Override
	public List<OrdDTO> getAllOrd() {
		List<OrdDTO> list = new ArrayList<OrdDTO>();
		list = ordDAO.getAll().stream().map(ord -> {
								return new OrdDTO(ord.getOrdNo(), 
												  ord.getUser().getUserNo(),
												  ord.getOrdPrice(),
												  ord.getOrdSt(),
												  dtlFormatToDTO(dtlDAO.findByOrdNo(ord.getOrdNo())));
							}).collect(Collectors.toList());
		System.out.println(list);
		return list;
	}

	@Override
	public List<Pro> getAddItems(int insertCount) {
		return proDAO.getAddItems(insertCount);
	}

	@Override
	public int deleteOrd(int ordNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Pro> excelToProList(File proData) throws IOException {
		List<Pro> proList = new ArrayList<Pro>();
	
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
		return proList;
	
	}

	@Override
	@Transactional
	public int importProItem(List<Pro> proList) {
		return proDAO.insertAll(proList);
	}

	@Override
	public int printOrdInfo(int ordNo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<DtlDTO> dtlFormatToDTO(List<Dtl> dtlList){
		return dtlList.stream()
					  .map(dtl -> new DtlDTO(dtl.getPro().getProNo(), 
							                 dtl.getPro().getProName(), 
							                 dtl.getDtlQty(), 
							                 dtl.getDtlPrice()))
					  .collect(Collectors.toList());
	}


	
}
