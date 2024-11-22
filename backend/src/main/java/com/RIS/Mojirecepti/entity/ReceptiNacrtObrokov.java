package com.RIS.Mojirecepti.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Recepti_Nacrt_obrokov")
public class ReceptiNacrtObrokov {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRecepti_Nacrt_obrokov")
    private int idReceptiNacrtObrokov;

    @ManyToOne
    @JoinColumn(name = "TK_Recepti", nullable = false)
    private Recepti recepti;

    @ManyToOne
    @JoinColumn(name = "TK_Nacrt_obrokov", nullable = false)
    private NacrtObrokov nacrtObrokov;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;


    // Default constructor
    public ReceptiNacrtObrokov() {}

    // Parameterized constructor
    public ReceptiNacrtObrokov(Recepti recepti, NacrtObrokov nacrtObrokov, MealType mealType) {
        this.recepti = recepti;
        this.nacrtObrokov = nacrtObrokov;
        this.mealType = mealType;
    }

    // Getters and setters
    public int getIdReceptiNacrtObrokov() {
        return idReceptiNacrtObrokov;
    }

    public void setIdReceptiNacrtObrokov(int idReceptiNacrtObrokov) {
        this.idReceptiNacrtObrokov = idReceptiNacrtObrokov;
    }

    public Recepti getRecepti() {
        return recepti;
    }

    public void setRecepti(Recepti recepti) {
        this.recepti = recepti;
    }

    public NacrtObrokov getNacrtObrokov() {
        return nacrtObrokov;
    }

    public void setNacrtObrokov(NacrtObrokov nacrtObrokov) {
        this.nacrtObrokov = nacrtObrokov;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    @Override
    public String toString() {
        return "ReceptiNacrtObrokov{" +
                "idReceptiNacrtObrokov=" + idReceptiNacrtObrokov +
                ", recepti=" + recepti +
                ", nacrtObrokov=" + nacrtObrokov +
                ", mealType=" + mealType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceptiNacrtObrokov that = (ReceptiNacrtObrokov) o;
        return idReceptiNacrtObrokov == that.idReceptiNacrtObrokov &&
                Objects.equals(recepti, that.recepti) &&
                Objects.equals(nacrtObrokov, that.nacrtObrokov) &&
                mealType == that.mealType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceptiNacrtObrokov, recepti, nacrtObrokov, mealType);
    }
}

