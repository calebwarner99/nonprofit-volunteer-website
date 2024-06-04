package com.example.TPXProj.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.TPXProj.models.DatabaseNonprofit;

/**
 * Interface for CrudRepository that connects to the ClearDB MySQL database
 *
 * @author Dr. Jose Annunziato
 */
public interface DatabaseNonprofitRepository
    extends CrudRepository<DatabaseNonprofit, Integer> {
} // DatabaseNonprofitRepository
