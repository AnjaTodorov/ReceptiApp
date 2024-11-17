package com.RIS.Mojirecepti.entity;
import jakarta.persistence.*;


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


    // Default constructor
    public ReceptiNacrtObrokov() {}

    // Parameterized constructor
    public ReceptiNacrtObrokov(Recepti recepti, NacrtObrokov nacrtObrokov) {
        this.recepti = recepti;
        this.nacrtObrokov = nacrtObrokov;
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

    @Override
    public String toString() {
        return "ReceptiNacrtObrokov{" +
                "idReceptiNacrtObrokov=" + idReceptiNacrtObrokov +
                ", recepti=" + recepti +
                ", nacrtObrokov=" + nacrtObrokov +
                '}';
    }
}