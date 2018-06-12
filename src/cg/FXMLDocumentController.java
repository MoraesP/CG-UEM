/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import modelo.Circulo;
import modelo.Reta;
import modelo.Retangulo;
import modelo.Triangulo;

/**
 *
 * @author pedro
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Button bReta;
    @FXML private Button bTriangulo;
    @FXML private Button bRetangulo;
    @FXML private Button bCirculo;
    @FXML private AnchorPane panel;  
    @FXML private Canvas canvas;    
    @FXML private Label labelMouse;   
    @FXML private Pane paneCanvas;  
    @FXML private Label labelMsg;
    @FXML private ComboBox combobox;
    @FXML private GraphicsContext gc;
    @FXML private CheckBox boxreta;
    @FXML private CheckBox boxtriangulo;
    @FXML private CheckBox boxretangulo;
    @FXML private CheckBox boxcirculo;
    @FXML private TextField dX;
    @FXML private TextField dY;
    @FXML private TextField sX;
    @FXML private TextField sY;
    @FXML private TextField grs;
    @FXML private TextField pX;
    @FXML private TextField pY;
    @FXML private CheckBox op2;
    @FXML private CheckBox op3;
    
    ArrayList<Reta> retas = new ArrayList<>();
    ArrayList<Triangulo> triangulos = new ArrayList<>();
    ArrayList<Retangulo> retangulos = new ArrayList<>();
    ArrayList<Circulo> circulos = new ArrayList<>();
    
    private int rotx, roty;
    private Reta reta;
    private Triangulo triangulo;
    private Retangulo retangulo;
    private Circulo circulo;
    
    private Point primeiroponto = null;
    private Point segundoponto = null;
    private Point terceiroPonto = null;
    
    private boolean desenharReta = false;
    private boolean desenharTriangulo = false;
    private boolean desenharRetangulo = false;
    private boolean desenharCirculo = false;
    
    private boolean primeiroClique = true;
    private boolean segundoClique = true;
    
    //-------------------- VERIFICAR CHECK BOX ------------------------------
    @FXML
    public void checarreta(ActionEvent e){
       if(boxreta.isSelected()){
           boxtriangulo.setSelected(false);
           boxcirculo.setSelected(false);
           boxretangulo.setSelected(false);
       }
    }
   
    @FXML
    public void checartriangulo(ActionEvent e){
       if(boxtriangulo.isSelected()){
           boxreta.setSelected(false);
           boxcirculo.setSelected(false);
           boxretangulo.setSelected(false);
       }
    }
    
    @FXML
    public void checarretangulo(ActionEvent e){
       if(boxretangulo.isSelected()){
           boxreta.setSelected(false);
           boxcirculo.setSelected(false);
           boxtriangulo.setSelected(false);
       }
    }
    
    @FXML
    public void checarciruclo(ActionEvent e){
       if(boxcirculo.isSelected()){
           boxreta.setSelected(false);
           boxtriangulo.setSelected(false);
           boxretangulo.setSelected(false);
       }
    }
    
    
    @FXML
    public void checarInicial(ActionEvent e){
        if(op2.isSelected()){
            op3.setSelected(false);
        }
    }
    
    @FXML
    public void checarCentro(ActionEvent e){
        if(op3.isSelected()){
            op2.setSelected(false);
        }
    }
    
    
    
    //------------------- POSIÇÃO DO PONTEIRO NO CANVAS --------------------------
    @FXML
    public void mouseMoved(MouseEvent e) {
        Point point = new Point(e.getX(), e.getY(),0,Color.BLACK);
        this.labelMouse.setText("   X: " + (int) point.getX() + "    Y: " + (int) point.getY());
    }
    
    //------------------- SELECIONAR OPÇAO DE DESENHO -------------------------------
    @FXML 
    public void clicarReta(ActionEvent e){
        this.desenharReta = true;
        this.desenharCirculo = false;
        this.desenharRetangulo = false;
        this.desenharTriangulo = false;
        zerar();
    }
    @FXML
    public void clicarTriangulo(ActionEvent e){
        this.desenharReta = false;
        this.desenharCirculo = false;
        this.desenharRetangulo = false;
        this.desenharTriangulo = true;
        zerar();
    }
    @FXML
    public void clicarRetangulo(ActionEvent e){
        this.desenharReta = false;
        this.desenharCirculo = false;
        this.desenharRetangulo = true;
        this.desenharTriangulo = false;
        zerar(); 
    }
    @FXML
    public void clicarCirculo(ActionEvent e){
        this.desenharReta = false;
        this.desenharCirculo = true;
        this.desenharRetangulo = false;
        this.desenharTriangulo = false;
        zerar(); 
    }
    
    //------------------------- MENSAGENS DOS BOTÕES -------------------------------------
    @FXML
    public void msgReta(MouseEvent evento){
        this.labelMsg.setText("Realizar dois cliques no Canvas para criar a reta");
    }
    
    @FXML
    public void msgTriangulo(MouseEvent evento){
        this.labelMsg.setText("Realizar tres cliques no Canvas para criar o triângulo");
    }
    @FXML
    public void msgRetangulo(MouseEvent evento){
        this.labelMsg.setText("Realizar dois cliques no Canvas (ponto inicial e ponto oposto) para criar o retângulo");
    }
    @FXML
    public void msgCicurlo(MouseEvent evento){
        this.labelMsg.setText("Definir um ponto inicial e o ponto oposto para criar um circulo ataves de um retangulo");
    }
    
    @FXML
    public void msgLimpar(MouseEvent evento){
        this.labelMsg.setText("Limpa todo o Canvas");
    }
    
    @FXML
    public void msgObjetos(MouseEvent evento){
        this.labelMsg.setText("Objeto selecionado + ponto origem");
    }
    
    @FXML
    public void msgTransladar(MouseEvent evento){
        this.labelMsg.setText("Selecionar um objeto na lista + marcar tipo do objeto + dar dX e dY");
    }
    
    @FXML
    public void msgEscalar(MouseEvent evento){
        this.labelMsg.setText("Selecionar um objeto na lista + marcar tipo do objeto + dar sX e sY (Reta apenas sX)");
    }
    
    @FXML
    public void msgRotacionar(MouseEvent evento){
        this.labelMsg.setText("Selecionar um objeto na lista + marcar tipo do objeto + dar o grau de rotação (Todos menos circulo)");
    }
    
    // ------------------------- LIMPAR CANVAS ------------------------------
    public void limpar(){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        retas.clear();
        circulos.clear();
        triangulos.clear();       
        retangulos.clear();
        combobox.getItems().clear();
    }
    
    //--------------------------- TRANFORMAR PONTO ESCALAR ---------------------
    private Point criarEscPonto(int x, int y, int px, int py){
        Point ponto;
        float sx, sy;
        sx = Float.parseFloat(sX.getText());
        if(boxreta.isSelected()){
            sy = Float.parseFloat(sX.getText());
        }else sy = Float.parseFloat(sY.getText());
        float pontox = (sx * px) + (x-(x*sx));
        float pontoy = (sy * py) + (y-(y*sy));
        ponto = new Point((int)pontox,(int) pontoy, 0, Color.BLACK);
        return ponto;   
    }
    
    //----------------------- TRANFORMAR PONTO ROTAÇÃO -----------------------
    private Point criarRotPonto(int x, int y, int px, int py){
        Point ponto;
        double graus;
        
        if(!op2.isSelected() && !op3.isSelected()){
            x = Integer.parseInt(pX.getText());
            y = Integer.parseInt(pY.getText());
        }
        
        graus = Double.parseDouble(grs.getText());
        double angulo = Math.PI * graus / 180;
        double pontox = (Math.cos(angulo) * px) + (-Math.sin(angulo) * py) + (y*Math.sin(angulo) - (x*Math.cos(angulo)) + x);
        double pontoy = (Math.sin(angulo) * px) + (Math.cos(angulo) * py) + (((-x)*Math.sin(angulo)) - (y*Math.cos(angulo)) +y);
        ponto = new Point((int)pontox, (int)pontoy, 0, Color.BLACK);
        return ponto;
    }
   
    
    // ------------------------ ROTAÇÃO --------------------------------
    public void rotacionar(){
        //reta
        if(boxreta.isSelected()){
            if(!retas.isEmpty()){
                Reta retaTrans = (Reta) combobox.getSelectionModel().getSelectedItem();
                
                if(op3.isSelected()){
                    rotx = ((int)retaTrans.getPonto1().getX() + (int)retaTrans.getPonto2().getX())/2;
                    roty = ((int)retaTrans.getPonto1().getY() + (int)retaTrans.getPonto2().getY())/2;
                }
                else if(op2.isSelected()){
                    rotx = (int)retaTrans.getPonto1().getX();
                    roty = (int)retaTrans.getPonto1().getY();
                }
                
                Point ponto1 = criarRotPonto(rotx, roty, 
                                            (int) retaTrans.getPonto1().getX(), 
                                            (int) retaTrans.getPonto1().getY());
                Point ponto2 = criarRotPonto(rotx, roty,
                                            (int) retaTrans.getPonto2().getX(), 
                                            (int) retaTrans.getPonto2().getY()); 
                Reta novaReta = new Reta(ponto1, ponto2);
                
                
                if(retas.contains(retaTrans)){
                    retas.remove(retaTrans);
                    combobox.getItems().remove(retaTrans); 
                    retas.add(novaReta);
                    combobox.getItems().add(novaReta);
                }
                redesenhar();
            }
        }
        //retangulo
        if(boxretangulo.isSelected()){
            if(!retangulos.isEmpty()){
                Retangulo retanguloTrans = (Retangulo) combobox.getSelectionModel().getSelectedItem();
                
                if(op3.isSelected()){
                    rotx = ((int)retanguloTrans.getpInicial().getX() + (int)retanguloTrans.getpOposto().getX())/2;
                    roty = ((int)retanguloTrans.getpInicial().getY() + (int)retanguloTrans.getpOposto().getY())/2;
                }
                else if(op2.isSelected()){
                    rotx = (int)retanguloTrans.getpInicial().getX();
                    roty = (int)retanguloTrans.getpInicial().getY();
                }
                Point ponto1 = criarRotPonto(rotx,roty,
                                            (int) retanguloTrans.getpInicial().getX(),
                                            (int) retanguloTrans.getpInicial().getY());
                
                Point ponto2 = criarRotPonto(rotx,roty,
                                            (int) retanguloTrans.getpOposto().getX(),
                                            (int) retanguloTrans.getpOposto().getY());
                
                Point ponto3 = criarRotPonto(rotx,roty,
                                            (int) retanguloTrans.getPonto1().getX(),
                                            (int) retanguloTrans.getPonto1().getY());
                
                Point ponto4 = criarRotPonto(rotx,roty,
                                            (int) retanguloTrans.getPonto2().getX(),
                                            (int) retanguloTrans.getPonto2().getY());
                
                Retangulo novoRentagulo = new Retangulo(ponto1, ponto2, ponto3, ponto4);
                if(retangulos.contains(retanguloTrans)){
                    retangulos.remove(retanguloTrans);
                    combobox.getItems().remove(retanguloTrans); 
                    retangulos.add(novoRentagulo);
                    combobox.getItems().add(novoRentagulo);
                }
                redesenhar();
            }
        }
        //triangulo
        if(boxtriangulo.isSelected()){
            if(!triangulos.isEmpty()){
               Triangulo triTrans = (Triangulo) combobox.getSelectionModel().getSelectedItem();
               if(op3.isSelected()){
                    rotx = ((int)triTrans.getPonto1().getX() + (int)triTrans.getPonto2().getX() + (int)triTrans.getPonto3().getX())/3;
                    roty = ((int)triTrans.getPonto1().getY() + (int)triTrans.getPonto2().getY() + (int)triTrans.getPonto3().getY())/3;
                }
                else if(op2.isSelected()){
                    rotx = (int)triTrans.getPonto1().getX();
                    roty = (int)triTrans.getPonto1().getY();
                }
               
                Point ponto1 = criarRotPonto(rotx,roty, 
                                            (int) triTrans.getPonto1().getX(), 
                                            (int) triTrans.getPonto1().getY());
                Point ponto2 = criarRotPonto(rotx,roty,  
                                            (int) triTrans.getPonto2().getX(), 
                                            (int) triTrans.getPonto2().getY());
                Point ponto3 = criarRotPonto(rotx,roty,  
                                            (int) triTrans.getPonto3().getX(), 
                                            (int) triTrans.getPonto3().getY());
                
                Triangulo novoTri = new Triangulo(ponto1, ponto2, ponto3);
                if(triangulos.contains(triTrans)){
                    triangulos.remove(triTrans);
                    combobox.getItems().remove(triTrans); 
                    triangulos.add(novoTri);
                    combobox.getItems().add(novoTri);
                }
                redesenhar();
            }
        }
    }
    
    //--------------------------- MUDANÇA DE ESCALA ------------------------------
    public void escalar(){
        if(boxcirculo.isSelected()){
            if(!circulos.isEmpty()){
                Circulo circTrans = (Circulo) combobox.getSelectionModel().getSelectedItem();
                Point ponto1 = criarEscPonto((int) circTrans.getpInicial().getX(), 
                                            (int) circTrans.getpInicial().getY(), 
                                            (int) circTrans.getpInicial().getX(), 
                                            (int) circTrans.getpInicial().getY());
                Point ponto2 = criarEscPonto((int) circTrans.getpInicial().getX(), 
                                            (int) circTrans.getpInicial().getY(), 
                                            (int) circTrans.getpOposto().getX(), 
                                            (int) circTrans.getpOposto().getY()); 
                Circulo novoCirc = new Circulo(ponto1, ponto2);
                if(circulos.contains(circTrans)){
                    circulos.remove(circTrans);
                    combobox.getItems().remove(circTrans); 
                    circulos.add(novoCirc);
                    combobox.getItems().add(novoCirc);
                }
                redesenhar();
            }
        }
        //reta
        if(boxreta.isSelected()){
            if(!retas.isEmpty()){
                Reta retaTrans = (Reta) combobox.getSelectionModel().getSelectedItem();
                Point ponto1 = criarEscPonto((int) retaTrans.getPonto1().getX(), 
                                            (int) retaTrans.getPonto1().getY(), 
                                            (int) retaTrans.getPonto1().getX(), 
                                            (int) retaTrans.getPonto1().getY());
                Point ponto2 = criarEscPonto((int) retaTrans.getPonto1().getX(), 
                                            (int) retaTrans.getPonto1().getY(), 
                                            (int) retaTrans.getPonto2().getX(), 
                                            (int) retaTrans.getPonto2().getY()); 
                Reta novaReta = new Reta(ponto1, ponto2);
                if(retas.contains(retaTrans)){
                    retas.remove(retaTrans);
                    combobox.getItems().remove(retaTrans); 
                    retas.add(novaReta);
                    combobox.getItems().add(novaReta);
                }
                redesenhar();
            }
        }
        //retangulo
        if(boxretangulo.isSelected()){
            if(!retangulos.isEmpty()){
                Retangulo retanguloTrans = (Retangulo) combobox.getSelectionModel().getSelectedItem();
                Point ponto1 = criarEscPonto((int) retanguloTrans.getpInicial().getX(),
                                            (int) retanguloTrans.getpInicial().getY(),
                                            (int) retanguloTrans.getpInicial().getX(),
                                            (int) retanguloTrans.getpInicial().getY());
                
                Point ponto2 = criarEscPonto((int) retanguloTrans.getpInicial().getX(),
                                            (int) retanguloTrans.getpInicial().getY(),
                                            (int) retanguloTrans.getpOposto().getX(),
                                            (int) retanguloTrans.getpOposto().getY());
                
                Point ponto3 = criarEscPonto((int) retanguloTrans.getpInicial().getX(),
                                            (int) retanguloTrans.getpInicial().getY(),
                                            (int) retanguloTrans.getPonto1().getX(),
                                            (int) retanguloTrans.getPonto1().getY());
                
                Point ponto4 = criarEscPonto((int) retanguloTrans.getpInicial().getX(),
                                            (int) retanguloTrans.getpInicial().getY(),
                                            (int) retanguloTrans.getPonto2().getX(),
                                            (int) retanguloTrans.getPonto2().getY());
                
                Retangulo novoRentagulo = new Retangulo(ponto1, ponto2, ponto3, ponto4);
                if(retangulos.contains(retanguloTrans)){
                    retangulos.remove(retanguloTrans);
                    combobox.getItems().remove(retanguloTrans); 
                    retangulos.add(novoRentagulo);
                    combobox.getItems().add(novoRentagulo);
                }
                redesenhar();
            }
        }
        //triangulo
        if(boxtriangulo.isSelected()){
            if(!triangulos.isEmpty()){
               Triangulo triTrans = (Triangulo) combobox.getSelectionModel().getSelectedItem();
                Point ponto1 = criarEscPonto((int) triTrans.getPonto1().getX(), 
                                            (int) triTrans.getPonto1().getY(), 
                                            (int) triTrans.getPonto1().getX(), 
                                            (int) triTrans.getPonto1().getY());
                Point ponto2 = criarEscPonto((int) triTrans.getPonto1().getX(), 
                                            (int) triTrans.getPonto1().getY(), 
                                            (int) triTrans.getPonto2().getX(), 
                                            (int) triTrans.getPonto2().getY());
                Point ponto3 = criarEscPonto((int) triTrans.getPonto1().getX(), 
                                            (int) triTrans.getPonto1().getY(), 
                                            (int) triTrans.getPonto3().getX(), 
                                            (int) triTrans.getPonto3().getY());
                Triangulo novoTri = new Triangulo(ponto1, ponto2, ponto3);
                if(triangulos.contains(triTrans)){
                    triangulos.remove(triTrans);
                    combobox.getItems().remove(triTrans); 
                    triangulos.add(novoTri);
                    combobox.getItems().add(novoTri);
                }
                redesenhar();
            }
        }
    }
    //--------------------------- TRANSLAÇÃO ------------------------------
    public void transladar(){
        Point novop1, novop2, novop3, novop4;
        
        if(boxcirculo.isSelected()){
            if(!circulos.isEmpty()){
                Circulo circTrans = (Circulo) combobox.getSelectionModel().getSelectedItem();
                novop1 = criarPontoTransladado((int) circTrans.getpInicial().getX(), (int) circTrans.getpInicial().getY());
                novop2 = criarPontoTransladado((int) circTrans.getpOposto().getX(), (int) circTrans.getpOposto().getY());
                Circulo novoCirc = new Circulo(novop1, novop2);
                if(circulos.contains(circTrans)){
                    circulos.remove(circTrans);
                    combobox.getItems().remove(circTrans); 
                    circulos.add(novoCirc);
                    combobox.getItems().add(novoCirc);
                }
                redesenhar();
            }
        }
        
        if(boxreta.isSelected()){
            if(!retas.isEmpty()){
                Reta retaTrans = (Reta) combobox.getSelectionModel().getSelectedItem();
                novop1 = criarPontoTransladado((int) retaTrans.getPonto1().getX(), (int) retaTrans.getPonto1().getY());
                novop2 = criarPontoTransladado((int) retaTrans.getPonto2().getX(), (int) retaTrans.getPonto2().getY());
                Reta novaReta = new Reta(novop1, novop2);
                if(retas.contains(retaTrans)){
                    retas.remove(retaTrans);
                    combobox.getItems().remove(retaTrans); 
                    retas.add(novaReta);
                    combobox.getItems().add(novaReta);
                }
                redesenhar();
            }
        }
        if(boxtriangulo.isSelected()){
            if(!triangulos.isEmpty()){
                Triangulo triTrans = (Triangulo) combobox.getSelectionModel().getSelectedItem();
                novop1 = criarPontoTransladado((int) triTrans.getPonto1().getX(), (int) triTrans.getPonto1().getY());
                novop2 = criarPontoTransladado((int) triTrans.getPonto2().getX(), (int) triTrans.getPonto2().getY());
                novop3 = criarPontoTransladado((int) triTrans.getPonto3().getX(), (int) triTrans.getPonto3().getY());
                Triangulo novoTri = new Triangulo(novop1, novop2, novop3);
                if(triangulos.contains(triTrans)){
                    triangulos.remove(triTrans);
                    combobox.getItems().remove(triTrans); 
                    triangulos.add(novoTri);
                    combobox.getItems().add(novoTri);
                }
                redesenhar();
            }
        }
        if(boxretangulo.isSelected()){
            if(!retangulos.isEmpty()){
                Retangulo retanguloTrans = (Retangulo) combobox.getSelectionModel().getSelectedItem();
                novop1 = criarPontoTransladado((int) retanguloTrans.getpInicial().getX(), (int) retanguloTrans.getpInicial().getY());
                novop2 = criarPontoTransladado((int) retanguloTrans.getpOposto().getX(), (int) retanguloTrans.getpOposto().getY());
                novop3 = criarPontoTransladado((int) retanguloTrans.getPonto1().getX(), (int) retanguloTrans.getPonto1().getY());
                novop4 = criarPontoTransladado((int) retanguloTrans.getPonto2().getX(), (int) retanguloTrans.getPonto2().getY());
                Retangulo novoRentagulo = new Retangulo(novop1, novop2, novop3, novop4);
                if(retangulos.contains(retanguloTrans)){
                    retangulos.remove(retanguloTrans);
                    combobox.getItems().remove(retanguloTrans); 
                    retangulos.add(novoRentagulo);
                    combobox.getItems().add(novoRentagulo);
                }
                redesenhar();
            }
        }
    }
    
    
    //-------------------------- CAPTURAR CLIQUE NO CANVAS -------------------------
    @FXML
    public void mouseClicked(MouseEvent evento){      
        int X = (int) evento.getX();
        int Y = (int) evento.getY();
        Point ponto = new Point(X, Y, 0, Color.BLACK);
        
        //reta
        if(desenharReta){
            if(primeiroClique){
                primeiroponto = ponto;
                primeiroClique = false;
            }else{
                segundoponto = ponto;
                gc.setStroke(Color.BLACK);
                gc.strokeLine(primeiroponto.getX(), primeiroponto.getY(), segundoponto.getX(), segundoponto.getY());
                retas.add(reta = new Reta(primeiroponto, segundoponto));
                combobox.getItems().add(reta); 
                zerar();    
            }  
        //triangulo
        }else if(desenharTriangulo){
            if(primeiroClique){
                primeiroponto = ponto;
                primeiroClique = false;               
            }else if(segundoClique){
                segundoponto = ponto;
                segundoClique = false;
            }else{
                terceiroPonto = ponto;
                gc.setStroke(Color.RED);
                gc.strokePolygon(new double[]{primeiroponto.getX(),segundoponto.getX(),terceiroPonto.getX()}, 
                        new double[]{primeiroponto.getY(),segundoponto.getY(),terceiroPonto.getY()}, 3);
                triangulos.add(triangulo = new Triangulo(primeiroponto, segundoponto, terceiroPonto));
                combobox.getItems().add(triangulo);
                zerar();
            }
        //retangulo
        }else if(desenharRetangulo){
            if(primeiroClique){
                primeiroponto = ponto;
                primeiroClique = false;
            }else{
                segundoponto = ponto;
                Point p1 = new Point(segundoponto.getX(), primeiroponto.getY(), 0, Color.BLACK);
                Point p2 = new Point(primeiroponto.getX(), segundoponto.getY(), 0, Color.BLACK);
                
                gc.setStroke(Color.BLUE);
                gc.strokePolygon(new double[]{primeiroponto.getX(),segundoponto.getX(),segundoponto.getX(),primeiroponto.getX()},
                        new double[]{primeiroponto.getY(),primeiroponto.getY(),segundoponto.getY(),segundoponto.getY()}, 4);
               
                retangulos.add(retangulo = new Retangulo(primeiroponto, segundoponto, p1, p2));              
                combobox.getItems().add(retangulo);
                zerar();             
            }
        //circulo
        }else if(desenharCirculo){
            if(primeiroClique){
                primeiroponto = ponto;
                primeiroClique = false;
            }else{
                segundoponto = ponto;
                gc.setStroke(Color.GREEN);
                int largura = (int) (segundoponto.getX() - primeiroponto.getX());
                int altura = (int) (segundoponto.getY() - primeiroponto.getY());
                
                if(altura < 0 && largura >= 0){
                    primeiroponto.setY( primeiroponto.getY() + altura);
                    segundoponto.setY(Math.abs(segundoponto.getY() - altura));   
                    
                }else if(altura >= 0 && largura < 0){
                    primeiroponto.setX( primeiroponto.getX() + largura);
                    segundoponto.setX(Math.abs(segundoponto.getX() - largura));
                
                }else if(altura < 0 && largura < 0){
                    primeiroponto.setY( primeiroponto.getY() + altura);
                    segundoponto.setY(Math.abs(segundoponto.getY() - altura));
                    primeiroponto.setX( primeiroponto.getX() + largura);
                    segundoponto.setX(Math.abs(segundoponto.getX() - largura));
                }

                gc.strokeOval(primeiroponto.getX(), primeiroponto.getY(),
                        segundoponto.getX() - primeiroponto.getX(), segundoponto.getY() - primeiroponto.getY());
                circulos.add(circulo = new Circulo(primeiroponto, segundoponto));
                combobox.getItems().add(circulo);
                zerar();               
            }
        }
    }
    
    //----------------------- ZERAR CLIQUES ------------------------------
    private void zerar(){
        this.primeiroClique = true;
        this.segundoClique = true;
        this.primeiroponto = null;
        this.segundoponto = null;
        this.terceiroPonto = null;
    }
    
    //----------------------- CRIAR UM PONTO   TRANSLADADO---------------------------------
    private Point criarPontoTransladado(int px, int py){
        int x = (Integer.parseInt(dX.getText()) + px);
        int y = (Integer.parseInt(dY.getText()) + py);
        Point ponto = new Point((int) x,(int) y, 0, Color.BLACK);       
        return ponto;
    }
    
    //--------------------------- REDESENHAR OBJETOS EXISTENTES -----------------------
    private void redesenhar(){
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if(!retas.isEmpty()){
            for(Reta r: retas){
                gc.setStroke(Color.BLACK);
                gc.strokeLine(r.getPonto1().getX(), r.getPonto1().getY(), r.getPonto2().getX(), r.getPonto2().getY());
            }
        }
        if(!triangulos.isEmpty()){
             for(Triangulo t: triangulos){
                gc.setStroke(Color.RED);
                gc.strokePolygon(new double[]{t.getPonto1().getX(),t.getPonto2().getX(),t.getPonto3().getX()}, 
                        new double[]{t.getPonto1().getY(),t.getPonto2().getY(),t.getPonto3().getY()}, 3);
            }
        }
        if(!retangulos.isEmpty()){
            for(Retangulo rt: retangulos){
                gc.setStroke(Color.BLUE);
                gc.strokePolygon(new double[]{rt.getpInicial().getX(),rt.getPonto1().getX(),rt.getpOposto().getX(),rt.getPonto2().getX()},
                        new double[]{rt.getpInicial().getY(),rt.getPonto1().getY(),rt.getpOposto().getY(),rt.getPonto2().getY()}, 4);
            }
        }
        if(!circulos.isEmpty()){
            for(Circulo c: circulos){
                gc.setStroke(Color.GREEN);
                
                int largura = (int) (c.getpOposto().getX() - c.getpInicial().getX());
                int altura = (int) (c.getpOposto().getY() - c.getpInicial().getY());
                
                if(altura < 0 && largura >= 0){
                    c.getpInicial().setY( c.getpInicial().getY() + altura);
                    c.getpOposto().setY(Math.abs(c.getpOposto().getY() - altura));   
                    
                }else if(altura >= 0 && largura < 0){
                    c.getpInicial().setX( c.getpInicial().getX() + largura);
                    c.getpOposto().setX(Math.abs(c.getpOposto().getX() - largura));
                
                }else if(altura < 0 && largura < 0){
                    c.getpInicial().setY( c.getpInicial().getY() + altura);
                    c.getpOposto().setY(Math.abs(c.getpOposto().getY() - altura));
                    c.getpInicial().setX( c.getpInicial().getX() + largura);
                    c.getpOposto().setX(Math.abs(c.getpOposto().getX() - largura));
                }
                gc.strokeOval(c.getpInicial().getX(), c.getpInicial().getY(),
                        c.getpOposto().getX() - c.getpInicial().getX(), c.getpOposto().getY() - c.getpInicial().getY());;
            }
        }
        zerarcheckbox();
    }
    
    // ------------------------- DESMARCAR CHECKBOX ------------------------------
    private void zerarcheckbox(){
        boxcirculo.setSelected(false);
        boxreta.setSelected(false);
        boxtriangulo.setSelected(false);
        boxretangulo.setSelected(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        this.canvas.setStyle("-fx-background-color: white;");
    }    
    
}
