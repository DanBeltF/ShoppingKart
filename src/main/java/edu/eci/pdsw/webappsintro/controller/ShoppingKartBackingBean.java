/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.webappsintro.controller;

import edu.eci.pdsw.stubs.servicesfacadestub.CurrencyServices;
import edu.eci.pdsw.stubs.servicesfacadestub.ItemPedido;
import edu.eci.pdsw.stubs.servicesfacadestub.Producto;
import edu.eci.pdsw.stubs.servicesfacadestub.ProductsServices;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author hcadavid
 */
@ManagedBean(name = "KartBean")
@SessionScoped
public class ShoppingKartBackingBean implements Serializable{

    private Producto seleccionProducto;
    private Integer cantidad;
    private ItemPedido nuevoItem;
    private List<ItemPedido> coleccionItems;
    private double costoT;
    
    
    public ShoppingKartBackingBean() {
       coleccionItems = new ArrayList<>();
       //seleccionProducto = null;//new Producto(seleccionProducto.getId(), seleccionProducto.getNombre(), seleccionProducto.getPrecioEnUSD());
       cantidad = 0;
       nuevoItem = null;
       costoT = 0;
    }

    public Producto getSeleccionProducto() {
        return seleccionProducto;
    }

    public void setSeleccionProducto(Producto seleccionProducto) {
        this.seleccionProducto = seleccionProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public ItemPedido getNuevoItem() {
        return nuevoItem;
    }

    public void setNuevoItem(ItemPedido nuevoItem) {
        this.nuevoItem = nuevoItem;
    }

    public List<ItemPedido> getColeccionItems() {
        return coleccionItems;
    }

    public void setColeccionItems(List<ItemPedido> coleccionItems) {
        this.coleccionItems = coleccionItems;
    }

    public double getCalcularCostos() {
        if (coleccionItems.size() > 0) {
            for (ItemPedido i : coleccionItems) {
                costoT += i.getCantidad() * i.getProducto().getPrecioEnUSD();
            }
        } else {
            costoT = 0;
        }
        return costoT;
    }

    
    public void agregarAlCarrito(){
        nuevoItem = new ItemPedido(seleccionProducto, cantidad);
        coleccionItems.add(nuevoItem);
        cantidad = 0;
    }
    
    public List<Producto> getProductos(){
        return ProductsServices.getInstance().getProductos();
    }
    
    public double getTasaCambioDolar(){
        return CurrencyServices.getInstance().getUSDExchangeRateInCOP();
    }
    
    
}
