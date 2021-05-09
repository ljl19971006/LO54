package com.utbm.lo54.dao;

import com.utbm.lo54.bean.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaDao extends JpaRepository<Client, Long> {

}