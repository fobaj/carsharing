package com.noirix.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository <K,V> {

    List<V> findAll();

    V findById(K key);

    V save(V object);

    V update(V object);

    K delete(V object);

}
