/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.lab8.AbstractShape;
import com.mycompany.lab8.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import javax.swing.JComboBox;
import org.json.simple.JSONObject;


public class Triangle extends AbstractShape {

    private Point point2;
    private Point point3;
    private Color color;
    private Color fillColor;

    public Triangle(Point position, Point p2, Point p3) {
        super(position);

        this.point2 = p2;
        this.point3 = p3;
    }

    public Point getP2() {
        return point2;
    }

    public void setP2(Point p2) {
        this.point2 = p2;
    }

    public Point getP3() {
        return point3;
    }

    public void setP3(Point p3) {
        this.point3 = p3;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

     public Boolean isPoint1(Point p){
        java.awt.Rectangle r = new java.awt.Rectangle(getPosition().x-5, getPosition().y-5, 10, 10);
        return r.contains(p.getX(), p.getY());
 }
 public Boolean isPoint2(Point p){
        java.awt.Rectangle r = new java.awt.Rectangle(point2.x-5, point2.y-5, 10, 10);
        return r.contains(p.getX(), p.getY());
 }
 public Boolean isPoint3(Point p){
        java.awt.Rectangle r = new java.awt.Rectangle(point3.x-5, point3.y-5, 10, 10);
        return r.contains(p.getX(), p.getY());
 }
    @Override
    public void draw(Graphics g) {
        int xPoints[] = {getPosition().x, point2.x, point3.x};
        int yPoints[] = {getPosition().y, point2.y, point3.y};
        if (fillColor != null) {
            g.setColor(fillColor);
            g.fillPolygon(xPoints, yPoints, 3);
        }
        g.setColor(getColor());
        g.drawPolygon(xPoints, yPoints, 3);
        if(isSelected())
     { g.setColor(Color.BLACK);
         g.fillRect(  getPosition().x-5, getPosition().y-5 , 10, 10);
        g.fillRect(point2.x-5 ,point2.y-5  , 10, 10);
         g.fillRect(point3.x-5 ,point3.y-5  , 10, 10);
     }
   }

    @Override
    public boolean contains(Point p) {
      Polygon polygon = new Polygon(new int[]{getPosition().x, point2.x, point3.x}, new int[]{getPosition().y, point2.y, point3.y}, 3);
        return polygon.contains(p);
    }

    @Override
    public void moveTo(Point p) {
        int xdiff = p.x - getDraggingPoint().x;
        int ydiff = p.y - getDraggingPoint().y;
        point2 = new Point(point2.x + xdiff, point2.y + ydiff);
        point3 = new Point(point3.x + xdiff, point3.y + ydiff);
        setPosition(new Point(getPosition().x + xdiff, getPosition().y + ydiff));
    }
    @Override
    public JSONObject toJSONObject() {
       JSONObject jo=new JSONObject();
        jo.put("pointX",getPosition().x);
         jo.put("pointY",getPosition().y);
         if(getColor()==null){
             setColor(Color.BLACK);
         }
         if(getFillColor()==null){
             setFillColor(Color.WHITE);
             
         }
          jo.put("borderColorRed", getColor().getRed());
          jo.put("borderColorGreen", getColor().getGreen());
            jo.put("borderColorBlue", getColor().getBlue());
        jo.put("fillColorRed", getFillColor().getRed());
         jo.put("fillColorGreen", getFillColor().getGreen());
          jo.put("fillColorBlue", getFillColor().getBlue());
        jo.put("point2X", point2.x);
        jo.put("point2Y", point2.y);
        jo.put("point3X", point3.x);
        jo.put("point3Y", point3.y);
        return jo;
    }

    @Override
    public Shape copy(JComboBox<String> chooseshape, Shape shape) {
        Triangle triangle =new Triangle(shape.getPosition(), ((Triangle)shape).point2, ((Triangle)shape).point3);
        
        chooseshape.addItem("Triangle "+ noOfTriangles);
        noOfTriangles++;
       triangle.setColor(shape.getColor());
        triangle.setFillColor(shape.getFillColor());
        return triangle;
    }

    @Override
    public void resize(Point p) {
        if(isPoint1(getDraggingPoint()))
        {
            setPosition(p);
        }  
        if(isPoint2(getDraggingPoint()))
        {
            setP2(p);
        }
        if(isPoint3(getDraggingPoint()))
        {
            setP3(p);
        }  
        if(isPoint1(getDraggingPoint())&& isPoint3(getDraggingPoint()))
            setP3(getDraggingPoint());
        if(isPoint1(getDraggingPoint())&& isPoint2(getDraggingPoint()))
            setP2(getDraggingPoint());
        if(isPoint2(getDraggingPoint())&& isPoint3(getDraggingPoint()))
            setP2(getDraggingPoint());
        
    }

    
    }


