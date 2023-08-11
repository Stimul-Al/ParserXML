package com.example.parser.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "E1WPA01",
        indexes = {
                @Index(name = "FILIALE", columnList = "FILIALE"),
                @Index(name = "MHDHB", columnList = "MHDHB")})
public class E1WPA01 {

    @Id
    @Column(name = "FILIALE")
    private String FILIALE;

    @Column(name = "AENDKENNZ")
    private String AENDKENNZ;

    @Column(name = "AKTIVDATUM")
    private String AKTIVDATUM;

    @Column(name = "AENDDATUM")
    private String AENDDATUM;

    @Column(name = "HAUPTEAN")
    private String HAUPTEAN;

    @Column(name = "ARTIKELNR")
    private String ARTIKELNR;

    @Column(name = "POSME")
    private String POSME;

    @Column(name = "WARENGR")
    private String WARENGR;

    @Column(name = "VERPGEW")
    private String VERPGEW;

    @Column(name = "RABERLAUBT")
    private String RABERLAUBT;

    @Column(name = "PRDRUCK")
    private String PRDRUCK;

    @Column(name = "ARTIKANZ")
    private String ARTIKANZ;

    @Column(name = "MHDHB")
    private String MHDHB;

    @Column(name = "QUALARTTXT")
    private String QUALARTTXT;

    @Column(name = "SPRASCODE")
    private String SPRASCODE;

    @Column(name = "TEXT")
    private String TEXT;

    @Column(name = "LFDNR")
    private String LFDNR;

    @OneToOne(mappedBy = "RCVPRN")
    private EDI_DC40 ediDc40;
}
