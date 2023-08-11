package com.example.parser.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "E1WPA01", indexes = { @Index(name = "e1wpa01_mhdhb", columnList = "MHDHB") })
public class E1WPA01 {

    @Id
    @Column(name = EntityName.FILIALE)
    private String FILIALE;

    @Column(name = EntityName.AENDKENNZ)
    private String AENDKENNZ;

    @Column(name = EntityName.AKTIVDATUM)
    private String AKTIVDATUM;

    @Column(name = EntityName.AENDDATUM)
    private String AENDDATUM;

    @Column(name = EntityName.HAUPTEAN)
    private String HAUPTEAN;

    @Column(name = EntityName.ARTIKELNR)
    private String ARTIKELNR;

    @Column(name = EntityName.POSME)
    private String POSME;

    @Column(name = EntityName.WARENGR)
    private String WARENGR;

    @Column(name = EntityName.VERPGEW)
    private String VERPGEW;

    @Column(name = EntityName.RABERLAUBT)
    private String RABERLAUBT;

    @Column(name = EntityName.PRDRUCK)
    private String PRDRUCK;

    @Column(name = EntityName.ARTIKANZ)
    private String ARTIKANZ;

    @Column(name = EntityName.MHDHB)
    private Integer MHDHB;

    @Column(name = EntityName.QUALARTTXT)
    private String QUALARTTXT;

    @Column(name = EntityName.SPRASCODE)
    private String SPRASCODE;

    @Column(name = EntityName.TEXT)
    private String TEXT;

    @Column(name = EntityName.LFDNR)
    private String LFDNR;

    @OneToOne(mappedBy = EntityName.RCVPRN)
    private EDI_DC40 ediDc40;
}
