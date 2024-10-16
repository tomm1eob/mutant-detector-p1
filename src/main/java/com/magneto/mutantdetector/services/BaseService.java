package com.magneto.mutantdetector.services;

import com.magneto.mutantdetector.entities.Base;
import java.io.Serializable;
import java.util.List;

public interface BaseService <E extends Base, ID extends Serializable>{
    public List<E> findAll() throws Exception;
    public E findBy(ID id) throws Exception;
    public E save(E entity) throws Exception;
    public boolean delete(ID id) throws Exception;
}
