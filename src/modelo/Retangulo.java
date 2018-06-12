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
public class Retangulo {
    
    private Point pInicial;
    private Point pOposto;
    private Point ponto1;
    private Point ponto2;
    
    public Retangulo(Point pI, Point pO, Point p1, Point p2){
        pInicial = pI;
        pOposto = pO;
        ponto1 = p1;
        ponto2 = p2;
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
    
    public Point getPonto1() {
        return ponto1;
    }

    public void setPonto1(Point ponto1) {
        this.ponto1 = ponto1;
    }

    public Point getPonto2() {
        return ponto2;
    }

    public void setPonto2(Point ponto2) {
        this.ponto2 = ponto2;
    }
    
    @Override
    public String toString() {
        return "Retangulo  X: " + this.getpInicial().getX() + " Y: " + this.getpInicial().getY();
    }
}
