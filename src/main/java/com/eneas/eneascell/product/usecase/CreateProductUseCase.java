package com.eneas.eneascell.product.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.exceptions.BusinessException;
import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.repositories.ProductRepository;

@Service
public class CreateProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    public Product execute(Product product) {

        if (product.getNome() == null || product.getNome().trim().isEmpty())
            throw new BusinessException("O nome do produto é obrigatorio!");

        if (product.getPreco() == null || product.getPreco().doubleValue() <= 0)
            throw new BusinessException("O preço do produto deve ser maior que zero!");

        if (product.getQuantidade() == null)
            throw new BusinessException("A quantidade do produto não pode ser vazio.");

        if (product.getDescricao() == null || product.getDescricao().trim().isEmpty())
            throw new BusinessException("A descrição é obrigatoria!");

        if (productRepository.findByNome(product.getNome()) != null)
            throw new BusinessException("Já existe um produto com esse nome!");

        return this.productRepository.save(product);
    }

}
