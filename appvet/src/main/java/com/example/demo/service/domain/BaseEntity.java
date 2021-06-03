package com.example.demo.service.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {
  @Id
  @Column(name = "id", length = 32)
  private String _id;

  public String get_id() {
    if (_id == null) {
      _id = createUUID();
    }
    return _id;
  }

  public void set_id(String uuid) {
    this._id = uuid;
  }

  @PrePersist
  public void prePersist() {
    if (_id == null) {
      _id = createUUID();
    }
  }

  protected String createUUID() {
    return UUID.randomUUID().toString().replaceAll("-", ""); // replace "-" 36 -> 32 char
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseEntity)) {
      return false;
    }

    BaseEntity that = (BaseEntity) o;

    return get_id().equals(that.get_id());
  }

  @Override
  public int hashCode() {
    return get_id().hashCode();
  }
}
