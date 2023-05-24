/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.AbstractShape;
import com.mycompany.mavenproject1.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;


public class LineSegment extends AbstractShape {

    private Point endPoint;
    private Color color;
    private Color fillColor;
    private Graphics g;

    public LineSegment(Point position, Point lineEndPoint) {
        super(position);
        this.endPoint = lineEndPoint;
    }

    public Point getLineEndPoint() {
        return endPoint;
    }

    public void setLineEndPoint(Point lineEndPoint) {
        this.endPoint = lineEndPoint;
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
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawLine(getPosition().x, getPosition().y, (int) endPoint.getX(), (int) endPoint.getY());
          if(isSelected())
     { g.setColor(Color.BLACK);
         g.fillRect(  getPosition().x-5, getPosition().y-5 , 10, 10);
        g.fillRect(endPoint.x-5 ,endPoint.y-5  , 10, 10);
     }

    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public void setFillColor(Color fillColor) {
        this.color = fillColor;
    }

    @Override
    public boolean contains(Point p) {
        int x1, y1, x2, y2;
        x1 = getPosition().x;
        y1 = getPosition().y;
        x2 = endPoint.x;
        y2 = endPoint.y;
        Line2D.Float x = new Line2D.Float(x1, y1, x2, y2);
        return (x.ptSegDist(p) < 2);
    }

    @Override
    public void moveTo(Point p) {
        int x = p.x - getDraggingPoint().x;
        int y = p.y - getDraggingPoint().y;
        endPoint = new Point(endPoint.x + x, endPoint.y + y);
        setPosition(new Point(getPosition().x + x, getPosition().y + y));
    }
    @Override
    public JSONObject toJSONObject() {
       JSONObject jo=new JSONObject();
        jo.put("pointX",getPosition().x);
         jo.put("pointY",getPosition().y);
         if(getColor()==null){
             setColor(Color.BLACK);
         }
       /*  if(getFillColor()==null){
             setFillColor(Color.WHITE);
             
         }*/
         jo.put("borderColorRed", getColor().getRed());
          jo.put("borderColorGreen", getColor().getGreen());
            jo.put("borderColorBlue", getColor().getBlue());
       /* jo.put("fillColorRed", getFillColor().getRed());
         jo.put("fillColorGreen", getFillColor().getGreen());
          jo.put("fillColorBlue", getFillColor().getBlue());*/
        jo.put("endPointX", endPoint.x);
        jo.put("endPointY", endPoint.y);
        return jo;
    }

    @Override
    public Shape copy(JComboBox<String> chooseshape, Shape shape) {
       LineSegment line=new LineSegment(shape.getPosition(),((LineSegment)shape).getLineEndPoint());
       
       chooseshape.addItem("Line "+ noOfLines);
       noOfLines++;
        line.setColor(shape.getColor());
      
        return line;
    }
     public Boolean isStartPoint(Point p){
        java.awt.Rectangle r = new java.awt.Rectangle(getPosition().x-5, getPosition().y-5, 10, 10);
        return r.contains(p.getX(), p.getY());
 }
    public Boolean isEndPoint(Point p){
        java.awt.Rectangle r = new java.awt.Rectangle(endPoint.x-5 ,endPoint.y-5 , 10, 10);
        return r.contains(p.getX(), p.getY());
 }

    @Override
    public void resize(Point p){
         if(isStartPoint(getDraggingPoint()))
        {
            setPosition(p);
        }  
       if(isEndPoint(getDraggingPoint()))
        {
            setLineEndPoint(p);
        }
       if(isStartPoint(getDraggingPoint())&&isEndPoint(getDraggingPoint()))
            setPosition(getDraggingPoint());
       
    }


    
    
    
    }

   
    


