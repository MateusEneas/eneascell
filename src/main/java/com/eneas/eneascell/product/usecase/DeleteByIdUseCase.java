package com.eneas.eneascell.product.usecase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.exceptions.BusinessException;
import com.eneas.eneascell.product.repositories.ProductRepository;

@Service
public class DeleteByIdUseCase {

    @Autowired
    private ProductRepository productRepository;

    public void execute(UUID id) {

        boolean exists = productRepository.existsById(id);

        if (!exists) {
            throw new BusinessException("Produto não encontrado para exclusão!");
        }

        productRepository.deleteById(id);
    }

}
