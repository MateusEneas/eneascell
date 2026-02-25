package com.eneas.eneascell.category.repositories;

import com.eneas.eneascell.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Category findByNome(String nome);

}
