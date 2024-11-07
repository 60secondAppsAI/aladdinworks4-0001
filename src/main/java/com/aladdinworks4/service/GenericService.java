package com.aladdinworks4.service;

import com.aladdinworks4.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}