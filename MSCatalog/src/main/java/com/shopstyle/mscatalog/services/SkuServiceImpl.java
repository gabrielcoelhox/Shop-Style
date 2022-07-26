package com.shopstyle.mscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.shopstyle.mscatalog.dto.SkuDTO;
import com.shopstyle.mscatalog.dto.SkuFormDTO;
import com.shopstyle.mscatalog.entities.Media;
import com.shopstyle.mscatalog.entities.Product;
import com.shopstyle.mscatalog.entities.Sku;
import com.shopstyle.mscatalog.exceptions.DefaultException;
import com.shopstyle.mscatalog.repository.MediaRepository;
import com.shopstyle.mscatalog.repository.ProductRepository;
import com.shopstyle.mscatalog.repository.SkuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkuServiceImpl implements SkuService {

	private final SkuRepository skuRepository;
	
	private final ProductRepository productRepository;
	
	private final MediaRepository mediaRepository;

	@Override
	public List<SkuDTO> findAll() {
		return skuRepository.findAll().stream().map(SkuDTO::new).collect(Collectors.toList());
	}
	
	@Override
	public SkuDTO findById(Long id) {
		return new SkuDTO(skuRepository.findById(id).orElseThrow(
				() -> new DefaultException("Sku with ID : "+ id + " not found.", "NOT_FOUND", 404)));
	}

	@Override
	public SkuDTO insert(@Valid SkuFormDTO form) {
		Product product = productRepository.findById(form.getProductId()).orElseThrow(
				() -> new DefaultException("Product with ID: " + form.getProductId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		
		Sku sku = new Sku(form, product);	
		for(String imagemUrl : form.getImages()) {
			Media media = new Media(imagemUrl, sku);
			sku.addImages(media);
			mediaRepository.save(media);
		}
		return new SkuDTO(skuRepository.save(sku));
	}

	@Override
	public SkuDTO update(Long id, @Valid SkuFormDTO skuForm) {
		Product product = productRepository.findById(skuForm.getProductId()).orElseThrow(
				() -> new DefaultException("Product with ID: " + skuForm.getProductId() + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		Sku sku = skuRepository.findById(id).orElseThrow(
				() -> new DefaultException("Sku with ID: "+ id + " not found. Enter a valid ID.", "NOT_FOUND", 404));
		
		sku.setProduct(product);
		sku.setColor(skuForm.getColor());
		sku.setColor(skuForm.getColor());
		sku.setPrice(skuForm.getPrice());
		sku.setQuantity(skuForm.getQuantity());
		sku.setSize(skuForm.getSize());	
		sku.setHeight(skuForm.getHeight());
		sku.setWidth(skuForm.getWidth());
		
		for(String imagemUrl : skuForm.getImages()) {
			mediaRepository.save(new Media(imagemUrl, sku));
		}	
		return new SkuDTO(skuRepository.save(sku));
	}

	@Override
	public void deleteById(Long id) {
		skuRepository.findById(id).orElseThrow(
				() -> new DefaultException("Sku with ID: "+ id + " not found. Enter a valid ID. Enter a valid ID.", "NOT_FOUND", 404));
		skuRepository.deleteById(id);
	}
	
	@Override
	public SkuDTO updateOrderSku(Long id, Integer quantity) {
		Sku sku = skuRepository.findById(id).orElseThrow(
				() -> new DefaultException("Sku ID : "+ id + " not found.", "NOT_FOUND", 404));
		sku.setQuantity(sku.getQuantity() - quantity);
		return new SkuDTO(skuRepository.save(sku));
	}
}
