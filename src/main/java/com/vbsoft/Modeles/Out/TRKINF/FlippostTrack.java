package com.vbsoft.Modeles.Out.TRKINF;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "FlippostTrack")
@Data
public class FlippostTrack {

    @Column(name = "ID")
    private int id;

    @Column(name = "WB_NO")
    private String wbNo;

    @Column(name = "TRK_TYPE")
    private String trkType;

    @Column(name = "TRK_DATE")
    private Date trkDate;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "NEW_FLAG")
    private Boolean newFlag;

    @Column(name = "PROCESSING_DATE")
    private Date processingDate;

}
