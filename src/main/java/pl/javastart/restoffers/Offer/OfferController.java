package pl.javastart.restoffers.Offer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping
    public List<OfferDto> getAllOffers(@RequestParam(required = false) String title) {
        if (title == null) {
            return offerService.getAllOffers();
        } else {
            return offerService.getOffersByTitle(title);
        }
    }

    @GetMapping("/count")
    long countAllOffers() {
        return offerService.countAllOffers();
    }

    @PostMapping
    ResponseEntity<OfferDto> saveOffer(@RequestBody OfferDto offerDto) {
        OfferDto savedOffer;
        try {
            savedOffer = offerService.saveOffer(offerDto);
            URI savedOfferUri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedOffer.getId())
                    .toUri();
            return ResponseEntity.created(savedOfferUri).body(savedOffer);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable Long id) {
        return offerService.getOfferById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long id) {
        if (offerService.deleteOffer(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
