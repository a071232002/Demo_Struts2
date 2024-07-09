package com.shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.model.dao.DtlDAO;
import com.shop.model.dao.OrdDAO;
import com.shop.model.entity.Dtl;
import com.shop.model.entity.Ord;
import com.shop.model.entity.User;
import com.shop.service.OrdService;

@Component("ordService")
public class OrdServiceImpl implements OrdService {

	@Autowired
	private OrdDAO ordDAO;

	@Autowired
	private DtlDAO dtlDAO;

	@Override
	@Transactional
	public int add(Ord ord, List<Dtl> dtlList) {
		int result = -1;
		int ordID = ordDAO.insert(ord);
		ord.setOrdNo(ordID);
		for (Dtl dtl : dtlList) {
			dtl.setOrd(ord);
		}
		dtlDAO = null;
		dtlDAO.insert(dtlList);
		result = 1;
		return result;
	}

	@Override
	public List<Ord> findByUser(User user) {
		return ordDAO.findByUser(user.getUserNo());
	}

	@Override
	public List<Ord> getAll() {
		return ordDAO.getAll();
	}

	@Override
	public Ord findByOrdNo(int OrdNo) {
		return ordDAO.findByOrdNo(OrdNo);
	}

	@Override
	public int update(Ord ord) {
		return ordDAO.update(ord);
	}

}
