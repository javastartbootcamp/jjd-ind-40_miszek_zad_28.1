package pl.javastart.restoffers.Offer;

import org.springframework.stereotype.Service;
import pl.javastart.restoffers.Category.Category;
import pl.javastart.restoffers.Category.CategoryRepository;

import java.util.NoSuchElementException;

@Service
public class OfferDtoMapper {

    private CategoryRepository categoryRepository;

    public OfferDtoMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    OfferDto map(Offer offer) {
        OfferDto offerDto = new OfferDto();
        offerDto.setId(offer.getId());
        offerDto.setTitle((offer.getTitle()));
        offerDto.setDescription(offer.getDescription());
        offerDto.setImgUrl(offer.getImgUrl());
        offerDto.setPrice(offer.getPrice());
        offerDto.setCategory(offer.getCategory().getName());
        return offerDto;
    }

    Offer map(OfferDto offerDto) throws NoSuchElementException {
        Offer offer = new Offer();
        offer.setTitle((offerDto.getTitle()));
        offer.setDescription(offerDto.getDescription());
        offer.setImgUrl(offerDto.getImgUrl());
        offer.setPrice(offerDto.getPrice());
        Category category = categoryRepository.findByName(offerDto.getCategory()).orElseThrow();
        offer.setCategory(category);
        return offer;
    }
}
