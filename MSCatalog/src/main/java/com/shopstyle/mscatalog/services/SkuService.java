package com.shopstyle.mscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopstyle.mscatalog.dto.SkuDTO;
import com.shopstyle.mscatalog.dto.SkuFormDTO;
import com.shopstyle.mscatalog.entities.Media;
import com.shopstyle.mscatalog.entities.Product;
import com.shopstyle.mscatalog.entities.Sku;
import com.shopstyle.mscatalog.exceptions.MethodArgumentNotValidException;
import com.shopstyle.mscatalog.repository.MediaRepository;
import com.shopstyle.mscatalog.repository.ProductRepository;
import com.shopstyle.mscatalog.repository.SkuRepository;

@Service
public class SkuService {

	@Autowired
	private SkuRepository skuRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MediaRepository mediaRepository;

	public List<SkuDTO> findAll() {
		return skuRepository.findAll().stream().map(SkuDTO::new).collect(Collectors.toList());
	}

	public SkuDTO save(@Valid SkuFormDTO skuFormDto) {
		Product product = productRepository.findById(skuFormDto.getProductId()).orElseThrow(
				() -> new MethodArgumentNotValidException("Product ID : " + skuFormDto.getProductId() + " not found."));
		Sku sku = new Sku();
		sku.setProduct(product);
		sku.setColor(skuFormDto.getColor());
		sku.setPrice(skuFormDto.getPrice());
		sku.setQuantity(skuFormDto.getQuantity());
		sku.setSize(skuFormDto.getSize());	
		sku.setHeight(skuFormDto.getHeight());
		sku.setWidth(skuFormDto.getWidth());
		
		for(String imagemUrl : skuFormDto.getImages()) {
			Media media = new Media(imagemUrl, sku);
			sku.addImages(media);
			mediaRepository.save(media);
		}
		
		return new SkuDTO(skuRepository.save(sku));
	}

	public SkuDTO update(Long id, @Valid SkuFormDTO skuFormDto) {
		Product product = productRepository.findById(skuFormDto.getProductId()).orElseThrow(
				() -> new MethodArgumentNotValidException("Product ID : " + skuFormDto.getProductId() + " not found."));
		Sku sku = skuRepository.findById(id).orElseThrow(
				() -> new MethodArgumentNotValidException("Sku ID : "+ id + " not found."));
		sku.setProduct(product);
		sku.setColor(skuFormDto.getColor());
		sku.setColor(skuFormDto.getColor());
		sku.setPrice(skuFormDto.getPrice());
		sku.setQuantity(skuFormDto.getQuantity());
		sku.setSize(skuFormDto.getSize());	
		sku.setHeight(skuFormDto.getHeight());
		sku.setWidth(skuFormDto.getWidth());
		
		for(String imagemUrl : skuFormDto.getImages()) {
			mediaRepository.save(new Media(imagemUrl, sku));
		}
				
		return new SkuDTO(skuRepository.save(sku));
	}

	public void deleteById(Long id) {
		skuRepository.findById(id).orElseThrow(
				() -> new MethodArgumentNotValidException("Sku ID : "+ id + " not found."));
		skuRepository.deleteById(id);
	}
}
