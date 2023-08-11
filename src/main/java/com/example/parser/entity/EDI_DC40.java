package com.example.parser.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EDI_DC40")
public class EDI_DC40 {

    @Id
    @Column(name = EntityName.DOCNUM)
    private String DOCNUM;

    @Column(name = EntityName.TABNAM)
    private String TABNAM;

    @Column(name = EntityName.MANDT)
    private String MANDT;

    @Column(name = EntityName.DOCREL)
    private String DOCREL;

    @Column(name = EntityName.STATUS)
    private String STATUS;

    @Column(name = EntityName.DIRECT)
    private String DIRECT;

    @Column(name = EntityName.OUTMOD)
    private String OUTMOD;

    @Column(name = EntityName.DOCTYP)
    private String DOCTYP;

    @Column(name = EntityName.MESTYP)
    private String MESTYP;

    @Column(name = EntityName.SNDPOR)
    private String SNDPOR;

    @Column(name = EntityName.SNDPRT)
    private String SNDPRT;

    @Column(name = EntityName.SNDPRN)
    private String SNDPRN;

    @Column(name = EntityName.RCVPRT)
    private String RCVPRT;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = EntityName.RCVPRN, referencedColumnName = EntityName.FILIALE)
    private E1WPA01 RCVPRN;

    @Column(name = EntityName.CREDAT)
    private String CREDAT;

    @Column(name = EntityName.CRETIM)
    private String CRETIM;

    @Column(name = EntityName.SERIAL)
    private String SERIAL;

}
