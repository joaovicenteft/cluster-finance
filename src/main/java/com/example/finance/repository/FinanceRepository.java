package com.example.finance.repository;
import com.example.finance.model.EntityFinance;
import org.springframework.data.repository.CrudRepository;

public interface FinanceRepository extends CrudRepository<EntityFinance, String>{

}
