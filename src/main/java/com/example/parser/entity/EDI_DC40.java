package com.example.parser.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EDI_DC40")
public class EDI_DC40 {

    @Id
    @Column(name = "DOCNUM")
    private String DOCNUM;

    @Column(name = "TABNAM")
    private String TABNAM;

    @Column(name = "MANDT")
    private String MANDT;

    @Column(name = "DOCREL")
    private String DOCREL;

    @Column(name = "STATUS")
    private String STATUS;

    @Column(name = "DIRECT")
    private String DIRECT;

    @Column(name = "OUTMOD")
    private String OUTMOD;

    @Column(name = "IDOCTYP")
    private String IDOCTYP;

    @Column(name = "MESTYP")
    private String MESTYP;

    @Column(name = "SNDPOR")
    private String SNDPOR;

    @Column(name = "SNDPRT")
    private String SNDPRT;

    @Column(name = "SNDPRN")
    private String SNDPRN;

    @Column(name = "RCVPRT")
    private String RCVPRT;

    @OneToOne
    @JoinColumn(name = "RCVPRN", referencedColumnName = "FILIALE")
    private E1WPA01 RCVPRN;

    @Column(name = "CREDAT")
    private String CREDAT;

    @Column(name = "CRETIM")
    private String CRETIM;

    @Column(name = "SERIAL")
    private String SERIAL;

}
