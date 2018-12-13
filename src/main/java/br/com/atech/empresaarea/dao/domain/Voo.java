package br.com.atech.empresaarea.dao.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VOO")
public class Voo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable =false)
    private Long id;

    @Column(name="DATA_PARTIDA")
    private String codigo;
    @Column(name="HORA_SAIDA")
    private Date horaSaida;
    @Column(name="HORA_CHEGADA")
    private Date horaChegada;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PILOTO_ID", referencedColumnName = "ID")
    private Piloto piloto;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CIDADE_ID", referencedColumnName = "ID")
    private Cidade cidade;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "AVIAO_ID", referencedColumnName = "ID")
    private Aviao aviao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Date horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Date getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(Date horaChegada) {
        this.horaChegada = horaChegada;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }

    @Override
    public String toString() {
        return "Voo{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", horaSaida=" + horaSaida +
                ", horaChegada=" + horaChegada +
                ", piloto=" + piloto +
                ", cidade=" + cidade +
                ", aviao=" + aviao +
                '}';
    }
}
