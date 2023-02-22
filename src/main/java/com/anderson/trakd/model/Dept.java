package com.anderson.trakd.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;




// POJO for dept
@Entity
@Table(name = "Dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Dept() {
        this.name = "";
    }

    public Dept(String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dept other = (Dept) obj;
        return id == other.id && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Dept [id=" + id + ", name=" + name + "]";
    }

}
