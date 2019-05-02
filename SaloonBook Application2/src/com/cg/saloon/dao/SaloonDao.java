package com.cg.saloon.dao;

import java.util.List;

import com.cg.saloon.dto.Saloon;
import com.cg.saloon.exception.SaloonNotFoundException;

public interface SaloonDao {

	public Saloon save(Saloon sal);
	public List<Saloon> findByName(String name) throws SaloonNotFoundException ;
}
