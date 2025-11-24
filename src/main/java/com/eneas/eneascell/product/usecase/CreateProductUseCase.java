package com.eneas.eneascell.product.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneas.eneascell.exceptions.BusinessException;
import com.eneas.eneascell.product.domain.Product;
import com.eneas.eneascell.product.dto.ProductDTO;
import com.eneas.eneascell.product.repositories.ProductRepository;

@Service
public class CreateProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    public Product execute(ProductDTO dto) {

        if (dto.getNome() == null || dto.getNome().trim().isEmpty())
            throw new BusinessException("O nome do produto é obrigatorio!");

        if (dto.getPreco() == null || dto.getPreco().doubleValue() <= 0)
            throw new BusinessException("O preço do produto deve ser maior que zero!");

        if (dto.getQuantidade() == null)
            throw new BusinessException("A quantidade do produto não pode ser vazio.");

        if (dto.getDescricao() == null || dto.getDescricao().trim().isEmpty())
            throw new BusinessException("A descrição é obrigatoria!");

        if (productRepository.findByNome(dto.getNome()) != null)
            throw new BusinessException("Já existe um produto com esse nome!");

        Product product = new Product();
        product.setNome(dto.getNome());
        product.setPreco(dto.getPreco());
        product.setQuantidade(dto.getQuantidade());
        product.setDescricao(dto.getDescricao());

        return this.productRepository.save(product);
    }

}
