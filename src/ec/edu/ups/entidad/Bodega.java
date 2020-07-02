package ec.edu.ups.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Bodega implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;
    private String nombre;

    @ManyToOne
    private Ciudad ciudad;

    @ManyToMany(mappedBy = "bodegasList")
    @JoinColumn
    private List<Producto> productosList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bodega")
    private List<Stock> lista_stock;

    public Bodega(){
        productosList= new ArrayList<Producto>();
    }

    public Bodega( String nombre, Ciudad ciudad) {

        this.nombre = nombre;
        this.ciudad = ciudad;
        productosList = new ArrayList<Producto>();
        lista_stock= new ArrayList<Stock>();
    }

    public List<Stock> getLista_stock() {
        return lista_stock;
    }

    public void setLista_stock(List<Stock> lista_stock) {
        this.lista_stock = lista_stock;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public List<Producto> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Producto> productosList) {
        this.productosList = productosList;
    }

    public boolean agregarProducto(Producto producto){
        return this.productosList.add(producto);
    }

    public boolean addStock(Stock stock){
        return this.lista_stock.add(stock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bodega)) return false;
        Bodega bodega = (Bodega) o;
        return codigo == bodega.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}