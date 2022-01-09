package es.iestetuan.dam2.vo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_CONTRATO")
public class Contrato {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cod_contrato")
	private long codigoContrato;
	
    @ManyToOne
    @JoinColumn(name="cod_socio", referencedColumnName="cod_socio",
    		foreignKey = @ForeignKey(name = "FK_SOCIO"))
	private Socio socio;
    
    @ManyToOne (cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="cod_servicio", referencedColumnName="cod_servicio",
    		foreignKey = @ForeignKey(name = "FK_SERVICIO"))
	private Servicio servicio;
    
	@Column(name="ind_pago_mensual")
	private boolean indicadorPagoMensual;

	
	/* getters y setters */
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public long getCodigoContrato() {
		return codigoContrato;
	}
	public void setCodigoContrato(long codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
	public boolean isIndicadorPagoMensual() {
		return indicadorPagoMensual;
	}
	public void setIndicadorPagoMensual(boolean indicadorPagoMensual) {
		this.indicadorPagoMensual = indicadorPagoMensual;
	}
	public Servicio getServicio() {
		return servicio;
	}
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
}
