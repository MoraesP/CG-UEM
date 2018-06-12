/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.scene.effect.Light.Point;
/**
 *
 * @author pedro
 */
public class Circulo {
    
    private Point pInicial;
    private Point pOposto;
    
    public Circulo(Point pI, Point pO){
        pInicial = pI;
        pOposto = pO;
    }

    public Point getpInicial() {
        return pInicial;
    }

    public void setpInicial(Point pInicial) {
        this.pInicial = pInicial;
    }

    public Point getpOposto() {
        return pOposto;
    }

    public void setpOposto(Point pOposto) {
        this.pOposto = pOposto;
    }
    
    @Override
    public String toString() {
        return "Ciruclo  X: " + this.getpInicial().getX() + " Y: " + this.getpInicial().getY();
    }
}
