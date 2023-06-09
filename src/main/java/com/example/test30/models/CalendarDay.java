package com.example.test30.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;



@Entity
@Data
public class CalendarDay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate date;
    private int day;
    private int month;
    private String description;
    @Column(name = "additional_info", columnDefinition = "LONGTEXT")
    private String additionalInfo;
    @Column(columnDefinition = "LONGTEXT")
    private String additionalInfo1;
    @Column(columnDefinition = "LONGTEXT")
    private String additionalInfo2;
    @Column(columnDefinition = "LONGTEXT")
    private String additionalInfo3;
    @Column(columnDefinition = "LONGTEXT")
    private String additionalInfo4;
    @Column(columnDefinition = "LONGTEXT")
    private String additionalInfo5;
    @Column(columnDefinition = "LONGTEXT")
    private String additionalInfo6;
    @Column(columnDefinition = "LONGTEXT")
    private String additionalInfo7;
    @Column(columnDefinition = "LONGTEXT")
    private String additionalInfo8;
    @Column(columnDefinition = "LONGTEXT")
    private String additionalInfo9;
    @Column(columnDefinition = "LONGTEXT")
    private String additionalInfo10;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String internationalInformation;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String internationalInformation1;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String internationalInformation2;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String internationalInformation3;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String internationalInformation4;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String internationalInformation5;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String internationalInformation6;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String internationalInformation7;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String internationalInformation8;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String internationalInformation9;

}
