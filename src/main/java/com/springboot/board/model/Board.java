package com.springboot.board.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@SequenceGenerator(name = "BOARD_SEQ", sequenceName = "boarddb_seq", initialValue = 1, allocationSize = 1)
@Table(name = "boarddb")
public class Board{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ")
    private Long bno;

    @Column
    @NotNull
    @Size(min = 2, max = 30, message = "제목은 2글자 이상 30자 이하 입니다.")
    private String title;

    @Column
    private String writer;

    @Column
    private String content;

    @Column
    @Temporal(TemporalType.DATE)
    private Date regdate = new Date();

}
