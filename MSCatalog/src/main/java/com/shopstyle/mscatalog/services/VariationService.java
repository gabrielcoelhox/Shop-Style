package com.shopstyle.mscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopstyle.mscatalog.dto.VariationDTO;
import com.shopstyle.mscatalog.dto.VariationFormDTO;
import com.shopstyle.mscatalog.entities.Product;
import com.shopstyle.mscatalog.entities.Variation;
import com.shopstyle.mscatalog.exceptions.ObjectNotFoundException;
import com.shopstyle.mscatalog.repository.ProductRepository;
import com.shopstyle.mscatalog.repository.VariationRepository;

@Service
public class VariationService {

	@Autowired
	private VariationRepository variationRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SequenceGeneratorService seqService;

	public List<VariationDTO> findAll() {
		return variationRepository.findAll().stream().map(VariationDTO::new).collect(Collectors.toList());
	}

	public VariationDTO save(VariationFormDTO variationFormDTO) {
		Product product = productRepository.findById(variationFormDTO.getProduct_id()).orElseThrow(
				() -> new ObjectNotFoundException("Product ID : " + variationFormDTO.getProduct_id() + " not found."));
		Variation variation = new Variation();
		variation.setId(seqService.getSequenceNumber(Variation.SEQUENCE_NAME));
		variation.setProduct(product);
		variation.setColor(variationFormDTO.getColor());
		variation.setPrice(variationFormDTO.getPrice());
		variation.setQuantity(variationFormDTO.getQuantity());
		variation.setSize(variationFormDTO.getSize());	
		return new VariationDTO(variationRepository.save(variation));
	}
}
