package pl.javastart.restoffers.Offer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OfferService {

    private OfferDtoMapper offerDtoMapper;
    private OfferRepository offerRepository;

    public OfferService(OfferDtoMapper offerDtoMapper, OfferRepository offerRepository) {
        this.offerDtoMapper = offerDtoMapper;
        this.offerRepository = offerRepository;
    }

    List<OfferDto> getAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(offerDtoMapper::map)
                .toList();
    }

    List<OfferDto> getOffersByTitle(String title) {
        return offerRepository.findAllByTitleContainingIgnoreCase(title)
                .stream()
                .map(offerDtoMapper::map)
                .toList();
    }

    long countAllOffers() {
        return offerRepository.count();
    }

    OfferDto saveOffer(OfferDto offerDto) throws NoSuchElementException {
        Offer offerToSave = offerDtoMapper.map(offerDto);
        Offer savedOffer = offerRepository.save(offerToSave);
        return offerDtoMapper.map(savedOffer);
    }

    Optional<OfferDto> getOfferById(Long id) {
        return offerRepository.findById(id)
                .map(offerDtoMapper::map);
    }

    public boolean deleteOffer(Long id) {
        if (offerRepository.findById(id).isPresent()) {
            offerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
