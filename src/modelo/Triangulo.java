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
public class Triangulo {
    
    private Point ponto1;
    private Point ponto2;
    private Point ponto3;
    
    public Triangulo(Point p1, Point p2, Point p3){
        ponto1 = p1;
        ponto2 = p2;
        ponto3 = p3;
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

    public Point getPonto3() {
        return ponto3;
    }

    public void setPonto3(Point ponto3) {
        this.ponto3 = ponto3;
    }
    
    @Override
    public String toString() {
        return "Triangulo  X: " + this.getPonto1().getX() + " Y: " + this.getPonto1().getY();
    }
}
