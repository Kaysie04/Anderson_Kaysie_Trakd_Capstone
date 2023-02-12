package com.anderson.trakd.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "Manager")
public class Manager implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_id")
    private Dept dept;

    public Manager() {
        this.name = "";
        this.dept = null;
    }

    public Manager( String name, Dept dept) {
        super();
        this.name = name;
        this.dept = dept;
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

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dept, id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Manager other = (Manager) obj;
        return Objects.equals(dept, other.dept) && id == other.id && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Manager [id=" + id + ", name=" + name + ", dept=" + dept + "]";
    }
}
