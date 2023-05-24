/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import com.mycompany.lab8.LineSegment;
import com.mycompany.mavenproject1.Moveable;
import com.mycompany.mavenproject1.Oval;
import com.mycompany.mavenproject1.Rectangle;
import com.mycompany.mavenproject1.Shape;
import com.mycompany.mavenproject1.Triangle;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JComboBox;
import org.json.simple.JSONObject;

public abstract class AbstractShape implements Shape, Moveable {

    private Color color = Color.black;
    private Color fillColor = Color.black;
    private Point position;
    private Point draggingPoint;
    static int noOfLines=0;
       static int noOfOvals=0;
      static int noOfRectangles=0;
       static int noOfTriangles=0;
       boolean selected;
    @Override
    public boolean isSelected() {
        return selected;
    }
    @Override
    public boolean getSelected(){
        return selected;
        
    }
    @Override
    abstract public void resize(Point p);
    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public AbstractShape(Point position) {
        this.position = position;
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

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getDraggingPoint() {
        return draggingPoint;
    }

    @Override
    public void setDraggingPoint(Point draggingPoint) {
        this.draggingPoint = draggingPoint;
    }
 public JSONObject toJsonObject(){
        JSONObject jo=new JSONObject();
        jo.put("point",getPosition());
        jo.put("borderColor", getColor());
        jo.put("fillColor", getFillColor());
        jo.put("draggingPoint", getDraggingPoint());
        return jo;
 }
  public static Shape fromJsonObject(JSONObject ja,JComboBox<String> chooseShape){
       
      String verticalRadius,horizontalRadius;
      
      String endPointX,endPointY,point2X,point2Y,point3X,point3Y,width,height, borderColorRed,borderColorGreen,borderColorBlue,fillColorRed,fillColorGreen,fillColorBlue;
     String pointX=Long.toString((long) ja.get("pointX"));
     String pointY=Long.toString((long)ja.get("pointY"));
        Color borderColor=(Color)ja.get("borderColor");
        Color fillColor=(Color)ja.get("fillColor");
        try{verticalRadius=Long.toString((long)ja.get("verticalRadius"));}
        catch(NullPointerException ex){
            verticalRadius=null;
        }
       
        try{ horizontalRadius=Long.toString((long)ja.get("horizontalRadius"));
        } 
        catch(NullPointerException ex){
            horizontalRadius=null;
        }

        try{ endPointX=Long.toString((long)ja.get("endPointX"));
        } 
        catch(NullPointerException ex){
            endPointX=null;
        }
         
      
        try{ endPointY=Long.toString((long)ja.get("endPointY"));
        } 
        catch(NullPointerException ex){
            endPointY=null;
        }
         
        try{ width=Long.toString((long)ja.get("width"));
        } 
        catch(NullPointerException ex){
            width=null;
        }
        
         try{height=Long.toString((long)ja.get("height"));
         }
         catch(NullPointerException ex){
            height=null;
        }
         
        try{point2X=Long.toString((long)ja.get("point2X"));
        }
        catch(NullPointerException ex){
            point2X=null;
        }

         try{ point2Y=Long.toString((long)ja.get("point2Y"));
         } 
         catch(NullPointerException ex){
            point2Y=null;
        }
          
         try{ point3X=Long.toString((long)ja.get("point3X"));
         } 
         catch(NullPointerException ex){
            point3X=null;
        }
        
         try{ point3Y=Long.toString((long)ja.get("point3Y"));
         } 
         catch(NullPointerException ex){
            point3Y=null;
        }
         try{borderColorRed=Long.toString((Long)ja.get("borderColorRed"));
         }catch(NullPointerException ex){
             borderColorRed=null;
         }try{borderColorGreen=Long.toString((Long)ja.get("borderColorGreen"));
         }catch(NullPointerException ex){
             borderColorGreen=null;
         }try{borderColorBlue=Long.toString((Long)ja.get("borderColorBlue"));
         }catch(NullPointerException ex){
             borderColorBlue=null;
         }try{fillColorRed=Long.toString((Long)ja.get("fillColorRed"));
         }catch(NullPointerException ex){
             fillColorRed=null;
         }
         try{fillColorGreen=Long.toString((Long)ja.get("fillColorGreen"));
         }catch(NullPointerException ex){
             fillColorGreen=null;
         }
         try{fillColorBlue=Long.toString((Long)ja.get("fillColorBlue"));
         }catch(NullPointerException ex){
             fillColorBlue=null;
         }
         if(verticalRadius!=null){
             Oval oval=new Oval(new Point(Integer.parseInt(pointX),Integer.parseInt(pointY)),Integer.parseInt( verticalRadius),Integer.parseInt( horizontalRadius));
             oval.setColor(new Color(Integer.parseInt(borderColorRed), Integer.parseInt(borderColorGreen), Integer.parseInt(borderColorBlue)));
             oval.setFillColor(new Color(Integer.parseInt(fillColorRed), Integer.parseInt(fillColorGreen), Integer.parseInt(fillColorBlue)));  
        chooseShape.addItem("Oval "+noOfOvals);
        noOfOvals++;
             return oval;
         }
         else if(endPointX!=null){
             LineSegment lineSegment=new LineSegment(new Point(Integer.parseInt(pointX),Integer.parseInt(pointY)), new Point(Integer.parseInt(endPointX),Integer.parseInt(endPointY)));
             lineSegment.setColor(new Color(Integer.parseInt(borderColorRed), Integer.parseInt(borderColorGreen), Integer.parseInt(borderColorBlue)));
        //lineSegment.setFillColor(new Color(Integer.parseInt(fillColorRed), Integer.parseInt(fillColorGreen), Integer.parseInt(fillColorBlue)));
        chooseShape.addItem("Line "+noOfLines);
        noOfLines++;
             return lineSegment;
             
         }
         else if(width!=null){
             
               Rectangle rectangle=new Rectangle(new Point(Integer.parseInt(pointX),Integer.parseInt(pointY)), Integer.parseInt(width),Integer.parseInt(height));
             rectangle.setColor(new Color(Integer.parseInt(borderColorRed), Integer.parseInt(borderColorGreen), Integer.parseInt(borderColorBlue)));
            rectangle.setFillColor(new Color(Integer.parseInt(fillColorRed), Integer.parseInt(fillColorGreen), Integer.parseInt(fillColorBlue)));
        chooseShape.addItem("Rectangle "+noOfRectangles);
        noOfRectangles++;
        return rectangle;
         }
         else if(point2X!=null){
             Triangle triangle=new Triangle(new Point(Integer.parseInt(pointX),Integer.parseInt(pointY)),new Point(Integer.parseInt(point2X),Integer.parseInt(point2Y)),new Point(Integer.parseInt(point3X),Integer.parseInt(point3Y)));
               triangle.setColor(new Color(Integer.parseInt(borderColorRed), Integer.parseInt(borderColorGreen), Integer.parseInt(borderColorBlue)));
               triangle.setFillColor(new Color(Integer.parseInt(fillColorRed), Integer.parseInt(fillColorGreen), Integer.parseInt(fillColorBlue)));
             chooseShape.addItem("Triangle "+noOfTriangles);
        noOfTriangles++;
        return triangle;
         }
        
         return null;
  }
  abstract public Shape copy(JComboBox<String> chooseshape,Shape shape);
}
