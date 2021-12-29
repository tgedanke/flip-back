package com.vbsoft.Modeles.Repositiries;

import com.vbsoft.Modeles.Out.GENRES.GENRESDelivery;
import org.springframework.data.repository.CrudRepository;

public interface GenresDAO extends CrudRepository<GENRESDelivery, Long> {
}
