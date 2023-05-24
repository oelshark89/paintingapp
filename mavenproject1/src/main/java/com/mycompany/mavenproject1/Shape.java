/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.awt.Point;
import org.json.simple.JSONObject;


public interface Shape {

    

    /* set position */
    public void setPosition(java.awt.Point position);

    public java.awt.Point getPosition();

    /* colorize */
    public void setColor(java.awt.Color color);

    public java.awt.Color getColor();

    public void setFillColor(java.awt.Color color);

    public java.awt.Color getFillColor();

    /* redraw the shape on the canvas */
    public void draw(java.awt.Graphics canvas);
    public JSONObject toJSONObject();
public boolean isSelected();
public void setSelected(boolean selected);
public boolean getSelected();
    /**
     *
     * @param ja
     * @return
     */
    
}
