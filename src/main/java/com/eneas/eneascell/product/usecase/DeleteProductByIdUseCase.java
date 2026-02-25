package com.eneas.eneascell.product.usecase;

import com.eneas.eneascell.exceptions.NotFoundException;
import com.eneas.eneascell.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class DeleteProductByIdUseCase {

    @Autowired
    private ProductRepository productRepository;

    public void execute(UUID id) {

        boolean exists = productRepository.existsById(id);

        if (!exists) {
            throw new NotFoundException("Produto não encontrado para exclusão!");
        }

        productRepository.deleteById(id);
    }

}
