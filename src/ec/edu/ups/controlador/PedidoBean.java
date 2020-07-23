package ec.edu.ups.controlador;


import com.sun.scenario.effect.impl.prism.PrImage;
import ec.edu.ups.ejb.PedidoFacade;
import ec.edu.ups.entidad.Bodega;
import ec.edu.ups.entidad.Pedido;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class PedidoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Pedido> listPedidos;
    private List<Integer> codigosPedidos;
    private String estadoSelect;
    private String msj;
    /*ADICIONADOS*/
    private List<RowPedido> rowPedidos;
    @EJB
    private PedidoFacade pedidoFacade;
    private  String[] estados={"Receptado", "En proceso", "En camino", "Finalizado"};
    public PedidoBean(){

    }
    @PostConstruct
    public void init(){
       iniciar();
    }

    public void iniciar(){
        listPedidos=new ArrayList<>();
        this.listPedidos=pedidoFacade.findAll();


        /*listPedidos.parallelStream().forEach(e->{
            codigosPedidos.add(e.getCodigo());
        });
*/
        rowPedidos=new ArrayList<>();
        for (Pedido p:listPedidos) {
            rowPedidos.add(new RowPedido(p));
        }
    }

    public List<Integer> getCodigosPedidos(){
        return this.codigosPedidos;
    }


    public String formatDate(GregorianCalendar gregorianCalendar){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(gregorianCalendar.getTime());
    }

    public String getEstadoSelect() {
        return estadoSelect;
    }

    public void setEstadoSelect(String estadoSelect) {
        this.estadoSelect = estadoSelect;
    }

    public Pedido[] getListPedidos() {
        return listPedidos.toArray(new Pedido[0]);
    }


    public void setListPedidos(List<Pedido> listPedidos) {
        this.listPedidos = listPedidos;
    }

    public PedidoFacade getPedidoFacade() {
        return pedidoFacade;
    }

    public void setPedidoFacade(PedidoFacade pedidoFacade) {
        this.pedidoFacade = pedidoFacade;
    }

    public String[] getEstados() {
        return estados;
    }

    public List<RowPedido> getRowPedidos() {
        return rowPedidos;
    }

    public void setRowPedidos(List<RowPedido> rowPedidos) {
        this.rowPedidos = rowPedidos;
    }

    public void setEstados(String[] estados) {
        this.estados = estados;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }

    public void edit(RowPedido p){
        Pedido pedido=p.getPedido();
        pedido.setEstado(p.getEstadoSelect().toUpperCase());
        pedidoFacade.edit(pedido);
        mensaje("Pedido Editado Correctamente.");
        iniciar();
    }

    public void mensaje(String msj){
        this.msj=msj;
    }

}
