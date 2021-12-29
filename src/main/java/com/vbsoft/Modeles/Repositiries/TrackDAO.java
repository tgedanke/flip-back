package com.vbsoft.Modeles.Repositiries;

import com.vbsoft.Modeles.Out.TRKINF.FlippostTrack;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackDAO extends CrudRepository<FlippostTrack, Long> {

    List<FlippostTrack> findByNewFlag(final Boolean flag);

}
