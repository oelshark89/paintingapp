/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.lab8.AbstractShape;
import com.mycompany.mavenproject1.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComboBox;
import org.json.simple.JSONObject;



public class Rectangle extends AbstractShape {

    private int width;
    private int height;
    private Color color;
    private Color fillColor;

    public Rectangle(Point position, int w, int h) {
        super(position);
        this.width = w;
        this.height = h;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

      public boolean isUpperRight(Point p) {
        int x = getPosition().x;
        int y = getPosition().y;
        java.awt.Rectangle r = new java.awt.Rectangle(x + width - 5, y - 5, 10, 10);
        return r.contains(p.getX(), p.getY());
    }
public boolean isUpperLeft(Point p) {
        int x = getPosition().x;
        int y = getPosition().y;
        java.awt.Rectangle r = new java.awt.Rectangle(x - 5, y - 5, 10, 10);
        return r.contains(p.getX(), p.getY());
    }
public boolean isLowerRight(Point p) {
        int x = getPosition().x;
        int y = getPosition().y;
        java.awt.Rectangle r = new java.awt.Rectangle(x+width - 5, y+height - 5, 10, 10);
        return r.contains(p.getX(), p.getY());}
public boolean isLowerLeft(Point p) {
        int x = getPosition().x;
        int y = getPosition().y;
        java.awt.Rectangle r = new java.awt.Rectangle(x - 5, y+height - 5, 10, 10);
        return r.contains(p.getX(), p.getY());}

    @Override
    public void draw(Graphics g) {
       int x=getPosition().x;
       int y=getPosition().y;
       if (width <= 0 && height > 0) { //width -ve
            x += width;
            width = -width;
        } else if (height <= 0 && width > 0) {
            y -= height;
            height = -height;
        } else if (width <= 0 && height <= 0) {
            x += width;
            width = -width;
            y -= height;
            height = -height;
        } 
        if (fillColor != null) {
            g.setColor(fillColor);
            g.fillRect(x, y, width, height);
        }
       
        
        g.setColor(getColor());
         g.drawRect(x, y, width, height);
  if(isSelected()){ g.setColor(Color.BLACK);
            g.fillRect(x-5  , y-5 , 10, 10);
            g.fillRect(x+ width-5 , y-5 , 10, 10);
            g.fillRect(x-5 , y + height-5 , 10, 10);
            g.fillRect(x+ width-5 , y + height-5 , 10, 10);}
    }
    @Override
    public boolean contains(Point p) {
        Point position = getPosition();
        java.awt.Rectangle r = new java.awt.Rectangle();
        r.setLocation(position);
        r.setSize(width, height);
        return r.contains(p);
    }
    
    @Override
    public void moveTo(Point p) {
        int x = p.x - getDraggingPoint().x;
        int y = p.y - getDraggingPoint().y;
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
         if(getFillColor()==null){
             setFillColor(Color.WHITE);
             
         }
          jo.put("borderColorRed", getColor().getRed());
          jo.put("borderColorGreen", getColor().getGreen());
            jo.put("borderColorBlue", getColor().getBlue());
        jo.put("fillColorRed", getFillColor().getRed());
         jo.put("fillColorGreen", getFillColor().getGreen());
          jo.put("fillColorBlue", getFillColor().getBlue());
        jo.put("width", width);
        jo.put("height", height);
        return jo;
    }

    @Override
    public Shape copy(JComboBox<String> chooseshape, Shape shape) {
        Rectangle rectangle=new Rectangle(shape.getPosition(), ((Rectangle)shape).width, ((Rectangle)shape).height);
        chooseshape.addItem("Rectangle "+ noOfRectangles);
        noOfRectangles++;
        rectangle.setColor(shape.getColor());
        rectangle.setFillColor(shape.getFillColor());
        return rectangle;
    }


    @Override
    public void resize(Point p) {
      /*  int x1 = getPosition().x;
        int y1 = getPosition().y;
        int x2 = p.x;
        int y2 = p.y;
        if (isUpperLeft(getDraggingPoint())) {
            if (x1 > x2 && y1 > y2) {
                width = width + Math.abs(x1 - x2);
                height = height + Math.abs(y1 - y2);
                setPosition(p);
            } else if (x1 < x2 && y1 < y2) {
                width = width - Math.abs(x1 - x2);
                height = height - Math.abs(y1 - y2);
                setPosition(p);
            } 
          else if (x1 >x2  ) {
                width = width + Math.abs(x1 - x2);
                setPosition(p);
    }
             else if (x1 < x2 ) {
                width = width - Math.abs(x1 - x2);
                setPosition(p);
    }
             else if ( y1 > y2) {
                height = height + Math.abs(y1 - y2);
                setPosition(p);
    }
             else if (y1 < y2) {
               height = height - Math.abs(y1 - y2);
                setPosition(p);
    }
        }
        if (isUpperRight(getDraggingPoint())) {
          int x = x1+width;
          int y = y1;
            if (x < x2 && y > y2) {
                width = width + Math.abs(x - x2);
                height = height + Math.abs(y - y2);
                setPosition(new Point (x2-width,y2));
            }else if (x > x2 && y < y2) {
                width = width - Math.abs(x - x2);
                height = height - Math.abs(y - y2);
                setPosition(new Point(x2-width,y2));
            } 
          else if (x >x2 ) {
                width = width - Math.abs(x - x2);
                setPosition(new Point(x2-width,y2));
    }
             else if (x < x2 ) {
                width = width + Math.abs(x - x2);
                setPosition(new Point(x2-width,y2));
    }
             else if ( y > y2) {
                height = height + Math.abs(y - y2);
                setPosition(new Point(x2-width,y2));
    }
             else if ( y < y2) {
               height = height - Math.abs(y - y2);
                setPosition(new Point(x2-width,y2));
    }    
   }
     
     if (isLowerRight(getDraggingPoint())) {
          int x = x1+width;
          int y = y1+height;
            if (x < x2 && y < y2) {
                width = width + Math.abs(x - x2);
                height = height + Math.abs(y - y2);
                setPosition(new Point (x2-width,y2-height));
            }else if (x > x2 && y > y2) {
                width = width - Math.abs(x - x2);
                height = height - Math.abs(y - y2);
                setPosition(new Point (x2-width,y2-height));
            } 
          else if (x >x2 ) {
                width = width - Math.abs(x - x2);
                setPosition(new Point (x2-width,y2-height));
    }
             else if (x < x2 ) {
                width = width + Math.abs(x - x2);
               setPosition(new Point (x2-width,y2-height));
    }
             else if ( y > y2) {
                height = height - Math.abs(y - y2);
                setPosition(new Point (x2-width,y2-height));
    }
             else if ( y < y2) {
               height = height + Math.abs(y - y2);
                setPosition(new Point (x2-width,y2-height));
    }    
 }  
       if (isLowerLeft(getDraggingPoint())) {
          int x = x1;
          int y = y1+height;
            if (x >x2 && y < y2) {
                width = width + Math.abs(x - x2);
                height = height + Math.abs(y - y2);
                setPosition(new Point (x2,y2-height));
            }else if (x <x2 && y > y2) {
                width = width - Math.abs(x - x2);
                height = height - Math.abs(y - y2);
                setPosition(new Point(x2,y2-height));
            } 
          else if (x >x2 ) {
                width = width + Math.abs(x - x2);
                setPosition(new Point(x2,y2-height));
    }
             else if (x < x2 ) {
                width = width - Math.abs(x - x2);
                setPosition(new Point(x2,y2-height));
    }
             else if ( y > y2) {
                height = height - Math.abs(y - y2);
                setPosition(new Point(x2,y2-height));
    }
             else if ( y < y2) {
               height = height + Math.abs(y - y2);
                setPosition(new Point(x2,y2-height));
    }    
   } */
      int x1 = getDraggingPoint().x;
        int y1 = getDraggingPoint().y;
        int x2 = p.x;
        int y2 = p.y;
        if (isUpperLeft(getDraggingPoint())) {
            width = width + x1 - x2;
            height = height + y1 - y2;
            getPosition().x = getPosition().x + x2 - x1;
            getPosition().y = getPosition().y + y2 - y1;
        } else if (isUpperRight(getDraggingPoint())) {
            width = width + x2 - x1;
            height = height + y1 - y2;
            getPosition().y = getPosition().y + y2 - y1;
        } else if (isLowerLeft(getDraggingPoint())) {
            width = width + x1 - x2;
            height = height + y2 - y1;
            getPosition().x = x2;
        } else if (isLowerRight(getDraggingPoint())) {
            width = width - x1 + x2;
            height = height + y2 - y1;
        }
        if (width < 0) {
            width = -1 * width;
            getPosition().x -= width;
        }
        if (height < 0) {
            height = -1 * height;
            getPosition().y -= height;
        }

    }

  

  
   

  
}
