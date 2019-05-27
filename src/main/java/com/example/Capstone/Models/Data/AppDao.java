package com.example.Capstone.Models.Data;

import com.example.Capstone.Models.App;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AppDao extends CrudRepository<App, Integer> {


}
