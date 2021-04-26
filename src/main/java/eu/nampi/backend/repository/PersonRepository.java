package eu.nampi.backend.repository;

import java.util.UUID;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.riot.Lang;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import eu.nampi.backend.model.QueryParameters;
import eu.nampi.backend.model.hydra.AbstractHydraBuilder;
import eu.nampi.backend.model.hydra.HydraCollectionBuilder;
import eu.nampi.backend.model.hydra.HydraSingleBuilder;
import eu.nampi.backend.vocabulary.Core;
import eu.nampi.backend.vocabulary.Doc;
import eu.nampi.backend.vocabulary.SchemaOrg;

@Repository
@CacheConfig(cacheNames = "persons")
public class PersonRepository extends AbstractHydraRepository {

  public Model findAll(QueryParameters params) {
    HydraCollectionBuilder hydra =
        new HydraCollectionBuilder(params, Core.person, Doc.personOrderByVar);
    addData(hydra);
    return construct(hydra);
  }

  @Cacheable(
      key = "{#lang, #params.limit, #params.offset, #params.orderByClauses, #params.type, #params.text}")
  public String findAll(QueryParameters params, Lang lang) {
    Model model = findAll(params);
    return serialize(model, lang, ResourceFactory.createResource(params.getBaseUrl()));
  }

  @Cacheable(key = "{#lang, #id}")
  public String findOne(Lang lang, UUID id) {
    String uri = individualsUri(Core.person, id);
    HydraSingleBuilder builder = new HydraSingleBuilder(uri, Core.person);
    addData(builder);
    Model model = construct(builder);
    return serialize(model, lang, ResourceFactory.createResource(uri));
  }

  private void addData(AbstractHydraBuilder<?> builder) {
    builder.addMainConstruct(SchemaOrg.sameAs, "?sa").addMainOptional(SchemaOrg.sameAs, "?sa");
  }
}
