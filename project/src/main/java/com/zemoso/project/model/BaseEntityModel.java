package com.zemoso.project.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import com.zemoso.project.utils.LocalDateTimeConverter;
import lombok.Data;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;


@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})


@Data
@MappedSuperclass
public class BaseEntityModel {

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "created_tstamp", updatable = false)
    private LocalDateTime created;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "last_updated_tstamp")
    private LocalDateTime lastUpdated;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "deleted_tstamp")
    private LocalDateTime deleted;

    @Column(name = "created_by_id", nullable = false)
    private Long createdById;

    @Column(name = "last_updated_by_id", nullable = false)
    private Long lastUpdatedById;

    public BaseEntityModel() {
        onCreate();
    }

    public BaseEntityModel(BaseEntityModel fromBaseEntityModel) {
        this.setCreated(fromBaseEntityModel.getCreated());
        this.setCreatedById(fromBaseEntityModel.getCreatedById());
        this.setLastUpdatedById(fromBaseEntityModel.getLastUpdatedById());
        this.setLastUpdated(fromBaseEntityModel.getLastUpdated());
        this.setDeleted(fromBaseEntityModel.getDeleted());
    }

    @PrePersist
    public void onCreate() {
        created = now();
        lastUpdated = now();
    }

    public void onCreate(Long userId) {
        onCreate();
        createdById = userId;
        lastUpdatedById = userId;
    }

    @PreUpdate
    public void onUpdate() {
        lastUpdated = now();
    }

    public void onUpdate(Long userId) {
        onUpdate();
        lastUpdatedById = userId;
    }

}
