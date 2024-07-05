package com.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.shop.model.dao.DtlDAO;
import com.shop.model.entity.Dtl;
import com.shop.service.DtlService;

@Component("dtlService")
public class DtlServiceImpl implements DtlService {

	DtlDAO dtlDAO;

	public DtlDAO getDtlDAO() {
		return dtlDAO;
	}

	@Resource(name = "dtlDAO")
	public void setDtlDAO(DtlDAO dtlDAO) {
		this.dtlDAO = dtlDAO;
	}

	@Override
	public int add(List<Dtl> dtlList) {
		return dtlDAO.insert(dtlList);
	}

	@Override
	public List<Dtl> findByOrdNo(int OrdNo) {
		return dtlDAO.findByOrdNo(OrdNo);
	}

}
