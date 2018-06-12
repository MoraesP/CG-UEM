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
public class Reta {
    
    private Point ponto1;
    private Point ponto2;
    
    public Reta(Point p1, Point p2){
        ponto1 = p1;
        ponto2 = p2;
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
        return "Reta  X: " + this.getPonto1().getX() + " Y: " + this.getPonto1().getY();
    }
}
