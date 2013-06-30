package br.com.linkout.bookLazarento.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import jmine.tec.persist.annotation.Alias;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import bancosys.tec.persist.bussobj.AuthPersistableBusinessObject;

@Entity
@Table(name = "LIVROS")
@SequenceGenerator(name = "SEQ_LIVROS", sequenceName = "SEQ_LIVROS")
@Filters({ @Filter(name = "authFilter", condition = "auth = :auth") })
@Alias("LV")
public class Livro extends AuthPersistableBusinessObject {
	
	private static final long serialVersionUID = 661407798248328888L;

	private Long id;
	
	private String titulo;
	
	private Double valorDaLocacao;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LIVROS")
	@Column(name = "PK_LIVRO")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
	this.id = id;
	}
	
	@Column(name = "TITULO")
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Column(name = "VALOR_LOCACAO")
	public Double getValorDaLocacao() {
		return valorDaLocacao;
	}
	public void setValorDaLocacao(Double valorDaLocacao) {
		this.valorDaLocacao = valorDaLocacao;
	}

}
