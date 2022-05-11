package com.psicotaller.psicoapp.backend.domain;

import java.util.List;

public interface ResourceService<E> {
    E save(E dto);
    E partialUpdate(E dto);
    List<E> findAll();
    E findOne(Integer id);
    Boolean delete(Integer id);
}
