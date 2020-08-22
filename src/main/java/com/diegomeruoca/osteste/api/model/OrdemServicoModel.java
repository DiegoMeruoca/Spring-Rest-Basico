package com.diegomeruoca.osteste.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.diegomeruoca.osteste.domain.model.StatusOredemServico;

/*Esta classe é um modelo de representação, nos pemit separar o Representation
Model do Domain Model, permitindo que caso façamos alguma alteração não quebre 
a API prejudicando os consumidores*/
public class OrdemServicoModel {
	private Long Id;
	private String nomeCliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOredemServico status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalização;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public StatusOredemServico getStatus() {
		return status;
	}
	public void setStatus(StatusOredemServico status) {
		this.status = status;
	}
	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public OffsetDateTime getDataFinalização() {
		return dataFinalização;
	}
	public void setDataFinalização(OffsetDateTime dataFinalização) {
		this.dataFinalização = dataFinalização;
	}
	
}
