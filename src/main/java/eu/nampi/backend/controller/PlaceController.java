package eu.nampi.backend.controller;

import java.util.Optional;
import java.util.UUID;
import org.apache.jena.riot.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import eu.nampi.backend.model.OrderByClauses;
import eu.nampi.backend.model.QueryParameters;
import eu.nampi.backend.repository.PlaceRepository;

@RestController
public class PlaceController extends AbstractRdfController {

  @Autowired
  PlaceRepository placeRepository;

  @GetMapping(value = "/places", produces = {"application/ld+json", "text/turtle",
      "application/rdf+xml", "application/n-triples"})
  public ResponseEntity<String> getPlaces(@RequestHeader("accept") Lang lang,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("pageIndex") Optional<Integer> pageIndex,
      @RequestParam("limit") Optional<Integer> limit,
      @RequestParam("offset") Optional<Integer> offset,
      @RequestParam("orderBy") Optional<OrderByClauses> orderBy,
      @RequestParam("type") Optional<String> type, @RequestParam("text") Optional<String> text) {
    QueryParameters params = getParameters(page, pageIndex, limit, offset, orderBy, type, text);
    String result = placeRepository.findAll(params, lang);
    return new ResponseEntity<String>(result, HttpStatus.OK);
  }

  @GetMapping(value = "/place/{id}", produces = {"application/ld+json", "text/turtle",
      "application/rdf+xml", "application/n-triples"})
  public ResponseEntity<String> getEvent(@RequestHeader("accept") Lang lang,
      @PathVariable UUID id) {
    String result = placeRepository.findOne(lang, id);
    return new ResponseEntity<String>(result, HttpStatus.OK);
  }

}
