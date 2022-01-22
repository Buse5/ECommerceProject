package com.works.entities;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class Books implements Serializable {

    @Id
    private String bookId;

    @NotNull
    private String bookName;

    @NotNull
    private BigDecimal bookPrice;

    private String bookDescription;

    @NotNull
    @Min(0)
    private Integer bookStock;

    @ColumnDefault("0")
    private Integer bookStatus;

    @ColumnDefault("0")
    private Integer bookCategoryType;

    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;

    public Books(){}

}
